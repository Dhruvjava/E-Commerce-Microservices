package com.cb.users;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.datars.PermissionsDataRs;
import com.cb.users.entity.Permissions;
import com.cb.users.rq.PermissionsRq;
import com.cb.users.service.PermissionsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionServicesTests extends WeshopifyUserManagementServiceApplicationTests{

    @Autowired
    private PermissionsService permissionsService;

    @Test
    public void createPermissionTest(){
        PermissionsRq rq = PermissionsRq.builder().name("Edit").build();
        PermissionsDataRs p= (PermissionsDataRs) permissionsService.createPermission(rq);
        Assertions.assertNotNull(p.getPermission().getId());
    }

}
