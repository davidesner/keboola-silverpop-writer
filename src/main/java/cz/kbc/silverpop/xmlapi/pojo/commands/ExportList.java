/*
 */
package cz.kbc.silverpop.xmlapi.pojo.commands;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 *
 * NOTE: Variables of type Object represent optional parameters:
 * parameter is set: if set to empty object (new Object())
 * parameter is not set: if set to NULL
 */
import com.thoughtworks.xstream.annotations.XStreamAlias;
import cz.kbc.silverpop.xmlapi.pojo.results.ExportListResult;
import java.util.Map;

//@XStreamAlias("ExportList")
public class ExportList implements SPCommand {

    private static final String alias = "ExportList";
    @XStreamAlias("LIST_ID")
    private Integer listId;
    @XStreamAlias("EMAIL")
    private String email;
    @XStreamAlias("EXPORT_TYPE")
    private ExportType exportType;
    @XStreamAlias("EXPORT_FORMAT")
    private ExportFormat exportFormat;
    @XStreamAlias("FILE_ENCODING")
    private String fileEncoding;
    @XStreamAlias("ADD_TO_STORED_FILES")
    private Object addToStoredFiles;
    @XStreamAlias("DATE_START")
    private String dateStart;
    @XStreamAlias("DATE_END")
    private String dateEnd;
    @XStreamAlias("USE_CREATED_DATE")
    private Object useCreatedDate;
    @XStreamAlias("INCLUDE_LEAD_SOURCE")
    private Boolean includeLeadSource;
    @XStreamAlias("LIST_DATE_FORMAT")
    private String listDateFormat;

    public ExportList(String dateStart, String dateEnd) {
        this.exportFormat = ExportFormat.CSV;
        this.exportType = ExportType.ALL;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;

    }

    public ExportList(Integer listId, String email, ExportType exportType,
            ExportFormat exportFormat, String fileEncoding, Object addToStoredFiles,
            String dateStart, String dateEnd, Object useCreatedDate, Boolean includeLeadSource, String listDateFormat) {
        this.listId = listId;
        this.email = email;
        this.exportType = exportType;
        this.exportFormat = exportFormat;
        this.fileEncoding = fileEncoding;
        this.addToStoredFiles = addToStoredFiles;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.useCreatedDate = useCreatedDate;
        this.includeLeadSource = includeLeadSource;
        this.listDateFormat = listDateFormat;
    }

    // TODO: Create constructor method using parameters map acquired from the config file
    public ExportList(Map<String, String> parameters) {
    }

    @Override
    public String getAlias() {
        return alias;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ExportType getExportType() {
        return exportType;
    }

    public void setExportType(ExportType exportType) {
        this.exportType = exportType;
    }

    public ExportFormat getExportFormat() {
        return exportFormat;
    }

    public void setExportFormat(ExportFormat exportFormat) {
        this.exportFormat = exportFormat;
    }

    public String getFileEncoding() {
        return fileEncoding;
    }

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    public Object getAddToStoredFiles() {
        return addToStoredFiles;
    }

    public void setAddToStoredFiles(Object addToStoredFiles) {
        this.addToStoredFiles = addToStoredFiles;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Object getUseCreatedDate() {
        return useCreatedDate;
    }

    public void setUseCreatedDate(Object useCreatedDate) {
        this.useCreatedDate = useCreatedDate;
    }

    public Boolean getIncludeLeadSource() {
        return includeLeadSource;
    }

    public void setIncludeLeadSource(Boolean includeLeadSource) {
        this.includeLeadSource = includeLeadSource;
    }

    public String getListDateFormat() {
        return listDateFormat;
    }

    public void setListDateFormat(String listDateFormat) {
        this.listDateFormat = listDateFormat;
    }

    public Class getResultType() {
        return ExportListResult.class;
    }

    public static enum ExportType {

        ALL,
        OPT_IN,
        OPT_OUT,
        UNDELIVERABLE
    }

    public static enum ExportFormat {

        CSV,
        TAB,
        PIPE
    }
}
