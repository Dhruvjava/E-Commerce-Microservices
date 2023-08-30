package com.cb.users;

import com.cb.users.datars.PermissionsDataRs;
import com.cb.users.rq.CreatePermissionsRq;
import com.cb.users.service.PermissionsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionServicesTests extends WeshopifyUserManagementServiceApplicationTests{

    @Autowired
    private PermissionsService permissionsService;

    @Test
    public void createPermissionTest(){
        CreatePermissionsRq rq = CreatePermissionsRq.builder().name("Edit").build();
        PermissionsDataRs p= (PermissionsDataRs) permissionsService.createPermission(rq);
        Assertions.assertNotNull(p.getPermission().getId());
    }

}
