
package com.bernardomg.example.security.adapter.keycloak.extractor;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.bernardomg.example.security.adapter.keycloak.client.KeycloakApiClient;
import com.bernardomg.example.security.adapter.keycloak.client.model.KeycloakUser;
import com.bernardomg.example.security.auth.model.DefaultUser;
import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.extractor.EntityReader;

public final class KeycloakUserReader implements EntityReader<User> {

    private final KeycloakApiClient client;

    public KeycloakUserReader(final KeycloakApiClient client) {
        super();

        this.client = client;
    }

    @Override
    public final Collection<User> readAll() {
        final Iterable<KeycloakUser> users;

        users = client.getAllUsers();

        return StreamSupport.stream(users.spliterator(), false)
            .map(this::toUser)
            .collect(Collectors.toList());
    }

    @Override
    public final Boolean supports(final Class<?> clz) {
        return clz.isAssignableFrom(User.class);
    }

    private final User toUser(final KeycloakUser kuser) {
        final User user;

        user = new DefaultUser();
        user.setUsername(kuser.getUsername());
        user.setEmail(kuser.getEmail());
        user.setEnabled(kuser.getEnabled());

        return user;
    }

}
