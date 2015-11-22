/*
 */
package cz.kbc.silverpop.xmlapi.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import cz.kbc.silverpop.xmlapi.results.SPError;
import cz.kbc.silverpop.xmlapi.results.SPResult;

/**
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
