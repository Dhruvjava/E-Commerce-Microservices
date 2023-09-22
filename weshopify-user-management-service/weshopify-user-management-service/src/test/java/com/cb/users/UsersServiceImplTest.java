package com.cb.users;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.entity.Roles;
import com.cb.users.mapper.RolesMapper;
import com.cb.users.repo.RolesRepo;
import com.cb.users.rq.UsersRq;
import com.cb.users.rs.RolesRs;
import com.cb.users.service.IUsersSerice;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

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

        Roles roles = rolesRepo.findById(1).orElse(null);
        RolesRs rolesRs = RolesMapper.mapToRoles(roles, mapper);
        UsersRq users = UsersRq.builder().userid("dhruvjava").firstname("Dhruv")
                .lastname("Kumar").email("dhruv@gmail.com").mobile("9149175183")
                .enabled(false).locked(true).role(rolesRs).build();
        BaseDataRs users1 = usersService.createUsers(users);

    }

    @Test
    void updateUsers() {
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
    void enableUser() {
    }

    @Test
    void unlockUser() {
    }
}
