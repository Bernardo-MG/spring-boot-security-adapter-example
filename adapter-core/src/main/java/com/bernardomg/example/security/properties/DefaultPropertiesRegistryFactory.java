
package com.bernardomg.example.security.properties;

import java.util.stream.StreamSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DefaultPropertiesRegistryFactory
        implements PropertiesRegistryFactory {

    private final Iterable<PropertiesRegistrySource> sources;

    public DefaultPropertiesRegistryFactory(
            final Iterable<PropertiesRegistrySource> sources) {
        super();

        this.sources = sources;
    }

    @Override
    public final PropertiesRegistry build() {
        final PropertiesRegistry registry;

        registry = new DefaultPropertiesRegistry();

        StreamSupport.stream(sources.spliterator(), false)
            .forEach(s -> s.load(registry));

        log.debug("Loaded properties: {}", registry);

        return registry;
    }

}
