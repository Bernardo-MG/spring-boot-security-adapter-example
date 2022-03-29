
package com.bernardomg.example.security.auth.model;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;

@Data
public class DefaultRole implements Role {

    private Long                  id;

    private String                name;

    private Collection<Privilege> privileges = new ArrayList<>();

    @Override
    public void addPrivilege(final Privilege privilege) {
        privileges.add(privilege);
    }

}
