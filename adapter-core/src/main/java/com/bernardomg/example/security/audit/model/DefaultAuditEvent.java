
package com.bernardomg.example.security.audit.model;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class DefaultAuditEvent implements AuditEvent {

    private String              author;

    private Map<String, Object> data = new HashMap<>();

    private Instant             timestamp;

    private String              type;

}
