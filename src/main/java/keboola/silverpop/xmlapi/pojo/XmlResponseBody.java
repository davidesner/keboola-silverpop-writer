/*
 */
package keboola.silverpop.xmlapi.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import keboola.silverpop.xmlapi.pojo.results.SPError;
import keboola.silverpop.xmlapi.pojo.results.SPResult;

/**
 * Pojo class of Engage XML API response wrapper.
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class XmlResponseBody {

    @XStreamAlias("RESULT")
    private SPResult apiResult;
    @XStreamAlias("Fault")
    private SPError apiFault;

    public SPResult getResult() {
        return apiResult;
    }

    public SPError getFault() {
        return apiFault;
    }
}
