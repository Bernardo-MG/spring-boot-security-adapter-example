
package com.bernardomg.example.security.adapter.user.service;

import com.bernardomg.example.security.auth.annotation.Authorized;
import com.bernardomg.example.security.auth.model.User;

public interface UserService {

    @Authorized("create")
    public User addUser(final User user);

    @Authorized("read")
    public Iterable<? extends User> getUsers();

    @Authorized("update")
    public User updateUser(final User user);

}
