package com.cb.users.mapper;

import com.cb.users.entity.Permissions;
import com.cb.users.entity.RoleToPermission;
import com.cb.users.entity.Roles;
import com.cb.users.repo.PermissionsRepo;
import com.cb.users.rq.CreateRolesRq;
import com.cb.users.rq.UpdateRolesRq;
import com.cb.users.rs.PermissionsRs;
import com.cb.users.rs.RolesRs;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class RolesMapper {

    public static Roles mapToRolesOld(CreateRolesRq rq, ModelMapper mapper, PermissionsRepo perRepo) {
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
            List<RoleToPermission> roleToPermissionsList = new ArrayList<>();
            if (rq.getPermissions() != null && !Utils.isEmpty(rq.getPermissions())) {
                rq.getPermissions().forEach(permissionsBean -> {
                    RoleToPermission roleToPermissions = new RoleToPermission();

                    Permissions permissionsEntity = perRepo.findById(permissionsBean.getId()).get();
                    roleToPermissions.setPermissions(permissionsEntity);
                    roleToPermissions.setRoles(roles);
                    roleToPermissionsList.add(roleToPermissions);
                });
            }
            roles.setRoleToPermissions(roleToPermissionsList);
            return roles;
        } catch (Exception e) {
            log.error("Exception in mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> {0}", e);
            throw e;
        }
    }

    public static Roles mapToRoles(UpdateRolesRq rq, ModelMapper mapper, PermissionsRepo perRepo) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToRoles(UpdateRolesRq rq, ModelMapper mapper) -> ");
        }
        try {
            Roles roles = mapper.map(rq, Roles.class);
            LocalDateTime createdOn = LocalDateTime.now();
            roles.setUpdatedBy("Admin");
            roles.setUpdatedOn(createdOn);
            List<RoleToPermission> roleToPermissionsList = new ArrayList<>();
            if (rq.getPermissions() != null && !Utils.isEmpty(rq.getPermissions())) {
                rq.getPermissions().forEach(permissionsBean -> {
                    RoleToPermission roleToPermissions = new RoleToPermission();

                    Permissions permissionsEntity = perRepo.findById(permissionsBean.getId()).get();
                    roleToPermissions.setPermissions(permissionsEntity);
                    roleToPermissions.setRoles(roles);
                    roleToPermissionsList.add(roleToPermissions);
                });
            }
            roles.setRoleToPermissions(roleToPermissionsList);
            return roles;
        } catch (Exception e) {
            log.error("Exception in mapToRoles(UpdateRolesRq rq, ModelMapper mapper) -> {0}", e);
            throw e;
        }
    }

    public static RolesRs mapToRoles(Roles roles, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToRoles(Roles roles, ModelMapper mapper) -> ");
        }
        try {
            RolesRs rs = mapper.map(roles, RolesRs.class);
            List<PermissionsRs> permissionsList = Optional.ofNullable(roles.getRoleToPermissions()).get().stream()
                    .map(RoleToPermission::getPermissions)
                    .map(permissions -> mapper.map(permissions, PermissionsRs.class))
                    .collect(Collectors.toList());
            rs.setPermissions(permissionsList);
            return rs;
        } catch (Exception e) {
            log.error("Exception in mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> {0}", e);
            throw e;
        }
    }

}
