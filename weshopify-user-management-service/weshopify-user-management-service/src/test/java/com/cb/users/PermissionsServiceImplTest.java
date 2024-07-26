package com.cb.users;

import com.cb.users.datars.PermissionsDataRSs;
import com.cb.users.datars.PermissionsDataRs;
import com.cb.users.rq.PermissionsRq;
import com.cb.users.service.impl.PermissionsServiceIImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class PermissionsServiceImplTest extends WeshopifyUserManagementServiceApplicationTests {

//    @Mock
//    private PermissionsRepo repo;

//    @Mock
//    private ModelMapper mapper;

//    @Mock
//    private Messages messages;

    @Autowired
//    @InjectMocks
    private PermissionsServiceIImpl permissionsService;
    
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
        PermissionsRq rq = PermissionsRq.builder().name("CREATE").build();
        PermissionsDataRs permissions = (PermissionsDataRs) permissionsService.createPermission(rq);
        log.info("Permissions are created :" + permissions.getPermission().toString());
        assertNotEquals(0, permissions.getPermission().getId());
        assertNotNull(permissions.getPermission().getName());
    }

//    @Test
//    @DisplayName("CREATE PERMISSION TESTS")
//    @Order(1)
//    void createPermission() {
//        Permissions permissions = Permissions.builder().id(1).name("View").build();
//        Mockito.when(repo.save(permissions)).thenReturn(permissions);
//
//        PermissionsRq rq = PermissionsRq.builder().name("View").build();
//        PermissionsRs rs = PermissionsRs.builder().id(1).name("View").build();
//        Mockito.when(mapper.map(rq, Permissions.class)).thenReturn(permissions);
//        Mockito.when(mapper.map(permissions, PermissionsRs.class)).thenReturn(rs);
//        PermissionsDataRs permissionsRs = (PermissionsDataRs) permissionsService.createPermission(rq);
//        log.info("Permissions are created :" + permissionsRs.getPermission().toString());
//        assertNotEquals(0, permissionsRs.getPermission().getId());
//        assertNotNull(permissionsRs.getPermission().getName());
//    }

    @Test
    @DisplayName("UPDATE PERMISSION TEST")
    @Order(3)
//    @Disabled("SKIPPING UPDATE PERMISSION TESTS")
    void updatePermission() {
        PermissionsRq rq = PermissionsRq.builder().id(1).name("CREATE").build();
        PermissionsDataRs permissions = (PermissionsDataRs) permissionsService.updatePermission(rq);
        log.info("Permissions are created :" + permissions.getPermission().toString());
        assertNotEquals(0, permissions.getPermission().getId());
        assertNotNull(permissions.getPermission().getName());
        log.info("Permissions are created :" + permissions.getPermission().toString());
        assertNotEquals(0, permissions.getPermission().getId());
        assertEquals(rq.getId(), permissions.getPermission().getId());
        assertNotNull(permissions.getPermission().getName());
    }

    @Test
    @DisplayName("FIND PERMISSION TEST")
    @Order(4)
    @Disabled("SKIPPING FIND PERMISSION TESTS")
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
    @Order(6)
    @Disabled("SKIPPING DELETE PERMISSION TESTS")
    void deletePermission() {
        int id = PERMISSION_ID;
        PermissionsDataRs rs = (PermissionsDataRs) permissionsService.deletePermission(id);
//        assertNotNull(rs.getPermissions());
    }

    @Test
    @DisplayName("DISPLAY PERMISSION TEST")
    @Order(5)
    @Disabled("SKIPPING FIND ALL PERMISSION TESTS")
    void findAllPermission() {

        PermissionsDataRSs rs = (PermissionsDataRSs) permissionsService.findAllPermission();
        assertNotNull(rs.getPermissions());
        assertNotEquals(0, rs.getPermissions().size());

    }

    @Test
    void searchPermission() {
    }
}
