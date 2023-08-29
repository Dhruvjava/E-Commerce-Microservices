package com.cb.users.service.impl;

import com.cb.users.bean.PermissionsBean;
import com.cb.users.entity.Permissions;
import com.cb.users.repo.PermissionsRepo;
import com.cb.users.service.PermissionsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsRepo permissionsRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PermissionsBean createPermission(PermissionsBean rq) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Executing createPermission(PermissionsBean) ->");
        }
        try {
            Permissions permissions = mapToPermissions(rq);
        } catch (Exception e) {
            log.error("Exception in reatePermission(PermissionsBean) -> {0}", e);
            throw new Exception(e.getMessage());
        }

        return null;
    }

    @Override
    public PermissionsBean updatePermission(PermissionsBean rq) {
        return null;
    }

    @Override
    public PermissionsBean findPermission(int id) {
        return null;
    }

    @Override
    public List<PermissionsBean> deletePermission(int id) {
        return null;
    }

    @Override
    public List<PermissionsBean> findAllPermission(int id) {
        return null;
    }

    @Override
    public List<PermissionsBean> SearchPermission(int id) {
        return null;
    }

    private Permissions mapToPermissions(PermissionsBean permissionsBean) {
        if (log.isDebugEnabled()) {
            log.debug("mapToPermissions(PermissionsBean) ->");
        }
        try {
            return modelMapper
                    .map(permissionsBean, Permissions.class);
        } catch (Exception e) {
            log.error("Exception in mapToPermissions(PermissionsBean permissionsBean) -> {0}", e);
            throw e;
        }
    }
}
