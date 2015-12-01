/*
 */
package keboola.silverpop.xmlapi.resultprocessor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import keboola.silverpop.ftp.FtpException;
import keboola.silverpop.ftp.SFTPClient;
import keboola.silverpop.xmlapi.pojo.results.GetJobStatusResult;

/**
 * Utility class implementing methods for proccessing of API Results.
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class ApiResultProcessor {

    /**
     * Proccess JobStatus command result and retrieve error message from FTP
     * (download folder)
     *
     * @param res - GetJobStatusResult
     * @param ftpclient - inicialized SFTP client
     * @return
     */
    public static DataJobResult proccessDataJob(GetJobStatusResult res, SFTPClient ftpclient) {
        if (!res.isSuccess()) {
            return new DataJobResult(false);
        }
        DataJobResult dataRes = new DataJobResult(res.getParameters());

        /*Proccess error message*/
        if (!dataRes.isFinishedSuccess()) {
            String error_message = "";
            try {
                ftpclient.connect();
                error_message = ftpclient.downloadFileToString("download", dataRes.getError_file_name());
                ftpclient.disconnect();
            } catch (FtpException ex) {
                System.err.print("Error retrieving resultfile. " + ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(ApiResultProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            dataRes.setErrorMessage(error_message);
        }
        return dataRes;
    }
}
