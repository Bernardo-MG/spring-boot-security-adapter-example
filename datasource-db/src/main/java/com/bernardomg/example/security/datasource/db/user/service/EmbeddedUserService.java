
package com.bernardomg.example.security.datasource.db.user.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardomg.example.security.adapter.user.service.UserService;
import com.bernardomg.example.security.datasource.db.user.model.PersistentUser;
import com.bernardomg.example.security.user.model.User;

public final class EmbeddedUserService implements UserService {

    private final JpaRepository<PersistentUser, Long> repository;

    public EmbeddedUserService(final JpaRepository<PersistentUser, Long> repo) {
        super();

        repository = Objects.requireNonNull(repo);
    }

    @Override
    public User addUser(final User user) {
        final PersistentUser persistent;

        persistent = toPersistentUser(user);

        return repository.save(persistent);
    }

    @Override
    public final Iterable<PersistentUser> getUsers() {
        return repository.findAll();
    }

    @Override
    public User updateUser(final User user) {
        final PersistentUser persistent;

        persistent = toPersistentUser(user);

        return repository.save(persistent);
    }

    private final PersistentUser toPersistentUser(final User user) {
        final PersistentUser persistent;

        persistent = new PersistentUser();
        BeanUtils.copyProperties(user, persistent);

        return persistent;
    }

}
