package com.cb.users.service.impl;

import com.cb.Messages;
import com.cb.base.data.rs.BaseDataRs;
import com.cb.exceptions.PermissionsException;
import com.cb.users.constants.ErrorCodes;
import com.cb.users.constants.MessageCodes;
import com.cb.users.datars.PermissionsDataRs;
import com.cb.users.entity.Permissions;
import com.cb.users.mapper.PermissionsMapper;
import com.cb.users.repo.PermissionsRepo;
import com.cb.users.rq.PermissionsRq;
import com.cb.users.rs.PermissionsRs;
import com.cb.users.service.PermissionsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsRepo permissionsRepo;

    @Autowired
    private Messages messages;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseDataRs createPermission(PermissionsRq rq) throws PermissionsException{
        if (log.isDebugEnabled()) {
            log.debug("Executing createPermission(PermissionsBean) ->");
        }
        try {
            Permissions permissions = mapToPermissions(rq);
            permissions = permissionsRepo.save(permissions);
            PermissionsRs rs = PermissionsMapper.mapToPermissionsRs(permissions, modelMapper);
            String message = messages.getMessageProperty(MessageCodes.MC_CREATED_SUCCESSFUL);
            return new PermissionsDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in reatePermission(PermissionsBean) -> {0}", e);
            String detail = messages.getErrorProperty(ErrorCodes.EC_UNABLE_TO_SAVE_PERMISSIONS);
            throw new PermissionsException(ErrorCodes.EC_PROBLEM_IN_CREATE_PERMISSIONS, detail, e.getMessage());
        }
    }

    @Override
    public PermissionsRq updatePermission(PermissionsRq rq) {
        return null;
    }

    @Override
    public PermissionsRq findPermission(int id) {
        return null;
    }

    @Override
    public List<PermissionsRq> deletePermission(int id) {
        return null;
    }

    @Override
    public List<PermissionsRq> findAllPermission(int id) {
        return null;
    }

    @Override
    public List<PermissionsRq> SearchPermission(int id) {
        return null;
    }

    private Permissions mapToPermissions(PermissionsRq permissionsBean) {
        if (log.isDebugEnabled()) {
            log.debug("mapToPermissions(PermissionsBean) ->");
        }
        try {
            Permissions permissions = modelMapper.map(permissionsBean, Permissions.class);
            LocalDateTime date = LocalDateTime.now();
            permissions.setCreatedBy("Admin");
            permissions.setCreatedOn(date);
            permissions.setUpdatedBy("Admin");
            permissions.setUpdatedOn(date);
            return permissions;
        } catch (Exception e) {
            log.error("Exception in mapToPermissions(PermissionsBean permissionsBean) -> {0}", e);
            throw e;
        }
    }

    private PermissionsRq mapToPermissionsBean(Permissions permissions) {
        if (log.isDebugEnabled()) {
            log.debug("mapToPermissions(PermissionsBean) ->");
        }
        try {
            return modelMapper.map(permissions, PermissionsRq.class);
        } catch (Exception e) {
            log.error("Exception in mapToPermissions(PermissionsBean permissionsBean) -> {0}", e);
            throw e;
        }
    }
}
