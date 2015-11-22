/*
 */
package cz.kbc.silverpop.xmlapi.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import cz.kbc.silverpop.xmlapi.commands.SPCommand;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class XmlRequestBody {

    private SPCommand apiCommand;

    public XmlRequestBody(SPCommand apiCommand) {
        this.apiCommand = apiCommand;
    }

    public SPCommand getApiCommand() {
        return apiCommand;
    }

    public void setApiCommand(SPCommand apiCommand) {
        this.apiCommand = apiCommand;
    }
}
