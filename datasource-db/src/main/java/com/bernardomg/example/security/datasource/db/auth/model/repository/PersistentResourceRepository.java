
package com.bernardomg.example.security.datasource.db.auth.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardomg.example.security.datasource.db.auth.model.PersistentResource;

public interface PersistentResourceRepository
        extends JpaRepository<PersistentResource, Long> {

}
