
package com.bernardomg.example.security.data.repository;

import java.util.Optional;

public interface CrudRepository<T> {

    public void delete(final T data);

    public Iterable<? extends T> readAll();

    public Optional<? extends T> readOne(final T sample);

    public T save(final T data);

    public Iterable<? extends T> saveAll(final Iterable<T> data);

}
