
package com.bernardomg.example.security.spring.auth.controller;

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
import com.bernardomg.example.security.auth.service.PrivilegeDataService;

@RestController
@RequestMapping("/rest/privilege")
public class PrivilegeController {

    private final PrivilegeDataService service;

    @Autowired
    public PrivilegeController(final PrivilegeDataService userService) {
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
