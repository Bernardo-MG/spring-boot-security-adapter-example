/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2021 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.security.ws.config;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bernardomg.example.security.auth.model.Privilege;
import com.bernardomg.example.security.auth.model.Resource;
import com.bernardomg.example.security.auth.model.Role;
import com.bernardomg.example.security.auth.model.User;
import com.bernardomg.example.security.auth.service.AuditDataService;
import com.bernardomg.example.security.auth.service.DefaultPrivilegeDataService;
import com.bernardomg.example.security.auth.service.DefaultResourceDataService;
import com.bernardomg.example.security.auth.service.DefaultRoleDataService;
import com.bernardomg.example.security.auth.service.DefaultUserDataService;
import com.bernardomg.example.security.auth.service.PrivilegeDataService;
import com.bernardomg.example.security.auth.service.ResourceDataService;
import com.bernardomg.example.security.auth.service.RoleDataService;
import com.bernardomg.example.security.auth.service.UserDataService;
import com.bernardomg.example.security.data.repository.CrudRepository;
import com.bernardomg.example.security.encoder.Encoder;
import com.bernardomg.example.security.spring.audit.service.SpringAuditDataService;

@Configuration
public class DataConfiguration {

    public DataConfiguration() {
        super();
    }

    @Bean("auditDataService")
    public AuditDataService
            getAuditDataService(final AuditEventRepository auditEventRepo) {
        return new SpringAuditDataService(auditEventRepo);
    }

    @Bean("privilegeDataService")
    public PrivilegeDataService getPrivilegeDataService(
            final CrudRepository<Privilege> repository) {
        return new DefaultPrivilegeDataService(repository);
    }

    @Bean("resourceDataService")
    public ResourceDataService
            getResourceDataService(final CrudRepository<Resource> repository) {
        return new DefaultResourceDataService(repository);
    }

    @Bean("userDataService")
    public UserDataService getResourceDataService(
            final CrudRepository<User> userStore,
            final CrudRepository<Role> roleRepo, final Encoder encoder) {
        return new DefaultUserDataService(userStore, roleRepo, encoder);
    }

    @Bean("roleDataService")
    public RoleDataService getRoleDataService(
            final CrudRepository<Role> roleRepo,
            final CrudRepository<Privilege> privilegeRepo) {
        return new DefaultRoleDataService(roleRepo, privilegeRepo);
    }

}
