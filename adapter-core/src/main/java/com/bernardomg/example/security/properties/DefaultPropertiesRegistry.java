
package com.bernardomg.example.security.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DefaultPropertiesRegistry implements PropertiesRegistry {

    private final Map<String, Object> properties = new HashMap<>();

    public DefaultPropertiesRegistry() {
        super();
    }

    @Override
    public final Boolean containsKey(final String key) {
        return properties.containsKey(key);
    }

    @Override
    public final Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public final Object getProperty(final String key) {
        final Object value;

        value = properties.get(key);

        log.debug("{}: {}", key, value);

        return value;
    }

    @Override
    public final void setProperty(final String key, final Object value) {
        properties.put(key, value);
    }

    @Override
    public final String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();
        for (final Entry<String, Object> entry : properties.entrySet()) {
            builder.append(entry.getKey());
            builder.append(": ");
            builder.append(entry.getValue());
            builder.append("\n");
        }

        return builder.toString();
    }

}
