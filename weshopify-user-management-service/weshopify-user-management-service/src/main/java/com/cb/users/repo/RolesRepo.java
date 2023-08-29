package com.cb.users.repo;

import com.cb.users.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles, Integer> {
}
