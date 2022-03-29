
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

import com.bernardomg.example.security.auth.model.Privilege;
import com.bernardomg.example.security.auth.service.PrivilegeService;

@RestController
@RequestMapping("/security/privilege")
public class PrivilegeController {

    private final PrivilegeService service;

    @Autowired
    public PrivilegeController(final PrivilegeService userService) {
        super();

        service = Objects.requireNonNull(userService,
            "Received a null pointer as service");
    }

    @PostMapping
    public Privilege create(@RequestBody final Privilege privilege) {
        return service.createPrivilege(privilege);
    }

    @DeleteMapping
    public void delete(@RequestBody final Privilege privilege) {
        service.deletePrivilege(privilege);
    }

    @GetMapping
    public Iterable<? extends Privilege> read() {
        return service.getPrivileges();
    }

    @PutMapping
    public Privilege update(@RequestBody final Privilege privilege) {
        return service.updatePrivilege(privilege);
    }

}
