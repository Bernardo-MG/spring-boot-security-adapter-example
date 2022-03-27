
package com.bernardomg.example.security.loader;

public interface EntityReader<T> {

    public Iterable<T> readAll();

}
