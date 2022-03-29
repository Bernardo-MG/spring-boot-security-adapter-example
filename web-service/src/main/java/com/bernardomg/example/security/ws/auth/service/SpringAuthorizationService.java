
package com.bernardomg.example.security.ws.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bernardomg.example.security.auth.exception.UnauthorizedException;
import com.bernardomg.example.security.auth.service.AuthorizationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SpringAuthorizationService implements AuthorizationService {

    public SpringAuthorizationService() {
        super();
    }

    @Override
    public final void checkPrivilege(final String privilege) {
        final Authentication auth;
        final Boolean valid;

        auth = SecurityContextHolder.getContext()
            .getAuthentication();

        if (auth != null) {
            valid = auth.getAuthorities()
                .stream()
                .noneMatch(a -> a.getAuthority()
                    .equals(privilege));
        } else {
            valid = false;
        }

        log.error("Checked user in session {} for privilege {}: {}",
            auth.getName(), privilege, valid);

        if (!valid) {
            // TODO: add message
            throw new UnauthorizedException();
        }
    }

}
