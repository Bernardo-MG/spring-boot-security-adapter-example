
package com.bernardomg.example.security.auth.service;

import java.util.Objects;
import java.util.Optional;

import com.bernardomg.example.security.auth.model.DefaultRole;
import com.bernardomg.example.security.auth.model.DefaultUser;
import com.bernardomg.example.security.auth.model.Role;
import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.data.repository.CrudRepository;
import com.bernardomg.example.security.encoder.Encoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DefaultUserDataService implements UserDataService {

    /**
     * Password encoder.
     */
    private final Encoder              passwordEncoder;

    private final CrudRepository<Role> roleDataStore;

    private final CrudRepository<User> userDataStore;

    public DefaultUserDataService(final CrudRepository<User> userStore,
            final CrudRepository<Role> roleRepo, final Encoder encoder) {
        super();

        roleDataStore = Objects.requireNonNull(roleRepo);
        userDataStore = Objects.requireNonNull(userStore);
        passwordEncoder = Objects.requireNonNull(encoder);
    }

    @Override
    public final void addRoleToUser(final String username,
            final String rolename) {
        final Optional<? extends User> foundUser;
        final Optional<? extends Role> foundRole;
        final User user;
        final Role role;
        final DefaultUser userSample;
        final DefaultRole roleSample;

        userSample = new DefaultUser();
        userSample.setUsername(username);
        foundUser = userDataStore.readOne(userSample);
        if (foundUser.isPresent()) {
            roleSample = new DefaultRole();
            roleSample.setName(rolename);
            foundRole = roleDataStore.readOne(roleSample);
            if (foundRole.isPresent()) {
                user = foundUser.get();
                role = foundRole.get();
                user.addRole(role);
                userDataStore.save(user);
            } else {
                log.error("No role found for name {}", rolename);
            }
        } else {
            log.error("No user found for username {}", username);
        }

    }

    @Override
    public final User createUser(final User user) {
        final String encodedPassword;

        if (user.getPassword() != null) {
            // Password is encoded
            encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }

        return userDataStore.save(user);
    }

    @Override
    public final void deleteUser(final User user) {
        userDataStore.delete(user);
    }

    @Override
    public final Iterable<? extends User> getUsers() {
        return userDataStore.readAll();
    }

    @Override
    public final User updateUser(final User user) {
        return userDataStore.save(user);
    }

}
