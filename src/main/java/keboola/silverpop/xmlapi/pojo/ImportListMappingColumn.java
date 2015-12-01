/*
 */
package keboola.silverpop.xmlapi.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
@XStreamAlias("COLUMN")
public class ImportListMappingColumn {

    @XStreamAlias("INDEX")
    private int index;
    @XStreamAlias("NAME")
    private String name;
    @XStreamAlias("INCLUDE")
    private boolean include = true;

    public ImportListMappingColumn(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public ImportListMappingColumn(int index, String name, boolean include) {
        this.index = index;
        this.name = name;
        this.include = include;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInclude() {
        return include;
    }

    public void setInclude(boolean include) {
        this.include = include;
    }

}
