package com.cb.users;

import com.cb.users.datars.PermissionsDataRSs;
import com.cb.users.datars.RolesDataRs;
import com.cb.users.rq.CreateRolesRq;
import com.cb.users.rs.PermissionsRs;
import com.cb.users.service.IPermissionsService;
import com.cb.users.service.IRolesService;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


class RolesServiceImplTest extends PermissionsServiceImplTest {

    @Autowired
    IPermissionsService permissionsService;

    @Autowired
    IRolesService rolesService;

    @Autowired
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(2)
    @DisplayName("CREATE ROLE TEST")
    void createRole() {
        PermissionsDataRSs permissionsList = (PermissionsDataRSs) permissionsService.findAllPermission();
        List<PermissionsRs> permissionsRSs = permissionsList.getPermissions();
        CreateRolesRq rolesRq = CreateRolesRq.builder().name("User").permissions(permissionsRSs).build();
        RolesDataRs role = (RolesDataRs) rolesService.createRole(rolesRq);
        Assertions.assertNotNull(role);
        Assertions.assertNotNull(role.getRoles());
    }

    @Test
    void updateRole() {
    }

    @Test
    void findRole() {
    }

    @Test
    void testFindRole() {
    }

    @Test
    void deleteRole() {
    }

    @Test
    void findAllRole() {
    }
}
