package com.cb.users.helper;

import com.cb.Messages;
import com.cb.base.rs.ErrorRs;
import com.cb.users.constants.ErrorCodes;
import com.cb.users.rq.UsersRq;
import com.cb.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class UsersHelper {

    private UsersHelper() {
    }

    public static List<ErrorRs> validateCreateUsers(UsersRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateCreateUsers(UsersRq rq, Messages messages) ->");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (Utils.isEmpty(rq.getUserid())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_USERID);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_USERS_USERID, messages));
            }
            if (Utils.isEmpty(rq.getFirstname())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRSTNAME);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_USERS_FIRSTNAME, messages));
            }
            if (Utils.isEmpty(rq.getLastname())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_LASTNAME);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_USERS_LASTNAME, messages));
            }
            if (Utils.isEmpty(rq.getMobile())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_MOBILE);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_USERS_MOBILE, messages));
            }
            if (Utils.isEmpty(rq.getEmail())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_EMAIL);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_USERS_EMAIL, messages));
            }
            if (Utils.isNotEmpty(rq.getEmail())) {
                if (!Utils.isValidEmail(rq.getEmail())) {
                    log.error(ErrorCodes.EC_INVALID_USERS_EMAIL);
                    errors.add(Utils.populateErrorRSs(ErrorCodes.EC_INVALID_USERS_EMAIL, messages));
                }
            }
            if (rq.getRole() == null) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_ROLE);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_USERS_ROLE, messages));
            }
            if (rq.getRole() != null) {
                List<ErrorRs> roleErrors = RolesHelper.validateUpdateRole(rq.getRole(), messages);
                errors.addAll(roleErrors);
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateCreateUsers(UsersRq rq, Messages messages) -> {0}", e);
            throw e;
        }
    }

    public static List<ErrorRs> validateUpdateUsers(UsersRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateCreateUsers(UsersRq rq, Messages messages) ->");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (rq.getId() == null) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_ID);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_USERS_ID, messages));
            }
            List<ErrorRs> validateErrors = validateCreateUsers(rq, messages);
            errors.addAll(validateErrors);
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateCreateUsers(UsersRq rq, Messages messages) -> {0}", e);
            return Collections.emptyList();
        }
    }
}
