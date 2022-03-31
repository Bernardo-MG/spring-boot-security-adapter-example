
package com.bernardomg.example.security.spring.audit.controller;

import java.time.Instant;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bernardomg.example.security.audit.model.AuditEvent;
import com.bernardomg.example.security.audit.service.AuditDataService;

@RestController
@RequestMapping("/rest/audit")
public class AuditController {

    private final AuditDataService service;

    @Autowired
    public AuditController(final AuditDataService auditService) {
        super();

        service = Objects.requireNonNull(auditService,
            "Received a null pointer as service");
    }

    @GetMapping
    public Iterable<? extends AuditEvent> read(
            @RequestParam(name = "principal",
                    required = false) final String principal,
            @RequestParam(name = "after", required = false) final Instant after,
            @RequestParam(name = "type", required = false) final String type) {
        return service.getAuditEvents(principal, after, type);
    }

}
