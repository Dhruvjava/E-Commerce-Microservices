package com.cb.users.service;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.exceptions.PermissionsException;
import com.cb.exceptions.PermissionsNotFoundException;
import com.cb.users.rq.CreatePermissionsRq;
import com.cb.users.rq.UpdatePermissionsRq;

public interface PermissionsService {


    BaseDataRs createPermission(CreatePermissionsRq rq) throws PermissionsException;

    BaseDataRs updatePermission(UpdatePermissionsRq rq) throws PermissionsNotFoundException;

    BaseDataRs findPermission(int id);

    BaseDataRs deletePermission(int id);

    BaseDataRs findAllPermission();

    BaseDataRs SearchPermission(int id);

}
