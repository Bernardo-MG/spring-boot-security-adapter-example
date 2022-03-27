
package com.bernardomg.example.security.loader;

import java.util.Collection;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ReaderModelLoader<T> implements ModelLoader {

    private final Collection<EntityReader<?>> readers;

    private final Collection<EntitySaver<?>>  savers;

    public ReaderModelLoader(final Collection<EntityReader<?>> readers,
            final Collection<EntitySaver<?>> savers) {
        super();

        this.readers = readers;
        this.savers = savers;
    }

    @Override
    public void load() {
        Collection read;

        for (final EntitySaver<?> saver : savers) {
            read = readers.stream()
                .filter(r -> r.supports(saver.supports()))
                .map(EntityReader::readAll)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

            log.debug("Read {}", read);
            saver.save(read);
        }
    }

}
