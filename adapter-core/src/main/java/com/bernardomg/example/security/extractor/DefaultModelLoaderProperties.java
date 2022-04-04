
package com.bernardomg.example.security.extractor;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public final class DefaultModelLoaderProperties
        implements ModelLoaderProperties {

    private String              source;

    private Map<String, String> properties = new HashMap<>();

}
