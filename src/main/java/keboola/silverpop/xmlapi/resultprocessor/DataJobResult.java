/*
 */
package keboola.silverpop.xmlapi.resultprocessor;

import java.util.List;
import java.util.Map;
import keboola.silverpop.xmlapi.pojo.results.GetJobStatusResult;

/**
 * DataJobResult object containing populated error messages and status messages.
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class DataJobResult {

    private final boolean finishedSuccess;
    private int rows_parsed;
    private int rows_valid;
    private int cont_added;
    private int cont_updated;
    /*errors*/
    private int rows_invalid;
    private int cont_disallowed;
    private int rows_duplicate;
    private int bad_addresses;
    private String error_file_name;
    private String errorMessage;

    public DataJobResult(boolean finishedSuccess) {
        this.finishedSuccess = finishedSuccess;
    }

    public DataJobResult(Map<String, String> params) {

        String p = params.get("TOTAL_ROWS");
        if (p.isEmpty()) {
            p = null;
        }
        rows_parsed = p == null ? 0 : Integer.valueOf(p);

        p = params.get("TOTAL_VALID");
        if (p.isEmpty()) {
            p = null;
        }
        rows_valid = p == null ? 0 : Integer.valueOf(p);

        p = params.get("SQL_ADDED");
        if (p.isEmpty()) {
            p = null;
        }
        cont_added = p == null ? 0 : Integer.valueOf(p);

        p = params.get("SQL_UPDATED");
        if (p.isEmpty()) {
            p = null;
        }
        cont_updated = p == null ? 0 : Integer.valueOf(p);

        p = params.get("BAD_RECORDS");
        if (p.isEmpty()) {
            p = null;
        }
        rows_invalid = p == null ? 0 : Integer.valueOf(p);

        p = params.get("NOT_ALLOWED");
        if (p.isEmpty()) {
            p = null;
        }
        cont_disallowed = p == null ? 0 : Integer.valueOf(p);

        p = params.get("DUPLICATES");
        if (p.isEmpty()) {
            p = null;
        }
        rows_duplicate = p == null ? 0 : Integer.valueOf(p);

        p = params.get("BAD_ADDRESSES");
        if (p.isEmpty()) {
            p = null;
        }
        bad_addresses = p == null ? 0 : Integer.valueOf(p);

        p = params.get("RESULTS_FILE_NAME");
        error_file_name = p == null ? "" : p;

        if (rows_parsed == rows_valid && cont_added + cont_updated > 0 && rows_invalid == 0) {
            finishedSuccess = true;
        } else {
            finishedSuccess = false;
        }
    }

    public String getError_file_name() {
        return error_file_name;
    }

    public String getResultMessage() {
        String rmsg;
        if (finishedSuccess) {
            rmsg = "Import successful. Contacts added: " + cont_added
                    + ", Contacts updated: " + cont_updated + ". Total parsed rows:" + rows_parsed;
        } else if (rows_valid > 0) {
            rmsg = "Import finished with errors! \n"
                    + "Contacts added: " + cont_added
                    + ", Contacts updated: " + cont_updated + ". Total parsed rows:" + rows_parsed
                    + ", Invalid rows: " + rows_invalid
                    + "\n Error msg: \n" + getErrorMessage();
        } else {
            rmsg = "Failed to import rows! No rows imported."
                    + "\n Error msg: \n" + getErrorMessage();
        }
        return rmsg;

    }

    public int getRows_parsed() {
        return rows_parsed;
    }

    public boolean isFinishedSuccess() {
        return finishedSuccess;
    }

    public void setRows_parsed(int rows_parsed) {
        this.rows_parsed = rows_parsed;
    }

    public int getRows_valid() {
        return rows_valid;
    }

    public void setRows_valid(int rows_valid) {
        this.rows_valid = rows_valid;
    }

    public int getCont_added() {
        return cont_added;
    }

    public void setCont_added(int cont_added) {
        this.cont_added = cont_added;
    }

    public int getCont_updated() {
        return cont_updated;
    }

    public void setCont_updated(int cont_updated) {
        this.cont_updated = cont_updated;
    }

    public int getRows_invalid() {
        return rows_invalid;
    }

    public void setRows_invalid(int rows_invalid) {
        this.rows_invalid = rows_invalid;
    }

    public int getCont_disallowed() {
        return cont_disallowed;
    }

    public void setCont_disallowed(int cont_disallowed) {
        this.cont_disallowed = cont_disallowed;
    }

    public int getRows_duplicate() {
        return rows_duplicate;
    }

    public void setRows_duplicate(int rows_duplicate) {
        this.rows_duplicate = rows_duplicate;
    }

    public int getBad_addresses() {
        return bad_addresses;
    }

    public void setBad_addresses(int bad_addresses) {
        this.bad_addresses = bad_addresses;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
