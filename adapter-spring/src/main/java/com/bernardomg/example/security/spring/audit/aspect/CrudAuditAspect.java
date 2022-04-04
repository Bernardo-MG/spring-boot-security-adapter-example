
package com.bernardomg.example.security.spring.audit.aspect;

import java.time.Instant;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bernardomg.example.security.audit.model.DefaultAuditEvent;
import com.bernardomg.example.security.audit.service.AuditDataService;

import lombok.extern.slf4j.Slf4j;

/**
 * Logging aspect for services. Will log arguments and returned values.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Slf4j
@Aspect
public class CrudAuditAspect {

    private final AuditDataService service;

    /**
     * Default constructor.
     */
    public CrudAuditAspect(final AuditDataService srv) {
        super();

        service = Objects.requireNonNull(srv);
    }

    @Before(value = "execution(* com.bernardomg.example..*CrudRepository*.*(..))",
            argNames = "joinPoint")
    public void beforeCall(final JoinPoint joinPoint) {
        final String methodName;
        final String type;
        final DefaultAuditEvent event;
        final Authentication authentication;

        log.trace("Calling {} with arguments {}", joinPoint.getSignature()
            .toShortString(), joinPoint.getArgs());

        methodName = joinPoint.getSignature()
            .toShortString()
            .toLowerCase();
        if (methodName.contains("create")) {
            type = "CREATE_DATA";
        } else if (methodName.contains("read")) {
            type = "READ_DATA";
        } else if (methodName.contains("update")) {
            type = "UPDATE_DATA";
        } else if (methodName.contains("delete")) {
            type = "DELETE_DATA";
        } else {
            type = "";
        }

        if (!type.isBlank()) {
            authentication = SecurityContextHolder.getContext()
                .getAuthentication();

            event = new DefaultAuditEvent();
            if (authentication != null) {
                event.setAuthor(authentication.getName());
            }
            // event.setData(null);
            event.setTimestamp(Instant.now());
            event.setType(type);

            service.addAuditEvent(event);
        }
    }

}
