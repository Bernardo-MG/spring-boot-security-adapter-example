
package com.bernardomg.example.security.datasource.db.auth.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardomg.example.security.datasource.db.auth.model.PersistentRole;

/**
 * Repository for users.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public interface PersistentRoleRepository
        extends JpaRepository<PersistentRole, Long> {

}
