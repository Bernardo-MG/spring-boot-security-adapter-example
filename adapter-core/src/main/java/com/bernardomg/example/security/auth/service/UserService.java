
package com.bernardomg.example.security.auth.service;

import com.bernardomg.example.security.auth.annotation.Authorized;
import com.bernardomg.example.security.auth.model.User;

public interface UserService {

    public void addRoleToUser(final String username, final String role);

    @Authorized("create")
    public User createUser(final User user);

    public void deleteUser(final User user);

    @Authorized("read")
    public Iterable<? extends User> getUsers();

    @Authorized("update")
    public User updateUser(final User user);

}
