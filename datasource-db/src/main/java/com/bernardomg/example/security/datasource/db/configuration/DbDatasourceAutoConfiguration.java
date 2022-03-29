
package com.bernardomg.example.security.datasource.db.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bernardomg.example.security.auth.model.Action;
import com.bernardomg.example.security.auth.model.Privilege;
import com.bernardomg.example.security.auth.model.Resource;
import com.bernardomg.example.security.auth.model.ResourceType;
import com.bernardomg.example.security.auth.model.Role;
import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.data.repository.CrudRepository;
import com.bernardomg.example.security.datasource.db.auth.model.PersistentAction;
import com.bernardomg.example.security.datasource.db.auth.model.PersistentPrivilege;
import com.bernardomg.example.security.datasource.db.auth.model.PersistentResource;
import com.bernardomg.example.security.datasource.db.auth.model.PersistentResourceType;
import com.bernardomg.example.security.datasource.db.auth.model.PersistentRole;
import com.bernardomg.example.security.datasource.db.auth.model.PersistentUser;
import com.bernardomg.example.security.datasource.db.auth.model.repository.PersistentActionRepository;
import com.bernardomg.example.security.datasource.db.auth.model.repository.PersistentPrivilegeRepository;
import com.bernardomg.example.security.datasource.db.auth.model.repository.PersistentResourceRepository;
import com.bernardomg.example.security.datasource.db.auth.model.repository.PersistentResourceTypeRepository;
import com.bernardomg.example.security.datasource.db.auth.model.repository.PersistentRoleRepository;
import com.bernardomg.example.security.datasource.db.auth.model.repository.PersistentUserRepository;
import com.bernardomg.example.security.datasource.db.data.repository.SpringCrudRepository;

@Configuration
@ConditionalOnProperty(value = "security.data.source", havingValue = "db",
        matchIfMissing = false)
@EnableJpaRepositories("com.bernardomg.example.security.datasource.db.**.repository")
@EntityScan("com.bernardomg.example.security.datasource.db.**.model")
@ComponentScan("com.bernardomg.example.security.datasource.db.configuration")
public class DbDatasourceAutoConfiguration {

    public DbDatasourceAutoConfiguration() {
        super();
    }

    @Bean("actionRepository")
    public CrudRepository<Action>
            getActionRepository(final PersistentActionRepository repo) {
        return new SpringCrudRepository<>(repo, PersistentAction::new);
    }

    @Bean("privilegeRepository")
    public CrudRepository<Privilege>
            getPrivilegeRepository(final PersistentPrivilegeRepository repo) {
        return new SpringCrudRepository<>(repo, PersistentPrivilege::new);
    }

    @Bean("privilegeRepository")
    public CrudRepository<Privilege>
            getResourceRepository(final PersistentPrivilegeRepository repo) {
        return new SpringCrudRepository<>(repo, PersistentPrivilege::new);
    }

    @Bean("resourceRepository")
    public CrudRepository<Resource>
            getResourceRepository(final PersistentResourceRepository repo) {
        return new SpringCrudRepository<>(repo, PersistentResource::new);
    }

    @Bean("resourceTypeRepository")
    public CrudRepository<ResourceType> getResourceTypeRepository(
            final PersistentResourceTypeRepository repo) {
        return new SpringCrudRepository<>(repo, PersistentResourceType::new);
    }

    @Bean("roleRepository")
    public CrudRepository<Role>
            getRoleRepository(final PersistentRoleRepository repo) {
        return new SpringCrudRepository<>(repo, PersistentRole::new);
    }

    @Bean("userRepository")
    public CrudRepository<User>
            getUserRepository(final PersistentUserRepository repo) {
        return new SpringCrudRepository<>(repo, PersistentUser::new);
    }

}
