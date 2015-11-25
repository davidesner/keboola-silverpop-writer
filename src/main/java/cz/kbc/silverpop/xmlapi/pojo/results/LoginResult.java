/*
 */
package cz.kbc.silverpop.xmlapi.pojo.results;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
@XStreamAlias("RESULT")
public class LoginResult implements SPResult {

    @XStreamAlias("SUCCESS")
    private boolean success;
    @XStreamAlias("SESSIONID")
    private String sessionId;
    @XStreamAlias("ORGANIZATION_ID")
    private String organizationId;
    @XStreamAlias("SESSION_ENCODING")
    private String sessionEncoding;

    public LoginResult(String sessionId, String organizationId, String sessionEncoding, boolean success) {
        this.sessionId = sessionId;
        this.organizationId = organizationId;
        this.sessionEncoding = sessionEncoding;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getSessionEncoding() {
        return sessionEncoding;
    }

    public void setSessionEncoding(String sessionEncoding) {
        this.sessionEncoding = sessionEncoding;
    }

}
