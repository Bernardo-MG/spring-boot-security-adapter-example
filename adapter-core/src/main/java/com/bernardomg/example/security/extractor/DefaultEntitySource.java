
package com.bernardomg.example.security.extractor;

import java.util.Collection;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DefaultEntitySource implements EntitySource {

    private final Collection<EntityReader<?>> readers;

    public DefaultEntitySource(final Collection<EntityReader<?>> readers) {
        super();

        this.readers = readers;
    }

    @Override
    public final <T> Collection<T> read(final Class<T> clz) {
        final Optional<EntityReader<?>> reader;

        reader = readers.stream()
            .filter(r -> r.supports(clz))
            .findFirst();

        if (reader.isEmpty()) {
            // TODO: Use concrete exception
            log.error("No reader for {}", clz);
            throw new RuntimeException();
        }

        return (Collection<T>) reader.get()
            .readAll();
    }

}
