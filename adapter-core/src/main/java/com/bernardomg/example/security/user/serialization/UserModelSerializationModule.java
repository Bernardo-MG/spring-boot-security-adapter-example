
package com.bernardomg.example.security.user.serialization;

import com.bernardomg.example.security.auth.model.Action;
import com.bernardomg.example.security.auth.model.DefaultAction;
import com.bernardomg.example.security.auth.model.DefaultPrivilege;
import com.bernardomg.example.security.auth.model.DefaultResource;
import com.bernardomg.example.security.auth.model.DefaultResourceType;
import com.bernardomg.example.security.auth.model.DefaultRole;
import com.bernardomg.example.security.auth.model.DefaultUser;
import com.bernardomg.example.security.auth.model.Privilege;
import com.bernardomg.example.security.auth.model.Resource;
import com.bernardomg.example.security.auth.model.ResourceType;
import com.bernardomg.example.security.auth.model.Role;
import com.bernardomg.example.security.auth.model.User;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Módulo de deserialización para definiciones de modelo. Contiene toda la
 * configuración de mapeos para Jackson.
 *
 * @author bmg
 *
 */
public final class UserModelSerializationModule extends SimpleModule {

    private static final long serialVersionUID = -3338095606150249240L;

    public UserModelSerializationModule() {
        super();

        // Mapeos
        addAbstractTypeMapping(Action.class, DefaultAction.class);
        addAbstractTypeMapping(Privilege.class, DefaultPrivilege.class);
        addAbstractTypeMapping(Resource.class, DefaultResource.class);
        addAbstractTypeMapping(ResourceType.class, DefaultResourceType.class);
        addAbstractTypeMapping(Role.class, DefaultRole.class);
        addAbstractTypeMapping(User.class, DefaultUser.class);
    }

}
