package com.cb.users.mapper;

import com.cb.users.entity.Permissions;
import com.cb.users.entity.RoleToPermission;
import com.cb.users.entity.Roles;
import com.cb.users.rq.CreateRolesRq;
import com.cb.users.rq.RolesTOPermissionsRq;
import com.cb.users.rq.UpdateRolesRq;
import com.cb.users.rs.RolesRs;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RolesMapper {

    public static Roles mapToRolesOld(CreateRolesRq rq, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> ");
        }
        try {
            Roles roles = mapper.map(rq, Roles.class);
            LocalDateTime createdOn = LocalDateTime.now();
            roles.setCreatedBy("Admin");
            roles.setCreatedOn(createdOn);
            roles.setUpdatedBy("Admin");
            roles.setUpdatedOn(createdOn);
            return roles;
        } catch (Exception e) {
            log.error("Exception in mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> {0}", e);
            throw e;
        }
    }

    public static Roles mapToRoles(CreateRolesRq rq, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> ");
        }
        try {
            if(rq == null){
                return null;
            }
            Roles roles = new Roles();
            roles.setName(rq.getName());
            List<RoleToPermission> permissionsList = new ArrayList<>();
//            permissions.setId(rq.getPermissions().get);
            rq.getPermissions().forEach(permission -> {
                Permissions permissions = new Permissions();
                permissions.setName(permission.getName());
                permissions.setId(permission.getId());
//                Roles role = new Roles();
//                role.setId();
                RoleToPermission roleToPermission = new RoleToPermission();
                roleToPermission.setPermissions(permissions);
                permissionsList.add(roleToPermission);
            });
            roles.setPermissions(permissionsList);
            LocalDateTime createdOn = LocalDateTime.now();
            roles.setCreatedBy("Admin");
            roles.setCreatedOn(createdOn);
            roles.setUpdatedBy("Admin");
            roles.setUpdatedOn(createdOn);
            return roles;
        } catch (Exception e) {
            log.error("Exception in mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> {0}", e);
            throw e;
        }
    }

    public static Roles mapToRoles(UpdateRolesRq rq, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> ");
        }
        try {
            Roles roles = mapper.map(rq, Roles.class);
            LocalDateTime createdOn = LocalDateTime.now();
            roles.setUpdatedBy("Admin");
            roles.setUpdatedOn(createdOn);
            return roles;
        } catch (Exception e) {
            log.error("Exception in mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> {0}", e);
            throw e;
        }
    }

    public static RolesRs mapToRoles(Roles roles, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> ");
        }
        try {
            return mapper.map(roles, RolesRs.class);
        } catch (Exception e) {
            log.error("Exception in mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> {0}", e);
            throw e;
        }
    }

}
