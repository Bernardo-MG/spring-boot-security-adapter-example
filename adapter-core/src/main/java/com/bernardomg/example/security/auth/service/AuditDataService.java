
package com.bernardomg.example.security.auth.service;

import java.time.Instant;

import com.bernardomg.example.security.audit.model.AuditEvent;

public interface AuditDataService {

    public Iterable<? extends AuditEvent> getAuditEvents(final String principal,
            final Instant after, final String type);

}
