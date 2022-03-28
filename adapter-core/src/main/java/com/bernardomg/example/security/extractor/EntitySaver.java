
package com.bernardomg.example.security.extractor;

public interface EntitySaver<T> {

    public void save(final Iterable<T> entities);

    public Class<T> supports();

}
