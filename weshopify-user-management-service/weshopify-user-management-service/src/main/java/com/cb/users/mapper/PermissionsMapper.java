package com.cb.users.mapper;

import com.cb.users.entity.Permissions;
import com.cb.users.rs.PermissionsRs;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class PermissionsMapper {

    public static PermissionsRs mapToPermissionsRs(Permissions permissions, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToPermissionsRs(Permissions) -> ");
        }
        try {
            return mapper.map(permissions, PermissionsRs.class);
        } catch (Exception e) {
            log.error("Exception in mapToPermissionsRs(Permissions) -> {0}", e);
            throw e;
        }
    }

}
