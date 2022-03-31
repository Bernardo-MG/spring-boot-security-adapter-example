
package com.bernardomg.example.security.audit.model;

import java.time.Instant;
import java.util.Map;

public interface AuditEvent {

    public String getAuthor();

    public Map<String, Object> getData();

    public Instant getTimestamp();

    public String getType();

}
