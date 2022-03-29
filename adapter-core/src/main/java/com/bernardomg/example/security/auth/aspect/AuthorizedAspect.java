
package com.bernardomg.example.security.auth.aspect;

import java.lang.reflect.Method;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.bernardomg.example.security.auth.annotation.Authorized;
import com.bernardomg.example.security.auth.service.AuthorizationService;

import lombok.extern.slf4j.Slf4j;

/**
 * Logging aspect for services. Will log arguments and returned values.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Slf4j
@Aspect
public class AuthorizedAspect {

    private final AuthorizationService service;

    /**
     * Default constructor.
     */
    public AuthorizedAspect(final AuthorizationService srv) {
        super();

        service = Objects.requireNonNull(srv);
    }

    @Pointcut("@within(com.bernardomg.example.security.auth.annotation.Authorized)")
    public void annotatedClass() {}

    @Pointcut("@annotation(com.bernardomg.example.security.auth.annotation.Authorized)")
    public void annotatedMethod() {}

    @Before("execution(* *(..)) && (annotatedMethod() || annotatedClass())")
    public void beforeAnnotation(final JoinPoint joinPoint) {
        final MethodSignature signature;
        final Method method;
        final Authorized annotation;

        signature = (MethodSignature) joinPoint.getSignature();
        method = signature.getMethod();

        log.debug("Calling {} with arguments {}", joinPoint.getSignature()
            .toShortString(), joinPoint.getArgs());

        annotation = method.getAnnotation(Authorized.class);
        if (annotation != null) {
            log.debug("Privilege: {}", annotation.value());

            service.checkPrivilege(annotation.value());
        } else {
            log.warn("Could not find annotation in {}",
                joinPoint.getSignature());
        }
    }

}
