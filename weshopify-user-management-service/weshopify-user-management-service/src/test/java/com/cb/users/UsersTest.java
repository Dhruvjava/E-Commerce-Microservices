package com.cb.users;

import com.cb.users.entity.Roles;
import com.cb.users.entity.Users;
import com.cb.users.repo.RolesRepo;
import com.cb.users.repo.UsersRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersTest extends RolesTest {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private RolesRepo rolesRepo;

    @Test
    void createUser() {
        Roles roles = rolesRepo.findById(1)
                .orElse(null);
        Users users = Users.builder().email("abc@gmail.com").firstname("ab").lastname("c")
                .mobile("9149175183").enabled(false).locked(true).userid("Dhruvjava").role(roles).build();

        usersRepo.save(users);
        Assertions.assertNotNull(users.getUserid());
        Assertions.assertNotNull(users.getRole());
    }

}
