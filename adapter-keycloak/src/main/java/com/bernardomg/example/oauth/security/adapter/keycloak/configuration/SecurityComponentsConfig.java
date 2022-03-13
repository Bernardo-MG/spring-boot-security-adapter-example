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

package com.bernardomg.example.oauth.security.adapter.keycloak.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bernardomg.example.oauth.security.adapter.keycloak.repository.KeycloakUserRepository;
import com.bernardomg.example.security.adapter.user.repository.UserRepository;

@Configuration
@ConditionalOnProperty(value = "security.type", havingValue = "oauth",
        matchIfMissing = false)
public class SecurityComponentsConfig {

    public SecurityComponentsConfig() {
        super();
    }

    @Bean("userRepository")
    public UserRepository getUserRepository(
            @Value("${security.server.url}") String url,
            @Value("${security.realm}") String rlm,
            @Value("${security.clientId}") String cltId,
            @Value("${security.admin.username}") String user,
            @Value("${security.admin.password}") String pass,
            @Value("${security.admin.realm}") String userRlm) {
        return new KeycloakUserRepository(url, rlm, cltId, user, pass, userRlm);
    }

}
