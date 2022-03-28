
package com.bernardomg.example.security.extractor.factory;

import com.bernardomg.example.security.extractor.EntitySource;
import com.bernardomg.example.security.extractor.ModelExtractorConfiguration;

public interface EntitySourceBuilder {

    public EntitySource build(final ModelExtractorConfiguration config);

    public String getSourceName();

}
