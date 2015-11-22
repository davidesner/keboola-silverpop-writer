/*
 */
package cz.kbc.silverpop.xmlapi.client;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class ApiAccessToken {

    private final String ACCESS_TOKEN;
    private long tokenExpireTime;

    public ApiAccessToken(String ACCESS_TOKEN, long tokenExpireTime) {
        this.ACCESS_TOKEN = ACCESS_TOKEN;
        this.tokenExpireTime = tokenExpireTime;
    }

    public boolean isExpired() {
        return false;
    }

    public long getExpireTime() {
        return tokenExpireTime;
    }
}
