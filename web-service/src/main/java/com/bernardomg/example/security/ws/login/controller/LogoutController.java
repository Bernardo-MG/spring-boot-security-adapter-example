
package com.bernardomg.example.security.ws.login.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardomg.example.security.auth.model.DefaultUser;
import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.login.LoginService;

@RestController
@RequestMapping("/auth/logout")
public class LogoutController {

    private final LoginService service;

    @Autowired
    public LogoutController(final LoginService serv) {
        super();

        service = Objects.requireNonNull(serv,
            "Received a null pointer as service");
    }

    @PostMapping("/")
    public User logout(@RequestBody final DefaultUser user) {
        return service.logout(user);
    }

}
