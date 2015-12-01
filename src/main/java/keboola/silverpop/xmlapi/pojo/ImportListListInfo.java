/*
 */
package keboola.silverpop.xmlapi.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Pojo class of xml mapping file used as a configuration for ImportList API
 * command.
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class ImportListListInfo {

    @XStreamAlias("ACTION")
    private String action = "ADD_AND_UPDATE";
    @XStreamAlias("LIST_ID")
    private String list_id;
    //DEFAULT SHARED-1 , 0-private
    @XStreamAlias("LIST_VISIBILITY")
    private Integer listVisibility = 1;
    @XStreamAlias("HASHEADERS")
    private boolean hasHeaders = true;
    //DEFAULT CSV-0
    @XStreamAlias("FILE_TYPE")
    private Integer fileType = 0;
    //OPTIONAL
    @XStreamAlias("LIST_DATE_FORMAT")
    //OPTIONAL
    private String listDateFormat;
    @XStreamAlias("ENCODED_AS_MD5")
    private boolean encodedMd5;
    /*only for ACTION=CREATE*/
    @XStreamAlias("LIST_TYPE")
    private Integer listType = null;

    public ImportListListInfo(String list_id, String listDateFormat) {
        this.list_id = list_id;
        this.listDateFormat = listDateFormat;
    }

    public ImportListListInfo(String list_id, String listDateFormat, Integer listVisibility, String action) {
        this.action = action;
        this.list_id = list_id;
        this.listDateFormat = listDateFormat;
        this.listVisibility = listVisibility;
    }

    public ImportListListInfo(String list_id, String listDateFormat, String action, boolean encodedMd5) {

        this.list_id = list_id;
        if (action != null) {
            this.action = action;
        }
        if (listDateFormat != null) {
            this.listDateFormat = listDateFormat;
        }
        this.encodedMd5 = encodedMd5;

    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public Integer getListVisibility() {
        return listVisibility;
    }

    public void setListVisibility(Integer listVisibility) {
        this.listVisibility = listVisibility;
    }

    public boolean isHasHeaders() {
        return hasHeaders;
    }

    public void setHasHeaders(boolean hasHeaders) {
        this.hasHeaders = hasHeaders;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getListDateFormat() {
        return listDateFormat;
    }

    public void setListDateFormat(String listDateFormat) {
        this.listDateFormat = listDateFormat;
    }

    public boolean isEncodedMd5() {
        return encodedMd5;
    }

    public void setEncodedMd5(boolean encodedMd5) {
        this.encodedMd5 = encodedMd5;
    }

    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }

}
