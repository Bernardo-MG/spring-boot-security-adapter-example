
package com.bernardomg.example.security.extractor.factory;

import java.util.Collection;
import java.util.Optional;

import com.bernardomg.example.security.extractor.EntitySource;
import com.bernardomg.example.security.extractor.ModelLoaderProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DefaultEntitySourceFactory implements EntitySourceFactory {

    private final Collection<EntitySourceBuilder> builders;

    public DefaultEntitySourceFactory(
            final Collection<EntitySourceBuilder> builders) {
        super();

        this.builders = builders;
    }

    @Override
    public final EntitySource connect(final ModelLoaderProperties properties) {
        final Optional<EntitySourceBuilder> builder;

        log.error("Searching code {} in {}", properties.getSource(), builders);
        builder = builders.stream()
            .filter(b -> b.getSourceName()
                .equals(properties.getSource()))
            .findFirst();

        if (builder.isEmpty()) {
            // TODO: Use concrete exception
            log.error("No builder for {}", properties.getSource());
            throw new RuntimeException();
        }

        return builder.get()
            .build(properties);
    }

}
