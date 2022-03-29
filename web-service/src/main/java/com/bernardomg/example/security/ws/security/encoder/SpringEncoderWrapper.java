
package com.bernardomg.example.security.ws.security.encoder;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.bernardomg.example.security.encoder.Encoder;

public final class SpringEncoderWrapper implements Encoder {

    private final PasswordEncoder passwordEncoder;

    public SpringEncoderWrapper(final PasswordEncoder wrapped) {
        super();

        passwordEncoder = Objects.requireNonNull(wrapped);
    }

    @Override
    public final String encode(final String text) {
        return passwordEncoder.encode(text);
    }

    @Override
    public final Boolean matches(final String raw, final String encoded) {
        return passwordEncoder.matches(raw, encoded);
    }

}
