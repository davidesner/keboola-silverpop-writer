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
import cz.kbc.silverpop.xmlapi.pojo.results.LoginResult;
import java.util.Map;

//@XStreamAlias("ExportList")
public class LoginCommand implements SPCommand {

    private static final String alias = "Login";
    @XStreamAlias("USERNAME")
    private String username;
    @XStreamAlias("PASSWORD")
    private String password;

    public LoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // TODO: Create constructor method using parameters map acquired from the config file
    public LoginCommand(Map<String, String> parameters) {
    }

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

    @Override
    public String getAlias() {
        return alias;
    }

    public Class getResultType() {
        return LoginResult.class;
    }

}
