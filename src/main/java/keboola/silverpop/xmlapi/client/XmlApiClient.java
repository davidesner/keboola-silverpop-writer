/*
 */
package keboola.silverpop.xmlapi.client;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.NullConverter;

import keboola.silverpop.xmlapi.pojo.results.SPResult;

import keboola.silverpop.xmlapi.pojo.XmlRequestEnvelope;
import keboola.silverpop.xmlapi.pojo.XmlResponseBody;
import keboola.silverpop.xmlapi.pojo.XmlResponseEnvelope;
import keboola.silverpop.xmlapi.pojo.commands.LoginCommandBody;
import keboola.silverpop.xmlapi.pojo.commands.LogoutCommandBody;
import keboola.silverpop.xmlapi.pojo.commands.SPCommandBody;
import keboola.silverpop.xmlapi.pojo.results.LoginResult;
import keboola.silverpop.xmlapi.pojo.results.SPError;
import keboola.silverpop.xmlapi.xstream.XStreamFactory;
import java.io.StringReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Engage XML API Client. Ready for OAuth 2.0 authentization support.
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class XmlApiClient {

    private final String apiUrl;
    /*//for OAuth 2.0
    private String CLIENT_ID;
    private String CLIENT_SECRET;
    private String REFRESH_TOKEN;
    private ApiAccessToken accessToken;*/
    private String USER_NAME;
    private String PASSWORD;
    private ApiSession session;

    public XmlApiClient() {
        this.apiUrl = null;
    }

    /**
     * Constructor to use with OAuth 2.0 auth.
     *
     * @param CLIENT_ID
     * @param CLIENT_SECRET
     * @param REFRESH_TOKEN
     * @param apiUrl
     */
    /* public XmlApiClient(String CLIENT_ID, String CLIENT_SECRET, String REFRESH_TOKEN, String apiUrl) {

        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
        this.REFRESH_TOKEN = REFRESH_TOKEN;
        this.apiUrl = apiUrl;
    }*/
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
        this.session = new ApiSession();
    }

    /**
     * Sends request to API. Automatically renewes session if expired.
     *
     * @param command
     * @return
     * @throws ApiException - if it is unable to renew session.
     */
    public XmlResponseBody apiRequest(SPCommandBody command) throws ApiException {
        XmlResponseBody result = performRequest(command);
        //check if session expired
        if (!result.getResult().isSuccess()) {
            //login and return result
            if (result.getFault().getMessage().equalsIgnoreCase("Session has expired or is invalid")) {
                login();
                return performRequest(command);
            }
        }
        return result;
    }

    /**
     * Send API request using Login API. Does not check for session validity.
     *
     * @param command
     * @return
     * @throws ApiException
     */
    public XmlResponseBody performRequest(SPCommandBody command) throws ApiException {

        /*Build XML request command*/
        XStream xstream = XStreamFactory.getXstream2();
        //place command in Envelope wrapper
        XmlRequestEnvelope request = new XmlRequestEnvelope(command);
        /*Proccess annotations in objects*/
        xstream.processAnnotations(XmlRequestEnvelope.class);
        xstream.processAnnotations(command.getClass());
        //remove class attribute
        xstream.aliasSystemAttribute(null, "class");

        /*Send API request*/
        Client client = ClientBuilder.newClient();
        Form form = new Form("xml", xstream.toXML(request));
        Entity postRequest = Entity.form(form);

        Response response = client.target(apiUrl + session.getSESSION_ENCODING())
                .request(MediaType.APPLICATION_XML)
                .header("Content-Type", "text/xml; charset=UTF-8")
                .post(postRequest);

        if (response.getStatus() != 200) {
            throw new ApiException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        /*Proccess/parse the response*/
        xstream = XStreamFactory.getXstream();
        XmlResponseEnvelope responseEnv;
        xstream.processAnnotations(XmlResponseEnvelope.class);
        xstream.processAnnotations(XmlResponseBody.class);
        xstream.processAnnotations(command.getResultType());
        xstream.processAnnotations(SPError.class);
        xstream.registerConverter(new NullConverter());
        xstream.addDefaultImplementation(command.getResultType(), SPResult.class);

        String responseBody = response.readEntity(String.class);

        XmlResponseBody result = null;
        try {
            responseEnv = (XmlResponseEnvelope) xstream.fromXML(new StringReader(responseBody));
            result = responseEnv.getBody();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return result;
    }

    /**
     * Login using LOGIN API call. Retrieves and establishes SESSION ID.
     *
     * @throws ApiException
     */
    public void login() throws ApiException {
        XmlResponseBody result = (XmlResponseBody) performRequest(new LoginCommandBody(USER_NAME, PASSWORD));
        this.session = new ApiSession((LoginResult) result.getResult());
        if (!session.isOpen()) {
            throw new ApiException("Retrieving session failed. " + result.getFault().getMessage());
        }
    }

    /**
     * Logout using LOGOUT API call.
     *
     * @throws ApiException
     */
    public void logout() throws ApiException {
        XmlResponseBody result = (XmlResponseBody) performRequest(new LogoutCommandBody());
        if (!result.getResult().isSuccess()) {
            throw new ApiException("Logout failed! " + result.getFault().getMessage());
        }
    }

    /**
     * OAuth 2.0 Sets up a session (retrieves access key)
     */
    /* public void authenticate() throws AuthorizationException {

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
    }*/
}
