package cz.kbc.silverpop.extractor;

import cz.kbc.silverpop.xmlapi.client.ApiException;
import cz.kbc.silverpop.xmlapi.client.AuthorizationException;
import cz.kbc.silverpop.xmlapi.client.XmlApiClient;
import cz.kbc.silverpop.xmlapi.pojo.commands.ExportList;
import cz.kbc.silverpop.xmlapi.pojo.commands.ExportList.ExportType;
import cz.kbc.silverpop.xmlapi.pojo.commands.SPCommand;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class Run {

    public static void main(String[] args) {

        XmlApiClient client = new XmlApiClient("keboola-test@etnetera.cz", "7Bn-5Qg-YU6-MHo", "https://api4.silverpop.com/XMLAPI");

        SPCommand comm = new ExportList(12345, "dd@gmail.com", ExportList.ExportType.ALL, ExportList.ExportFormat.CSV,
                "UTF-8", new Object(), "1-1-2015", "2-2-2015", new Object(), null, null);

        try {
            client.login();
        } catch (ApiException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println();
    }
}
