package com.cb.users.service.impl;

import com.cb.Messages;
import com.cb.base.data.rs.BaseDataRs;
import com.cb.base.rs.ErrorRs;
import com.cb.exceptions.RolesException;
import com.cb.exceptions.RolesNotFoundException;
import com.cb.users.constants.ErrorCodes;
import com.cb.users.constants.MessageCodes;
import com.cb.users.datars.RolesDataRSs;
import com.cb.users.datars.RolesDataRs;
import com.cb.users.entity.Roles;
import com.cb.users.helper.RolesHelper;
import com.cb.users.mapper.RolesMapper;
import com.cb.users.repo.RolesRepo;
import com.cb.users.rq.CreateRolesRq;
import com.cb.users.rq.UpdateRolesRq;
import com.cb.users.rs.RolesRs;
import com.cb.users.service.IRolesService;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RolesServiceImpl implements IRolesService {

    @Autowired
    private RolesRepo rolesRepo;

    @Autowired
    private Messages messages;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BaseDataRs createRole(CreateRolesRq rq) throws RolesException {
        if (log.isDebugEnabled()) {
            log.debug("Executing createRole(CreateRolesRq rq) -> ");
        }
        try {
            List<ErrorRs> errors = RolesHelper.validateCreateRole(rq, messages);
            if (Utils.isNotEmpty(errors)) {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                log.warn(errorMessage);
                throw new RolesException(ErrorCodes.EC_INVALID_INPUT, errorMessage);
            }
            String name = Utils.getValidString(rq.getName());
            Optional<Roles> rolesOpt = rolesRepo.findByName(name);
            if (rolesOpt.isPresent()) {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_ALREADY_EXISTS);
                log.warn(errorMessage);
                throw new RolesException(ErrorCodes.EC_ROLE_ALREADY_EXISTS, errorMessage);
            }
            Roles roles = RolesMapper.mapToRoles(rq, mapper);
            roles = Optional.of(rolesRepo.save(roles))
                    .orElseThrow(
                            () -> new RolesException
                                    (ErrorCodes.EC_UNKNOWN_DATABASE_ERROR,
                                            messages.getErrorProperty
                                                    (ErrorCodes.EC_ROLE_CREATED_FAILED)));
//            roles = rolesRepo.save(roles);
            RolesRs rs = RolesMapper.mapToRoles(roles, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_CREATED_SUCCESSFUL);
            return new RolesDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in createRole(CreateRolesRq rq) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs updateRole(UpdateRolesRq rq) throws RolesNotFoundException {
        if (log.isDebugEnabled()) {
            log.debug("Executing updateRole(UpdateRolesRq rq) ->");
        }
        try {
            List<ErrorRs> errors = RolesHelper.validateUpdateRole(rq, messages);
            if (Utils.isNotEmpty(errors)) {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                log.warn(errorMessage);
                throw new RolesException(ErrorCodes.EC_INVALID_INPUT, errorMessage);
            }
            int id = rq.getId();
            Roles roles = RolesMapper.mapToRoles(rq, mapper);
            if (id != 0 && rolesRepo.existsById(id)) {
                roles = Optional.of(rolesRepo.save(roles))
                        .orElseThrow(
                                () -> {
                                    String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_UPDATE_FAILED);
                                    log.info(errorMessage);
                                    return new RolesException
                                            (ErrorCodes.EC_UNKNOWN_DATABASE_ERROR,
                                                    errorMessage);
                                });
            }
            RolesRs rs = RolesMapper.mapToRoles(roles, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_UPDATED_SUCCESSFUL);
            return new RolesDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in updateRole(UpdateRolesRq rq) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findRole(int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing findRole(int id) ->");
        }
        try {
            Roles roles = Optional.of(rolesRepo.findById(id))
                    .orElseThrow(
                            () -> {
                                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_FIND_FAILED);
                                log.info(errorMessage);
                                return new RolesException(ErrorCodes.EC_UNKNOWN_DATABASE_ERROR, errorMessage);
                            }).orElseThrow(() -> {
                        String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                        log.info(errorMessage);
                        return new RolesNotFoundException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);

                    });
//            Roles roles = null;
//            if (rolesOpt.isPresent()) {
//                roles = rolesOpt.get();
//            } else {
//                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
//                log.info(errorMessage);
//                throw new RolesNotFoundException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);
//            }
            RolesRs rs = RolesMapper.mapToRoles(roles, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFUL);
            return new RolesDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in findRole(int id) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findRole(String name) {
        if (log.isDebugEnabled()) {
            log.debug("Executing findRole(String name) ->");
        }
        try {

            if (Utils.isEmpty(name)) {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_REQUIRED_ROLES_NAME);
                log.info(errorMessage);
                throw new RolesException(ErrorCodes.EC_REQUIRED_ROLES_NAME, errorMessage);
            }
            Roles role = rolesRepo.findByName(name).orElseThrow(() -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                log.info(errorMessage);
                return new RolesNotFoundException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);
            });
            RolesRs rs = RolesMapper.mapToRoles(role, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFUL);
            return new RolesDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in findRole(String name) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs deleteRole(int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing deleteRole(int id) ->");
        }
        try {
            Optional.of(rolesRepo.existsById(id))
                    .filter(exists -> exists)
                    .ifPresentOrElse(exist -> rolesRepo.deleteById(id), () -> {
                        String errorMessage = messages.getMessageProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                        log.warn(errorMessage);
                        throw new RolesNotFoundException(MessageCodes.MC_NO_RECORDS_FOUND, errorMessage);
                    });
            String message = messages.getMessageProperty(MessageCodes.MC_DELETED_SUCCESSFUL);
            return new RolesDataRs(message);
        } catch (Exception e) {
            log.error("Exception in deleteRole(int id) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findAllRole() {
        if (log.isDebugEnabled()) {
            log.debug("Executing findAllRole() ->");
        }
        try {
            List<Roles> roles = Optional.of(rolesRepo.findAll()).orElseThrow(() -> {
                String errorMessage = messages.getMessageProperty(MessageCodes.MC_NO_RECORDS_FOUND);
                log.info(errorMessage);
                return new RolesNotFoundException(MessageCodes.MC_NO_RECORDS_FOUND, errorMessage);
            });
            List<RolesRs> rs = roles.stream().map(role -> RolesMapper.mapToRoles(role, mapper)).toList();
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFUL);
            return new RolesDataRSs(message, rs);
        } catch (Exception e) {
            log.error("Exception in findAllRole() -> {0}", e);
            throw e;
        }
    }
}
