/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.datetime;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tomoaki
 */
public class DateTimeHelper {

    public static final String REG_EXP_PATTERN_DATE = "^[0-9]{4}[-/][0-9]{2}[-/][0-9]{2}$";
//    public static final String REG_EXP_PATTERN_DATE_TIME = "^[0-9]{4}[-/][0-9]{2}[-/][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})?$";
    public static final String REG_EXP_PATTERN_DATE_TIME = "^[0-9]{4}[-/][0-9]{2}[-/][0-9]{2}[ T][0-9]{2}:[0-9]{2}:[0-9]{2}$";

    public static Boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(REG_EXP_PATTERN_DATE);
        Matcher matcher = pattern.matcher(strDate);
        return matcher.matches();
    }

    public static Boolean isDateTime(String strDateTime) {
        Pattern pattern = Pattern.compile(REG_EXP_PATTERN_DATE_TIME);
        Matcher matcher = pattern.matcher(strDateTime);
        return matcher.matches();
    }

    public static Boolean isAfterEq(LocalDateTime standardDateTime, LocalDateTime compareDatetime) {
        return standardDateTime.isAfter(compareDatetime) || standardDateTime.equals(compareDatetime);
    }

    public static Boolean isBeforeEq(LocalDateTime standardDateTime, LocalDateTime compareDatetime) {
        return standardDateTime.isBefore(compareDatetime) || standardDateTime.equals(compareDatetime);
    }
     
}
