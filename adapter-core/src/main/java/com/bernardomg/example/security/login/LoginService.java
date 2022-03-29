
package com.bernardomg.example.security.login;

import com.bernardomg.example.security.auth.model.User;

public interface LoginService {

    public User login(final User user);

    public User logout(final User user);

}
