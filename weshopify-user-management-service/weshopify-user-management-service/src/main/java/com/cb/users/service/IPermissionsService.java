package com.cb.users.service;

import com.cb.exceptions.PermissionsException;
import com.cb.exceptions.PermissionsNotFoundException;
import com.cb.users.rq.PermissionsRq;
import org.cb.commons.base.datars.BaseDataRs;

public interface IPermissionsService {


    BaseDataRs createPermission(PermissionsRq rq) throws PermissionsException;

    BaseDataRs updatePermission(PermissionsRq rq) throws PermissionsNotFoundException;

    BaseDataRs findPermission(int id);

    BaseDataRs deletePermission(int id);

    BaseDataRs findAllPermission();

    BaseDataRs SearchPermission(int id);

}
