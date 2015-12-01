/*
 */
package keboola.silverpop.xmlapi.pojo.results;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Pojo of XML API fault result.
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class SPError {

    @XStreamAlias("Request")
    private String request;
    @XStreamAlias("FaultCode")
    private String code;
    @XStreamAlias("FaultString")
    private String message;

    public SPError(String request, String code, String message) {
        this.request = request;
        this.code = code;
        this.message = message;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getResponseText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
