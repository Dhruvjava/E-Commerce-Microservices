package com.cb.users.helper;

import com.cb.base.rs.ErrorRs;
import com.cb.users.rq.CreateRolesRq;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RolesHelper {

    public static List<ErrorRs> validateCreateRole(CreateRolesRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateCreateRole(CreateRolesRq rq) ->");
        }
        try {

        } catch (Exception e) {
            log.error("Exception in validateCreateRole(CreateRolesRq rq) -> {0}", e);
        }
    }
}
