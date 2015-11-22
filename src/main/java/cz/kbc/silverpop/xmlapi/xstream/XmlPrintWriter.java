/*
 */
package cz.kbc.silverpop.xmlapi.xstream;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import java.io.Writer;
import java.util.regex.Pattern;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 *
 * Xml writer ensuring that any special characters will be placed in CDATA tags.
 */
public class XmlPrintWriter extends PrettyPrintWriter {

    public static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[&<>\"']");
    public static final String CDATA_START = "<![CDATA[";
    public static final String CDATA_END = "]]>";

    public XmlPrintWriter(Writer writer, XmlFriendlyNameCoder replacer) {
        super(writer, replacer);
    }

    @Override
    protected void writeText(QuickWriter writer, String text) {
        if (hasSpecialCharacters(text)) {
            writer.write(String.format("%s%s%s", CDATA_START, text, CDATA_END));
        } else {
            super.writeText(writer, text);
        }
    }

    private boolean hasSpecialCharacters(String text) {
        return SPECIAL_CHARACTERS.matcher(text).find();
    }
}
