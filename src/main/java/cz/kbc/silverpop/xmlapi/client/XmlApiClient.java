/*
 */
package cz.kbc.silverpop.xmlapi.client;

import com.thoughtworks.xstream.XStream;
import cz.kbc.silverpop.xmlapi.commands.SPCommand;
import cz.kbc.silverpop.xmlapi.request.XmlRequestBody;
import cz.kbc.silverpop.xmlapi.request.XmlRequestEnvelope;
import cz.kbc.silverpop.xmlapi.xstream.XStreamFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.routines.UrlValidator;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class XmlApiClient {

    private final String apiUrl;
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private final String REFRESH_TOKEN;
    private ApiAccessToken accessToken;
    // private final int podNumber;

    public XmlApiClient(String CLIENT_ID, String CLIENT_SECRET, String REFRESH_TOKEN, String apiUrl) {

        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
        this.REFRESH_TOKEN = REFRESH_TOKEN;
        //this.podNumber = podNumber;
        //this.apiUrl = "https://api[" + podNumber + "ibmmarketingcloud.com/";
        this.apiUrl = apiUrl;
    }

    public String performRequest(SPCommand command) {

        XStream xstream = XStreamFactory.getXstream2();
        //XStream xstream = new XStream();


        //place command in Envelope wrapper
        XmlRequestEnvelope request = new XmlRequestEnvelope(command);
        /*Proccess annotations in objects*/
        xstream.processAnnotations(XmlRequestEnvelope.class);

        //set element name for given command
        xstream.aliasField(command.getAlias(), XmlRequestBody.class, "apiCommand");
        xstream.processAnnotations(command.getClass());
        //remove class attribute
        xstream.aliasSystemAttribute(null, "class");

        return xstream.toXML(request);

    }

    /**
     * Sets up a session (retrieves access key)
     */
    public void authenticate() throws AuthorizationException {

        try {
            String url = apiUrl + "/oauth/token";
            //Validate URL
            UrlValidator urlValidator = new UrlValidator();
            if (!urlValidator.isValid(url)) {
                throw new AuthorizationException("Api url:" + url + " is not valid.");
            }

            OAuthClientRequest.TokenRequestBuilder reqBuider = new OAuthClientRequest.TokenRequestBuilder(url.toString());
            OAuthClientRequest request = reqBuider
                    .setGrantType(GrantType.REFRESH_TOKEN)
                    .setClientId(CLIENT_ID)
                    .setClientSecret(CLIENT_SECRET)
                    .setRefreshToken(REFRESH_TOKEN)
                    .buildBodyMessage();

            //create OAuth client that uses custom http client under the hood
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

            GitHubTokenResponse oAuthResponse = oAuthClient.accessToken(request, GitHubTokenResponse.class);

            //set the access token
            this.accessToken = new ApiAccessToken(oAuthResponse.getAccessToken(), oAuthResponse.getExpiresIn());

            /* //oauth client using Apache HC
             CloseableHttpClient client = HttpClients.createDefault();
             HttpPost httpPost = new HttpPost(apiUrl + "/oauth/token");

             List<NameValuePair> params = new ArrayList<NameValuePair>();
             params.add(new BasicNameValuePair("grant_type", "refresh_token"));
             params.add(new BasicNameValuePair("client_id", CLIENT_ID));
             params.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
             params.add(new BasicNameValuePair("refresh_token", REFRESH_TOKEN));
             //httpPost.setHeader("Accept", "application/json");
             httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
             httpPost.setEntity(new UrlEncodedFormEntity(params));
             CloseableHttpResponse response = client.execute(httpPost);
             Header[] headers = response.getAllHeaders();
             client.close();
            


             } catch (MalformedURLException ex) {
             Logger.getLogger(XmlApiClient.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
             Logger.getLogger(XmlApiClient.class.getName()).log(Level.SEVERE, null, ex);
             */        } catch (OAuthSystemException ex) {
            Logger.getLogger(XmlApiClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OAuthProblemException ex) {
            Logger.getLogger(XmlApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
