
package com.bernardomg.example.security.encoder;

public interface Encoder {

    public String encode(final String text);

    public Boolean matches(final String raw, final String encoded);

}
