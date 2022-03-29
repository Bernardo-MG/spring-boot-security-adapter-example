
package com.bernardomg.example.security.datasource.db.auth.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardomg.example.security.datasource.db.auth.model.PersistentAction;

public interface PersistentActionRepository
        extends JpaRepository<PersistentAction, Long> {

}
