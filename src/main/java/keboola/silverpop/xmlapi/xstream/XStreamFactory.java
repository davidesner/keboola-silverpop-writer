/*
 */
package keboola.silverpop.xmlapi.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import java.io.Writer;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class XStreamFactory {

    /**
     * Builds Xstream with driver ignoring undefined elements
     *
     * @return
     */
    public static XStream getXstream() {
        return new XStream(new StaxDriver()) {
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn,
                            String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
    }

    /**
     * Function building xStream instance with custom XML writer.
     *
     * @return XStream instance
     */
    public static XStream getXstream2() {
        return new XStream(
                new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new XmlPrintWriter(out, new XmlFriendlyNameCoder("__", "_"));
            }
        });
    }
}
