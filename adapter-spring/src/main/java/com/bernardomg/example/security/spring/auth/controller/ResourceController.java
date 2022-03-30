
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

import com.bernardomg.example.security.auth.model.Resource;
import com.bernardomg.example.security.auth.service.ResourceDataService;

@RestController
@RequestMapping("/rest/resource")
public class ResourceController {

    private final ResourceDataService service;

    @Autowired
    public ResourceController(final ResourceDataService resourceService) {
        super();

        service = Objects.requireNonNull(resourceService,
            "Received a null pointer as service");
    }

    @PostMapping
    public Resource create(@RequestBody final Resource resource) {
        return service.createResource(resource);
    }

    @DeleteMapping
    public void delete(@RequestBody final Resource resource) {
        service.deleteResource(resource);
    }

    @GetMapping
    public Iterable<? extends Resource> read() {
        return service.getResources();
    }

    @PutMapping
    public Resource update(@RequestBody final Resource resource) {
        return service.updateResource(resource);
    }

}
