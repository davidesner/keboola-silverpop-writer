/*
 */
package cz.kbc.silverpop.xmlapi.client;

import com.thoughtworks.xstream.XStream;
import cz.kbc.silverpop.xmlapi.pojo.commands.LoginCommand;
import cz.kbc.silverpop.xmlapi.pojo.commands.SPCommand;
import cz.kbc.silverpop.xmlapi.pojo.results.LoginResult;
import cz.kbc.silverpop.xmlapi.pojo.results.SPResult;
import cz.kbc.silverpop.xmlapi.pojo.XmlRequestBody;
import cz.kbc.silverpop.xmlapi.pojo.XmlRequestEnvelope;
import cz.kbc.silverpop.xmlapi.pojo.XmlResponseEnvelope;
import cz.kbc.silverpop.xmlapi.xstream.XStreamFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.HttpHeaders;

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
    private String CLIENT_ID;
    private String CLIENT_SECRET;
    private String REFRESH_TOKEN;
    private String USER_NAME;
    private String PASSWORD;
    private ApiSession session;

    public XmlApiClient() {
        this.apiUrl = null;
    }
    private ApiAccessToken accessToken;

    /**
     * Constructor to use with OAuth 2.0 auth.
     *
     * @param CLIENT_ID
     * @param CLIENT_SECRET
     * @param REFRESH_TOKEN
     * @param apiUrl
     */
    public XmlApiClient(String CLIENT_ID, String CLIENT_SECRET, String REFRESH_TOKEN, String apiUrl) {

        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
        this.REFRESH_TOKEN = REFRESH_TOKEN;
        this.apiUrl = apiUrl;
    }

    /**
     * Consctructor to use with Login - session_id auth method
     *
     * @param USER_NAME
     * @param PASSWORD
     * @param apiUrl
     */
    public XmlApiClient(String USER_NAME, String PASSWORD, String apiUrl) {
        this.USER_NAME = USER_NAME;
        this.PASSWORD = PASSWORD;
        this.apiUrl = apiUrl;
    }

    public SPResult performRequest(SPCommand command) throws ApiException {
        /*Build XML request command*/
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

        Client client = ClientBuilder.newClient();
        Form form = new Form("xml", xstream.toXML(request));

        Entity postRequest = Entity.form(form);

        Response response = client.target(apiUrl)
                .request(MediaType.APPLICATION_XML)
                .header("Content-Type", "text/xml; charset=UTF-8")
                .post(postRequest);

        if (response.getStatus() > 300) {
            throw new ApiException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        /*Proccess/parse the response*/
        xstream = new XStream();
        XmlResponseEnvelope responseEnv = new XmlResponseEnvelope();
        xstream.processAnnotations(XmlResponseEnvelope.class);
        xstream.processAnnotations(command.getResultType());
        String responseBody = response.readEntity(String.class);

        SPResult result = null;
        try {
            responseEnv = (XmlResponseEnvelope) xstream.fromXML(responseBody);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return result;

    }

    public void login() throws ApiException {
        LoginResult result = (LoginResult) performRequest(new LoginCommand(USER_NAME, PASSWORD));
        this.session = new ApiSession(result);

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

        } catch (OAuthSystemException ex) {
            Logger.getLogger(XmlApiClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OAuthProblemException ex) {
            Logger.getLogger(XmlApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
