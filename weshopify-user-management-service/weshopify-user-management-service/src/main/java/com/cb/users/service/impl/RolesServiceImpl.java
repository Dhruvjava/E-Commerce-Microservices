package com.cb.users.service.impl;

import com.cb.Messages;
import com.cb.base.data.rs.BaseDataRs;
import com.cb.base.rs.ErrorRs;
import com.cb.exceptions.RolesException;
import com.cb.exceptions.RolesNotFoundException;
import com.cb.users.helper.RolesHelper;
import com.cb.users.repo.RolesRepo;
import com.cb.users.rq.CreateRolesRq;
import com.cb.users.rq.UpdateRolesRq;
import com.cb.users.service.IRolesService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            List<ErrorRs> errors = RolesHelper.validateCreateRole(rq);
        } catch (Exception e) {
            log.error("Exception in createRole(CreateRolesRq rq) -> {0}", e);
        }
        return null;
    }

    @Override
    public BaseDataRs updateRole(UpdateRolesRq rq) throws RolesNotFoundException {
        return null;
    }

    @Override
    public BaseDataRs findRole(int id) {
        return null;
    }

    @Override
    public BaseDataRs deleteRole(int id) {
        return null;
    }

    @Override
    public BaseDataRs findAllRole() {
        return null;
    }
}
