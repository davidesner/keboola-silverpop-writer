/*
 */
package cz.kbc.silverpop.xmlapi.pojo.commands;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import cz.kbc.silverpop.xmlapi.pojo.results.RawRecipientDataExportResult;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 *
 * NOTE: Variables of type Object represent optional parameters:
 * parameter is set: if set to empty object (new Object())
 * parameter is not set: if set to NULL
 */
public class WebTrackingDataExport implements SPCommand {

    private static final String alias = "WebTrackingDataExport";
    @XStreamAlias("EVENT_DATA_START")
    private String eventDateStart;
    @XStreamAlias("EVENT_DATE_END")
    private String eventDateEnd;
    @XStreamAlias("EXPORT_FORMAT")
    private Integer exportFormat;
    @XStreamAlias("EMAIL")
    private String email;
    @XStreamAlias("DATABASE_ID")
    private Integer databaseId;
    @XStreamAlias("INCLUDE_SITE_VISIT_EVENTS")
    private Object visidSiteEvents;
    @XStreamAlias("INCLUDE_PAGE_VIEW_EVENTS")
    private Object pageViewEvents;
    @XStreamAlias("INCLUDE_CLICK_EVENTS")
    private Object clickEvents;
    @XStreamAlias("INCLUDE_FORM_SUBMIT_EVENTS")
    private Object formSubmitEv;
    @XStreamAlias("INCLUDE_DOWNLOAD_EVENTS")
    private Object downloadEvents;
    @XStreamAlias("INCLUDE_MEDIA_EVENTS")
    private Object mediaEvents;
    @XStreamAlias("INCLUDE_SHARE_TO_SOCIAL_EVENTS")
    private Object shareToSocialEv;
    @XStreamAlias("INCLUDE_CUSTOM_EVENTS")
    private Object customEvents;
    @XStreamAlias("ALL_EVENT_TYPES")
    private Object allEventTypes;
    @XStreamAlias("MOVE_TO_FTP")
    private Object moveToFtp;
    @XStreamAlias("FILE_ENCODING")
    private String fileEncoding;
    @XStreamAlias("EXPORT_FILE_NAME")
    private String exportFileName;

    /**
     * Set default parameters
     */
    private void initDefaults() {
        fileEncoding = "utf-8";
        moveToFtp = new Object();
        allEventTypes = new Object();
    }

    public WebTrackingDataExport(String eventDateStart, String eventDateEnd, String email) {
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = eventDateEnd;
        this.email = email;
        initDefaults();
    }

    public WebTrackingDataExport(String eventDateStart, String eventDateEnd,
            Integer exportFormat, String email, Integer databaseId, Object visidSiteEvents,
            Object pageViewEvents, Object clickEvents, Object formSubmitEv, Object downloadEvents,
            Object mediaEvents, Object shareToSocialEv, Object customEvents, Object allEventTypes,
            Object moveToFtp, String fileEncoding, String exportFileName) {
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = eventDateEnd;
        this.exportFormat = exportFormat;
        this.email = email;
        this.databaseId = databaseId;
        this.visidSiteEvents = visidSiteEvents;
        this.pageViewEvents = pageViewEvents;
        this.clickEvents = clickEvents;
        this.formSubmitEv = formSubmitEv;
        this.downloadEvents = downloadEvents;
        this.mediaEvents = mediaEvents;
        this.shareToSocialEv = shareToSocialEv;
        this.customEvents = customEvents;
        this.allEventTypes = allEventTypes;
        this.moveToFtp = moveToFtp;
        this.fileEncoding = fileEncoding;
        this.exportFileName = exportFileName;
    }

    public String getEventDateStart() {
        return eventDateStart;
    }

    public void setEventDateStart(String eventDateStart) {
        this.eventDateStart = eventDateStart;
    }

    public String getEventDateEnd() {
        return eventDateEnd;
    }

    public void setEventDateEnd(String eventDateEnd) {
        this.eventDateEnd = eventDateEnd;
    }

    public Integer getExportFormat() {
        return exportFormat;
    }

    public void setExportFormat(Integer exportFormat) {
        this.exportFormat = exportFormat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Integer databaseId) {
        this.databaseId = databaseId;
    }

    public Object getVisidSiteEvents() {
        return visidSiteEvents;
    }

    public void setVisidSiteEvents(Object visidSiteEvents) {
        this.visidSiteEvents = visidSiteEvents;
    }

    public Object getPageViewEvents() {
        return pageViewEvents;
    }

    public void setPageViewEvents(Object pageViewEvents) {
        this.pageViewEvents = pageViewEvents;
    }

    public Object getClickEvents() {
        return clickEvents;
    }

    public void setClickEvents(Object clickEvents) {
        this.clickEvents = clickEvents;
    }

    public Object getFormSubmitEv() {
        return formSubmitEv;
    }

    public void setFormSubmitEv(Object formSubmitEv) {
        this.formSubmitEv = formSubmitEv;
    }

    public Object getDownloadEvents() {
        return downloadEvents;
    }

    public void setDownloadEvents(Object downloadEvents) {
        this.downloadEvents = downloadEvents;
    }

    public Object getMediaEvents() {
        return mediaEvents;
    }

    public void setMediaEvents(Object mediaEvents) {
        this.mediaEvents = mediaEvents;
    }

    public Object getShareToSocialEv() {
        return shareToSocialEv;
    }

    public void setShareToSocialEv(Object shareToSocialEv) {
        this.shareToSocialEv = shareToSocialEv;
    }

    public Object getCustomEvents() {
        return customEvents;
    }

    public void setCustomEvents(Object customEvents) {
        this.customEvents = customEvents;
    }

    public Object getAllEventTypes() {
        return allEventTypes;
    }

    public void setAllEventTypes(Object allEventTypes) {
        this.allEventTypes = allEventTypes;
    }

    public Object getMoveToFtp() {
        return moveToFtp;
    }

    public void setMoveToFtp(Object moveToFtp) {
        this.moveToFtp = moveToFtp;
    }

    public String getFileEncoding() {
        return fileEncoding;
    }

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }

    public String getAlias() {
        return alias;
    }

    public Class getResultType() {
        return RawRecipientDataExportResult.class;
    }
}
