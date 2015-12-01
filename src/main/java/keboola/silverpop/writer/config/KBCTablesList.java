/*
 */
package keboola.silverpop.writer.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KBCTablesList {

    @JsonProperty("tables")
    private List<KBCOutputMapping> tables;

    public KBCTablesList() {
    }

    public KBCTablesList(List<KBCOutputMapping> tables) {
        this.tables = tables;
    }

    public List<KBCOutputMapping> getTables() {
        return tables;
    }

    public void setTables(List<KBCOutputMapping> tables) {
        this.tables = tables;
    }

}