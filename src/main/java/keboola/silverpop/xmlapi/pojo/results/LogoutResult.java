/*
 */
package keboola.silverpop.xmlapi.pojo.results;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
@XStreamAlias("RESULT")
public class LogoutResult implements SPResult {

    @XStreamAlias("SUCCESS")
    private boolean success;

    public LogoutResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return this.success;
    }

}
