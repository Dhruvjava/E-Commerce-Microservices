package com.cb.users.service;

import com.cb.exceptions.RolesException;
import com.cb.exceptions.RolesNotFoundException;
import com.cb.users.rq.RolesRq;
import org.cb.commons.base.datars.BaseDataRs;

public interface IRolesService {

    BaseDataRs createRole(RolesRq rq) throws RolesException;

    BaseDataRs updateRole(RolesRq rq) throws RolesNotFoundException;

    BaseDataRs findRole(int id);

    BaseDataRs findRole(String name);

    BaseDataRs deleteRole(int id);

    BaseDataRs findAllRole();

}
