
package com.bernardomg.example.security.extractor;

import com.bernardomg.example.security.data.repository.CrudRepository;

public final class CrudRepositoryEntitySaver<T> implements EntitySaver<T> {

    private final CrudRepository<T> repository;

    private final Class<T>          clazz;

    public CrudRepositoryEntitySaver(final CrudRepository<T> repository,
            final Class<T> clazz) {
        super();

        this.repository = repository;
        this.clazz = clazz;
    }

    @Override
    public final void save(final Iterable<T> entities) {
        repository.saveAll(entities);
    }

    @Override
    public final Class<T> supports() {
        return clazz;
    }

}
