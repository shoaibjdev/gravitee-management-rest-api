/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.management.service.impl;

import io.gravitee.management.model.NewRoleEntity;
import io.gravitee.management.model.permissions.*;
import io.gravitee.management.service.InitializerService;
import io.gravitee.management.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.gravitee.management.model.permissions.RolePermissionAction.*;
import static io.gravitee.management.model.permissions.RoleScope.*;

/**
 * @author Nicolas GERAUD (nicolas.geraud at graviteesource.com) 
 * @author GraviteeSource Team
 */
@Component
public class InitializerServiceImpl extends io.gravitee.common.service.AbstractService<InitializerServiceImpl> implements InitializerService<InitializerServiceImpl> {

    private final Logger logger = LoggerFactory.getLogger(InitializerServiceImpl.class);

    @Autowired
    RoleService roleService;

    @Override
    protected String name() {
        return "Initializer Service";
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();

        // initialize roles.
        if(roleService.findAll().isEmpty()) {
            logger.info("    No role found. Add default ones.");
            roleService.createSystemRoles();

            Map<String, char[]> perms = new HashMap<>();

            logger.info("     - <MANAGEMENT> API_PUBLISHER");
            perms.put(ManagementPermission.API.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ManagementPermission.APPLICATION.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ManagementPermission.TAG.getName(), new char[]{READ.getId()});
            perms.put(ManagementPermission.GROUP.getName(), new char[]{READ.getId()});
            perms.put(ManagementPermission.TENANT.getName(), new char[]{READ.getId()});
            perms.put(ManagementPermission.VIEW.getName(), new char[]{READ.getId()});
            perms.put(ManagementPermission.ROLE.getName(), new char[]{READ.getId()});
            roleService.create(new NewRoleEntity(
                    "API_PUBLISHER",
                    "Management Role. Created by Gravitee.io.",
                    MANAGEMENT,
                    false,
                    perms
            ));

            logger.info("     - <MANAGEMENT> USER (default)");
            perms.clear();
            perms.put(ManagementPermission.API.getName(), new char[]{READ.getId()});
            perms.put(ManagementPermission.APPLICATION.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ManagementPermission.ROLE.getName(), new char[]{READ.getId()});
            perms.put(ManagementPermission.GROUP.getName(), new char[]{READ.getId()});
            roleService.create(new NewRoleEntity(
                    "USER",
                    "Default Management Role. Created by Gravitee.io.",
                    MANAGEMENT,
                    true,
                    perms
            ));

            logger.info("     - <PORTAL> USER (default)");
            perms.clear();
            perms.put(PortalPermission.APPLICATION.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(PortalPermission.DOCUMENTATION.getName(), new char[]{READ.getId()});
            roleService.create(new NewRoleEntity(
                    "USER",
                    "Default Portal Role. Created by Gravitee.io.",
                    PORTAL,
                    true,
                    perms
            ));

            logger.info("     - <API> USER (default)");
            perms.clear();
            perms.put(ApiPermission.DEFINITION.getName(), new char[]{READ.getId()});
            perms.put(ApiPermission.PLAN.getName(), new char[]{READ.getId()});
            perms.put(ApiPermission.SUBSCRIPTION.getName(), new char[]{READ.getId()});
            perms.put(ApiPermission.MEMBER.getName(), new char[]{READ.getId()});
            perms.put(ApiPermission.METADATA.getName(), new char[]{READ.getId()});
            perms.put(ApiPermission.EVENT.getName(), new char[]{READ.getId()});
            perms.put(ApiPermission.DOCUMENTATION.getName(), new char[]{READ.getId()});
            roleService.create(new NewRoleEntity(
                    "USER",
                    "Default API Role. Created by Gravitee.io.",
                    API,
                    true,
                    perms
            ));

            logger.info("     - <API> OWNER");
            perms.clear();
            perms.put(ApiPermission.DEFINITION.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApiPermission.GATEWAY_DEFINITION.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApiPermission.PLAN.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApiPermission.SUBSCRIPTION.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApiPermission.MEMBER.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApiPermission.METADATA.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApiPermission.ANALYTICS.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApiPermission.EVENT.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApiPermission.HEALTH.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApiPermission.LOG.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApiPermission.DOCUMENTATION.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            roleService.create(new NewRoleEntity(
                    "OWNER",
                    "API Role. Created by Gravitee.io.",
                    API,
                    false,
                    perms
            ));

            logger.info("     - <APPLICATION> USER (default)");
            perms.clear();
            perms.put(ApplicationPermission.DEFINITION.getName(), new char[]{READ.getId()});
            perms.put(ApplicationPermission.MEMBER.getName(), new char[]{READ.getId()});
            perms.put(ApplicationPermission.ANALYTICS.getName(), new char[]{READ.getId()});
            perms.put(ApplicationPermission.LOG.getName(), new char[]{READ.getId()});
            perms.put(ApplicationPermission.SUBSCRIPTION.getName(), new char[]{READ.getId()});
            roleService.create(new NewRoleEntity(
                    "USER",
                    "Default Application Role. Created by Gravitee.io.",
                    APPLICATION,
                    true,
                    perms
            ));

            logger.info("     - <APPLICATION> OWNER");
            perms.clear();
            perms.put(ApplicationPermission.DEFINITION.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApplicationPermission.MEMBER.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApplicationPermission.ANALYTICS.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApplicationPermission.LOG.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            perms.put(ApplicationPermission.SUBSCRIPTION.getName(), new char[]{CREATE.getId(), READ.getId(), UPDATE.getId(), DELETE.getId()});
            roleService.create(new NewRoleEntity(
                    "OWNER",
                    "Default Application Role. Created by Gravitee.io.",
                    APPLICATION,
                    false,
                    perms
            ));

        } else {
            logger.debug("    Roles found, nothing to do.");
        }
    }
}

