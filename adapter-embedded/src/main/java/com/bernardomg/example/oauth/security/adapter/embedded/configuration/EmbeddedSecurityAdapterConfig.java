
package com.bernardomg.example.oauth.security.adapter.embedded.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.bernardomg.example.oauth.security.adapter.embedded.repository.EmbeddedUserRepository;
import com.bernardomg.example.security.adapter.user.repository.UserRepository;

@Configuration
@ConditionalOnProperty(value = "security.type", havingValue = "embedded",
        matchIfMissing = false)
@Import({ EmbeddedSecurityAdapterSecurityConfig.class })
public class EmbeddedSecurityAdapterConfig {

    public EmbeddedSecurityAdapterConfig() {
        super();
    }

    @Bean("userRepository")
    public UserRepository getUserRepository() {
        return new EmbeddedUserRepository();
    }

}
