
package com.bernardomg.example.security.datasource.db.extractor;

import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.data.repository.CrudRepository;
import com.bernardomg.example.security.extractor.EntitySaver;

public final class CrudRepositoryEntitySaver implements EntitySaver<User> {

    private final CrudRepository<User> repository;

    public CrudRepositoryEntitySaver(final CrudRepository<User> repository) {
        super();

        this.repository = repository;
    }

    @Override
    public final void save(final Iterable<User> entities) {
        repository.saveAll(entities);
    }

    @Override
    public final Class<User> supports() {
        return User.class;
    }

}
