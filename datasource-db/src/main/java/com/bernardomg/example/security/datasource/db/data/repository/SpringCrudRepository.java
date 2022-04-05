
package com.bernardomg.example.security.datasource.db.data.repository;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public final void delete(final T data) {
        final P persistent;

        persistent = toPersistent(data);

        wrapped.delete(persistent);
    }

    @Override
    public final Iterable<? extends T> readAll() {
        return wrapped.findAll();
    }

    @Override
    public final Optional<? extends T> readOne(final T sample) {
        final Example<P> example;
        final P persistent;

        persistent = toPersistent(sample);

        example = Example.of(persistent);

        return wrapped.findOne(example);
    }

    @Override
    public final T save(final T data) {
        final P persistent;

        persistent = toPersistent(data);

        return wrapped.save(persistent);
    }

    @Override
    public final Iterable<? extends T> saveAll(Iterable<T> data) {
        final Iterable<P> persistent;

        persistent = StreamSupport.stream(data.spliterator(), false)
            .map(this::toPersistent)
            .collect(Collectors.toList());

        return wrapped.saveAll(persistent);
    }

    private final P toPersistent(final T data) {
        final P persistent;

        persistent = persistentProvider.get();
        BeanUtils.copyProperties(data, persistent);

        return persistent;
    }

}
