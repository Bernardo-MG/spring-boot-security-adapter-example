
package com.bernardomg.example.security.login;

import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.encoder.Encoder;

public final class PasswordLoginValidator implements LoginValidator {

    private final Encoder passwordEncoder;

    public PasswordLoginValidator(final Encoder passwordEncoder) {
        super();

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public final Boolean valid(final User received, final User existing) {
        return passwordEncoder.matches(received.getPassword(),
            existing.getPassword());
    }

}
