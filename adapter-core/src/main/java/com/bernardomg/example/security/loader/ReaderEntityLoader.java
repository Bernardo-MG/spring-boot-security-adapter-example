
package com.bernardomg.example.security.loader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ReaderEntityLoader<T> implements EntityLoader {

    private final EntityReader<T> reader;

    private final EntitySaver<T>  saver;

    public ReaderEntityLoader(final EntityReader<T> reader,
            final EntitySaver<T> saver) {
        super();

        this.reader = reader;
        this.saver = saver;
    }

    @Override
    public void load() {
        final Iterable<T> read;

        read = reader.readAll();
        log.debug("Saving {}", read);
        saver.save(read);
    }

}
