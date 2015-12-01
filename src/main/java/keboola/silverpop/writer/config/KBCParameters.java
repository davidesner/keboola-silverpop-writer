/*
 */
package keboola.silverpop.writer.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class KBCParameters {

    private final static String[] REQUIRED_FIELDS = {"user", "pass", "apiUrl", "listId",};
    private final Map<String, Object> parametersMap;

    @JsonProperty("user")
    private String user;
    @JsonProperty("#pass")
    private String pass;
    @JsonProperty("api_url")
    private String apiUrl;
    @JsonProperty("sftp_url")
    private String sftpUrl;
    //date format and date in source file. default: mm/dd/yyyy
    @JsonProperty("list_date_format")
    private String listDateFormat;
    @JsonProperty("action")
    private String action;
    @JsonProperty("list_id")
    private String listId;
    @JsonProperty("encode_md5")
    private boolean encodeMd5;

    @JsonProperty("contact_lists")
    private ArrayList<String> contactLists;
    @JsonProperty("mapping")
    private Map<String, String> mapping;

    public KBCParameters() {
        parametersMap = new HashMap<String, Object>();

    }

    @JsonCreator
    public KBCParameters(@JsonProperty("user") String user, @JsonProperty("#pass") String pass,
            @JsonProperty("api_url") String apiUrl, @JsonProperty("sftp_url") String sftpUrl,
            @JsonProperty("list_date_format") String listDateFormat, @JsonProperty("action") String action,
            @JsonProperty("list_id") String listId, @JsonProperty("encode_md5") boolean encodeMd5,
            @JsonProperty("contact_lists") ArrayList<String> contactLists, @JsonProperty("mapping") Map<String, String> mapping) {

        this.user = user;
        this.pass = pass;
        this.apiUrl = apiUrl;
        this.sftpUrl = sftpUrl;
        this.listDateFormat = listDateFormat;
        this.action = action;
        this.listId = listId;
        this.encodeMd5 = encodeMd5;
        this.contactLists = contactLists;
        this.mapping = mapping;

        parametersMap = new HashMap<String, Object>();
        parametersMap.put("user", user);
        parametersMap.put("pass", pass);
        parametersMap.put("apiUrl", apiUrl);
        parametersMap.put("sftpUrl", sftpUrl);
        parametersMap.put("listDateFormat", listDateFormat);
        parametersMap.put("action", action);
        parametersMap.put("listId", listId);
        parametersMap.put("encodeMd5", encodeMd5);
    }

    /**
     * Returns list of required fields missing in config
     *
     * @return
     */
    public List<String> getMissingFields() {
        List<String> missing = new ArrayList<String>();
        for (int i = 0; i < REQUIRED_FIELDS.length; i++) {
            Object value = parametersMap.get(REQUIRED_FIELDS[i]);
            if (value == null) {
                missing.add(REQUIRED_FIELDS[i]);
            }
        }

        if (missing.isEmpty()) {
            return null;
        }
        return missing;
    }

    public Map<String, Object> getParametersMap() {
        return parametersMap;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getSftpUrl() {
        return sftpUrl;
    }

    public void setSftpUrl(String sftpUrl) {
        this.sftpUrl = sftpUrl;
    }

    public String getListDateFormat() {
        return listDateFormat;
    }

    public void setListDateFormat(String listDateFormat) {
        this.listDateFormat = listDateFormat;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public boolean isEncodeMd5() {
        return encodeMd5;
    }

    public void setEncodeMd5(boolean encodeMd5) {
        this.encodeMd5 = encodeMd5;
    }

    public ArrayList<String> getContactLists() {
        return contactLists;
    }

    public void setContactLists(ArrayList<String> contactLists) {
        this.contactLists = contactLists;
    }

    public Map<String, String> getMapping() {
        if (mapping == null) {
            mapping = new HashMap();
        }
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }

}
