
package com.bernardomg.example.security.encoder;

public final class DisabledEncoder implements Encoder {

    @Override
    public final String encode(final String text) {
        return text;
    }

    @Override
    public final Boolean matches(final String raw, final String encoded) {
        return true;
    }

}
