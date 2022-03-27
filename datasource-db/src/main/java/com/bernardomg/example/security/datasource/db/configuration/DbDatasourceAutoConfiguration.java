
package com.bernardomg.example.security.datasource.db.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bernardomg.example.security.datasource.db.auth.service.PersistentUserDetailsService;
import com.bernardomg.example.security.datasource.db.loader.PersistentUserEntitySaver;
import com.bernardomg.example.security.datasource.db.user.repository.PersistentUserRepository;
import com.bernardomg.example.security.loader.EntitySaver;
import com.bernardomg.example.security.user.model.User;

@Configuration
@ConditionalOnProperty(value = "security.data.source", havingValue = "db",
        matchIfMissing = false)
public class DbDatasourceAutoConfiguration {

    public DbDatasourceAutoConfiguration() {
        super();
    }

    @Bean("persistentUserEntitySaver")
    public EntitySaver<User> getPersistentUserEntitySaver(
            final PersistentUserRepository userRepository) {
        return new PersistentUserEntitySaver(userRepository);
    }

    @Bean("userDetailsService")
    public UserDetailsService getUserDetailsService(
            final PersistentUserRepository userRepository) {
        return new PersistentUserDetailsService(userRepository);
    }

}
