
package com.bernardomg.example.security.spring.auth.controller.model;

import lombok.Data;

@Data
public class RolePrivilegeRequest {

    private String privilege;

    private String role;

}
