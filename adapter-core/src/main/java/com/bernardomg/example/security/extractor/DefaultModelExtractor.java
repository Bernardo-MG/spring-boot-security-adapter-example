
package com.bernardomg.example.security.extractor;

import java.util.Collection;

import com.bernardomg.example.security.extractor.factory.EntitySourceFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DefaultModelExtractor implements ModelExtractor {

    private final EntitySourceFactory        factory;

    private final Collection<EntitySaver<?>> savers;

    public DefaultModelExtractor(final EntitySourceFactory factory,
            final Collection<EntitySaver<?>> savers) {
        super();

        this.factory = factory;
        this.savers = savers;
    }

    @Override
    public final void extract(final ModelLoaderProperties properties) {
        final EntitySource source;
        Collection read;

        source = factory.connect(properties);

        log.debug("Reading from {}", properties.getSource());
        for (final EntitySaver<?> saver : savers) {
            read = source.read(saver.supports());
            log.debug("Read {}: {}", saver.supports(), read);
            saver.save(read);
        }
    }

}
