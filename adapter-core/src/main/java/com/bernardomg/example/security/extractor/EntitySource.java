
package com.bernardomg.example.security.extractor;

import java.util.Collection;

public interface EntitySource {

    public <T> Collection<T> read(final Class<T> clz);

}
