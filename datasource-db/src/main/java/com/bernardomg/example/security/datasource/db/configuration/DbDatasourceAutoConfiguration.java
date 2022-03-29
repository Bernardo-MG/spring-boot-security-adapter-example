
package com.bernardomg.example.security.datasource.db.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bernardomg.example.security.adapter.user.service.UserService;
import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.datasource.db.auth.model.PersistentUser;
import com.bernardomg.example.security.datasource.db.auth.model.repository.PersistentUserRepository;
import com.bernardomg.example.security.datasource.db.auth.model.service.PersistentUserService;
import com.bernardomg.example.security.datasource.db.auth.service.PersistentUserDetailsService;
import com.bernardomg.example.security.datasource.db.extractor.PersistentUserEntitySaver;
import com.bernardomg.example.security.extractor.EntitySaver;

@Configuration
@ConditionalOnProperty(value = "security.data.source", havingValue = "db",
        matchIfMissing = false)
@EnableJpaRepositories("com.bernardomg.example.security.datasource.db.user.repository")
@EntityScan("com.bernardomg.example.security.datasource.db.user.model")
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

    @Bean("userService")
    public UserService
            getUserService(final JpaRepository<PersistentUser, Long> repo) {
        return new PersistentUserService(repo);
    }

}
