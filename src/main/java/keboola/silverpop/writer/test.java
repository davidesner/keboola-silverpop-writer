/*
 */
package keboola.silverpop.writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.NullConverter;
import keboola.silverpop.xmlapi.client.XmlApiClient;
import keboola.silverpop.xmlapi.pojo.XmlRequestEnvelope;
import keboola.silverpop.xmlapi.pojo.XmlResponseBody;
import keboola.silverpop.xmlapi.pojo.XmlResponseEnvelope;
import keboola.silverpop.xmlapi.pojo.commands.GetJobStatusCommandBody;
import keboola.silverpop.xmlapi.pojo.results.GetJobStatusResult;
import keboola.silverpop.xmlapi.pojo.results.ImportListResult;
import keboola.silverpop.xmlapi.pojo.results.LoginResult;
import keboola.silverpop.xmlapi.pojo.results.SPError;
import keboola.silverpop.xmlapi.pojo.results.SPResult;
import keboola.silverpop.xmlapi.xstream.XStreamFactory;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class test {

    public static void main(String[] args) {

        XStream xstream = XStreamFactory.getXstream();

        String res = "<Envelope>\n"
                + "<Body>\n"
                + "    <RESULT>\n"
                + "<SUCCESS>TRUE</SUCCESS>\n"
                + "<JOB_ID>60904848</JOB_ID>\n"
                + "</RESULT>\n"
                + "  </Body>\n"
                + "</Envelope>\n";

        xstream.processAnnotations(XmlResponseEnvelope.class);
        xstream.processAnnotations(XmlResponseBody.class);
        xstream.processAnnotations(ImportListResult.class);
        xstream.processAnnotations(SPError.class);
        xstream.registerConverter(new NullConverter());
        xstream.addDefaultImplementation(ImportListResult.class, SPResult.class);
        //remove class attribute

        XmlResponseEnvelope xml = (XmlResponseEnvelope) xstream.fromXML(res);
        res.getClass();
    }

}
