/*
 */
package keboola.silverpop.xmlapi.pojo.results;

/**
 * Interface defining XML API response.
 *
 * @author David Esner <esnerda at gmail.com>
 */
public interface SPResult {

    /**
     * True if command was successfull.
     *
     * @return
     */
    boolean isSuccess();
}
