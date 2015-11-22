package cz.kbc.silverpop.extractor;

import cz.kbc.silverpop.xmlapi.client.AuthorizationException;
import cz.kbc.silverpop.xmlapi.client.XmlApiClient;
import cz.kbc.silverpop.xmlapi.commands.ExportList;
import cz.kbc.silverpop.xmlapi.commands.ExportList.ExportType;
import cz.kbc.silverpop.xmlapi.commands.SPCommand;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class Run {

    public static void main(String[] args) {

        XmlApiClient client = new XmlApiClient("c_id", "c_secret", "r_token", "http://127.0.0.1:8084");

        SPCommand comm = new ExportList(12345, "dd@gmail.com", ExportList.ExportType.ALL, ExportList.ExportFormat.CSV,
                "UTF-8", new Object(), "1-1-2015", "2-2-2015", new Object(), null, null);

        String r = client.performRequest(comm);
        try {
            client.authenticate();
        } catch (AuthorizationException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(r);
    }
}
