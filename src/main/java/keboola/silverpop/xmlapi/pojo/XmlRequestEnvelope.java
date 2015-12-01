/*
 */
package keboola.silverpop.xmlapi.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import keboola.silverpop.xmlapi.pojo.commands.SPCommandBody;

/**
 * Pojo class of Engage XML API request command wrapper.
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
@XStreamAlias("Envelope")
public class XmlRequestEnvelope {

    @XStreamAlias("Body")
    private SPCommandBody body;

    public XmlRequestEnvelope(SPCommandBody apiCommand) {
        this.body = apiCommand;
    }

    public SPCommandBody getBody() {
        return body;
    }
}
