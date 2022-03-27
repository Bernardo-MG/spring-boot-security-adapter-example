
package com.bernardomg.example.security.adapter.user.service;

import com.bernardomg.example.security.user.model.User;

public interface UserService {

    public User addUser(final User user);

    public Iterable<? extends User> getUsers();

    public User updateUser(final User user);

}
