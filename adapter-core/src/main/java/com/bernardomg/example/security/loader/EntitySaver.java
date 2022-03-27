
package com.bernardomg.example.security.loader;

public interface EntitySaver<T> {

    public void save(final Iterable<T> entities);

}
