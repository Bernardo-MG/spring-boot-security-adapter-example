
package com.bernardomg.example.security.adapter.keycloak.client;

import com.bernardomg.example.security.adapter.keycloak.client.model.KeycloakUser;
import com.bernardomg.example.security.adapter.keycloak.client.model.KeycloakUserTokenDetails;

public interface KeycloakApiClient {

    public Iterable<KeycloakUser> getAllUsers();

    public String getUser(final String username);

    public KeycloakUserTokenDetails login(final String username,
            final String password);

    public String logout(final String username, final String refreshToken);

}
