
package com.bernardomg.example.security.login;

import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.encoder.Encoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PasswordLoginValidator implements LoginValidator {

    private final Encoder passwordEncoder;

    public PasswordLoginValidator(final Encoder passwordEncoder) {
        super();

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public final Boolean valid(final User received, final User existing) {
        log.debug("Received: {}", received.getPassword());
        log.debug("Existing: {}", existing.getPassword());

        log.debug("Encoded received: {}",
            passwordEncoder.encode(received.getPassword()));
        log.debug("Match: {}", passwordEncoder.matches(received.getPassword(),
            existing.getPassword()));

        return passwordEncoder.matches(received.getPassword(),
            existing.getPassword());
    }

}
