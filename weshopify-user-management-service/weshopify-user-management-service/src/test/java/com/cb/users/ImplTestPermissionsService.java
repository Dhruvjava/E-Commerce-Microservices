package com.cb.users;

import com.cb.users.datars.PermissionsDataRSs;
import com.cb.users.datars.PermissionsDataRs;
import com.cb.users.rq.CreatePermissionsRq;
import com.cb.users.rq.UpdatePermissionsRq;
import com.cb.users.service.IPermissionsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ImplTestPermissionsService extends WeshopifyUserManagementServiceApplicationTests {

    @Autowired
    private IPermissionsService permissionsService;
    
    private static final int PERMISSION_ID = 1;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("CREATE PERMISSION TESTS")
    @Order(1)
    void createPermission() {
        CreatePermissionsRq rq = CreatePermissionsRq.builder().name("CREATE").build();
        PermissionsDataRs permissions = (PermissionsDataRs) permissionsService.createPermission(rq);
        log.info("Permissions are created :" + permissions.getPermission().toString());
        assertNotEquals(0, permissions.getPermission().getId());
        assertNotNull(permissions.getPermission().getName());
    }

    @Test
    @DisplayName("UPDATE PERMISSION TEST")
    @Order(2)
    void updatePermission() {
        UpdatePermissionsRq rq = UpdatePermissionsRq.builder().id(PERMISSION_ID).name("EDIT").build();
        PermissionsDataRs permissions = (PermissionsDataRs) permissionsService.updatePermission(rq);
        log.info("Updated permissions : " + permissions.getPermission().toString());
        assertEquals("EDIT", permissions.getPermission().getName());
    }

    @Test
    @DisplayName("FIND PERMISSION TEST")
    @Order(3)
    void findPermission() {
        int id = PERMISSION_ID;
        PermissionsDataRs rs = (PermissionsDataRs) permissionsService.findPermission(id);
        log.info("Updated Permissions are :" + rs.getPermission().toString());
        assertNotNull(rs.getPermission());
        assertEquals(id, rs.getPermission().getId());
        assertNotNull(rs.getPermission().getName());
        assertEquals("EDIT", rs.getPermission().getName());
    }

    @Test
    @DisplayName("DELETE PERMISSION TEST")
    @Order(5)
    void deletePermission() {
        int id = PERMISSION_ID;
        PermissionsDataRs rs = (PermissionsDataRs) permissionsService.deletePermission(id);
//        assertNotNull(rs.getPermissions());
    }

    @Test
    @DisplayName("DISPLAY PERMISSION TEST")
    @Order(4)
    void findAllPermission() {

        PermissionsDataRSs rs = (PermissionsDataRSs) permissionsService.findAllPermission();
        assertNotNull(rs.getPermissions());
        assertNotEquals(0, rs.getPermissions().size());

    }

    @Test
    void searchPermission() {
    }
}
