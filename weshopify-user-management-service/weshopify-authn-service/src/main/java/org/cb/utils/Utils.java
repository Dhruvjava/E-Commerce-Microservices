package org.cb.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.dt.bo.common.FieldMinBO;
import com.dt.constants.IntegerConstants;
import com.dt.constants.StringConstants;
import com.dt.etd.constants.ErrorCodes;
import com.dt.vm.common.ErrorRs;
import com.dt.vm.core.BaseErrorRs;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Girish
 *
 */
@Slf4j
public class Utils implements Serializable {

    private static final long serialVersionUID = -9058103104198899675L;

    public static final String REGEX_UNSIGNED_DECIMAL = "^(\\d*\\.?[0-9]\\d*)$";

    public static final String REGEX_DECIMAL = "^([+-]?\\d*\\.?[0-9]\\d*)$";

    public static final String REGEX_NUMBER = "^(\\d*)$";

    public static final String REGEX_NUMBER_WITH_NEGETIVE = "^([+-]?\\d*)$";

    public static final String REGEX_NUMBER_ALPHABETS = "^[a-zA-Z0-9]*$";

    public static final String REGEX_ALPHABETS = "^[a-zA-Z]*$";

    public static final String REGEX_PAN_NUM = "[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}";

    static final String ATTACHMENT_NAME_REPLACE_REGEXP = "[^a-zA-Z0-9\\.\\(\\)\\s\\#\\$\\&]+";

    public static String getValidString(String str) {
        return ((null == str) ? StringConstants.EMPTY : str.trim());
    }

    public static boolean isValidString(String str) {
        return (((null == str) || (str.trim().isEmpty())) ? false : true);
    }

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

    public static boolean isValidEmail(String email) {
        String strEmail = Utils.getValidString(email);
        if ((Utils.isNotEmpty(strEmail)) && (strEmail.matches(
                        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
            return true;
        }
        return false;
    }

    public static boolean isValidMobile(String mobile) {
        String strMobile = Utils.getValidString(mobile);
        if ((Utils.isNotEmpty(strMobile)) && (strMobile.matches("^[789][0-9]{9}$"))) {
            return true;
        }
        return false;
    }

    public static boolean isValidNumber(String number) {
        String strNumber = Utils.getValidString(number);
        if ((Utils.isNotEmpty(strNumber)) && (strNumber.matches(REGEX_NUMBER))) {
            return true;
        }
        return false;
    }

    public static boolean isValidNumberWithNegetive(String number) {
        String strNumber = Utils.getValidString(number);
        if ((Utils.isNotEmpty(strNumber)) && (strNumber.matches(REGEX_NUMBER_WITH_NEGETIVE))) {
            return true;
        }
        return false;
    }

    public static boolean isValidDecimal(String number) {
        String strNumber = Utils.getValidString(number);
        if ((Utils.isNotEmpty(strNumber)) && (strNumber.matches(REGEX_DECIMAL))) {
            return true;
        }
        return false;
    }

    public static boolean isValidAlphabetString(String str) {
        String string = Utils.getValidString(str);
        if ((Utils.isNotEmpty(string)) && (string.matches(REGEX_ALPHABETS))) {
            return true;
        }
        return false;
    }

    public static boolean isValidPan(String pan) {
        String panStr = Utils.getValidString(pan);
        if ((Utils.isNotEmpty(panStr)) && (panStr.matches(REGEX_PAN_NUM))) {
            return true;
        }
        return false;
    }

    public static int convertKilometersToRadians(int kms) {
        return (kms / IntegerConstants.RADIUS_OF_EARTH_IN_KMS);
    }

    public static String generateRandomHexString(int numchars) {

        if (log.isDebugEnabled()) {
            log.debug("Executing generateRandomHexString(int) ->");
        }

        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }

    public static String generateRandomNumber() {

        if (log.isDebugEnabled()) {
            log.debug("Executing generateRandomNumber() ->");
        }

        Random random = new Random();
        int randomNo = ((random.nextInt(899999)) + 100000);
        return String.valueOf(randomNo);
    }

    public static String formattedCurrency(double currency) {

        if (log.isDebugEnabled()) {
            log.debug("Executing formattedCurrency(currency) ->");
        }

        return String.format(StringConstants.FMT_CURRENCY, currency);
    }

    public static String formattedQuanity(double quantity) {

        if (log.isDebugEnabled()) {
            log.debug("Executing formattedQuanity(quantity) ->");
        }

        return String.format(StringConstants.FMT_QUANTITY_2F, quantity);
    }

    public static String formattedQuanity(double quantity, String format) {

        if (log.isDebugEnabled()) {
            log.debug("Executing formattedQuanity(quantity, format) ->");
        }

        String strFormat = Utils.getValidString(format);
        if (Utils.isNotEmpty(strFormat)) {
            return String.format(strFormat, quantity);
        }
        return String.format(StringConstants.FMT_QUANTITY_2F, quantity);
    }

    /**
     * To check whether the items in the lists are same or not.(case sensitive)
     * 
     * @param list1 - Should not be null, can be empty
     * @param list2 - Should not be null, can be empty
     * @return
     */
    @Deprecated
    public static boolean isStringListsAreSame(List<String> list1, List<String> list2) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isStringListsAreSame(list1, list2) ->");
        }

        if (list1.size() != list2.size()) {
            log.error("Sizes of the lists are not same");
            return false;
        }

        list1.removeAll(list2);
        if (list1.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * To check whether the items in the lists are same (With Order)or not.(case sensitive)
     * 
     * @param list1 - Should not be null, can be empty
     * @param list2 - Should not be null, can be empty
     * @return
     */
    public static boolean isStringListsAreSameWithOrder(List<String> list1, List<String> list2) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isStringListsAreSameWithOrder(list1, list2) ->");
        }

        try {
            if ((list1 == null) && (list2 == null)) {
                return true;
            }
            if ((list1 == null) || (list2 == null)) {
                return false;
            }

            if (list1.size() != list2.size()) {
                log.error("Sizes of the lists are not same");
                return false;
            }

            for (int itr = 0; itr < list1.size(); itr++) {
                if (!(list1.get(itr).equals(list2.get(itr)))) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            log.error("Exception in isStringListsAreSameWithOrder(list1, list2) - " + e);
            return false;
        }
    }

    public static double convertMetersToKilometers(double value) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertMetersToKilometers(value) ->");
        }

        return (value / IntegerConstants.ONE_THOUSAND);
    }

    public static double convertToLakhs(double value) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertToLakhs(value) ->");
        }

        return (value / IntegerConstants.ONE_LAKH);
    }

    public static double convertToMillions(double value) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertToMillions(value) ->");
        }

        return (value / IntegerConstants.ONE_MILLION);
    }

    public static boolean isMatches(String value, String regExp) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isMatches(value, regExp) ->");
        }

        try {
            Pattern pattern = Pattern.compile(regExp);
            Matcher matcher = pattern.matcher(value);
            if (matcher.matches()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("Exception in isMatches(value, regExp) - " + e);
            return false;
        }
    }

    /** Remove duplicates and empty elements **/
    public static List<String> getValidList(List<String> list) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getValidList(List<String> list) ->");
        }

        try {
            if (Utils.isEmpty(list)) {
                return Collections.<String>emptyList();
            }
            List<String> result = new ArrayList<String>();
            for (String element : list) {
                String elm = Utils.getValidString(element);
                if (Utils.isEmpty(elm) || result.contains(elm)) {
                    continue;
                }
                result.add(elm);
            }
            return result;
        } catch (Exception e) {
            log.error("Exception in getValidList(List<String> list) ->");
            return Collections.<String>emptyList();
        }
    }

    public static List<String> splitByComma(String str, boolean isUpperCase) {

        if (log.isDebugEnabled()) {
            log.debug("Executing splitByComma(String str, boolean isUpperCase) ->");
        }

        try {
            if (isEmpty(str)) {
                return Collections.<String>emptyList();
            }
            String value = StringConstants.EMPTY;
            if (isUpperCase) {
                value = Utils.getValidString(str).toUpperCase();
            } else {
                value = Utils.getValidString(str);
            }
            List<String> list = Arrays.asList(value.split(StringConstants.COMMA));
            return getValidList(list);
        } catch (Exception e) {
            log.error("Exception in splitByComma(String str, boolean isUpperCase) -" + e);
            return Collections.<String>emptyList();
        }
    }

    public static boolean isEqual(String magicKey, int magicKeyDB) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isEqual(MagicKey, MagicKeyDB) ->");
        }

        try {
            int magicKeyValue = 0;
            String magicKeyStr = Utils.getValidString(magicKey);
            if (Utils.isNotEmpty(magicKeyStr) && Utils.isValidNumber(magicKeyStr)) {
                magicKeyValue = Integer.valueOf(magicKeyStr);
            }
            if (magicKeyValue != magicKeyDB) {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Exception in isEqual(MagicKey, MagicKeyDB) - " + e);
            return false;
        }
    }

    public static boolean isNotEqual(String magicKey, int magicKeyDB) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isEqual(MagicKey, MagicKeyDB) ->");
        }
        try {
            return !isEqual(magicKey, magicKeyDB);
        } catch (Exception e) {
            log.error("Exception in isEqual(MagicKey, MagicKeyDB) - " + e);
            return true;
        }
    }

    public static boolean anyMatch(List<String> list1, List<String> list2) {

        if (log.isDebugEnabled()) {
            log.debug("Executing anyMatch(List1, List2) ->");
        }

        try {
            if (Utils.isEmpty(list1) || Utils.isEmpty(list2)) {
                return false;
            }
            return list1.stream().anyMatch(list2::contains);
        } catch (Exception e) {
            log.error("Exception in anyMatch(List1, List2) - " + e);
        }
        return false;
    }

    public static boolean isPositives(long... values) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isPositives(values) ->");
        }
        try {
            if (Utils.isEmpty(values)) {
                return true;
            }
            for (long value : values) {
                if (value < 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            log.error("Exception in isPositives(values) - " + e);
            return false;
        }
    }

    public static List<FieldMinBO> getKeyValueBOs(Object obj, List<String> excludeFields) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getKeyValueBOs(Object, ExcludeFields) ->");
        }
        try {
            if (obj == null) {
                return Collections.emptyList();
            }
            Class<?> cls = obj.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(cls);
            if (beanInfo == null || Utils.isEmpty(beanInfo.getPropertyDescriptors())) {
                return Collections.emptyList();
            }
            List<FieldMinBO> bos = new ArrayList<FieldMinBO>();
            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                if (Utils.isNotEmpty(excludeFields) && excludeFields.contains(pd.getName())) {
                    continue;
                }
                FieldMinBO bo = new FieldMinBO();
                bo.setKey(pd.getName());
                String valueStr = StringConstants.EMPTY;
                Object value = pd.getReadMethod().invoke(obj);
                if (value != null) {
                    if (value instanceof String) {
                        valueStr = value.toString();
                    } else if (value instanceof Integer) {
                        valueStr = value.toString();
                    } else if (value instanceof Double) {
                        valueStr = value.toString();
                    } else if (value instanceof LocalDateTime) {
                        valueStr = LocalDateTimeUtils.convertLdtToDateString((LocalDateTime) value);
                    } else if (value instanceof LocalDate) {
                        valueStr = LocalDateTimeUtils.convertLdToDateString((LocalDate) value,
                                        LocalDateTimeUtils.ddDotMMDotyyyy);
                    } else {
                        valueStr = value.toString();
                    }
                }
                bo.setValue(valueStr);
                bos.add(bo);
            }
            return bos;
        } catch (Exception e) {
            log.debug("Exception in  getKeyValueBOs(Object, ExcludeFields) -> " + e);
            return Collections.emptyList();
        }
    }

    public static Double parseToDouble(String str, String ignoreStr) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("Executing parseToDouble(String, IgnoreStr) ->");
        }

        try {
            if (Utils.isEmpty(str, ignoreStr)) {
                return null;
            }
            return Double.parseDouble(Utils.getValidString(str));
        } catch (Exception e) {
            log.error("Exception in parseToInteger(String, IgnoreStr) - " + e);
            throw new Exception(e);
        }
    }

    public static boolean isEqual(Double value1, Double value2) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("Executing isEqual(value1, value2) ->");
        }

        try {
            if (isEmpty(value1) && isEmpty(value2)) {
                return true;
            }
            if (isEmpty(value1)) {
                return false;
            }
            if (isEmpty(value2)) {
                return false;
            }
            return value1.compareTo(value2) == 0 ? true : false;
        } catch (Exception e) {
            log.error("Exception in isEqual(value1, value2) - " + e);
            throw new Exception(e);
        }
    }

    public static String prepareMagicKey(LocalDateTime dateTime) {

        if (log.isDebugEnabled()) {
            log.debug("Executing prepareMagicKey(LocalDateTime) ->");
        }
        try {
            long magicKey = LocalDateTimeUtils.convertLdToMillis(dateTime);
            if (magicKey >= 0) {
                return String.valueOf(magicKey);
            }
        } catch (Exception e) {
            log.error("Exception in prepareMagicKey(LocalDateTime) - " + e);
        }
        return StringConstants.EMPTY;
    }

    public static Integer parseToInteger(String str, String ignoreStr) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("Executing parseToInteger(String, IgnoreStr) ->");
        }

        try {
            if (Utils.isEmpty(str, ignoreStr)) {
                return null;
            }
            return Integer.parseInt(Utils.getValidString(str));
        } catch (Exception e) {
            log.error("Exception in parseToInteger(String, IgnoreStr) - " + e);
            throw new Exception(e);
        }
    }

    public static boolean isEqual(Integer value1, Integer value2) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("Executing isEqual(Double1, Double2) ->");
        }

        try {
            if ((value1 == null) && (value2 == null)) {
                return true;
            }
            if (value1 == null) {
                return false;
            }
            if (value2 == null) {
                return false;
            }
            return value1.compareTo(value2) == 0 ? true : false;
        } catch (Exception e) {
            log.error("Exception in isEqual(Double1, Double2) - " + e);
            throw new Exception(e);
        }
    }

    public static Integer part(Integer whole, Integer pct) {

        if (log.isDebugEnabled()) {
            log.debug("Executing pct(Whole, Pct) ->");
        }

        try {
            if (whole == null || pct == null) {
                return null;
            }
            return Math.round((pct * whole) / IntegerConstants.ONE_HUNDRED);
        } catch (Exception e) {
            log.error("Exception in part(Whole, Pct) - " + e);
            return null;
        }
    }

    public static String convertToIndianNumberFormat(Integer number) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertToIndianNumberFormat(Number) ->");
        }

        try {
            if (number == null) {
                return StringConstants.EMPTY;
            }
            if (number < IntegerConstants.ONE_THOUSAND) {
                return String.valueOf(number);
            }

            String result = StringConstants.EMPTY;
            String numberString = String.valueOf(number);
            int length = numberString.length();
            int a = 0;
            int b = 0;
            for (int i = length - 1; i >= 0; i--) {
                if (a < 3) {
                    result = numberString.charAt(i) + result;
                    a++;
                } else if (b < 2) {
                    if (b == 0) {
                        result = StringConstants.COMMA + result;
                        result = numberString.charAt(i) + result;
                        b++;
                    } else {
                        result = numberString.charAt(i) + result;
                        b = 0;
                    }
                }
            }
            return result;
        } catch (Exception e) {
            log.error("Exception in convertToIndianNumberFormat(Number) - " + e);
            return StringConstants.EMPTY;
        }
    }

    public static String getErrorsDesc(BaseErrorRs rs) {
        if (rs == null || Utils.isEmpty(rs.getErrors())) {
            return StringConstants.EMPTY;
        }
        List<String> errorMessages = rs.getErrors().stream().map(ErrorRs::getMessage)
                        .collect(Collectors.toList());
        if (Utils.isEmpty(errorMessages)) {
            return StringConstants.EMPTY;
        }
        return String.join(StringConstants.COMMA, errorMessages);
    }

    public static int totalRecords(MultipartFile file, String header) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("Executing totalRecords(MultipartFile, Header) ->");
        }
        BufferedReader br = null;
        try {
            if (file == null || Utils.isEmpty(header)) {
                log.error(ErrorCodes.EC_REQUIRED_FILE);
                return -1;
            }
            br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line = StringConstants.EMPTY;
            boolean found = false;
            int recNum = 0;
            while ((line = br.readLine()) != null) {
                if (Utils.isEmpty(line)) {
                    continue;
                }
                if (header.equals(line) && !found) {
                    found = true;
                    continue;
                } else if (!found) {
                    continue;
                }
                if (found) {
                    recNum++;
                }
            }
            return recNum;
        } catch (Exception e) {
            log.debug("Exception in totalRecords(MultipartFile, Header) - " + e);
            throw new Exception(e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public static String getAttFilename(String reportIdParam, String prefix, String suffix,
                    String fileType) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getAttFilename(reportIdParam, prefix, suffix) ->");
        }

        try {
            String name = StringConstants.EMPTY;
            LocalDateTime date = LocalDateTime.now();
            String strDate = LocalDateTimeUtils.convertLdtToString(date,
                            LocalDateTimeUtils.dd_MMM_yyyy_HH_mm_ss);
            // String repId = Utils.getValidString(reportIdParam).toUpperCase();
            if (Utils.isNotEmpty(prefix)) {
                name += Utils.getValidString(prefix)
                                .replace(StringConstants.HYPHEN, StringConstants.UNDERSCORE)
                                .replace(StringConstants.SPACE, StringConstants.UNDERSCORE)
                                + StringConstants.UNDERSCORE;
            }
            if (Utils.isNotEmpty(suffix)) {
                name += StringConstants.UNDERSCORE + Utils.getValidString(suffix)
                                .replace(StringConstants.HYPHEN, StringConstants.UNDERSCORE);
            }

            name += StringConstants.UNDERSCORE + Utils.getValidString(strDate)
                            .replace(StringConstants.COLON, StringConstants.EMPTY)
                            .replace(StringConstants.SPACE, StringConstants.UNDERSCORE);

            String fileName = Utils.getValidString(name).replaceAll(ATTACHMENT_NAME_REPLACE_REGEXP,
                            StringConstants.UNDERSCORE);
            if (Utils.isNotEmpty(fileType)) {
                if (StringConstants.FILE_CSV.equals(fileType)) {
                    return fileName + ".csv";
                }
            }
            return fileName + ".xlsx";
        } catch (Exception e) {
            log.error("Exception getAttFilename(reportIdParam, prefix, suffix) ->" + e);
            return StringConstants.EMPTY;
        }
    }

    public static String join(String delimiter, String... args) {

        try {
            if (Utils.isEmpty(args)) {
                return StringConstants.EMPTY;
            }
            if (Utils.isEmpty(delimiter)) {
                delimiter = StringConstants.COMMA;
            }
            List<String> l = new ArrayList<String>();
            for (String str : args) {
                if (Utils.isEmpty(str)) {
                    continue;
                }
                String strVal = Utils.getValidString(str);
                if (strVal.endsWith(Utils.getValidString(delimiter))) {
                    strVal = strVal.substring(0, strVal.length() - 1);
                }
                l.add(strVal);
            }
            return String.join(delimiter, l);
        } catch (Exception e) {
            log.error("Exception getAttFilename(reportIdParam, prefix, suffix) ->" + e);
            return StringConstants.EMPTY;
        }
    }
}
