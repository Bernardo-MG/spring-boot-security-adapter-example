
package com.bernardomg.example.security.auth.service;

import com.bernardomg.example.security.auth.model.Resource;

public interface ResourceDataService {

    public Resource addResource(final Resource resource);

    public Resource createResource(final Resource resource);

    public void deleteResource(final Resource resource);

    public Iterable<? extends Resource> getResources();

    public Resource updateResource(final Resource resource);

}
