package com.cb.users.mapper;

import com.cb.Messages;
import com.cb.exceptions.PermissionsNotFoundException;
import com.cb.users.constants.ErrorCodes;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class RolesMapper {

    private RolesMapper() {
    }

    public static Roles mapToRoles(CreateRolesRq rq, ModelMapper mapper, PermissionsRepo perRepo, Messages messages) {
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

                    Permissions permissionsEntity = perRepo.findById(permissionsBean.getId()).orElseThrow(() -> {
                        String errorMessage = messages.getErrorProperty(ErrorCodes.EC_PERMISSIONS_NOT_FOUND);
                        log.warn(errorMessage);
                        return new PermissionsNotFoundException(ErrorCodes.EC_PERMISSIONS_NOT_FOUND, errorMessage);
                    });
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

    public static Roles mapToRoles(UpdateRolesRq rq, ModelMapper mapper, PermissionsRepo perRepo, Messages messages) {
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

                    Permissions permissionsEntity = perRepo.findById(permissionsBean.getId()).orElseThrow(() -> {
                        String errorMessage = messages.getErrorProperty(ErrorCodes.EC_PERMISSIONS_NOT_FOUND);
                        log.warn(errorMessage);
                        return new PermissionsNotFoundException(ErrorCodes.EC_PERMISSIONS_NOT_FOUND, errorMessage);
                    });
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
            List<PermissionsRs> permissionsList = Optional.ofNullable(roles.getRoleToPermissions()).map(list -> list.stream()
                    .map(RoleToPermission::getPermissions)
                    .map(permissions -> mapper.map(permissions, PermissionsRs.class))
                    .toList()).orElse(Collections.emptyList());
            rs.setPermissions(permissionsList);
            return rs;
        } catch (Exception e) {
            log.error("Exception in mapToRoles(CreateRolesRq rq, ModelMapper mapper) -> {0}", e);
            throw e;
        }
    }

}
