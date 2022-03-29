
package com.bernardomg.example.security.auth.service;

import com.bernardomg.example.security.auth.model.Role;

public interface RoleService {

    public void addPrivilegeToRole(final String role, final String privilege);

    public Role addRole(final Role role);

    public Role createRole(final Role role);

    public void deleteRole(final Role role);

    public Iterable<? extends Role> getRoles();

    public Role updateRole(final Role role);

}
