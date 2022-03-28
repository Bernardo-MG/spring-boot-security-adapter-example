
package com.bernardomg.example.security.extractor;

import com.bernardomg.example.security.properties.PropertiesRegistry;

public final class PropertiesRegistryModelExtractorConfiguration
        implements ModelExtractorConfiguration {

    private final PropertiesRegistry properties;

    private final String             sourceName;

    public PropertiesRegistryModelExtractorConfiguration(
            final String sourceName, final PropertiesRegistry properties) {
        super();

        this.sourceName = sourceName;
        this.properties = properties;
    }

    @Override
    public final String getProperty(final String key) {
        return String.valueOf(properties.getProperty(key));
    }

    @Override
    public final String getSourceName() {
        return sourceName;
    }

}
