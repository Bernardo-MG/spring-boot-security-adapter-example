
package com.bernardomg.example.security.properties;

import java.util.Map;

public interface PropertiesRegistry {

    public Boolean containsKey(final String key);

    public Map<String, Object> getProperties();

    public Object getProperty(final String key);

    public void setProperty(final String key, final Object value);

}
