package com.cb.users.service;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.exceptions.RolesException;
import com.cb.exceptions.RolesNotFoundException;
import com.cb.users.rq.CreateRolesRq;
import com.cb.users.rq.UpdateRolesRq;

public interface IRolesService {

    BaseDataRs createRole(CreateRolesRq rq) throws RolesException;

    BaseDataRs updateRole(UpdateRolesRq rq) throws RolesNotFoundException;

    BaseDataRs findRole(int id);

    BaseDataRs deleteRole(int id);

    BaseDataRs findAllRole();

}
