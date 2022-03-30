/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2021 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.auth.service.UserDataService;
import com.bernardomg.example.security.ws.auth.controller.model.UserRoleRequest;

/**
 * Rest controller for the users.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@RestController
@RequestMapping("/rest/user")
public class UserController {

    /**
     * Example entity service.
     */
    private final UserDataService service;

    /**
     * Constructs a controller with the specified dependencies.
     *
     * @param userService
     *            user service
     */
    @Autowired
    public UserController(final UserDataService userService) {
        super();

        service = Objects.requireNonNull(userService,
            "Received a null pointer as service");
    }

    @PostMapping("/role")
    public void addRole(@RequestBody final UserRoleRequest request) {
        service.addRoleToUser(request.getUsername(), request.getRole());
    }

    @PostMapping
    public User create(@RequestBody final User user) {
        return service.createUser(user);
    }

    @DeleteMapping
    public void delete(@RequestBody final User user) {
        service.deleteUser(user);
    }

    @GetMapping
    public Iterable<? extends User> read() {
        return service.getUsers();
    }

    @PutMapping
    public User update(@RequestBody final User user) {
        return service.updateUser(user);
    }

}
