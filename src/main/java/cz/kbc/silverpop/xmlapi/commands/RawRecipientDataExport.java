/*
 */
package cz.kbc.silverpop.xmlapi.commands;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import cz.kbc.silverpop.xmlapi.results.RawRecipientDataExportResult;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 *
 * NOTE: Variables of type Object represent optional parameters:
 * parameter is set: if set to empty object (new Object())
 * parameter is not set: if set to NULL
 */
public class RawRecipientDataExport implements SPCommand {

    private static final String alias = "RawRecipientDataExport";
    @XStreamAlias("EMAILEVENT_DATE_START")
    private String eventDateStart;
    @XStreamAlias("EVENT_DATE_END")
    private String eventDateEnd;
    @XStreamAlias("EXPORT_FORMAT")
    private Integer exportFormat;
    @XStreamAlias("EMAIL")
    private String email;
    @XStreamAlias("LIST_ID")
    private Integer listId;
    @XStreamAlias("INCLUDE_CHILDREN")
    private Object includeChildren;
    @XStreamAlias("SHARED")
    private Object shared;
    @XStreamAlias("RETURN_MAILING_NAME")
    private Object returnMailingName;
    @XStreamAlias("SENT_MAILINGS")
    private Object sentMailings;
    @XStreamAlias("ALL_EVENT_TYPES")
    private Object allEventTypes;
    @XStreamAlias("RETURN_SUBJECT")
    private Object returnSubject;

    public RawRecipientDataExport(String eventDateStart, String eventDateEnd, String email) {
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = eventDateEnd;
        this.email = email;
        this.exportFormat = 0;

    }

    public RawRecipientDataExport(String eventDateStart, String eventDateEnd, Integer exportFormat,
            String email, Integer listId, Object includeChildren, Object shared, Object returnMailingName,
            Object sentMailings, Object allEventTypes, Object returnSubject) {
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = eventDateEnd;
        this.exportFormat = exportFormat;
        this.email = email;
        this.listId = listId;
        this.includeChildren = includeChildren;
        this.shared = shared;
        this.returnMailingName = returnMailingName;
        this.sentMailings = sentMailings;
        this.allEventTypes = allEventTypes;
        this.returnSubject = returnSubject;
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

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Object getIncludeChildren() {
        return includeChildren;
    }

    public void setIncludeChildren(Object includeChildren) {
        this.includeChildren = includeChildren;
    }

    public Object getShared() {
        return shared;
    }

    public void setShared(Object shared) {
        this.shared = shared;
    }

    public Object getReturnMailingName() {
        return returnMailingName;
    }

    public void setReturnMailingName(Object returnMailingName) {
        this.returnMailingName = returnMailingName;
    }

    public Object getSentMailings() {
        return sentMailings;
    }

    public void setSentMailings(Object sentMailings) {
        this.sentMailings = sentMailings;
    }

    public Object getAllEventTypes() {
        return allEventTypes;
    }

    public void setAllEventTypes(Object allEventTypes) {
        this.allEventTypes = allEventTypes;
    }

    public Object getReturnSubject() {
        return returnSubject;
    }

    public void setReturnSubject(Object returnSubject) {
        this.returnSubject = returnSubject;
    }

    public String getAlias() {
        return alias;
    }

    public Class getResultType() {
        return RawRecipientDataExportResult.class;
    }
}
