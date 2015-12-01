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
import keboola.silverpop.xmlapi.pojo.results.LogoutResult;

@XStreamAlias("Body")
public class LogoutCommandBody implements SPCommandBody {

    @XStreamAlias("Logout")
    private Command command;

    public LogoutCommandBody() {
        command = new Command();
    }

    public Class getResultType() {
        return LogoutResult.class;
    }

    static class Command {

        public Command() {
        }

        private static final String alias = "Login";
        @XStreamAlias("Logout")
        private final String logout = null;

    }

}
