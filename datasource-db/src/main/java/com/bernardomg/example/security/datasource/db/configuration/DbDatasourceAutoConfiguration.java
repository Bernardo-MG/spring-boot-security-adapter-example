
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
import com.bernardomg.example.security.data.repository.CrudRepository;
import com.bernardomg.example.security.datasource.db.auth.model.PersistentUser;
import com.bernardomg.example.security.datasource.db.auth.model.repository.PersistentUserRepository;
import com.bernardomg.example.security.datasource.db.auth.model.service.PersistentUserService;
import com.bernardomg.example.security.datasource.db.auth.service.PersistentUserDetailsService;
import com.bernardomg.example.security.datasource.db.data.repository.SpringCrudRepository;
import com.bernardomg.example.security.datasource.db.extractor.PersistentUserEntitySaver;
import com.bernardomg.example.security.encoder.Encoder;
import com.bernardomg.example.security.extractor.EntitySaver;
import com.bernardomg.example.security.login.LoginValidator;
import com.bernardomg.example.security.login.PasswordLoginValidator;

@Configuration
@ConditionalOnProperty(value = "security.data.source", havingValue = "db",
        matchIfMissing = false)
@EnableJpaRepositories("com.bernardomg.example.security.datasource.db.**.repository")
@EntityScan("com.bernardomg.example.security.datasource.db.**.model")
public class DbDatasourceAutoConfiguration {

    public DbDatasourceAutoConfiguration() {
        super();
    }

    @Bean("loginValidator")
    public LoginValidator getLoginValidator(final Encoder passwordEncoder) {
        return new PasswordLoginValidator(passwordEncoder);
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

    @Bean("userRepository")
    public CrudRepository<User>
            getUserRepository(final PersistentUserRepository repo) {
        return new SpringCrudRepository<>(repo, PersistentUser::new);
    }

    @Bean("userService")
    public UserService
            getUserService(final JpaRepository<PersistentUser, Long> repo) {
        return new PersistentUserService(repo);
    }

}
