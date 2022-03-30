
package com.bernardomg.example.security.spring.properties;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;

import com.bernardomg.example.security.properties.PropertiesRegistry;
import com.bernardomg.example.security.properties.PropertiesRegistrySource;

public final class SpringEnvironmentPropertiesRegistrySource
        implements PropertiesRegistrySource {

    private final ConfigurableEnvironment environment;

    public SpringEnvironmentPropertiesRegistrySource(
            final ConfigurableEnvironment env) {
        super();

        environment = Objects.requireNonNull(env);
    }

    @Override
    public void load(final PropertiesRegistry registry) {
        environment.getPropertySources()
            .stream()
            .filter(EnumerablePropertySource.class::isInstance)
            .map(EnumerablePropertySource.class::cast)
            .forEach(s -> load(registry, s));
    }

    private final void load(final PropertiesRegistry registry,
            final EnumerablePropertySource<?> source) {
        Arrays.stream(source.getPropertyNames())
            .filter(n -> n.startsWith("security"))
            .forEach(n -> registry.setProperty(n,
                String.valueOf(source.getProperty(n))));
    }

}
