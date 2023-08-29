package com.cb.users.service;

import com.cb.users.bean.PermissionsBean;

import java.util.List;

public interface PermissionsService {

    PermissionsBean createPermission(PermissionsBean rq) throws Exception;

    PermissionsBean updatePermission(PermissionsBean rq);

    PermissionsBean findPermission(int id);

    List<PermissionsBean> deletePermission(int id);

    List<PermissionsBean> findAllPermission(int id);
    List<PermissionsBean> SearchPermission(int id);

}
