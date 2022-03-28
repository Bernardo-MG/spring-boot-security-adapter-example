
package com.bernardomg.example.security.extractor;

import java.util.Collection;

public interface EntityReader<T> {

    public Collection<T> readAll();

    public Boolean supports(final Class<?> clz);

}
