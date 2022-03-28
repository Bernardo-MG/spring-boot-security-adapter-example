
package com.bernardomg.example.security.ws.auth.validator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bernardomg.example.security.auth.validator.PrivilegeValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SpringSessionPrivilegeValidator
        implements PrivilegeValidator {

    @Override
    public final void checkPrivilege(final String privilege) {
        final Authentication auth;

        auth = SecurityContextHolder.getContext()
            .getAuthentication();

        if ((auth != null) && auth.getAuthorities()
            .stream()
            .noneMatch(a -> a.getAuthority()
                .equals(privilege))) {
            log.error("User in session has not the privilege {}", privilege);
        }
    }

}
