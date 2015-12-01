/*
 */
package keboola.silverpop.xmlapi.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
@XStreamAlias("LIST_IMPORT")
public class ImportListMapFileWrapper {

    @XStreamAlias("LIST_INFO")
    private ImportListListInfo listInfo;
    @XStreamAlias("MAPPING")
    private ImportListMapping mapping;
    @XStreamAlias("CONTACT_LISTS")
    ContactList contactLists;

    public ImportListMapFileWrapper(ImportListListInfo listInfo, ImportListMapping mapping, List<String> contactListsIds) {
        this.listInfo = listInfo;
        this.mapping = mapping;
        this.contactLists = new ContactList(contactListsIds);
    }

    public ImportListListInfo getListInfo() {
        return listInfo;
    }

    public void setListInfo(ImportListListInfo listInfo) {
        this.listInfo = listInfo;
    }

    public ImportListMapping getMapping() {
        return mapping;
    }

    public void setMapping(ImportListMapping mapping) {
        this.mapping = mapping;
    }

    @XStreamAlias("CONTACT_LISTS")
    static class ContactList {

        @XStreamImplicit(itemFieldName = "CONTACT_LIST_ID")
        private List<String> contactListId;

        public ContactList(List<String> contactListId) {
            this.contactListId = contactListId;
        }

        public List<String> getContactListId() {
            return contactListId;
        }

        public void setContactListId(List<String> contactListId) {
            this.contactListId = contactListId;
        }

    }

}
