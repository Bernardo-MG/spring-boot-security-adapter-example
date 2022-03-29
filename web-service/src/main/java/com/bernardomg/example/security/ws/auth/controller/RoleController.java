
package com.bernardomg.example.security.ws.auth.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardomg.example.security.auth.model.Role;
import com.bernardomg.example.security.auth.service.RoleDataService;
import com.bernardomg.example.security.ws.auth.controller.model.RolePrivilegeRequest;

@RestController
@RequestMapping("/security/role")
public class RoleController {

    private final RoleDataService service;

    @Autowired
    public RoleController(final RoleDataService roleService) {
        super();

        service = Objects.requireNonNull(roleService,
            "Received a null pointer as service");
    }

    @PostMapping("/privilege")
    public void addPrivilege(@RequestBody final RolePrivilegeRequest request) {
        service.addPrivilegeToRole(request.getRole(), request.getPrivilege());
    }

    @PostMapping
    public Role create(@RequestBody final Role role) {
        return service.createRole(role);
    }

    @DeleteMapping
    public void delete(@RequestBody final Role role) {
        service.deleteRole(role);
    }

    @GetMapping
    public Iterable<? extends Role> read() {
        return service.getRoles();
    }

    @PutMapping
    public Role update(@RequestBody final Role role) {
        return service.updateRole(role);
    }

}
