
package com.bernardomg.example.security.ws.adapter.service;

import com.bernardomg.example.security.loader.ModelLoader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DefaultAdapterLoaderService implements AdapterLoaderService {

    private final ModelLoader userLoader;

    public DefaultAdapterLoaderService(final ModelLoader userLoader) {
        super();

        this.userLoader = userLoader;
    }

    @Override
    public void loadAll() {
        log.debug("Loading users");
        userLoader.load();
    }

}
