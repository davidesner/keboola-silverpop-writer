/*
 */
package keboola.silverpop.writer.utils;

import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class CsvProcesser {

    public static String[] getHeader(File file) throws Exception {
        CSVReader reader = null;
        try {

            reader = new CSVReader(new FileReader(file));
            String[] firstLine = reader.readNext();
            reader.close();
            return firstLine;
        } catch (FileNotFoundException ex) {
            throw new Exception("Cannot parse the source file.");
        } catch (IOException ex) {
            throw new Exception("Cannot parse the source file.");
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
                Logger.getLogger(CsvProcesser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
