/*
 */
package cz.kbc.silverpop.xmlapi.client;

import cz.kbc.silverpop.ftp.*;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class ApiException extends Exception {

    public ApiException(String message) {
        super(message);
    }
}
