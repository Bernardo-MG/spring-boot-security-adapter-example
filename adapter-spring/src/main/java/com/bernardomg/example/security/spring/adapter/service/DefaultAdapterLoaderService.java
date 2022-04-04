
package com.bernardomg.example.security.spring.adapter.service;

import com.bernardomg.example.security.extractor.ModelExtractor;
import com.bernardomg.example.security.extractor.ModelLoaderProperties;

public final class DefaultAdapterLoaderService implements AdapterLoaderService {

    private final ModelExtractor extractor;

    public DefaultAdapterLoaderService(final ModelExtractor extractor) {
        super();

        this.extractor = extractor;
    }

    @Override
    public void load(final ModelLoaderProperties properties) {
        extractor.extract(properties);
    }

}
