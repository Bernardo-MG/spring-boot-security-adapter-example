
package com.bernardomg.example.security.adapter.embedded.repository;

import java.util.Collections;

import com.bernardomg.example.security.adapter.user.model.User;
import com.bernardomg.example.security.adapter.user.repository.UserRepository;

public final class EmbeddedUserRepository implements UserRepository {

    public EmbeddedUserRepository() {
        super();
    }

    @Override
    public final User create(final User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Iterable<User> findAll() {
        return Collections.emptyList();
    }

    @Override
    public final User update(final User user) {
        // TODO Auto-generated method stub
        return null;
    }

}
