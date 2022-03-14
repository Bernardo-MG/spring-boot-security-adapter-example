
package com.bernardomg.example.security.adapter.embedded.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bernardomg.example.security.adapter.embedded.auth.service.PersistentUserDetailsService;
import com.bernardomg.example.security.adapter.embedded.repository.EmbeddedUserRepository;
import com.bernardomg.example.security.adapter.embedded.user.repository.PersistentUserRepository;
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

    @Bean("userDetailsService")
    public UserDetailsService getUserDetailsService(
            final PersistentUserRepository userRepository) {
        return new PersistentUserDetailsService(userRepository);
    }

}
