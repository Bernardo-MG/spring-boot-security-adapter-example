
package com.bernardomg.example.security.datasource.db.auth.model.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardomg.example.security.adapter.user.service.UserService;
import com.bernardomg.example.security.auth.annotation.Authorized;
import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.datasource.db.auth.model.PersistentUser;

public final class PersistentUserService implements UserService {

    private final JpaRepository<PersistentUser, Long> repository;

    public PersistentUserService(
            final JpaRepository<PersistentUser, Long> repo) {
        super();

        repository = Objects.requireNonNull(repo);
    }

    @Override
    @Authorized("create")
    public User addUser(final User user) {
        final PersistentUser persistent;

        persistent = toPersistentUser(user);

        return repository.save(persistent);
    }

    @Override
    @Authorized("read")
    public final Iterable<PersistentUser> getUsers() {
        return repository.findAll();
    }

    @Override
    @Authorized("update")
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
