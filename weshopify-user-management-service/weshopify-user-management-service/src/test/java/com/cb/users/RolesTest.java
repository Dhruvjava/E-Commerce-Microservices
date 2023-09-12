package com.cb.users;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.datars.RolesDataRs;
import com.cb.users.entity.RoleToPermission;
import com.cb.users.entity.Roles;
import com.cb.users.repo.PermissionsRepo;
import com.cb.users.repo.RolesRepo;
import com.cb.users.rq.CreateRolesRq;
import com.cb.users.rq.UpdatePermissionsRq;
import com.cb.users.rs.RolesRs;
import com.cb.users.service.IRolesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RolesTest extends PermissionsTest {

    @Autowired
    private RolesRepo rolesRepo;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private PermissionsRepo permissionsRepo;

    @Test
    public void createRoles() {
        CreateRolesRq rq = new CreateRolesRq();
        rq.setName("Admin");
        UpdatePermissionsRq roleToPermission = UpdatePermissionsRq.builder().name("View").id(1).build();
        rq.setPermissions(List.of(roleToPermission));

        RolesDataRs baseRs= (RolesDataRs) rolesService.createRole(rq);
        RolesRs roles = baseRs.getRoles();
        Assertions.assertNotNull(roles.getPermissions());
        Assertions.assertNotNull(roles.getId());
        Assertions.assertNotNull(roles.getName());
    }

}
