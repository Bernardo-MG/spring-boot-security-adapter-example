
package com.bernardomg.example.security.datasource.db.data.repository;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardomg.example.security.data.repository.CrudRepository;

public final class SpringCrudRepository<T, P extends T, ID>
        implements CrudRepository<T> {

    private final Supplier<P>          persistentProvider;

    private final JpaRepository<P, ID> wrapped;

    public SpringCrudRepository(final JpaRepository<P, ID> wrapped,
            final Supplier<P> persistentProvider) {
        super();

        this.wrapped = wrapped;
        this.persistentProvider = persistentProvider;
    }

    @Override
    public void delete(final T data) {
        final P persistent;

        persistent = persistentProvider.get();
        BeanUtils.copyProperties(data, persistent);

        wrapped.delete(persistent);
    }

    @Override
    public Iterable<? extends T> readAll() {
        return wrapped.findAll();
    }

    @Override
    public Optional<? extends T> readOne(final T sample) {
        final Example<P> example;
        final P persistent;

        persistent = persistentProvider.get();
        BeanUtils.copyProperties(sample, persistent);

        example = Example.of(persistent);

        return wrapped.findOne(example);
    }

    @Override
    public T save(final T data) {
        final P persistent;

        persistent = persistentProvider.get();
        BeanUtils.copyProperties(data, persistent);

        return wrapped.save(persistent);
    }

}
