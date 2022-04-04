
package com.bernardomg.example.security.auth.service;

import java.util.Objects;

import com.bernardomg.example.security.auth.model.Resource;
import com.bernardomg.example.security.data.repository.CrudRepository;

public final class DefaultResourceDataService implements ResourceDataService {

    private final CrudRepository<Resource> repository;

    public DefaultResourceDataService(final CrudRepository<Resource> repo) {
        super();

        repository = Objects.requireNonNull(repo);
    }

    @Override
    public final Resource addResource(final Resource resource) {
        return repository.save(resource);
    }

    @Override
    public final Resource createResource(final Resource resource) {
        return repository.save(resource);
    }

    @Override
    public final void deleteResource(final Resource resource) {
        repository.delete(resource);
    }

    @Override
    public final Iterable<? extends Resource> getResources() {
        return repository.readAll();
    }

    @Override
    public final Resource updateResource(final Resource resource) {
        return repository.save(resource);
    }

}
