
package com.bernardomg.example.security.ws.adapter.service;

import com.bernardomg.example.security.extractor.ModelExtractor;
import com.bernardomg.example.security.extractor.ModelExtractorConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DefaultAdapterLoaderService implements AdapterLoaderService {

    private final ModelExtractorConfiguration config;

    private final ModelExtractor              extractor;

    public DefaultAdapterLoaderService(final ModelExtractor extractor,
            final ModelExtractorConfiguration config) {
        super();

        this.extractor = extractor;
        this.config = config;
    }

    @Override
    public void loadAll() {
        log.debug("Loading users");
        extractor.extract(config);
    }

}
