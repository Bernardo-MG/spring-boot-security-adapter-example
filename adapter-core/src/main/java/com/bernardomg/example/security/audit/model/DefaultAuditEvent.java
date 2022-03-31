
package com.bernardomg.example.security.audit.model;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class DefaultAuditEvent implements AuditEvent {

    private Instant             timestamp;

    private String              author;

    private String              type;

    private Map<String, Object> data = new HashMap<>();

}
