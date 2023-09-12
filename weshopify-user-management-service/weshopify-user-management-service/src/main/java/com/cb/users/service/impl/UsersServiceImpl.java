package com.cb.users.service.impl;

import com.cb.Messages;
import com.cb.base.data.rs.BaseDataRs;
import com.cb.exceptions.RolesException;
import com.cb.users.constants.ErrorCodes;
import com.cb.users.entity.Roles;
import com.cb.users.entity.Users;
import com.cb.users.repo.RolesRepo;
import com.cb.users.repo.UsersRepo;
import com.cb.users.rq.CreateUsersRq;
import com.cb.users.rq.UpdateUsersRq;
import com.cb.users.service.IRolesService;
import com.cb.users.service.IUsersSerice;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public BaseDataRs createUsers(CreateUsersRq rq) {
        return null;
    }

    @Override
    public BaseDataRs updateUsers(UpdateUsersRq rq) {
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
