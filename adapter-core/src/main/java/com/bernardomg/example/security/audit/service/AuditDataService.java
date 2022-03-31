
package com.bernardomg.example.security.audit.service;

import java.time.Instant;

import com.bernardomg.example.security.audit.model.AuditEvent;

public interface AuditDataService {

    public void addAuditEvent(final AuditEvent event);

    public Iterable<? extends AuditEvent> getAuditEvents(final String principal,
            final Instant after, final String type);

}
