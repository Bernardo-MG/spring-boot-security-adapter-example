
package com.bernardomg.example.security.adapter.user.service;

import com.bernardomg.example.security.adapter.user.model.User;

public interface UserService {

    public Iterable<User> getUsers();

    public User addUser(final User user);

    public User updateUser(final User user);

}
