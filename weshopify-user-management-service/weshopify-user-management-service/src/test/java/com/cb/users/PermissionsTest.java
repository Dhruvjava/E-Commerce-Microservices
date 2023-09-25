package com.cb.users;

import com.cb.users.datars.PermissionsDataRs;
import com.cb.users.repo.PermissionsRepo;
import com.cb.users.rs.PermissionsRs;
import com.cb.users.service.IPermissionsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionsTest extends WeshopifyUserManagementServiceApplicationTests {

    @Autowired
    private PermissionsRepo permissionsRepo;

    @Autowired
    private IPermissionsService permissionsService;

    @Test
    public void createPermissions() {
        CreatePermissionsRq rq = CreatePermissionsRq.builder().name("View").build();
//        Permissions view = Permissions.builder().name("View").build();
//        permissionsRepo.save(view);
        PermissionsDataRs dataRs = (PermissionsDataRs) permissionsService.createPermission(rq);
        PermissionsRs rs = dataRs.getPermission();
        Assertions.assertNotNull(rs.getId());
        Assertions.assertNotNull(rs.getName());
    }
}
