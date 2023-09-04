package com.cb.users.repo;

import com.cb.users.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepo extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(String name);
}
