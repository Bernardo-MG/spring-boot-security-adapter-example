
package com.bernardomg.example.security.datasource.db.login.service;

import java.util.Objects;
import java.util.Optional;

import com.bernardomg.example.security.auth.model.DefaultUser;
import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.data.repository.CrudRepository;
import com.bernardomg.example.security.login.LoginService;
import com.bernardomg.example.security.login.LoginValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PersistentLoginService implements LoginService {

    private final LoginValidator       loginValidator;

    private final CrudRepository<User> userRepository;

    public PersistentLoginService(final CrudRepository<User> userRepo,
            final LoginValidator loginVal) {
        super();

        userRepository = Objects.requireNonNull(userRepo);
        loginValidator = Objects.requireNonNull(loginVal);
    }

    @Override
    public final User login(final User user) {
        final Optional<? extends User> details;
        final User userSample;
        final Optional<User> result;
        final Boolean valid;

        log.debug("Attempting to log in {}", user.getUsername());

        userSample = new DefaultUser();
        userSample.setUsername(user.getUsername());

        details = userRepository.readOne(userSample);

        if (details.isEmpty()) {
            log.debug("No user found");
            result = Optional.empty();
        } else {
            log.debug("Applying validar {}", loginValidator);
            valid = loginValidator.valid(user, details.map(User.class::cast)
                .get());
            if (valid) {
                result = details.map(User.class::cast);
                log.debug("Found user for {}", user.getUsername());
            } else {
                log.debug("Password not matching");
                result = Optional.empty();
            }
        }

        return result.orElse(null);
    }

    @Override
    public final User logout(final User user) {
        final DefaultUser result;

        log.debug("Logging out {}", user.getUsername());

        result = new DefaultUser();
        result.setUsername(user.getUsername());

        return result;
    }

}
