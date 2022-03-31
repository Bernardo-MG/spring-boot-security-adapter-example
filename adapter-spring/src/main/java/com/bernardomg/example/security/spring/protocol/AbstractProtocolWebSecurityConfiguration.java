/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2021 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.security.spring.protocol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;

@Configuration
public abstract class AbstractProtocolWebSecurityConfiguration
        extends WebSecurityConfigurerAdapter {

    private final Collection<String> allowedUrls = new ArrayList<>();

    public AbstractProtocolWebSecurityConfiguration() {
        super();

        allowedUrls.add("/actuator/**");
        allowedUrls.add("/login/**");
    }

    @Value("${security.url.allowed:}")
    public void loadAllowedUrls(final String urls) {

        if (!urls.isBlank()) {
            allowedUrls.addAll(Arrays.asList(urls.split(",")));
        }

    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        final Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry> authorizeRequestsCustomizer;
        final Customizer<FormLoginConfigurer<HttpSecurity>> formLoginCustomizer;
        final Customizer<LogoutConfigurer<HttpSecurity>> logoutCustomizer;

        // Authorization
        authorizeRequestsCustomizer = requestAuthCustomizer();
        // Login form
        formLoginCustomizer = formLoginCustomizer();
        // Logout
        logoutCustomizer = logoutCustomizer();

        http.csrf()
            .disable()
            .cors()
            .and()
            .authorizeRequests(authorizeRequestsCustomizer)
            .formLogin(formLoginCustomizer)
            .logout(logoutCustomizer);
    }

    protected Customizer<FormLoginConfigurer<HttpSecurity>>
            formLoginCustomizer() {
        return c -> c.disable();
    }

    protected Customizer<LogoutConfigurer<HttpSecurity>> logoutCustomizer() {
        return c -> c.disable();
    }

    protected void requestAuthConfigurer(
            final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry c) {
        if (!allowedUrls.isEmpty()) {
            c.antMatchers(allowedUrls.toArray(new String[allowedUrls.size()]))
                .permitAll();
        }
    }

    protected
            Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry>
            requestAuthCustomizer() {
        return this::requestAuthConfigurer;
    }

}
