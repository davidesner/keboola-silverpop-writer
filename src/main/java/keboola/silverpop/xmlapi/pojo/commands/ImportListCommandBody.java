/*
 */
package keboola.silverpop.xmlapi.pojo.commands;

/**
 * Pojo class of Engage XML API command body.
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 *
 * NOTE: Variables of type Object represent optional parameters:
 * parameter is set: if set to empty object (new Object())
 * parameter is not set: if set to NULL
 */
import com.thoughtworks.xstream.annotations.XStreamAlias;
import keboola.silverpop.xmlapi.pojo.results.ImportListResult;
import keboola.silverpop.xmlapi.pojo.results.LoginResult;

@XStreamAlias("Body")
public class ImportListCommandBody implements SPCommandBody {

    @XStreamAlias("ImportList")
    private Command command;

    public ImportListCommandBody(String mapFile, String sourceFile) {
        this.command = new Command(mapFile, sourceFile);
    }

    public ImportListCommandBody(String mapFile, String sourceFile, String email, String fileEncoding) {
        this.command = new Command(mapFile, sourceFile, email, fileEncoding);
    }

    public Class getResultType() {
        return ImportListResult.class;
    }

    static class Command {

        @XStreamAlias("MAP_FILE")
        private String mapFile;
        @XStreamAlias("SOURCE_FILE")
        private String sourceFile;
        //(OPTIONAL)
        @XStreamAlias("EMAIL")
        private String email;
        //UTF-8 || ISO-8859-1 (OPTIONAL)
        @XStreamAlias("FILE_ENCODING")
        private String fileEncoding;

        public Command(String mapFile, String sourceFile) {
            this.mapFile = mapFile;
            this.sourceFile = sourceFile;
        }

        public Command(String mapFile, String sourceFile, String email, String fileEncoding) {
            this.mapFile = mapFile;
            this.sourceFile = sourceFile;
            this.email = email;
            this.fileEncoding = fileEncoding;
        }

    }

}
