package com.cb.users.repo;

import com.cb.users.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionsRepo extends JpaRepository<Permissions, Integer> {

    boolean existsByName(String name);

//    int countAllPermissions();
}
