package com.cb.util;

import com.cb.Messages;
import com.cb.base.rs.ErrorRs;
import com.cb.constants.StringConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Utils {

    private static final Pattern EMAIL_PATTERN_REGIX = Pattern.compile("^([A-Za-z0-9_.-]+)@([\\da-z\\.-]+)\\.([a-z]{2,})$");

    public static boolean isEmpty(String str) {
        return (((null == str) || (str.trim().isEmpty())) ? true : false);
    }

    public static boolean isEmpty(String str, String ignoreStr) {
        return (((null == str) || (str.trim().isEmpty())) || str.equals(ignoreStr) ? true : false);
    }

    public static boolean isNotEmpty(String str) {
        return !(isEmpty(str));
    }

    public static boolean isEmpty(String[] strings) {
        return (((null == strings) || (0 == strings.length)) ? true : false);
    }

    public static boolean isNotEmpty(String[] strings) {
        return !(isEmpty(strings));
    }

    public static boolean isEmpty(byte[] bytes) {
        return (((null == bytes) || (0 == bytes.length)) ? true : false);
    }

    public static boolean isNotEmpty(byte[] bytes) {
        return !(isEmpty(bytes));
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(List list) {
        return (((null == list) || (list.isEmpty())) ? true : false);
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(List list) {
        return !(isEmpty(list));
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(ArrayList arrList) {
        return (((null == arrList) || (arrList.isEmpty())) ? true : false);
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(ArrayList arrList) {
        return !(isEmpty(arrList));
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Map map) {
        return (((null == map) || (map.isEmpty())) ? true : false);
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Map map) {
        return !(isEmpty(map));
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Set set) {
        return (((null == set) || (set.isEmpty())) ? true : false);
    }

    public static boolean isEmpty(Object[] objs) {
        return (((null == objs) || (0 == objs.length)) ? true : false);
    }

    public static boolean isNotEmpty(Object[] objs) {
        return !(isEmpty(objs));
    }

    public static boolean isEmpty(int[] values) {
        return (((null == values) || (0 == values.length)) ? true : false);
    }

    public static boolean isNotEmpty(int[] values) {
        return !(isEmpty(values));
    }

    public static boolean isEmpty(long[] values) {
        return (((null == values) || (0 == values.length)) ? true : false);
    }

    public static boolean isNotEmpty(long[] values) {
        return !(isEmpty(values));
    }

    public static boolean isEmpty(Double value) {
        return (((null == value) || (Double.isNaN(value))) ? true : false);
    }

    public static boolean isNullOrZero(Integer value) {
        return (((null == value) || (value == 0)) ? true : false);
    }

    public static boolean isPositive(Integer value) {
        return (((null != value) && (value > 0)) ? true : false);
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Set set) {
        return !(isEmpty(set));
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
