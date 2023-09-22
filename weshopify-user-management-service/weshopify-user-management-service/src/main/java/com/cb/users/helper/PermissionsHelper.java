package com.cb.users.helper;

import com.cb.Messages;
import com.cb.base.rs.ErrorRs;
import com.cb.users.constants.ErrorCodes;
import com.cb.users.rq.CreatePermissionsRq;
import com.cb.users.rq.UpdatePermissionsRq;
import com.cb.users.rs.PermissionsRs;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PermissionsHelper {
    public static List<ErrorRs> validateCreatePermissions(CreatePermissionsRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateCreatePermissions(PermissionsRq) -> ");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (Utils.isEmpty(rq.getName())) {
                log.error(ErrorCodes.EC_REQUIRED_PERMISSIONS_NAME);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_PERMISSIONS_NAME, messages));
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateCreatePermissions(PermissionsRq) -> {0}", e);
            throw e;
        }
    }

    public static List<ErrorRs> validateUpdatePermissions(UpdatePermissionsRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateUpdatePermissions(PermissionsRq) -> ");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (rq.getId() == null) {
                log.error(ErrorCodes.EC_REQUIRED_PERMISSIONS_ID);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_PERMISSIONS_ID, messages));
            }
            if (Utils.isEmpty(rq.getName())) {
                log.error(ErrorCodes.EC_REQUIRED_PERMISSIONS_NAME);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_PERMISSIONS_NAME, messages));
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateUpdatePermissions(PermissionsRq) -> {0}", e);
            throw e;
        }
    }

    public static List<ErrorRs> validateUpdatePermissions(PermissionsRs rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateUpdatePermissions(PermissionsRq) -> ");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (rq.getId() == null) {
                log.error(ErrorCodes.EC_REQUIRED_PERMISSIONS_ID);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_PERMISSIONS_ID, messages));
            }
            if (Utils.isEmpty(rq.getName())) {
                log.error(ErrorCodes.EC_REQUIRED_PERMISSIONS_NAME);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_PERMISSIONS_NAME, messages));
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateUpdatePermissions(PermissionsRq) -> {0}", e);
            throw e;
        }
    }
}
