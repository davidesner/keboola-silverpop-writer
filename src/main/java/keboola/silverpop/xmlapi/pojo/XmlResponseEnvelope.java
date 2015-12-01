/*
 */
package keboola.silverpop.xmlapi.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import keboola.silverpop.xmlapi.pojo.results.SPResult;

/**
 * Pojo class of Engage XML API command wrapper.
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
@XStreamAlias("Envelope")
public class XmlResponseEnvelope {

    @XStreamAlias("Body")
    private XmlResponseBody body;

    public XmlResponseBody getBody() {
        return body;
    }

    public void setBody(XmlResponseBody body) {
        this.body = body;
    }

    public SPResult getResult() {
        return this.body.getResult();
    }
}
