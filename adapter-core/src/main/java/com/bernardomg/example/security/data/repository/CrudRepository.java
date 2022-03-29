
package com.bernardomg.example.security.data.repository;

import java.util.Optional;

public interface CrudRepository<T> {

    public void delete(final T data);

    public Iterable<? extends T> findAll();

    public Optional<? extends T> findOne(final T sample);

    public T save(final T data);

}
