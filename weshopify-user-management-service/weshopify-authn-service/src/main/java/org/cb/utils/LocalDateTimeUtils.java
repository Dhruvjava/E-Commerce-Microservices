package org.cb.utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

import com.dt.constants.StringConstants;
import com.dt.constants.TmplConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Girish
 *
 */
@Slf4j
public class LocalDateTimeUtils implements Serializable {

    private static final long serialVersionUID = -5948653276588477749L;

    /******************************************************************************
     * HOURS - Format Types * h - Hours without leading zeros for single-digit hours (12-hour clock)
     * * hh - Hours with leading zeros for single-digit hours (12-hour clock) * H - Hours without
     * leading zeros for single-digit hours (24-hour clock) * HH - Hours with leading zeros for
     * single-digit hours (24-hour clock) * * MINUTES - Format Types * m - Minutes without leading
     * zeros for single-digit minutes * mm - Minutes with leading zeros for single-digit minutes * *
     * SECONDS - Format Types * s - Seconds without leading zeros for single-digit seconds * ss -
     * Seconds with leading zeros for single-digit seconds *
     *******************************************************************************/

    public static final String HH_mm = "HH:mm";

    public static final String HH_00 = "HH:'00'";

    public static final String HH_mm_ss = "HH:mm:ss";

    public static final String dd = "dd";

    public static final String MMM_yy = "MMM-yy";

    public static final String MMM_yyyy = "MMM-yyyy";

    public static final String dd_MMM_yy = "dd-MMM-yy";

    // public static final String dd_MMM_yyyy = "dd-MMM-yyyy";

    public static final String ddMMyyyy = "ddMMyyyy";

    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    // public static final String dd_MMM_yyyy_HH_mm = "dd-MMM-yyyy HH:mm";

    public static final String dd_MMM_yyyy_HH_00 = "dd-MMM-yyyy HH:'00'";

    public static final String dd_MMM_yyyy_HH_mm_ss = "dd-MMM-yyyy HH:mm:ss";

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String yyyy = "yyyy";

    public static final String UTC_PATTERN = "yyyyMMdd'T'HHmmss";

    public static final String REGEX_TIME =
                    "^([0-9]|0[0-9]|1[0-9]|2[0-4]):[0-5][0-9](:[0-5][0-9])?$";

    public static final String yyyy_ww = "yyyy-ww";

    public static final String yyyy_MM = "yyyy-MM";

    public static final String yy_mm_dd = "yy/MM/dd";

    public static final String yymmdd = "yyMMdd";

    public static final String yyyyMM = "yyyyMM";

    public static final String yy = "yy";

    public static final String HH = "HH";

    public static final String mm = "mm";

    public static final String ss = "ss";

    public static final String MM = "MM";

    public static final String MMM = "MMM";

    public static final String ddDotMMDotyyyy = "dd.MM.yyyy";

    public static final String ddDotMMDotyyyy_HH_mm = "dd.MM.yyyy HH:mm";

    public static DateTimeFormatter DEFAULT_DATE_FORMATTER =
                    DateTimeFormatter.ofPattern(ddDotMMDotyyyy);

    public static DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER =
                    DateTimeFormatter.ofPattern(ddDotMMDotyyyy_HH_mm);

    public static DateTimeFormatter DEFAULT_YEAR_MONTH_FORMATTER =
                    DateTimeFormatter.ofPattern(yyyyMM);

    public static final String ddDotMMDotyyyy_HH_mm_ss = "dd.MM.yyyy HH:mm:ss";

    public static final String yyyyMMddHHmm = "yyyyMMddHHmm";

    public static final String yyyyMMdd = "yyyyMMdd";

    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static LocalDateTime getStartOfDay(LocalDate localDate) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getStartOfDay(LocalDate) ->");
        }

        try {
            if (null == localDate) {
                log.warn("LocalDate IS NULL");
                return null;
            }
            return localDate.atStartOfDay();
        } catch (Exception e) {
            log.error("Exception in getStartOfDay(LocalDate) -" + e);
            return null;
        }
    }

    public static LocalDateTime getStartOfDay(LocalDateTime dateTime) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getStartOfDay(LocalDateTime) ->");
        }

        try {
            if (null == dateTime) {
                log.warn("LocalDate IS NULL");
                return null;
            }
            return getStartOfDay(dateTime.toLocalDate());
        } catch (Exception e) {
            log.error("Exception in getStartOfDay(LocalDateTime) -" + e);
            return null;
        }
    }

    public static LocalDateTime getEndOfDay(LocalDate localDate) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getEndOfDay(LocalDate) ->");
        }

        try {
            if (null == localDate) {
                log.warn("LocalDate IS NULL");
                return null;
            }
            return localDate.atTime(LocalTime.MAX);
        } catch (Exception e) {
            log.error("Exception in getEndOfDay(LocalDate) -" + e);
            return null;
        }
    }

    public static LocalDateTime getWeekFirstDateStartOfDay(LocalDateTime dateTime) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getWeekFirstDateStartOfDay(dateTime) ->");
        }

        try {
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            return getStartOfDay(dateTime
                            .with(TemporalAdjusters.previous(weekFields.getFirstDayOfWeek())));
        } catch (Exception e) {
            log.error("Exception in getWeekFirstDateStartOfDay(dateTime) -" + e);
            return null;
        }
    }

    public static LocalDateTime getMonthFirstDateStartOfDay(LocalDateTime dateTime) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getMonthFirstDateStartOfDay(dateTime) ->");
        }

        try {
            if (null == dateTime) {
                log.warn("LocalDateTime is NULL) ->");
                return null;
            }
            YearMonth month = YearMonth.from(dateTime);
            return getStartOfDay(month.atDay(1));
        } catch (Exception e) {
            log.error("Exception in getMonthFirstDateStartOfDay(dateTime) ->");
            return null;
        }
    }

    public static LocalDateTime getStartTimeOfQtrHour(LocalDateTime dateTime) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getStartTimeOfQtrHour(dateTime) ->");
        }

        try {
            if (null == dateTime) {
                log.warn("LocalDateTime is NULL) ->");
                return null;
            }
            int min = dateTime.getMinute();
            if (min >= 0 && min < 15) {
                return dateTime.withMinute(0).truncatedTo(ChronoUnit.MINUTES);
            } else if (min >= 15 && min < 30) {
                return dateTime.withMinute(15).truncatedTo(ChronoUnit.MINUTES);
            } else if (min >= 30 && min < 45) {
                return dateTime.withMinute(30).truncatedTo(ChronoUnit.MINUTES);
            } else if (min >= 45 && min < 60) {
                return dateTime.withMinute(30).truncatedTo(ChronoUnit.MINUTES);
            }
            return null;
        } catch (Exception e) {
            log.error("Exception in getStartTimeOfQtrHour(dateTime) ->");
            return null;
        }
    }

    public static String convertLdtToDateString(LocalDateTime ldt) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertLdtToDateString(LocalDateTime) ->");
        }

        try {
            if (ldt != null) {
                return DEFAULT_DATE_FORMATTER.format(ldt);
            }
        } catch (Exception e) {
            log.error("Exception in convertLdtToDateString(LocalDateTime) ->");
        }
        return StringConstants.EMPTY;
    }

    // public static String convertLdtToDateTimeString(LocalDateTime ldt) {
    //
    // if (log.isDebugEnabled()) {
    // log.debug("Executing convertLdtToDateTimeString(LocalDateTime) ->");
    // }
    //
    // try {
    // if (null != ldt) {
    // return DEFAULT_DATE_TIME_FORMATTER.format(ldt);
    // }
    // } catch (Exception e) {
    // log.error("Exception in convertLdtToDateTimeString(LocalDateTime) ->");
    // }
    // return StringConstants.EMPTY;
    // }

    public static String convertLdtToString(LocalDateTime ldt, String format) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertLdtToString(LocalDateTime, format) ->");
        }

        try {
            if (ldt != null) {
                return DateTimeFormatter.ofPattern(format).format(ldt);
            }
        } catch (Exception e) {
            log.error("Exception in convertLdtToString(LocalDateTime, format) ->");
        }
        return StringConstants.EMPTY;
    }

    public static LocalTime convertTimeStringToLt(String timeStr, String format) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertStringToLt(TimeStr, format) ->");
        }

        String strTime = Utils.getValidString(timeStr);
        if (Utils.isNotEmpty(strTime)) {
            try {
                return LocalTime.parse(strTime, DateTimeFormatter.ofPattern(format));
            } catch (Exception e) {
                log.error("Exception in convertStringToLt(TimeStr, format) - " + e);
            }
        }
        return null;
    }

    public static String convertLtToString(LocalTime lt, String format) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertLtToString(LocalTime, format) ->");
        }

        try {
            if (lt != null) {
                return DateTimeFormatter.ofPattern(format).format(lt);
            }
        } catch (Exception e) {
            log.error("Exception in convertLtToString(LocalTime, format) - " + e);
        }
        return StringConstants.EMPTY;
    }

    public static LocalDateTime convertDateStringToLdt(final String dateString) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertDateStringToLdt(dateString) ->");
        }

        String strDate = Utils.getValidString(dateString);
        if (Utils.isNotEmpty(strDate)) {
            try {
                return LocalDate.parse(strDate, DEFAULT_DATE_FORMATTER).atStartOfDay();
            } catch (Exception e) {
                log.error("Exception in convertDateStringToLdt(dateString) - " + e);
            }
        }
        return null;
    }

    public static LocalDateTime convertDateTimeStringToLdt(final String dateTimeString) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertDateTimeStringToLdt(dateTimeString) ->");
        }

        String strDateTime = Utils.getValidString(dateTimeString);
        if (Utils.isNotEmpty(strDateTime)) {
            try {
                return LocalDateTime.parse(strDateTime, DEFAULT_DATE_TIME_FORMATTER);
            } catch (Exception e) {
                log.error("Exception in convertDateTimeStringToLdt(dateTimeString) - " + e);
            }
        }
        return null;
    }

    // public static LocalDateTime convertStringToLdt(final String string, final String format) {
    //
    // if (log.isDebugEnabled()) {
    // log.debug("Executing convertStringToLdt(string, format) ->");
    // }
    //
    // String strDate = Utils.getValidString(string);
    // if (Utils.isNotEmpty(strDate)) {
    // try {
    // return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(format));
    // } catch (Exception e) {
    // log.error("Exception in convertStringToLdt(string, format) - " + e);
    // }
    // }
    // return null;
    // }

    public static LocalDate convertStringToLd(final String dateString) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertStringToLd(dateString) ->");
        }

        String strDateTime = Utils.getValidString(dateString);
        if (Utils.isNotEmpty(strDateTime)) {
            try {
                return LocalDate.parse(strDateTime, DEFAULT_DATE_FORMATTER);
            } catch (Exception e) {
                log.error("Exception in convertStringToLd(dateString) - " + e);
            }
        }
        return null;
    }

    public static YearMonth convertStringToYm(final String yearMonthStr, String format) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertStringToYm(YearMonthStr,  Format) ->");
        }

        String yearMonth = Utils.getValidString(yearMonthStr);
        if (Utils.isNotEmpty(yearMonth)) {
            try {
                return YearMonth.parse(yearMonthStr, DateTimeFormatter.ofPattern(format));
            } catch (Exception e) {
                log.error("Exception in convertStringToYm(YearMonthStr, Format) - " + e);
            }
        }
        return null;
    }

    public static Year convertStringToYear(final String yearStr, String format) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertStringToYear(YearStr,  Format) ->");
        }

        String year = Utils.getValidString(yearStr);
        if (Utils.isNotEmpty(year)) {
            try {
                return Year.parse(yearStr, DateTimeFormatter.ofPattern(format));
            } catch (Exception e) {
                log.error("Exception in convertStringToYear(YearStr, Format) - " + e);
            }
        }
        return null;
    }

    public static String convertYmToString(YearMonth yearMonth, String format) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertYmToString(YearMonthStr,  Format) ->");
        }

        try {
            if (yearMonth != null) {
                return DateTimeFormatter.ofPattern(format).format(yearMonth);
            }
        } catch (Exception e) {
            log.error("Exception in convertYmToString(YearMonthStr,  Format) - " + e);
        }
        return StringConstants.EMPTY;
    }

    public static String convertYearToString(Year year, String format) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertYearToString(YearMonthStr,  Format) ->");
        }

        try {
            if (year != null) {
                return DateTimeFormatter.ofPattern(format).format(year);
            }
        } catch (Exception e) {
            log.error("Exception in convertYearToString(YearMonthStr,  Format) - " + e);
        }
        return StringConstants.EMPTY;
    }

    public static long diffInHours(LocalDateTime fromTime, LocalDateTime toTime) {

        if (log.isDebugEnabled()) {
            log.debug("Executing diffInHours(fromTime, toTime) ->");
        }

        try {
            if (fromTime != null && toTime != null) {
                long hrs = ChronoUnit.HOURS.between(fromTime, toTime);
                if (hrs > 0) {
                    return hrs;
                }
            }
        } catch (Exception e) {
            log.error("Exception in diffInHours(fromTime, toTime) -" + e);
        }
        return -1;
    }

    public static long diffInDays(LocalDateTime fromTime, LocalDateTime toTime) {

        if (log.isDebugEnabled()) {
            log.debug("Executing diffInDays(fromTime, toTime) ->");
        }

        try {
            if (fromTime != null && toTime != null) {
                long days = ChronoUnit.DAYS.between(fromTime, toTime);
                if (days >= 0) {
                    return days;
                }
            }
        } catch (Exception e) {
            log.error("Exception in diffInDays(fromTime, toTime) -" + e);
        }
        return -1;
    }

    public static long diffInMonths(LocalDateTime fromTime, LocalDateTime toTime) {

        if (log.isDebugEnabled()) {
            log.debug("Executing diffInMonths(fromTime, toTime) ->");
        }

        try {
            if (fromTime != null && toTime != null) {
                long months = ChronoUnit.MONTHS.between(fromTime, toTime);
                if (months > 0) {
                    return months;
                }
            }
        } catch (Exception e) {
            log.error("Exception in diffInMonths(fromTime, toTime) -" + e);
        }
        return -1;
    }

    public static long diffInYearMonths(YearMonth fromYearMonth, YearMonth toYearMonth) {

        if (log.isDebugEnabled()) {
            log.debug("Executing diffInMonths(fromTime, toTime) ->");
        }

        try {
            if (fromYearMonth != null && toYearMonth != null) {
                long months = ChronoUnit.MONTHS.between(fromYearMonth, toYearMonth);
                if (months >= 0) {
                    return months + 1;
                }
            }
        } catch (Exception e) {
            log.error("Exception in diffInMonths(fromTime, toTime) -" + e);
        }
        return -1;
    }

    public static long diffInMinutes(LocalDateTime fromTime, LocalDateTime toTime) {

        if (log.isDebugEnabled()) {
            log.debug("Executing diffInMinutes(fromTime, toTime) ->");
        }

        try {
            if (fromTime != null && toTime != null) {
                long minutes = ChronoUnit.MINUTES.between(fromTime, toTime);
                if (minutes > 0) {
                    return minutes;
                }
            }
        } catch (Exception e) {
            log.error("Exception in diffInMinutes(fromTime, toTime) -" + e);
        }
        return -1;
    }

    public static Date convertLdtToDate(LocalDateTime dateTime) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertLdtToDate(LocalDateTime) ->");
        }

        try {
            if (dateTime != null) {
                return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
            }
        } catch (Exception e) {
            log.error("Exception in convertLdtToDate(LocalDateTime) -" + e);
        }
        return null;
    }

    public static Date convertLdToDate(LocalDate localDate) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertLdToDate(LocalDate) ->");
        }

        try {
            if (localDate != null) {
                return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault())
                                .toInstant());
            }
        } catch (Exception e) {
            log.error("Exception in convertLdToDate(LocalDate) -" + e);
        }
        return null;
    }

    public static LocalDateTime convertDateToLdt(Date date) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertDateToLdt(Date) ->");
        }

        try {
            if (date != null) {
                return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            }
        } catch (Exception e) {
            log.error("Exception in convertDateToLdt(Date) -" + e);
        }
        return null;
    }

    public static LocalDate convertDateToLd(Date date) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertDateToLd(Date) ->");
        }

        try {
            if (date != null) {
                return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
                                .toLocalDate();
            }
        } catch (Exception e) {
            log.error("Exception in convertDateToLd(Date) -" + e);
        }
        return null;
    }

    public static boolean isPastDate(final LocalDateTime date) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isPastDate(Date) ->");
        }

        boolean bStatus = false;
        if (null != date) {
            LocalDateTime currDate = LocalDate.now().atStartOfDay();
            if (date.compareTo(currDate) < 0) {
                bStatus = true;
            }
        }
        return bStatus;
    }

    // public static String convertLdToDateString(LocalDate ld) {
    //
    // if (log.isDebugEnabled()) {
    // log.debug("Executing convertLdToDateString(LocalDate) ->");
    // }
    //
    // try {
    // if (ld != null) {
    // return DEFAULT_DATE_FORMATTER.format(ld);
    // }
    // } catch (Exception e) {
    // log.error("Exception in convertLdToDateString(LocalDate) ->");
    // }
    // return StringConstants.EMPTY;
    // }

    public static String convertLdToDateString(LocalDate ld, String format) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertLdToDateString(LocalDate) ->");
        }

        try {
            if (ld != null) {
                return DateTimeFormatter.ofPattern(format).format(ld);
            }
        } catch (Exception e) {
            log.error("Exception in convertLdToDateString(LocalDate) ->");
        }
        return StringConstants.EMPTY;
    }

    public static boolean isPastDateTime(final LocalDateTime date) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isPastDateTime(Date) ->");
        }

        boolean bStatus = false;
        if (null != date) {
            LocalDateTime currDate = LocalDateTime.now();
            if (date.compareTo(currDate) < 0) {
                bStatus = true;
            }
        }
        return bStatus;
    }

    public static boolean isFutureMonth(final YearMonth ym) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isPastDate(Date) ->");
        }

        boolean bStatus = false;
        if (null != ym) {
            YearMonth currYm = YearMonth.now();
            if (ym.isAfter(currYm)) {
                bStatus = true;
            }
        }
        return bStatus;
    }

    public static boolean isValidYear(String year) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isValidYear(Year) ->");
        }
        try {
            if (Utils.isEmpty(year)) {
                return false;
            }
            Year y = Year.parse(year);
            if (y == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Exception in isValidYear(year) ->");
            return false;
        }
    }

    public static boolean isValidMonth(String month) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isValidMonth(month) ->");
        }
        try {
            if (Utils.isEmpty(month)) {
                return false;
            }
            Month m = Month.of(Integer.parseInt(month));
            if (m == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Exception in isValidMonth(month) ->");
            return false;
        }
    }

    public static LocalDate getStartDate(String year) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getStartDate(Year) ->");
        }
        try {
            Year y = Year.parse(year);
            if (y == null) {
                return null;
            }
            return y.atDay(1);
        } catch (Exception e) {
            log.error("Exception in getStartDate(year) ->");
            return null;
        }
    }

    public static LocalDate getEndDate(String year) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getEndDate(Year) ->");
        }
        try {
            String month = String.valueOf(Month.DECEMBER.getValue());
            YearMonth ym = getYearMonth(year, month);
            return ym.atEndOfMonth();
        } catch (Exception e) {
            log.error("Exception in getEndDate(year) ->");
            return null;
        }
    }

    public static LocalDate getStartDate(String year, String month) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getStartDate(Year, Month) ->");
        }
        try {
            YearMonth ym = getYearMonth(year, month);
            if (ym == null) {
                return null;
            }
            return ym.atDay(1);
        } catch (Exception e) {
            log.error("Exception in getStartDate(Year, Month) ->");
            return null;
        }
    }

    public static LocalDate getEndDate(String year, String month) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getEndDate(Year, Month) ->");
        }
        try {
            YearMonth ym = getYearMonth(year, month);
            if (ym == null) {
                return null;
            }
            return ym.atEndOfMonth();
        } catch (Exception e) {
            log.error("Exception in getEndDate(Year, Month) ->");
            return null;
        }
    }

    public static YearMonth getYearMonth(String year, String month) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getYearMonth(Year, Month) ->");
        }
        try {
            if (Utils.isEmpty(year) || Utils.isEmpty(month)) {
                return null;
            }
            Year y = Year.parse(year);
            Month m = Month.of(Integer.parseInt(month));
            return YearMonth.of(y.getValue(), m);
        } catch (Exception e) {
            log.error("Exception in getYearMonth(Year, Month) ->");
            return null;
        }
    }

    public static int getYearMonthValue(YearMonth yearMonth) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getYearMonthValue(YearMonth) ->");
        }
        try {
            if (yearMonth == null) {
                return 0;
            }
            String valueStr = convertYmToString(yearMonth, LocalDateTimeUtils.yyyyMM);
            return Integer.valueOf(valueStr);
        } catch (Exception e) {
            log.error("Exception in getYearMonthValue(YearMonth) ->");
            return 0;
        }
    }

    public static YearMonth getStartFinancialYearMonth(YearMonth yearMonth) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getStartFinancialYearMonth(YearMonth) ->");
        }
        try {
            if (yearMonth == null) {
                return null;
            }
            int monthValue = yearMonth.getMonthValue();
            if (monthValue < Month.APRIL.getValue()) {
                return yearMonth.minusYears(1).withMonth(Month.APRIL.getValue());
            }
            return yearMonth.withMonth(Month.APRIL.getValue());
        } catch (Exception e) {
            log.error("Exception in getStartFinancialYearMonth(YearMonth) ->");
            return null;
        }
    }

    public static YearMonth getEndFinancialYearMonth(YearMonth yearMonth) {

        if (log.isDebugEnabled()) {
            log.debug("Executing getEndFinancialYearMonth(YearMonth) ->");
        }
        try {
            if (yearMonth == null) {
                return null;
            }
            int monthValue = yearMonth.getMonthValue();
            if (monthValue < Month.APRIL.getValue()) {
                return yearMonth.withMonth(Month.MARCH.getValue());
            }
            return yearMonth.plusYears(1).withMonth(Month.MARCH.getValue());
        } catch (Exception e) {
            log.error("Exception in getEndFinancialYearMonth(YearMonth) ->");
            return null;
        }
    }

    public static String getCurrentFinancialYear() {

        if (log.isDebugEnabled()) {
            log.debug("Executing getCurrentFinancialYear() ->");
        }
        try {
            YearMonth currYm = YearMonth.now();
            int year = currYm.getYear();
            String fiscalYear;
            if (currYm.getMonthValue() >= Month.APRIL.getValue()) { // fy starts in April
                fiscalYear = year + StringConstants.HYPHEN
                                + convertYearToString(Year.of(year + 1), yy);
            } else {
                fiscalYear = (year - 1) + StringConstants.HYPHEN
                                + convertYearToString(Year.of(year), yy);
            }
            return fiscalYear;
        } catch (Exception e) {
            log.error("Exception in getCurrentFinancialYear() ->");
            return null;
        }
    }

    public static long convertLdToMillis(LocalDateTime ldt) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertLdToMillis(LocalDateTime) ->");
        }

        try {
            if (ldt != null) {
                return ZonedDateTime.of(ldt, ZoneId.systemDefault()).toInstant().toEpochMilli();
            }
        } catch (Exception e) {
            log.error("Exception in convertLdToMillis(LocalDateTime) - " + e);
        }
        return 0;
    }

    public static boolean isValidYearFormat(String year) {

        if (Utils.isEmpty(year)) {
            return false;
        }
        try {
            String[] strArray = year.split(StringConstants.HYPHEN);
            if (Utils.isEmpty(strArray) || strArray.length != 2) {
                return false;
            }
            Year firstYear = Year.parse(strArray[0]);
            if (firstYear == null) {
                return false;
            }
            Year secondYear = Year.of(firstYear.getValue() + 1);
            String secondYearStr = convertYearToString(secondYear, yy);
            if (Utils.isEmpty(secondYearStr) || !secondYearStr.equals(strArray[1])) {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Exception in isValidYearFormat(FY) - " + e);
            return false;
        }
    }

    public static boolean isFutureAy(String ay) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isFutureAy(ay) ->");
        }
        if (Utils.isEmpty(ay)) {
            return false;
        }
        String fromFy = getCurrentFinancialYear().split(StringConstants.HYPHEN)[0];
        String fromYear = ay.split(StringConstants.HYPHEN)[0];
        LocalDate currentDate = LocalDate.now();
        Year currentYear = Year.from(currentDate);

        try {
            // TODO need to consider march to feb
            Year startYear = LocalDateTimeUtils.convertStringToYear(fromYear,
                            LocalDateTimeUtils.yyyy);
            Year startFy = LocalDateTimeUtils.convertStringToYear(fromFy, LocalDateTimeUtils.yyyy);
            // Year endYear = startYear.plusYears(1);
            if (startYear.isAfter(currentYear) || startYear.isAfter(startFy)) {
                return true;
            }
        } catch (Exception e) {
            log.error("Exception in isFutureAy(StartAyYear)");
        }
        return false;
    }

    public static boolean isPastAy(String ay) {

        if (log.isDebugEnabled()) {
            log.debug("Executing isFutureAy(ay) ->");
        }
        if (Utils.isEmpty(ay)) {
            return false;
        }
        Year fromAy = getStartofCurrentAY();
        String fromYear = ay.split(StringConstants.HYPHEN)[0];
        LocalDate currentDate = LocalDate.now();
        Year currentYear = Year.from(currentDate);

        try {
            // TODO need to consider march to feb
            Year startYear = LocalDateTimeUtils.convertStringToYear(fromYear,
                            LocalDateTimeUtils.yyyy);
            // Year endYear = startYear.plusYears(1);
            if (startYear.isBefore(currentYear) || startYear.isBefore(fromAy)) {
                return true;
            }
        } catch (Exception e) {
            log.error("Exception in isFutureAy(StartAyYear)");
        }
        return false;
    }

    public static Year getStartofCurrentAY() {

        if (log.isDebugEnabled()) {
            log.debug("Executing getStartofCurrentAY() ->");
        }
        try {
            YearMonth currYm = YearMonth.now();
            if (currYm.getMonthValue() >= Month.APRIL.getValue()) { // AY starts in April
                return Year.now().plusYears(1);
            }
            return Year.now();
        } catch (Exception e) {
            log.error("Exception in getStartofCurrentAY() ->");
            return null;
        }
    }

    public static String convertAyToFy(String ay) {

        if (log.isDebugEnabled()) {
            log.debug("Executing convertAyToFy(Ay) ->");
        }

        try {
            if (!isValidYearFormat(ay)) {
                return StringConstants.EMPTY;
            }
            String fromAyStr = ay.split(StringConstants.HYPHEN)[0];
            Year fromAy = Year.parse(fromAyStr);

            Year fromFy = fromAy.minusYears(1);

            String fromFyStr = convertYearToString(fromFy, yyyy);
            String toFyStr = convertYearToString(fromAy, yy);
            return fromFyStr + StringConstants.HYPHEN + toFyStr;
        } catch (Exception e) {
            log.error("Exception convertAyToFy(Ay) ->" + e);
            return StringConstants.EMPTY;
        }
    }

    public static String replace(String tmpl, LocalDateTime time) {

        if (log.isDebugEnabled()) {
            log.debug("Executing replace(Tmpl, Time) ->");
        }

        try {
            if (Utils.isEmpty(tmpl)) {
                return StringConstants.EMPTY;
            }
            if (log.isDebugEnabled()) {
                log.debug("BEFORE REPLACE - " + tmpl);
            }

            if (time == null) {
                time = LocalDateTime.now();
            }
            if (tmpl.contains(TmplConstants.YYYYMMDDHHMMSS)) {
                String tsStr = convertLdtToString(time, yyyyMMddHHmmss);
                tmpl = tmpl.replace(TmplConstants.YYYYMMDDHHMMSS, tsStr);
            }
            if (tmpl.contains(TmplConstants.YYYYMMDDHHMM)) {
                String tsStr = convertLdtToString(time, yyyyMMddHHmm);
                tmpl = tmpl.replace(TmplConstants.YYYYMMDDHHMM, tsStr);
            }
            if (tmpl.contains(TmplConstants.YYYYMMDD)) {
                String tsStr = convertLdtToString(time, yyyyMMdd);
                tmpl = tmpl.replace(TmplConstants.YYYYMMDD, tsStr);
            }
            if (tmpl.contains(TmplConstants.DDMMYYYY)) {
                String tsStr = convertLdtToString(time, ddMMyyyy);
                tmpl = tmpl.replace(TmplConstants.DDMMYYYY, tsStr);
            }
            if (tmpl.contains(TmplConstants.YYYY)) {
                String yyyyStr = convertLdtToString(time, yyyy);
                tmpl = tmpl.replace(TmplConstants.YYYY, yyyyStr);
            }
            if (tmpl.contains(TmplConstants.MM)) {
                String mmStr = convertLdtToString(time, MM);
                tmpl = tmpl.replace(TmplConstants.MM, mmStr);
            }
            if (tmpl.contains(TmplConstants.DD)) {
                String ddStr = convertLdtToString(time, dd);
                tmpl = tmpl.replace(TmplConstants.DD, ddStr);
            }
            if (tmpl.contains(TmplConstants.YY)) {
                String ddStr = convertLdtToString(time, yy);
                tmpl = tmpl.replace(TmplConstants.YY, ddStr);
            }
            if (tmpl.contains(TmplConstants.HH)) {
                String hhStr = convertLdtToString(time, HH);
                tmpl = tmpl.replace(TmplConstants.HH, hhStr);
            }
            if (tmpl.contains(TmplConstants.mm)) {
                String mmStr = convertLdtToString(time, mm);
                tmpl = tmpl.replace(TmplConstants.mm, mmStr);
            }
            if (tmpl.contains(TmplConstants.ss)) {
                String ssStr = convertLdtToString(time, ss);
                tmpl = tmpl.replace(TmplConstants.ss, ssStr);
            }
            if (log.isDebugEnabled()) {
                log.debug("AFTER REPLACE - " + tmpl);
            }
            return tmpl;
        } catch (Exception e) {
            log.error("Exception in replace(Tmpl, Time) - " + e);
            return StringConstants.EMPTY;
        }
    }
}
