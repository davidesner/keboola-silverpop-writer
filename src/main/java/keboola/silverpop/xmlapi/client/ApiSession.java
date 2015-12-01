/*
 */
package keboola.silverpop.xmlapi.client;

import keboola.silverpop.xmlapi.pojo.results.LoginResult;

/**
 * JSESSION retrieved by login auth method
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class ApiSession {

    private boolean open;
    private String SESSION_ID;
    private String SESSION_ENCODING;

    public ApiSession(LoginResult result) {
        this.open = result.isSuccess();
        this.SESSION_ID = result.getSessionId();
        this.SESSION_ENCODING = result.getSessionEncoding();
    }

    public ApiSession() {
        //this.open = result.isSuccess();
        this.SESSION_ID = "";
        this.SESSION_ENCODING = "";
        this.open = false;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getSESSION_ID() {
        return SESSION_ID;
    }

    public void setSESSION_ID(String SESSION_ID) {
        this.SESSION_ID = SESSION_ID;
    }

    public String getSESSION_ENCODING() {
        return SESSION_ENCODING;
    }

    public void setSESSION_ENCODING(String SESSION_ENCODING) {
        this.SESSION_ENCODING = SESSION_ENCODING;
    }

}
