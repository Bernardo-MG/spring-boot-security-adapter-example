
package com.bernardomg.example.security.datasource.db.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bernardomg.example.security.auth.model.ResourceType;

import lombok.Data;

/**
 * Persistent implementation of {@code User}.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Entity(name = "ResourceType")
@Table(name = "RESOURCE_TYPES")
@Data
public class PersistentResourceType implements ResourceType {

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long   id;

    @Column(name = "name", nullable = false, unique = true, length = 60)
    private String name;

}
