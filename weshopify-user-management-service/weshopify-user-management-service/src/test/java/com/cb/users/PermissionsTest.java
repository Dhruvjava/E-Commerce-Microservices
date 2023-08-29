package com.cb.users;

import com.cb.users.entity.Permissions;
import com.cb.users.repo.PermissionsRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionsTest extends WeshopifyUserManagementServiceApplicationTests {

    @Autowired
    private PermissionsRepo permissionsRepo;

    @Test
    public void createPermissions() {
        Permissions view = Permissions.builder().name("View").build();
        permissionsRepo.save(view);
        Assertions.assertNotNull(view.getId());
        Assertions.assertNotNull(view.getName());
    }
}
