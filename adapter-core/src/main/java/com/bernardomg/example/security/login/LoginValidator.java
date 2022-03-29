
package com.bernardomg.example.security.login;

import com.bernardomg.example.security.auth.model.User;

public interface LoginValidator {

    public Boolean valid(final User received, final User existing);

}
