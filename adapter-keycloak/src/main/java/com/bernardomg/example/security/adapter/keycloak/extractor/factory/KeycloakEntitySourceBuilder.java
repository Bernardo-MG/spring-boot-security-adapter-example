
package com.bernardomg.example.security.adapter.keycloak.extractor.factory;

import java.util.ArrayList;
import java.util.Collection;

import com.bernardomg.example.security.adapter.keycloak.client.KeycloakApiClient;
import com.bernardomg.example.security.adapter.keycloak.client.RestTemplateKeycloakApiClient;
import com.bernardomg.example.security.adapter.keycloak.extractor.KeycloakUserReader;
import com.bernardomg.example.security.extractor.DefaultEntitySource;
import com.bernardomg.example.security.extractor.EntityReader;
import com.bernardomg.example.security.extractor.EntitySource;
import com.bernardomg.example.security.extractor.ModelExtractorConfiguration;
import com.bernardomg.example.security.extractor.factory.EntitySourceBuilder;

public final class KeycloakEntitySourceBuilder implements EntitySourceBuilder {

    public KeycloakEntitySourceBuilder() {
        super();
    }

    @Override
    public final EntitySource build(final ModelExtractorConfiguration config) {
        final KeycloakApiClient client;
        final Collection<EntityReader<?>> readers;

        client = new RestTemplateKeycloakApiClient(
            config.getProperty("security.admin.clientId"),
            config.getProperty("security.admin.username"),
            config.getProperty("security.admin.password"),
            config.getProperty("security.admin.realm"),
            config.getProperty("security.clientId"),
            config.getProperty("security.endpoint"),
            config.getProperty("security.realm"));

        readers = new ArrayList<>();
        readers.add(new KeycloakUserReader(client));

        return new DefaultEntitySource(readers);
    }

    @Override
    public final String getSourceName() {
        return "keycloak";
    }

}
