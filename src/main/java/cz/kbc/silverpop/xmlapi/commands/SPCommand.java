/*
 */
package cz.kbc.silverpop.xmlapi.commands;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 *
 */
public interface SPCommand {

    public String getAlias();

    public Class getResultType();
}
