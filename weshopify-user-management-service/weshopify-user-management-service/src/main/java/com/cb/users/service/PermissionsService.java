package com.cb.users.service;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.exceptions.PermissionsException;
import com.cb.users.rq.PermissionsRq;

import java.util.List;

public interface PermissionsService {


    BaseDataRs createPermission(PermissionsRq rq) throws PermissionsException;

    PermissionsRq updatePermission(PermissionsRq rq);

    PermissionsRq findPermission(int id);

    List<PermissionsRq> deletePermission(int id);

    List<PermissionsRq> findAllPermission(int id);
    List<PermissionsRq> SearchPermission(int id);

}
