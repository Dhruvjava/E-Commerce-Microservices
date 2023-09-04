package com.cb.users.service.impl;

import com.cb.Messages;
import com.cb.base.data.rs.BaseDataRs;
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
    public BaseDataRs provisioning(int roleId) {
        Optional.of(rolesService.findRole(roleId));
        return null;
    }

    @Override
    public BaseDataRs deProvisioning(int roleId) {
        return null;
    }

    @Override
    public BaseDataRs enableUser(String role) {
        return null;
    }
}
