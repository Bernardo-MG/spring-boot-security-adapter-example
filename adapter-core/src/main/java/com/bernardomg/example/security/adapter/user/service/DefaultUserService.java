
package com.bernardomg.example.security.adapter.user.service;

import java.util.Objects;

import com.bernardomg.example.security.adapter.user.model.User;
import com.bernardomg.example.security.adapter.user.repository.UserRepository;

public final class DefaultUserService implements UserService {

    private final UserRepository repository;

    public DefaultUserService(final UserRepository repo) {
        super();

        repository = Objects.requireNonNull(repo);
    }

    @Override
    public final Iterable<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public User addUser(User user) {
        return repository.create(user);
    }

    @Override
    public User updateUser(User user) {
        return repository.update(user);
    }

}
