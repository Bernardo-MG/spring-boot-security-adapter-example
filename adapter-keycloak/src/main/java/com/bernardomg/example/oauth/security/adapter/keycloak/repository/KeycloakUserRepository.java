
package com.bernardomg.example.oauth.security.adapter.keycloak.repository;

import java.util.Objects;
import java.util.stream.Collectors;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

import com.bernardomg.example.security.adapter.user.model.DefaultUser;
import com.bernardomg.example.security.adapter.user.model.User;
import com.bernardomg.example.security.adapter.user.repository.UserRepository;

public final class KeycloakUserRepository implements UserRepository {

    private final String clientId;

    private final String password;

    private final String realm;

    private final String serverURL;

    private final String userName;

    private final String userRealm;

    public KeycloakUserRepository(final String url, final String rlm,
            final String cltId, final String user, final String pass,
            final String userRlm) {
        super();

        serverURL = Objects.requireNonNull(url);
        realm = Objects.requireNonNull(rlm);
        clientId = Objects.requireNonNull(cltId);
        userName = Objects.requireNonNull(user);
        password = Objects.requireNonNull(pass);
        userRealm = Objects.requireNonNull(userRlm);
    }

    @Override
    public final User create(final User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Iterable<User> findAll() {
        Keycloak keycloak;
        RealmResource realmResource;
        UsersResource userResource;

        keycloak = KeycloakBuilder.builder()
            .serverUrl(serverURL)
            .grantType(OAuth2Constants.PASSWORD)
            .realm(userRealm)
            .username(userName)
            .password(password)
            .clientId(clientId)
            .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10)
                .build())
            .build();

        realmResource = keycloak.realm(realm);
        userResource = realmResource.users();

        return userResource.list()
            .stream()
            .map(this::toUser)
            .collect(Collectors.toList());
    }

    @Override
    public final User update(final User user) {
        // TODO Auto-generated method stub
        return null;
    }

    private final User toUser(final UserRepresentation representation) {
        final User user;

        user = new DefaultUser();
        user.setUsername(representation.getUsername());

        return user;
    }

}
