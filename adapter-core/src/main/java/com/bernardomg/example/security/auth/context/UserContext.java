
package com.bernardomg.example.security.auth.context;

public interface UserContext {

    public Boolean hasUserInSessionPrivilege(final String privilege);

}
