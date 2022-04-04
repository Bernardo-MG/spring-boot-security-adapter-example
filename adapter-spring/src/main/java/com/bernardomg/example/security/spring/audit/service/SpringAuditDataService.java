
package com.bernardomg.example.security.spring.audit.service;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.boot.actuate.audit.AuditEventRepository;

import com.bernardomg.example.security.audit.model.AuditEvent;
import com.bernardomg.example.security.audit.model.DefaultAuditEvent;
import com.bernardomg.example.security.audit.service.AuditDataService;

public final class SpringAuditDataService implements AuditDataService {

    private final AuditEventRepository auditEventRepository;

    public SpringAuditDataService(final AuditEventRepository auditEventRepo) {
        super();

        auditEventRepository = auditEventRepo;
    }

    @Override
    public void addAuditEvent(final AuditEvent event) {
        final org.springframework.boot.actuate.audit.AuditEvent toSave;

        toSave = new org.springframework.boot.actuate.audit.AuditEvent(
            event.getTimestamp(), event.getAuthor(), event.getType(),
            event.getData());

        auditEventRepository.add(toSave);
    }

    @Override
    public Iterable<? extends AuditEvent> getAuditEvents(final String principal,
            final Instant after, final String type) {
        return auditEventRepository.find(principal, after, type)
            .stream()
            .map(this::toAuditEvent)
            .collect(Collectors.toList());
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

}
