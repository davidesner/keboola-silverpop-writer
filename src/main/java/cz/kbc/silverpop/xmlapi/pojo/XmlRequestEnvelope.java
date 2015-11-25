/*
 */
package cz.kbc.silverpop.xmlapi.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import cz.kbc.silverpop.xmlapi.pojo.commands.SPCommand;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
@XStreamAlias("Envelope")
public class XmlRequestEnvelope {

    @XStreamAlias("Body")
    private XmlRequestBody body;

    public XmlRequestEnvelope(SPCommand apiCommand) {
        this.body = new XmlRequestBody(apiCommand);
    }

    public XmlRequestBody getBody() {
        return body;
    }
}
