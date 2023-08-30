package com.cb.users.service.impl;

import com.cb.Messages;
import com.cb.base.data.rs.BaseDataRs;
import com.cb.base.rs.ErrorRs;
import com.cb.exceptions.PermissionsException;
import com.cb.exceptions.PermissionsNotFoundException;
import com.cb.users.constants.ErrorCodes;
import com.cb.users.constants.MessageCodes;
import com.cb.users.datars.PermissionsDataRs;
import com.cb.users.entity.Permissions;
import com.cb.users.helper.PermissionsHelper;
import com.cb.users.mapper.PermissionsMapper;
import com.cb.users.repo.PermissionsRepo;
import com.cb.users.rq.CreatePermissionsRq;
import com.cb.users.rq.UpdatePermissionsRq;
import com.cb.users.rs.PermissionsRs;
import com.cb.users.service.PermissionsService;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public BaseDataRs createPermission(CreatePermissionsRq rq) throws PermissionsException {
        if (log.isDebugEnabled()) {
            log.debug("Executing createPermission(PermissionsBean) ->");
        }
        try {
            List<ErrorRs> errors = PermissionsHelper.validateCreatePermissions(rq, messages);
            if (Utils.isNotEmpty(errors)) {
                String detail = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                log.error(detail);
                throw new PermissionsException(ErrorCodes.EC_INVALID_INPUT, detail, errors);
            }
            Permissions permissions = PermissionsMapper.mapToPermissions(rq, modelMapper);
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
    public BaseDataRs updatePermission(UpdatePermissionsRq rq) throws PermissionsNotFoundException{
        if (log.isDebugEnabled()) {
            log.debug("Executing updatePermission(PermissionsRq) -> ");
        }
        try {
            List<ErrorRs> errors = PermissionsHelper.validateUpdatePermissions(rq, messages);
            if (Utils.isNotEmpty(errors)) {
                String detail = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                log.error(detail);
                throw new PermissionsException(ErrorCodes.EC_INVALID_INPUT, detail, errors);
            }
            Permissions permissions = PermissionsMapper.mapToPermissions(rq, modelMapper);
            if (permissionsRepo.existsById(rq.getId())) {
                permissions = permissionsRepo.save(permissions);
            } else {
                String detail = messages.getErrorProperty(ErrorCodes.EC_PERMISSIONS_NOT_FOUND);
                throw new PermissionsNotFoundException(ErrorCodes.EC_PERMISSIONS_NOT_FOUND, detail, detail);
            }
            PermissionsRs rs = PermissionsMapper.mapToPermissionsRs(permissions, modelMapper);
            String message = messages.getMessageProperty(MessageCodes.MC_UPDATED_SUCCESSFUL);
            return new PermissionsDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in updatePermission(PermissionsRq) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findPermission(int id) {
        return null;
    }

    @Override
    public BaseDataRs deletePermission(int id) {
        return null;
    }

    @Override
    public BaseDataRs findAllPermission(int id) {
        return null;
    }

    @Override
    public BaseDataRs SearchPermission(int id) {
        return null;
    }


}
