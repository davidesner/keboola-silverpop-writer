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
import keboola.silverpop.xmlapi.pojo.results.LoginResult;

@XStreamAlias("Body")
public class LoginCommandBody implements SPCommandBody {

    @XStreamAlias("Login")
    private Command command;

    public LoginCommandBody(String user, String pass) {
        command = new Command(user, pass);
    }

    public Class getResultType() {
        return LoginResult.class;
    }

    static class Command {

        public Command(String username, String password) {
            this.username = username;
            this.password = password;
        }

        private static final String alias = "Login";
        @XStreamAlias("USERNAME")
        private String username;
        @XStreamAlias("PASSWORD")
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

}
