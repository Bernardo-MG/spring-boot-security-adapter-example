
package com.bernardomg.example.security.extractor.factory;

import java.util.Collection;
import java.util.Optional;

import com.bernardomg.example.security.extractor.EntitySource;
import com.bernardomg.example.security.extractor.ModelExtractorConfiguration;

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
    public final EntitySource
            connect(final ModelExtractorConfiguration config) {
        final Optional<EntitySourceBuilder> builder;

        builder = builders.stream()
            .filter(b -> b.getSourceName()
                .equals(config.getSourceName()))
            .findFirst();

        if (builder.isEmpty()) {
            // TODO: Use concrete exception
            log.error("No builder for {}", config.getSourceName());
            throw new RuntimeException();
        }

        return builder.get()
            .build(config);
    }

}
