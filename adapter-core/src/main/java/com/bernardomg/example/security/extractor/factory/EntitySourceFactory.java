
package com.bernardomg.example.security.extractor.factory;

import com.bernardomg.example.security.extractor.EntitySource;
import com.bernardomg.example.security.extractor.ModelExtractorConfiguration;

public interface EntitySourceFactory {

    public EntitySource connect(final ModelExtractorConfiguration config);

}
