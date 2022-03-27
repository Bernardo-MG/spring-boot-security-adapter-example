
package com.bernardomg.example.security.datasource.db.loader;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardomg.example.security.datasource.db.user.model.PersistentUser;
import com.bernardomg.example.security.loader.EntitySaver;
import com.bernardomg.example.security.user.model.User;

public class PersistentUserEntitySaver implements EntitySaver<User> {

    private final JpaRepository<PersistentUser, Long> repository;

    public PersistentUserEntitySaver(
            JpaRepository<PersistentUser, Long> repository) {
        super();

        this.repository = repository;
    }

    @Override
    public void save(Iterable<User> entities) {
        final Collection<PersistentUser> persistentUsers;

        persistentUsers = StreamSupport.stream(entities.spliterator(), false)
            .map(this::toPersistentUser)
            .collect(Collectors.toList());

        repository.saveAll(persistentUsers);
    }

    private final PersistentUser toPersistentUser(final User user) {
        final PersistentUser persistent;

        persistent = new PersistentUser();
        BeanUtils.copyProperties(user, persistent);

        return persistent;
    }

}
