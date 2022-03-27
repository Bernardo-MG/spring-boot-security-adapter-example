
package com.bernardomg.example.security.ws.adapter.service;

import com.bernardomg.example.security.loader.EntityLoader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DefaultAdapterLoaderService implements AdapterLoaderService {

    private final EntityLoader userLoader;

    public DefaultAdapterLoaderService(final EntityLoader userLoader) {
        super();

        this.userLoader = userLoader;
    }

    @Override
    public void loadAll() {
        log.debug("Loading users");
        userLoader.load();
    }

}
