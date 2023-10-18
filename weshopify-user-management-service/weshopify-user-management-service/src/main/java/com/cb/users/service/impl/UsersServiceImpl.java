package com.cb.users.service.impl;

import com.cb.Messages;
import com.cb.base.data.rs.BaseDataRs;
import com.cb.base.rs.ErrorRs;
import com.cb.exceptions.RolesNotFoundException;
import com.cb.exceptions.UsersDuplicateFieldVoilationException;
import com.cb.exceptions.UsersException;
import com.cb.exceptions.UsersNotFoundException;
import com.cb.users.constants.ErrorCodes;
import com.cb.users.constants.MessageCodes;
import com.cb.users.datars.UserProvisioningDataRs;
import com.cb.users.datars.UsersDataRSs;
import com.cb.users.datars.UsersDataRs;
import com.cb.users.entity.Roles;
import com.cb.users.entity.Users;
import com.cb.users.helper.UsersHelper;
import com.cb.users.mapper.UsersMapper;
import com.cb.users.repo.RolesRepo;
import com.cb.users.repo.UsersRepo;
import com.cb.users.rq.UsersRq;
import com.cb.users.rs.UsersRs;
import com.cb.users.service.IRolesService;
import com.cb.users.service.IUsersSerice;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImpl implements IUsersSerice {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private Messages messages;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private RolesRepo rolesRepo;

    @Override
    public BaseDataRs createUsers(UsersRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing createUsers(UsersRq rq) ->");
        }
        try {
            List<ErrorRs> errors = UsersHelper.validateCreateUsers(rq, messages);
            if (Utils.isNotEmpty(errors)) {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                log.warn(errorMessage);
                throw new UsersException(ErrorCodes.EC_INVALID_INPUT, errorMessage, errors);
            }
            Users users = UsersMapper.mapToUsers(rq, mapper);
            provisioning(users);

            users = usersRepo.save(users);
//            Roles roles = rolesRepo.findById(users.getRole().getId()).orElse(null);
//            users.setRole(roles);
            UsersRs userRs = UsersMapper.maptoUsers(users, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_CREATED_SUCCESSFUL);
            return new UsersDataRs(message, userRs);
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
            log.error(errorMessage);
            List<ErrorRs> errors = new ArrayList<>();
            if (ex.getMessage().contains(rq.getEmail())) {
                ErrorRs error = new ErrorRs();
                String errorMessages = messages.getErrorProperty(ErrorCodes.EC_USER_EMAIL_ALREADY_EXISTS);
                log.info(errorMessages);
                error.setCode(ErrorCodes.EC_USER_EMAIL_ALREADY_EXISTS);
                error.setMessage(errorMessages);
                errors.add(error);
            }
            if (ex.getMessage().contains(rq.getUserid())) {
                ErrorRs error = new ErrorRs();
                String errorMessages = messages.getErrorProperty(ErrorCodes.EC_USER_USERID_ALREADY_EXISTS);
                log.info(errorMessages);
                error.setCode(ErrorCodes.EC_USER_USERID_ALREADY_EXISTS);
                error.setMessage(errorMessages);
                errors.add(error);
            }
            if (ex.getMessage().contains(rq.getMobile())) {
                ErrorRs error = new ErrorRs();
                String errorMessages = messages.getErrorProperty(ErrorCodes.EC_USER_MOBILE_ALREADY_EXISTS);
                log.info(errorMessages);
                error.setCode(ErrorCodes.EC_USER_MOBILE_ALREADY_EXISTS);
                error.setMessage(errorMessages);
                errors.add(error);
            }
            throw new UsersDuplicateFieldVoilationException(ErrorCodes.EC_INVALID_INPUT, errorMessage, errors);
        } catch (Exception e) {
            log.error("Exception in createUsers(UsersRq rq) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs updateUsers(UsersRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing updateUsers(UsersRq rq) ->");
        }
        try {
            List<ErrorRs> errors = UsersHelper.validateUpdateUsers(rq, messages);
            if (Utils.isNotEmpty(errors)) {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                log.error(errorMessage);
                throw new UsersException(ErrorCodes.EC_INVALID_INPUT, errorMessage, errors);
            }
            Users users = UsersMapper.mapToUsers(rq, mapper);
            String operation = rq.getRole().getOperation();
            if (StringUtils.hasText(operation) && "deprovision".contentEquals(operation)) {
                deProvisioning(users);
            }
            if (users.getId() != 0 && usersRepo.existsById(users.getId())) {
                users = usersRepo.save(users);
            }
            UsersRs rs = UsersMapper.maptoUsers(users, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_UPDATED_SUCCESSFUL);
            return new UsersDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in updateUsers(UsersRq rq) -> " + e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findUser(int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing findUser(int id) ->");
        }
        try {
            Users users = usersRepo.findById(id).orElseThrow(() -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_USER_NOT_FOUND);
                log.info(errorMessage);
                return new UsersNotFoundException(ErrorCodes.EC_USER_NOT_FOUND, errorMessage);
            });
            UsersRs rs = UsersMapper.maptoUsers(users, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFUL);
            return new UsersDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in findUser(int id) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findUserByEmail(String email) {
        if (log.isDebugEnabled()) {
            log.debug("Executing findUserByEmail(String email) ->");
        }
        try {
            if (Utils.isEmpty(email)) {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_REQUIRED_EMAIL);
                log.error(errorMessage);
                throw new UsersException(ErrorCodes.EC_REQUIRED_EMAIL, errorMessage);
            }
            Users users = usersRepo.findByEmail(email).orElseThrow(() -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_USER_NOT_FOUND);
                log.info(errorMessage);
                return new UsersNotFoundException(ErrorCodes.EC_USER_NOT_FOUND, errorMessage);
            });
            UsersRs rs = UsersMapper.maptoUsers(users, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFUL);
            return new UsersDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in findUserByEmail(String email) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs deleteUsers(int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing deleteUsers(int id) ->");
        }
        try {
            if (id != 0 && usersRepo.existsById(id)) {
                usersRepo.deleteById(id);
            } else {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_USER_NOT_FOUND);
                log.info(errorMessage + " with id : " + id);
                throw new UsersException(ErrorCodes.EC_USER_NOT_FOUND, errorMessage + " with id : " + id);
            }
            String message = messages.getMessageProperty(MessageCodes.MC_DELETED_SUCCESSFUL);
            return new UsersDataRs(message);
        } catch (Exception e) {
            log.error("Exception in deleteUsers(int id) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findUsers() {
        if (log.isDebugEnabled()) {
            log.debug("Executing findUsers() ->");
        }
        try {
            List<Users> users = usersRepo.findAll();
            List<UsersRs> usersRs = new ArrayList<>();
            users.forEach(user -> {
                UsersRs rs = UsersMapper.maptoUsers(user, mapper);
                if (rs != null) {
                    usersRs.add(rs);
                }
            });
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFUL);
            return new UsersDataRSs(message, usersRs);
        } catch (Exception e) {
            log.error("Exception in findUsers() -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findUsers(int offSet, int limit) {
        return null;
    }

    @Override
    public BaseDataRs searchUsers() {
        return null;
    }

    @Override
    public void provisioning(Users userBO) {
        if (log.isDebugEnabled()) {
            log.debug("Executing provisioning(int roleId) ->");
        }
        try {
            Roles roles = Optional.of(userBO.getRole()).map(role -> rolesRepo.findById(role.getId())).get()
                    .orElseThrow(() -> {
                        String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                        log.warn(errorMessage);
                        return new RolesNotFoundException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);
                    });
//            Roles role = null;
//            if (rolesOpt.isPresent()) {
//                role = rolesOpt.get();
//            } else {
//                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
//                log.warn(errorMessage);
//                throw new RolesNotFoundException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);
//            }
            userBO.setRole(roles);
//            String message = messages.getMessageProperty(MessageCodes.MC_USER_WITH_ROLE_PROVISIONING_SUCCESSFUL);
//            return new UserProvisioningDataRs(message, userBO);
        } catch (Exception e) {
            log.error("Exception in provisioning(int roleId) -> {0}", e);
            throw e;
        }
    }

    @Override
    public void deProvisioning(Users userBO) {
        if (log.isDebugEnabled()) {
            log.debug("Executing deProvisioning(int roleId) ->");
        }
        try {
            Optional.of(userBO.getRole()).map(role -> rolesRepo.findById(role.getId())).orElseThrow(() -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                log.warn(errorMessage);
                return new RolesNotFoundException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);
            });
            userBO.setRole(null);
//            String message = messages.getMessageProperty(MessageCodes.MC_USER_WITH_ROLE_PROVISIONING_SUCCESSFUL);
//            return new UserProvisioningDataRs(message, userBO);
        } catch (Exception e) {
            log.error("Exception in deProvisioning(int roleId) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs enableUser(int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing enableUser(int id) ->");
        }
        try {
            Optional.of(usersRepo.findById(id)).get().ifPresentOrElse(users -> {
                users.setEnabled(true);
                usersRepo.save(users);
            }, () -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_USER_NOT_FOUND);
                log.info(errorMessage);
                throw new UsersException(ErrorCodes.EC_USER_NOT_FOUND, errorMessage);
            });
            String message = messages.getMessageProperty(MessageCodes.MC_ENABLED_SUCCESSFUL);
            UsersDataRs dataRs = (UsersDataRs) findUser(id);
            dataRs.setMessage(message);
            return dataRs;
        } catch (Exception e) {
            log.error("Exception in enableUser(int id) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs unlockUser(int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing unlockUser(int id) -> ");
        }
        try {
            Optional.of(usersRepo.findById(id)).get().ifPresentOrElse(users -> {
                users.setLocked(false);
                usersRepo.save(users);
            }, () -> {
                String errorMessage = messages.getErrorProperty("EC_USER_NOT_FOUND");
                log.info(errorMessage);
                throw new UsersException(ErrorCodes.EC_USER_NOT_FOUND, errorMessage);
            });
            String message = messages.getMessageProperty(MessageCodes.MC_UNLOCKED_SUCCESSFUL);
            UsersDataRs rs = (UsersDataRs) findUser(id);
            rs.setMessage(message);
            return rs;
        } catch (Exception e) {
            log.error("Exception in unlockUser(int id) -> {}", e);
            throw e;
        }
    }
}
