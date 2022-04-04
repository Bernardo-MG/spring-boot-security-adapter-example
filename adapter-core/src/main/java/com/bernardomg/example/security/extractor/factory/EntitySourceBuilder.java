
package com.bernardomg.example.security.extractor.factory;

import com.bernardomg.example.security.extractor.EntitySource;
import com.bernardomg.example.security.extractor.ModelLoaderProperties;

public interface EntitySourceBuilder {

    public EntitySource build(final ModelLoaderProperties properties);

    public String getSourceName();

}
