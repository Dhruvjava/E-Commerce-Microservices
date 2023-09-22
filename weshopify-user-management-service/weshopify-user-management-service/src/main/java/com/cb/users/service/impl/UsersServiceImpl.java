package com.cb.users.service.impl;

import com.cb.Messages;
import com.cb.base.data.rs.BaseDataRs;
import com.cb.base.rs.ErrorRs;
import com.cb.exceptions.RolesNotFoundException;
import com.cb.exceptions.UsersException;
import com.cb.users.constants.ErrorCodes;
import com.cb.users.constants.MessageCodes;
import com.cb.users.datars.UserProvisioningDataRs;
import com.cb.users.entity.Roles;
import com.cb.users.entity.Users;
import com.cb.users.helper.UsersHelper;
import com.cb.users.repo.RolesRepo;
import com.cb.users.repo.UsersRepo;
import com.cb.users.rq.UsersRq;
import com.cb.users.service.IRolesService;
import com.cb.users.service.IUsersSerice;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                throw new UsersException(ErrorCodes.EC_INVALID_INPUT, errorMessage);
            }

        } catch (Exception e) {
            log.error("Exception in createUsers(UsersRq rq) -> {0}", e);
            throw e;
        }
        return null;
    }

    @Override
    public BaseDataRs updateUsers(UsersRq rq) {
        return null;
    }

    @Override
    public BaseDataRs findUser(int id) {
        return null;
    }

    @Override
    public BaseDataRs findUserByEmail(String email) {
        return null;
    }

    @Override
    public BaseDataRs deleteUsers(int id) {
        return null;
    }

    @Override
    public BaseDataRs findUsers() {
        return null;
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
    public BaseDataRs provisioning(Users userBO) {
        if (log.isDebugEnabled()) {
            log.debug("Executing provisioning(int roleId) ->");
        }
        try {
            Optional<Roles> rolesOpt = Optional.of(userBO.getRole()).map(role -> rolesRepo.findById(role.getId())).get();
            Roles role = null;
            if (rolesOpt.isPresent()) {
                role = rolesOpt.get();
            } else {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                log.warn(errorMessage);
                throw new RolesNotFoundException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);
            }
            userBO.setRole(role);
            String message = messages.getMessageProperty(MessageCodes.MC_USER_WITH_ROLE_PROVISIONING_SUCCESSFUL);
            return new UserProvisioningDataRs(message, userBO);
        } catch (Exception e) {
            log.error("Exception in provisioning(int roleId) -> {0}", e);
        }
        return null;
    }

    @Override
    public BaseDataRs deProvisioning(Users userB) {
        return null;
    }

    @Override
    public BaseDataRs enableUser(String role) {
        return null;
    }

    @Override
    public BaseDataRs unlockUser(String role) {
        return null;
    }
}
