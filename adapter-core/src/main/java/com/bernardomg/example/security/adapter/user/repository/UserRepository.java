
package com.bernardomg.example.security.adapter.user.repository;

import com.bernardomg.example.security.adapter.user.model.User;

public interface UserRepository {

    public User create(final User user);

    public Iterable<User> findAll();

    public User update(final User user);

}
