
package com.bernardomg.example.security.auth.service;

import com.bernardomg.example.security.auth.model.Privilege;

public interface PrivilegeService {

    public Privilege addPrivilege(final Privilege privilege);

    public Privilege createPrivilege(final Privilege privilege);

    public void deletePrivilege(final Privilege privilege);

    public Iterable<? extends Privilege> getPrivileges();

    public Privilege updatePrivilege(final Privilege privilege);

}
