/*
 */
package cz.kbc.silverpop.xmlapi.results;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public interface SPError {

    String getMessage();

    boolean isSessionLost();

    String getResponseText();
}
