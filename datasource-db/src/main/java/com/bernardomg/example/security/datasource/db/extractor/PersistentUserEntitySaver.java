
package com.bernardomg.example.security.datasource.db.extractor;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardomg.example.security.datasource.db.user.model.PersistentUser;
import com.bernardomg.example.security.extractor.EntitySaver;
import com.bernardomg.example.security.user.model.User;

public final class PersistentUserEntitySaver implements EntitySaver<User> {

    private final JpaRepository<PersistentUser, Long> repository;

    public PersistentUserEntitySaver(
            final JpaRepository<PersistentUser, Long> repository) {
        super();

        this.repository = repository;
    }

    @Override
    public final void save(final Iterable<User> entities) {
        final Collection<PersistentUser> persistentUsers;

        persistentUsers = StreamSupport.stream(entities.spliterator(), false)
            .map(this::toPersistentUser)
            .collect(Collectors.toList());

        repository.saveAll(persistentUsers);
    }

    @Override
    public final Class<User> supports() {
        return User.class;
    }

    private final PersistentUser toPersistentUser(final User user) {
        final PersistentUser persistent;

        persistent = new PersistentUser();
        BeanUtils.copyProperties(user, persistent);

        return persistent;
    }

}
