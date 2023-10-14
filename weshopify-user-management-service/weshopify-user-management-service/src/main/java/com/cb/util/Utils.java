package com.cb.util;

import com.cb.Messages;
import com.cb.base.rs.ErrorRs;
import com.cb.constants.StringConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Utils {

    private static final Pattern EMAIL_PATTERN_REGIX = Pattern.compile("^([a-z0-9_.-]+)@([\\da-z\\.-]+)\\.([a-z]{2,})$");

    public static boolean isEmpty(List list) {
        return null == list || list.isEmpty();
    }

    public static boolean isEmpty(String string) {
        return null == string || string.trim().isEmpty();
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    public static ErrorRs populateErrorRSs(String codes, Messages message) {
        if (log.isDebugEnabled()) {
            log.debug("Executing populateErrorRs(List<String>) -> ");
        }
        try {
            if (isEmpty(codes)) {
                return null;
            }
            ErrorRs error = new ErrorRs();
            error.setCode(codes);
            error.setMessage(message.getErrorProperty(codes));
            return error;
        } catch (Exception e) {
            log.error("Exception in populateErrorRs(List<String>) -> {0}", e);
            return null;
        }
    }

    public static String getValidString(String str) {
        return ((null == str) ? StringConstants.EMPTY : str.trim());
    }

    public static boolean isValidEmail(final String email) {
        if (log.isDebugEnabled()) {
            log.debug("Executing isValidEmail(final String email) ->");
        }
        try {
            Matcher matcher = EMAIL_PATTERN_REGIX.matcher(email);
            return matcher.matches();
        } catch (Exception e) {
            log.error("Exception in isValidEmail(final String email) -> {}", e);
            throw e;
        }
    }

}
