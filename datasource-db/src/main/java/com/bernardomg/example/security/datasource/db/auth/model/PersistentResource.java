
package com.bernardomg.example.security.datasource.db.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bernardomg.example.security.auth.model.Resource;

import lombok.Data;

/**
 * Persistent implementation of {@code User}.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Entity(name = "Resource")
@Table(name = "RESOURCES")
@Data
public class PersistentResource implements Resource {

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
