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

package com.bernardomg.example.oauth.security.adapter.embedded.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class EmbeddedSecurityAdapterSecurityConfig
        extends WebSecurityConfigurerAdapter {

    public EmbeddedSecurityAdapterSecurityConfig() {
        super();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
            throws Exception {

        auth.inMemoryAuthentication()
            .withUser("test-user")
            .password("{noop}1234")
            .authorities("read")
            .and()
            .withUser("admin")
            .password("{noop}password")
            .authorities("read", "write");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            // HTTP Basic authentication
            .httpBasic()
            .and()
            .authorizeRequests()
            // Sets authority required for GET requests
            .antMatchers(HttpMethod.GET, "/rest/**")
            .hasAuthority("read")
            // Sets authority required for POST requests
            .antMatchers(HttpMethod.POST, "/rest")
            .hasAuthority("write")
            .and()
            .csrf()
            .disable()
            .formLogin()
            .disable();
    }

}
