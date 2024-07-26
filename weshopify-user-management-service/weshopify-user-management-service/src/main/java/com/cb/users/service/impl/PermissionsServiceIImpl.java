package com.cb.users.service.impl;

import com.cb.Messages;
import com.cb.users.constants.ErrorCodes;
import com.cb.users.constants.MessageCodes;
import com.cb.users.datars.PermissionsDataRSs;
import com.cb.users.datars.PermissionsDataRs;
import com.cb.users.entity.Permissions;
import com.cb.users.helper.PermissionsHelper;
import com.cb.users.mapper.PermissionsMapper;
import com.cb.users.repo.PermissionsRepo;
import org.cb.commons.base.datars.BaseDataRs;
import org.cb.commons.base.rs.ErrorRs;
import com.cb.exceptions.PermissionsException;
import com.cb.exceptions.PermissionsNotFoundException;
import com.cb.users.rq.PermissionsRq;
import com.cb.users.rs.PermissionsRs;
import com.cb.users.service.IPermissionsService;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PermissionsServiceIImpl implements IPermissionsService {

    @Autowired
    private PermissionsRepo permissionsRepo;

    @Autowired
    private Messages messages;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseDataRs createPermission(PermissionsRq rq) throws PermissionsException {
        if (log.isDebugEnabled()) {
            log.debug("Executing createPermission(PermissionsRq) ->");
        }
        try {
            List<ErrorRs> errors = PermissionsHelper.validateCreatePermissions(rq, messages);
            if (Utils.isNotEmpty(errors)) {
                String detail = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                log.error(detail);
                throw new PermissionsException(ErrorCodes.EC_INVALID_INPUT, detail, errors);
            }
            boolean isExists = permissionsRepo.existsByName(rq.getName());
            if (isExists) {
                String detail = messages.getErrorProperty(ErrorCodes.EC_PERMISSIONS_ALREADY_EXISTS);
                log.info(detail);
                throw new PermissionsException(ErrorCodes.EC_PERMISSIONS_ALREADY_EXISTS, detail);
            }
            Permissions permissions = PermissionsMapper.mapToPermissions(rq, modelMapper);
            permissions = permissionsRepo.save(permissions);
            PermissionsRs rs = PermissionsMapper.mapToPermissionsRs(permissions, modelMapper);
            String message = messages.getMessageProperty(MessageCodes.MC_CREATED_SUCCESSFUL);
            return new PermissionsDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in createPermission(CreatePermissionsRq) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs updatePermission(PermissionsRq rq) throws PermissionsNotFoundException {
        if (log.isDebugEnabled()) {
            log.debug("Executing updatePermission(UpdatePermissionsRq) -> ");
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
                throw new PermissionsNotFoundException(ErrorCodes.EC_PERMISSIONS_NOT_FOUND, detail);
            }
            PermissionsRs rs = PermissionsMapper.mapToPermissionsRs(permissions, modelMapper);
            String message = messages.getMessageProperty(MessageCodes.MC_UPDATED_SUCCESSFUL);
            return new PermissionsDataRs(message, rs);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BaseDataRs findPermission(int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing findPermission(id) -> ");
        }
        try {
            Permissions permissions = permissionsRepo.findById(id).orElseThrow(() -> {
                String detail = messages.getMessageProperty(MessageCodes.MC_NO_RECORDS_FOUND);
                log.warn(detail);
                return new PermissionsNotFoundException(MessageCodes.MC_NO_RECORDS_FOUND, detail);
            });
            PermissionsRs rs = PermissionsMapper.mapToPermissionsRs(permissions, modelMapper);
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFUL);
            return new PermissionsDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in findPermission(id) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs deletePermission(int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing deletePermission(int id) ->");
        }
        try {
            Optional.of(permissionsRepo.existsById(id))
                    .filter(exists -> exists)
                    .ifPresentOrElse(exist ->
                            permissionsRepo.deleteById(id),
                            () -> {
                String detail = messages.getMessageProperty(MessageCodes.MC_NO_RECORDS_FOUND);
                log.warn(detail);
                throw new PermissionsNotFoundException(MessageCodes.MC_NO_RECORDS_FOUND, detail);
            });
            String message = messages.getMessageProperty(MessageCodes.MC_DELETED_SUCCESSFUL);
            return new PermissionsDataRs(message);
        } catch (Exception e) {
            log.error("Exception in deletePermission(int id) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findAllPermission() {
        if (log.isDebugEnabled()) {
            log.debug("Executing findAllPermission() ->");
        }
        try {
            List<PermissionsRs> permissionsRSs = new ArrayList<>();
            Optional.of(permissionsRepo.findAll())
                    .filter(Utils::isNotEmpty)
                    .ifPresentOrElse(permissions ->
                            permissions.forEach(permission ->
                                    permissionsRSs.add(PermissionsMapper.mapToPermissionsRs(permission, modelMapper))),
                            () -> {
                String detail = messages.getMessageProperty(MessageCodes.MC_NO_RECORDS_FOUND);
                log.info(detail);
                throw new PermissionsNotFoundException(MessageCodes.MC_NO_RECORDS_FOUND, detail);
            });
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFUL);
            return new PermissionsDataRSs(message, permissionsRSs);
        } catch (Exception e) {
            log.error("Exception in findAllPermission() -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs SearchPermission(int id) {
        return null;
    }


}
