
package com.bernardomg.example.security.datasource.db.auth.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardomg.example.security.datasource.db.auth.model.PersistentResourceType;

public interface PersistentResourceTypeRepository
        extends JpaRepository<PersistentResourceType, Long> {

}
