package keboola.silverpop.writer;

import com.thoughtworks.xstream.XStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import keboola.silverpop.xmlapi.client.ApiException;
import keboola.silverpop.xmlapi.client.XmlApiClient;
import keboola.silverpop.xmlapi.pojo.ImportListListInfo;
import keboola.silverpop.xmlapi.pojo.ImportListMapFileWrapper;
import keboola.silverpop.xmlapi.pojo.ImportListMapping;
import keboola.silverpop.xmlapi.pojo.XmlResponseBody;
import keboola.silverpop.xmlapi.xstream.XStreamFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import keboola.silverpop.ftp.FtpException;
import keboola.silverpop.ftp.SFTPClient;
import keboola.silverpop.writer.config.KBCConfig;
import keboola.silverpop.writer.config.YamlConfigParser;
import keboola.silverpop.writer.utils.CsvProcesser;
import keboola.silverpop.xmlapi.pojo.commands.GetJobStatusCommandBody;
import keboola.silverpop.xmlapi.pojo.commands.ImportListCommandBody;
import keboola.silverpop.xmlapi.pojo.results.GetJobStatusResult;
import keboola.silverpop.xmlapi.pojo.results.ImportListResult;
import keboola.silverpop.xmlapi.resultprocessor.ApiResultProcessor;
import keboola.silverpop.xmlapi.resultprocessor.DataJobResult;

/**
 * Hello world!
 *
 */
public class Writer {

    private static final long JOB_WAIT_INTERVAL = 180000;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.print("No parameters provided.");
            System.exit(1);
        }
        String dataPath = args[0];
        String inTablesPath = dataPath + File.separator + "in" + File.separator + "tables"; //parse config
        KBCConfig config = null;
        File confFile = new File(args[0] + File.separator + "config.yml");
        if (!confFile.exists()) {
            System.out.println("config.yml does not exist!");
            System.err.println("config.yml does not exist!");
            System.exit(1);
        }
        //Parse config file
        try {
            if (confFile.exists() && !confFile.isDirectory()) {
                config = YamlConfigParser.parseFile(confFile);
            }
        } catch (Exception ex) {
            System.out.println("Failed to parse config file");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        if (!config.validate()) {
            System.out.println(config.getValidationError());
            System.err.println(config.getValidationError());
            System.exit(1);
        }

        //get source file
        String sourceFileName = config.getStorage().getInputTables().getTables().get(0).getDestination();
        File sourceFile = new File(inTablesPath + File.separator + sourceFileName);
        if (!sourceFile.exists()) {
            System.out.println("Source file " + sourceFileName + " does not exist!");
            System.err.println("Source file " + sourceFileName + " does not exist!");
            System.exit(2);
        }

        XStream xstream = XStreamFactory.getXstream2();
        System.out.println("Building configuration..");
        /* Build MapFile / configuration*/
        boolean isOptOut = false;
        if (config.getParams().getAction() != null) {
            if (config.getParams().getAction().equalsIgnoreCase("opt_out")) {
                isOptOut = true;
            }
        }
        Map<Integer, String> mapColumns = new HashMap<Integer, String>();

        String[] headers = null;
        try {
            headers = CsvProcesser.getHeader(sourceFile);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        /*/if mapping is specified, retrieve matching indexes and check validity*/

        if (config.getParams().getMapping().isEmpty()) {
            for (int i = 0; i < headers.length; i++) {
                //error if wrong action
                if (!isOptOut && isOptOutCommand(headers[i])) {
                    System.out.println(headers[i] + " column found. Cannot define opt out information, when action is not OPT_OUT!");
                    System.err.println(headers[i] + " column found. Cannot define opt out information, when action is not OPT_OUT!");
                    System.exit(1);
                }
                mapColumns.put(i + 1, headers[i]);
            }
        } else {
            for (Map.Entry<String, String> entry : config.getParams().getMapping().entrySet()) {
                int s = arrayContains(headers, entry.getKey());
                if (s >= 0) {
                    if (!isOptOut && isOptOutCommand(entry.getValue())) {
                        System.out.println(entry.getValue() + " column found. Cannot define opt out information, when action is not OPT_OUT!");
                        System.err.println(entry.getValue() + " column found. Cannot define opt out information, when action is not OPT_OUT!");
                        System.exit(1);
                    }
                    mapColumns.put(s + 1, entry.getValue());
                } else {//error if mapping column does not exist
                    System.out.println(entry.getValue() + " column specified in mapping parameter not found in the source file.");
                    System.err.println(entry.getValue() + " column specified in mapping parameter not found in the source file.");
                    System.exit(1);
                }

            }

        }
        ImportListListInfo listInfo = new ImportListListInfo(config.getParams().getListId(), config.getParams().getListDateFormat(),
                config.getParams().getAction(), config.getParams().isEncodeMd5());
        //set mapping
        ImportListMapping mapping = new ImportListMapping(mapColumns);
        //build mapfile
        ImportListMapFileWrapper mapFile = new ImportListMapFileWrapper(listInfo, mapping, config.getParams().getContactLists());

        xstream.processAnnotations(ImportListListInfo.class);
        xstream.processAnnotations(ImportListMapping.class);
        xstream.processAnnotations(ImportListMapFileWrapper.class);
        String mapFileXml = xstream.toXML(mapFile);
        
        /*Try to login with API*/
         XmlApiClient client = new XmlApiClient(config.getParams().getUser(),
                config.getParams().getPass(), config.getParams().getApiUrl());
        XmlResponseBody response = null;
        String jobId;

        try {
            client.login();
        } catch (ApiException ex) {
            System.out.println("Failed to login with API. " + ex.getMessage());
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        //create string inputstream
        InputStream in = new ByteArrayInputStream(mapFileXml.getBytes(StandardCharsets.UTF_8));
        System.out.println("Uploading files to Engage FTP...");
        /*Upload files to ftp*/
        SFTPClient ftpclient = new SFTPClient(config.getParams().getUser(), config.getParams().getPass(), config.getParams().getSftpUrl());
        try {
            ftpclient.connect();
            ftpclient.uploadFile("upload", sourceFile.getName(), sourceFile.getAbsolutePath());
            ftpclient.uploadFile("upload", "map_file.xml", in);

        } catch (FtpException ex) {
            System.out.println("Failed to upload the file to ftp repository. " + ex.getMessage());
            System.err.println("Failed to upload the file to ftp repository. " + ex.getMessage());
            System.exit(1);
        }
        try {
            ftpclient.disconnect();
        } catch (IOException ex) {
            System.out.println("Error disconnecting from ftp.");
        }
        System.out.println("Performing XML API request and proccessing results...");
        /*Send api request*/
       
        /*Build and send ImportList command*/
        ImportListCommandBody impListBody = new ImportListCommandBody("map_file.xml", sourceFile.getName());
        /*API request*/
        try {
            response = client.apiRequest(impListBody);
            if (!response.getResult().isSuccess()) {
                System.out.println("Command execution failed. " + response.getFault().getMessage());
                System.err.println(response.getFault().getMessage());
                System.exit(1);
            }
        } catch (ApiException ex) {
            try {
                client.logout();
            } catch (ApiException ex1) {
                Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("API request failed failed. " + ex.getMessage());
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        jobId = ((ImportListResult) response.getResult()).getJobId();
        /*Wait until job is done*/
        int r = 0;
        boolean finished = false;

        try {
            while (r < JOB_WAIT_INTERVAL / 10000 && !finished) {
                response = client.apiRequest(new GetJobStatusCommandBody(jobId));
                if (!response.getResult().isSuccess()) {
                    System.out.println("Command execution failed. " + response.getFault().getMessage());
                    System.err.println(response.getFault().getMessage());
                    System.exit(1);
                }

                if (((GetJobStatusResult) response.getResult()).getJobStatus().equalsIgnoreCase("COMPLETE")) {
                    finished = true;
                    continue;
                }

                if (((GetJobStatusResult) response.getResult()).getJobStatus().equalsIgnoreCase("ERROR")) {
                    DataJobResult dRes = ApiResultProcessor.proccessDataJob((GetJobStatusResult) response.getResult(), ftpclient);
                    System.out.println("SilverPop internal job execution error. " + dRes.getResultMessage());
                    System.err.println("SilverPop internal job execution error. " + dRes.getResultMessage());
                    System.exit(2);
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (ApiException ex) {
            System.out.println("API request failed failed. " + ex.getMessage());
            System.err.println(ex.getMessage());
            System.exit(1);
        } finally {
            try {
                client.logout();
            } catch (ApiException ex1) {
                Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        if (!finished) {
            System.out.println("SilverPop internal job execution exceeded time limit of " + JOB_WAIT_INTERVAL / 10000 + " seconds.");
            System.err.println("SilverPop internal job execution exceeded time limit of " + JOB_WAIT_INTERVAL / 10000 + " seconds.");
            System.exit(2);
        }


        /*JOB FINISHED, PROCESS RESULTS*/
        DataJobResult dRes = ApiResultProcessor.proccessDataJob((GetJobStatusResult) response.getResult(), ftpclient);

        if (dRes.isFinishedSuccess()) {
            System.out.println("Contact imported successfully. ");
            System.out.println(dRes.getResultMessage());
            System.exit(0);
        } else {
            System.out.println("Import finished with errors. ");
            System.out.println(dRes.getResultMessage());
            System.exit(1);
        }

        System.out.println();
    }

    /**
     * Searches for a string in an array. Not case sensitive.
     *
     * @param list - array to search in
     * @param search - searched String key
     * @return first possition of string in the array, or -1 if not found.
     */
    private static int arrayContains(String[] list, String search) {
        for (int i = 0; i < list.length; i++) {
            String c = list[i].toLowerCase();
            if (c.equals(search.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isOptOutCommand(String c) {
        return c.equalsIgnoreCase("opted out")
                || c.equalsIgnoreCase("opted_out")
                || c.equalsIgnoreCase("opted out date")
                || c.equalsIgnoreCase("opted_out_date")
                || c.equalsIgnoreCase("opted_out_details")
                || c.equalsIgnoreCase("opted out details");
    }
}
