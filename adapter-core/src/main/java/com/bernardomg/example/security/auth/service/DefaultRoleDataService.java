
package com.bernardomg.example.security.auth.service;

import java.util.Objects;
import java.util.Optional;

import com.bernardomg.example.security.auth.model.DefaultPrivilege;
import com.bernardomg.example.security.auth.model.DefaultRole;
import com.bernardomg.example.security.auth.model.Privilege;
import com.bernardomg.example.security.auth.model.Role;
import com.bernardomg.example.security.data.repository.CrudRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DefaultRoleDataService implements RoleDataService {

    private final CrudRepository<Privilege> privilegeRepository;

    private final CrudRepository<Role>      roleRepository;

    public DefaultRoleDataService(final CrudRepository<Role> roleRepo,
            final CrudRepository<Privilege> privilegeRepo) {
        super();

        roleRepository = Objects.requireNonNull(roleRepo);
        privilegeRepository = Objects.requireNonNull(privilegeRepo);
    }

    @Override
    public final void addPrivilegeToRole(final String rolename,
            final String privilegename) {
        final Optional<? extends Role> foundRole;
        final Optional<? extends Privilege> foundPrivilege;
        final Privilege privilege;
        final Role role;
        final DefaultRole roleSample;
        final DefaultPrivilege privilegeSample;

        roleSample = new DefaultRole();
        roleSample.setName(rolename);
        foundRole = roleRepository.readOne(roleSample);
        if (foundRole.isPresent()) {
            privilegeSample = new DefaultPrivilege();
            privilegeSample.setName(privilegename);
            foundPrivilege = privilegeRepository.readOne(privilegeSample);
            if (foundPrivilege.isPresent()) {
                role = foundRole.get();
                privilege = foundPrivilege.get();
                role.addPrivilege(privilege);
                roleRepository.save(role);
            } else {
                log.error("No privilege found for name {}", privilegename);
            }
        } else {
            log.error("No role found for username {}", rolename);
        }

    }

    @Override
    public final Role addRole(final Role role) {
        return roleRepository.save(role);
    }

    @Override
    public final Role createRole(final Role role) {
        return roleRepository.save(role);
    }

    @Override
    public final void deleteRole(final Role role) {
        roleRepository.delete(role);
    }

    @Override
    public final Iterable<? extends Role> getRoles() {
        return roleRepository.readAll();
    }

    @Override
    public final Role updateRole(final Role role) {
        return roleRepository.save(role);
    }

}
