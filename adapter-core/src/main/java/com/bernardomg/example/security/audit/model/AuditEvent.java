
package com.bernardomg.example.security.audit.model;

import java.time.Instant;
import java.util.Map;

public interface AuditEvent {

    public Instant getTimestamp();

    public String getAuthor();

    public String getType();

    public Map<String, Object> getData();

}
