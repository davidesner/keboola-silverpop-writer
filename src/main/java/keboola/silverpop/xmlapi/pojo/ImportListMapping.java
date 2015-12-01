/*
 */
package keboola.silverpop.xmlapi.pojo;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class ImportListMapping {

    @XStreamImplicit(itemFieldName = "COLUMN")
    List<ImportListMappingColumn> columns;

    public ImportListMapping(List<ImportListMappingColumn> columns) {
        this.columns = columns;
    }

    public ImportListMapping(Map<Integer, String> columns) {
        this.columns = new ArrayList<ImportListMappingColumn>();
        for (Map.Entry<Integer, String> entry : columns.entrySet()) {
            this.columns.add(new ImportListMappingColumn(entry.getKey(), entry.getValue()));
        }
    }

    public List<ImportListMappingColumn> getColumns() {
        return columns;
    }

}
