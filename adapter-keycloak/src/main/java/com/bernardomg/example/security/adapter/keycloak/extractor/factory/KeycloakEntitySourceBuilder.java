
package com.bernardomg.example.security.adapter.keycloak.extractor.factory;

import java.util.ArrayList;
import java.util.Collection;

import com.bernardomg.example.security.adapter.keycloak.client.KeycloakApiClient;
import com.bernardomg.example.security.adapter.keycloak.client.RestTemplateKeycloakApiClient;
import com.bernardomg.example.security.adapter.keycloak.extractor.KeycloakUserReader;
import com.bernardomg.example.security.extractor.DefaultEntitySource;
import com.bernardomg.example.security.extractor.EntityReader;
import com.bernardomg.example.security.extractor.EntitySource;
import com.bernardomg.example.security.extractor.ModelLoaderProperties;
import com.bernardomg.example.security.extractor.factory.EntitySourceBuilder;

public final class KeycloakEntitySourceBuilder implements EntitySourceBuilder {

    public KeycloakEntitySourceBuilder() {
        super();
    }

    @Override
    public final EntitySource build(final ModelLoaderProperties properties) {
        final KeycloakApiClient client;
        final Collection<EntityReader<?>> readers;

        client = new RestTemplateKeycloakApiClient(properties.getProperties()
            .get("dahs.security.admin.clientId"),
            properties.getProperties()
                .get("dahs.security.admin.username"),
            properties.getProperties()
                .get("dahs.security.admin.password"),
            properties.getProperties()
                .get("dahs.security.admin.realm"),
            properties.getProperties()
                .get("dahs.security.clientId"),
            properties.getProperties()
                .get("dahs.security.endpoint"),
            properties.getProperties()
                .get("dahs.security.realm"));

        readers = new ArrayList<>();
        readers.add(new KeycloakUserReader(client));

        return new DefaultEntitySource(readers);
    }

    @Override
    public final String getSourceName() {
        return "keycloak";
    }

}
