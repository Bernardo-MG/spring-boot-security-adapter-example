
package com.bernardomg.example.security.extractor;

import java.util.Collection;

import com.bernardomg.example.security.extractor.factory.EntitySourceFactory;

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
    public final void extract(final ModelExtractorConfiguration config) {
        final EntitySource source;
        Collection read;

        source = factory.connect(config);

        for (final EntitySaver<?> saver : savers) {
            read = source.read(saver.getClass());
            saver.save(read);
        }
    }

}
