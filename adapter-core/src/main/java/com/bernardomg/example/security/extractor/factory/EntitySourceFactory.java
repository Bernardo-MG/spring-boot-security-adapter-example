
package com.bernardomg.example.security.extractor.factory;

import com.bernardomg.example.security.extractor.EntitySource;
import com.bernardomg.example.security.extractor.ModelLoaderProperties;

public interface EntitySourceFactory {

    public EntitySource connect(final ModelLoaderProperties properties);

}
