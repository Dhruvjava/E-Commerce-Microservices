package com.cb.users;

import com.cb.users.entity.RoleToPermission;
import com.cb.users.entity.Roles;
import com.cb.users.repo.PermissionsRepo;
import com.cb.users.repo.RolesRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RolesTest extends PermissionsTest {

    @Autowired
    private RolesRepo rolesRepo;

    @Autowired
    private PermissionsRepo permissionsRepo;

    @Test
    public void createRoles() {
        Roles roles = new Roles();
        roles.setName("Admin");
        RoleToPermission roleToPermission = RoleToPermission.builder().permissions(permissionsRepo.findById(1).orElse(null)).roles(roles).build();
        roles.setPermissions(List.of(roleToPermission));

        rolesRepo.save(roles);
        Assertions.assertNotNull(roles.getPermissions());
        Assertions.assertNotNull(roles.getId());
        Assertions.assertNotNull(roles.getName());
    }

}
