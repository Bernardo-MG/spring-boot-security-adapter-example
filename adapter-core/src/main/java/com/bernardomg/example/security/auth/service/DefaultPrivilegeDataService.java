
package com.bernardomg.example.security.auth.service;

import java.util.Objects;

import com.bernardomg.example.security.auth.model.Privilege;
import com.bernardomg.example.security.data.repository.CrudRepository;

public final class DefaultPrivilegeDataService implements PrivilegeDataService {

    private final CrudRepository<Privilege> privilegeRepository;

    public DefaultPrivilegeDataService(
            final CrudRepository<Privilege> privilegeRepo) {
        super();

        privilegeRepository = Objects.requireNonNull(privilegeRepo);
    }

    @Override
    public final Privilege addPrivilege(final Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public final Privilege createPrivilege(final Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public final void deletePrivilege(final Privilege privilege) {
        privilegeRepository.delete(privilege);
    }

    @Override
    public final Iterable<? extends Privilege> getPrivileges() {
        return privilegeRepository.findAll();
    }

    @Override
    public final Privilege updatePrivilege(final Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

}
