
package com.bernardomg.example.security.auth.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

/**
 * Logging aspect for services. Will log arguments and returned values.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Slf4j
@Aspect
public class AuthorizedAspect {

    /**
     * Default constructor.
     */
    public AuthorizedAspect() {
        super();
    }

    @Pointcut("@within(es.enviraiot.dahs.security.access.annotation.Authorized)")
    public void annotatedClass() {}

    @Pointcut("@annotation(es.enviraiot.dahs.security.access.annotation.Authorized)")
    public void annotatedMethod() {}

    @Before("execution(* *(..)) && (annotatedMethod() || annotatedClass())")
    public void beforeCall(final JoinPoint joinPoint) {
        log.trace("Calling {} with arguments {}", joinPoint.getSignature()
            .toShortString(), joinPoint.getArgs());
    }

}
