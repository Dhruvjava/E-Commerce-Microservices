package com.cb.users;

import com.cb.users.datars.UsersDataRs;
import com.cb.users.repo.RolesRepo;
import com.cb.users.rq.UserRoleRq;
import com.cb.users.rq.UsersRq;
import com.cb.users.rs.UsersRs;
import com.cb.users.service.IUsersSerice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class UsersServiceImplTest extends RolesServiceImplTest {

    @Autowired
    RolesRepo rolesRepo;

    @Autowired
    IUsersSerice usersService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(3)
    @DisplayName("CREATE USER TEST")
    void createUsers() {

        UserRoleRq roleRq = UserRoleRq.builder().id(1).name("User").build();
        UsersRq users = UsersRq.builder().userid("dhruvjava").firstname("Dhruv")
                .lastname("Kumar").email("dhruv@gmail.com").mobile("9149175183")
                .enabled(false).locked(true).role(roleRq).build();
        UsersDataRs usersRs = (UsersDataRs) usersService.createUsers(users);
        Assertions.assertNotNull(usersRs);
        log.info("User is craeded !!!");
        UsersRs rs= usersRs.getUser();
        Assertions.assertNotNull(rs);
        Assertions.assertNotNull(rs.getId());
        Assertions.assertNotNull(rs.getUserid());
        Assertions.assertNotNull(rs.getFirstname());
        Assertions.assertNotNull(rs.getLastname());
        Assertions.assertNotNull(rs.getEmail());
        Assertions.assertNotNull(rs.getMobile());
        Assertions.assertNotNull(rs.getRole());
        Assertions.assertNotNull(rs.getRole().getId());
        Assertions.assertNotNull(rs.getRole().getName());
        Assertions.assertNotNull(rs.getRole().getPermissions());
    }

    @Test
    @Order(4)
    @DisplayName("UPDATE USER TEST")
    void updateUsers() {
        UserRoleRq roleRq = UserRoleRq.builder().id(1).name("User").operation("deprovision").build();
        UsersRq users = UsersRq.builder().id(1).userid("dhruvjava").firstname("Dhruv")
                .lastname("Kumar").email("dhruv@gmail.com").mobile("9149175183")
                .enabled(false).locked(true).role(roleRq).build();
        UsersDataRs usersRs = (UsersDataRs) usersService.updateUsers(users);
        Assertions.assertNotNull(usersRs);
        log.info("Users deprovision Updated !!!");
        UsersRs rs = usersRs.getUser();
        Assertions.assertNotNull(rs);
        Assertions.assertNotNull(rs.getId());
        Assertions.assertNotNull(rs.getFirstname());
        Assertions.assertNotNull(rs.getLastname());
        Assertions.assertNotNull(rs.getUserid());
        Assertions.assertNotNull(rs.getEmail());
        Assertions.assertNotNull(rs.getMobile());
    }

    @Test
    @Order(4)
    @DisplayName("UPDATE USER PROVISION TEST")
    void updateUsersProviSion() {
        UserRoleRq roleRq = UserRoleRq.builder().id(1).name("User").build();
        UsersRq users = UsersRq.builder().id(1).userid("dhruvjava").firstname("Dhruv")
                .lastname("Kumar").email("dhruv@gmail.com").mobile("9149175183")
                .enabled(false).locked(true).role(roleRq).build();
        UsersDataRs usersRs = (UsersDataRs) usersService.updateUsers(users);
        Assertions.assertNotNull(usersRs);
        log.info("User Provision is updated !!!");
        UsersRs rs= usersRs.getUser();
        Assertions.assertNotNull(rs);
        Assertions.assertNotNull(rs.getId());
        Assertions.assertNotNull(rs.getUserid());
        Assertions.assertNotNull(rs.getFirstname());
        Assertions.assertNotNull(rs.getLastname());
        Assertions.assertNotNull(rs.getEmail());
        Assertions.assertNotNull(rs.getMobile());
        Assertions.assertNotNull(rs.getRole());
        Assertions.assertNotNull(rs.getRole().getId());
        Assertions.assertNotNull(rs.getRole().getName());
        Assertions.assertNotNull(rs.getRole().getPermissions());
    }

    @Test
    void findUser() {
    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void deleteUsers() {
    }

    @Test
    void findUsers() {
    }

    @Test
    void testFindUsers() {
    }

    @Test
    void searchUsers() {
    }

    @Test
    void provisioning() {
    }

    @Test
    void deProvisioning() {
    }

    @Test
    @Order(5)
    @DisplayName("ENABLED USER TEST")
    void enableUser() {
        UsersDataRs usersDataRs = (UsersDataRs) usersService.enableUser(1);
        Assertions.assertNotNull(usersDataRs);
        log.info("ENABLED USER TEST");
        UsersRs rs = usersDataRs.getUser();
        Assertions.assertNotNull(rs);
        Assertions.assertTrue(rs.isEnabled());
    }

    @Test
    void unlockUser() {
    }
}
