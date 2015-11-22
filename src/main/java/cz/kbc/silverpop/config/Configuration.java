/*
 */
package cz.kbc.silverpop.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class Configuration {

    @JsonProperty
    private KBCStorage storage;
    @JsonProperty
    private KBCParameters params;

    public Configuration(KBCStorage storage, KBCParameters params) {
        this.storage = storage;
        this.params = params;
    }

    public KBCStorage getStorage() {
        return storage;
    }

    public void setStorage(KBCStorage storage) {
        this.storage = storage;
    }

    public KBCParameters getParams() {
        return params;
    }

    public void setParams(KBCParameters params) {
        this.params = params;
    }
}
