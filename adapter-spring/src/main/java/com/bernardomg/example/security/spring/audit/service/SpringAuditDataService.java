
package com.bernardomg.example.security.spring.audit.service;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.boot.actuate.audit.AuditEventRepository;

import com.bernardomg.example.security.audit.model.AuditEvent;
import com.bernardomg.example.security.audit.model.DefaultAuditEvent;
import com.bernardomg.example.security.auth.service.AuditDataService;

public final class SpringAuditDataService implements AuditDataService {

    private final AuditEventRepository auditEventRepository;

    public SpringAuditDataService(final AuditEventRepository auditEventRepo) {
        super();

        this.auditEventRepository = auditEventRepo;
    }

    private final AuditEvent toAuditEvent(
            final org.springframework.boot.actuate.audit.AuditEvent event) {
        final DefaultAuditEvent result;

        result = new DefaultAuditEvent();
        result.setAuthor(event.getPrincipal());
        result.setTimestamp(event.getTimestamp());
        result.setType(event.getType());
        result.setData(event.getData());

        return result;
    }

    @Override
    public Iterable<? extends AuditEvent> getAuditEvents(final String principal,
            final Instant after, final String type) {
        return auditEventRepository.find(principal, after, type)
            .stream()
            .map(this::toAuditEvent)
            .collect(Collectors.toList());
    }

}
