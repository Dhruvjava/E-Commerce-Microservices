package com.cb.users.helper;

import com.cb.Messages;
import com.cb.base.rs.ErrorRs;
import com.cb.users.constants.ErrorCodes;
import com.cb.users.rq.CreateRolesRq;
import com.cb.users.rq.UpdateRolesRq;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RolesHelper {

    public static List<ErrorRs> validateCreateRole(CreateRolesRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateCreateRole(CreateRolesRq rq) ->");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (Utils.isEmpty(rq.getName())) {
                log.error(ErrorCodes.EC_REQUIRED_PERMISSIONS_NAME);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_ROLES_NAME, messages));
            }
            if (Utils.isEmpty(rq.getPermissions())) {
                log.error(ErrorCodes.EC_REQUIRED_PERMISSIONS);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_PERMISSIONS, messages));
            }
            return errors;

        } catch (Exception e) {
            log.error("Exception in validateCreateRole(CreateRolesRq rq) -> {0}", e);
            throw e;
        }
    }

    public static List<ErrorRs> validateUpdateRole(UpdateRolesRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateCreateRole(UpdateRolesRq rq, Messages messages) ->");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (rq.getId() == null) {
                log.error(ErrorCodes.EC_REQUIRED_ROLES_ID);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_ROLES_ID, messages));
            }
            if (Utils.isEmpty(rq.getName())) {
                log.error(ErrorCodes.EC_REQUIRED_ROLES_NAME);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_ROLES_NAME, messages));
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateCreateRole(UpdateRolesRq rq, Messages messages) -> {0}", e);
            throw e;
        }
    }
}
