
package com.bernardomg.example.security.datasource.db.login.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardomg.example.security.auth.model.DefaultUser;
import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.datasource.db.auth.model.PersistentUser;
import com.bernardomg.example.security.encoder.Encoder;
import com.bernardomg.example.security.login.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PersistentLoginService implements LoginService {

    private final Encoder                             passwordEncoder;

    private final JpaRepository<PersistentUser, Long> userRepository;

    public PersistentLoginService(
            final JpaRepository<PersistentUser, Long> userRepo,
            final Encoder encoder) {
        super();

        userRepository = Objects.requireNonNull(userRepo);
        passwordEncoder = Objects.requireNonNull(encoder);
    }

    @Override
    public final User login(final User user) {
        final Optional<? extends User> details;
        final PersistentUser userSample;
        final Optional<User> result;
        final Boolean logged;
        final Example<PersistentUser> example;

        log.debug("Attempting to log in {}", user.getUsername());

        userSample = new PersistentUser();
        userSample.setUsername(user.getUsername());

        example = Example.of(userSample);

        details = userRepository.findOne(example);

        if (details.isEmpty()) {
            log.debug("No user found");
            result = Optional.empty();
        } else {
            logged = passwordEncoder.matches(user.getPassword(), details.get()
                .getPassword());
            if (logged) {
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
