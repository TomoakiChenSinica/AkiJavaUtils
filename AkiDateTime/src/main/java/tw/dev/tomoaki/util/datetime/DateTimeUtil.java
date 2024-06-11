/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.datetime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Tomoaki Chen\
 *
 * 原本是要像在家裡重寫 DateTimeProvider 那樣，整理分類重構程式。 <br>
 * 但看了一下新的 LocalDate、LocalTime、LocalDateTime，很多功能都自帶(比如plusDays.....) <br>
 * 有一些String 跟 Date轉換也直接更好用 (LocalDateTime.parse) <br>
 * https://www.baeldung.com/java-8-date-time-intro <br>
 * https://stackoverflow.com/questions/19431234/converting-between-java-time-localdatetime-and-java-util-date
 * <br>
 *
 * 基本上Java8 原生的 LocalDateTime、LocalDate、LocalTime 已經很完善 <br>
 * 這邊只是就個人需求稍微方便而已 <br>
 *
 * 預設的時間格式為 yyyy-MM-dd HH:mm:ss 。<br>
 * 之後應該可以開放指定格式
 *
 */
public class DateTimeUtil {

//    private static String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMMATTER = DateTimeUtil.Provider.obtainFormatter(DEFAULT_DATE_TIME_FORMAT);
    private static final DateTimeFormatter DEFAULT_DATE_FORMMATTER = DateTimeUtil.Provider.obtainFormatter(DEFAULT_DATE_FORMAT);
    private static final DateTimeFormatter DEFAULT_TIME_FORMMATTER = DateTimeUtil.Provider.obtainFormatter(DEFAULT_TIME_FORMAT);

//    public static Date MIN_UTIL_DATE = DateTimeUtil.Converter.convert(LocalDateTime.MIN);
//    public static Date MAX_UTIL_DATE = DateTimeUtil.Converter.convert(LocalDateTime.MAX);
    public static Date MIN_UTIL_DATE = new Date(Long.MIN_VALUE);
    public static Date MAX_UTIL_DATE = new Date(Long.MAX_VALUE);

    /**
     * 
     * 此一系列在 LocalDateTIme、LocalDate、LocalTime 後， <br>
     * 比較的功能是對 LocalXxxx.parse 更進一步包裝， <br>
     * DateTimeFormatter 可以透過傳入 String format 協助轉換 (雖然也不難就是...)
     */
    public static class Provider {

        public static DateTimeFormatter obtainFormatter(String strFormat) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strFormat);
            return formatter;
        }

        /**
         * 將比較舊的日期時間 java.util.Date 轉成 String。<br>
         * 轉出來的日期時間格式為 yyyy-MM-dd HH:mm:ss <br>
         *
         * @param utilDateTime 日期時間資料，格式為 java.util.Date
         * @return yyyy-MM-dd HH:mm:ss 這種時間格式的字串
         *
         */
        public static String parseDateTimeToString(java.util.Date utilDateTime) {
            if(utilDateTime == null) {
                return null;
            }
            SimpleDateFormat timeFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
            String strDateTime = timeFormat.format(utilDateTime);
            return strDateTime;
        }
        
        /**
         *
         * 將 java.time.LocalDateTime 格式的 日期時間轉成 String <br>
         * 轉出來的日期時間格式為預設格式: yyyy-MM-dd HH:mm:ss <br>
         * 
         * @param localDateTime 指定的日期時間，格式為 java.time.LocalDateTime
         * @return 回傳日期時間字串，格式為預設格式: yyyy-MM-dd HH:mm:ss 
         */
        public static String parseDateTimeToString(LocalDateTime localDateTime) {
            if(localDateTime == null) {
                return null;
            }
            DateTimeFormatter formatter = DEFAULT_DATE_TIME_FORMMATTER;
            return parseDateTimeToString(localDateTime, formatter);
        }
        
        /**
         *
         * 將 java.time.LocalDateTime 格式的 日期時間轉成指定格式的 String <br>         
         * 
         * @param localDateTime 指定的日期時間，格式為 java.time.LocalDateTime
         * @param strFormat 指定的格式
         * @return 回傳日期時間字串，格式為預設格式: yyyy-MM-dd HH:mm:ss 
         */        
        public static String parseDateTimeToString(LocalDateTime localDateTime, String strFormat) {
            if(localDateTime == null) {
                return null;
            }            
            DateTimeFormatter formatter = obtainFormatter(strFormat);
            return parseDateTimeToString(localDateTime, formatter);
        }
        
        /**
         *
         * 將 java.time.LocalDateTime 格式的 日期時間轉成指定格式的 String <br>         
         * 
         * @param localDateTime 指定的日期時間，格式為 java.time.LocalDateTime
         * @param dateTimeFormatter 指定的格式 Formatter，可以用 obtainFormatter() 產生
         * @return 回傳日期時間字串，格式為預設格式: yyyy-MM-dd HH:mm:ss 
         */          
        public static String parseDateTimeToString(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
            if(localDateTime == null) {
                return null;
            }            
            return localDateTime.format(dateTimeFormatter);            
        }        


        /**
         * 將比較舊的日期 java.util.Date 轉成 String。<br>
         * 轉出來的日期格式為 yyyy-MM-dd <br>
         *
         * @param utilDate 日期時間資料，格式為 java.util.Date
         * @return yyyy-MM-dd 這種日期格式的字串
         *
         */
        public static String parseDateToString(java.util.Date utilDate) {
            if(utilDate == null) {
                return null;
            }
            
            SimpleDateFormat utilDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
//            String strDate = timeFormat.format(utilDate);
//            return strDate;
            return parseDateToString(utilDate, utilDateFormat);
        }
        
        
        /**
         * 將比較舊的日期 java.util.Date 轉成 String。<br>
         * 轉出來的日期格式為 yyyy-MM-dd <br>
         *
         * @param utilDate 日期時間資料，格式為 java.util.Date
         * @param utilDateFormat 格式為 SimpleDateFormat
         * @return yyyy-MM-dd 這種日期格式的字串
         *
         */        
        public static String parseDateToString(java.util.Date utilDate, SimpleDateFormat utilDateFormat) {
            if(utilDate == null) {
                return null;
            }
            String strDate = utilDateFormat.format(utilDate);
            return strDate;            
        }
        
        public static String parseDateToString(LocalDate localDate) {
            return localDate.format(DEFAULT_DATE_FORMMATTER);
        }
        
        public static String parseDateToString(LocalDate localDate, String strDateFormat) {
            DateTimeFormatter dateFormatter = Provider.obtainFormatter(strDateFormat);
            return localDate.format(dateFormatter);
        }

                
        
        /**
         * 將日期時間字串轉成日期時間資料(entity)，<br>
         * 資料格式為 java.time.LocalDateTime <br>
         *
         * @param strDateTime 日期時間字串。字串格式為 yyyy-MM-dd HH:mm:ss
         * @return 日期時間資料，格式為 java.time.LocalDateTime
         *
         */
        public static LocalDateTime parse2DateTime(String strDateTime) {
            return strDateTime == null ? null : LocalDateTime.parse(strDateTime, DEFAULT_DATE_TIME_FORMMATTER);
        }

        /**
         * 將日期時間字串轉成日期時間資料(entity)，<br>
         * 資料格式為 java.time.LocalDateTime <br>
         *
         * @param strDateTime 日期時間字串
         * @param strDateTimeFormat 指定的日期時間字串格式
         * @return 日期時間資料，格式為 java.time.LocalDateTime
         *
         */        
        public static LocalDateTime parse2DateTime(String strDateTime, String strDateTimeFormat) {
            DateTimeFormatter formatter = Provider.obtainFormatter(strDateTimeFormat);
            return strDateTime == null ? null : LocalDateTime.parse(strDateTime, formatter);
        }

        /**
         * 將日期時間字串轉成日期資料(entity)，<br>
         * 資料格式為 java.time.LocalDateTime <br>
         *
         * @param strDate 日期字串。字串格式為 yyyy-MM-dd
         * @return 日期資料，格式為 java.time.LocalDate
         *
         */        
        public static LocalDate parse2Date(String strDate) {
            return strDate == null ? null : LocalDate.parse(strDate, DEFAULT_DATE_FORMMATTER);
        }
        
        /**
         * 將日期時間字串轉成日期資料(entity)，<br>
         * 資料格式為 java.time.LocalDateTime <br>
         *
         * @param strDate 日期字串。字串格式為 yyyy-MM-dd
         * @param dateFormat 指定的日期字串格式
         * @return 日期資料，格式為 java.time.LocalDate
         *
         */         
        public static LocalDate parse2Date(String strDate, String dateFormat) {
            DateTimeFormatter formatter = Provider.obtainFormatter(dateFormat);
            return strDate == null ? null : LocalDate.parse(strDate, formatter);
        }        

        public static LocalTime parse2Time(String strTime) {
            return strTime == null ? null : LocalTime.parse(strTime, DEFAULT_TIME_FORMMATTER);
        }

        public static LocalTime parse2Time(String strTime, String timeFormat) {
            DateTimeFormatter formatter = Provider.obtainFormatter(timeFormat);
            return strTime == null ? null : LocalTime.parse(strTime, formatter);
        }
        
//        public static LocalYearMonth parse2YearMonth(String strYearMonth, String yearMonthFormat) {
//        } 
               
        public static LocalDate obtainDayOfWeek(LocalDate theDate, Integer dayOfWeek) {
            //要檢查 dayOfWeek 1 ~ 7
            return theDate == null ? null : theDate.with(WeekFields.of(Locale.TAIWAN).dayOfWeek(), dayOfWeek);
        }

        /**
         * 2023-12-19 新增，需測試
         * 
         * 取得「現在的日期時間」，並依照預設的 yyyy-MM-dd HH:mm:ss
         * 
         * @return 現在的日期時間」，並依照的「yyyy-MM-dd HH:mm:ss」格式
         * 
         */
        public static LocalDateTime obtainNow() {
            LocalDateTime nowDateTime = LocalDateTime.now();
            String strNowDateTime = Provider.parseDateTimeToString(nowDateTime);
            return Provider.parse2DateTime(strNowDateTime);
        }
        
        /**
         * 2023-12-19 新增，需測試
         * 
         * 取得「現在的日期時間」，並依照的「指定格式」 
         * 
         * @param strDateTimeFormat 指定的 Java 日期時間格式
         * @return 現在的日期時間」，並依照的「指定格式」 
         * 
         */
        public static LocalDateTime obtainNow(String strDateTimeFormat) {
            LocalDateTime nowDateTime = LocalDateTime.now();
            String strNowDateTime = Provider.parseDateTimeToString(nowDateTime, strDateTimeFormat);
            return Provider.parse2DateTime(strNowDateTime, strDateTimeFormat);
        }        
        
        public static LocalDate obtainToday() {
            return LocalDate.now();
        }
        
        public static LocalDate obtainToday(String strDateFormat) {
            LocalDate today = LocalDate.now();
            String strToday = Provider.parseDateToString(today, strDateFormat);
            return Provider.parse2Date(strToday, strDateFormat);
        }        

        public static LocalTime obtainFirstSecondOfDay() {
            return LocalTime.of(0, 0, 0);
        }

        public static LocalTime obtainLastSecondOfDay() {
            return LocalTime.MIN;
        }

        public static LocalDateTime obtainFirstSecondOfDate(LocalDate date) {
            return date.atTime(0, 0, 0);
        }

        public static LocalDateTime obtainFirstMinuteOfDate(LocalDate date) {
            return date.atStartOfDay();
        }

        public static LocalDateTime obtainLastSecondOfDate(LocalDate date) {
            return date.atTime(23, 59, 59);
        }

        public static LocalDateTime obtainLastMinuteOfDate(LocalDate date) {
            return date.atTime(23, 59);
        }

        public static LocalDateTime obtainFirstSecondOfToday() {
            LocalDate today = DateTimeUtil.Provider.obtainToday();
            return DateTimeUtil.Provider.obtainFirstSecondOfDate(today);
        }

        public static LocalDateTime obtainFirstMinuteOfToday() {
            LocalDate today = DateTimeUtil.Provider.obtainToday();
            return DateTimeUtil.Provider.obtainFirstMinuteOfDate(today);
        }

        public static LocalDateTime obtainLastSecondOfToday() {
            LocalDate today = DateTimeUtil.Provider.obtainToday();
            return DateTimeUtil.Provider.obtainLastSecondOfDate(today);
        }

        public static LocalDateTime obtainLastMinuteOfToday() {
            LocalDate today = DateTimeUtil.Provider.obtainToday();
            return DateTimeUtil.Provider.obtainLastMinuteOfDate(today);
        }

        public static Integer otainNowYear() {
            LocalDate today = DateTimeUtil.Provider.obtainToday();
            return today.getYear();
        }               
    }

    public static class Converter {

        public static LocalDateTime convert2DateTime(Date utilDateTime) {
            LocalDateTime ldt = utilDateTime == null ? null : LocalDateTime.ofInstant(utilDateTime.toInstant(), ZoneId.systemDefault());
            return ldt;
        }

        public static LocalDateTime convert2DateTime(Timestamp timeStamp) {
            return timeStamp == null ? null : timeStamp.toLocalDateTime();
        }

        public static LocalDate convert2Date(java.util.Date utilDate) {
            //https://www.baeldung.com/java-date-to-localdate-and-localdatetime (主要)
            //https://stackoverflow.com/questions/21242110/convert-java-util-date-to-java-time-localdate
//            LocalDate ld = utilDate == null ? null : utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate ld = utilDate == null ? null : Instant.ofEpochMilli(utilDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            return ld;
        }

        public static LocalDate convert2Date(Timestamp timeStamp) {
            return timeStamp == null ? null : timeStamp.toLocalDateTime().toLocalDate();
        }

        /*
          https://www.baeldung.com/java-date-to-localdate-and-localdatetime 
        不過這邊應用在 LocalDateTime --> Date。
        
        Date --> LocalDateTime反而StakOvertflow的似乎比較簡潔(嗎)
        不過 LocalDate反而又沒有相同的 method
        
        2023-01-30 15:21 tomoaki.sinica 筆: 
        覺得 Provider 可能是給 字串、數字的類型
        
         */
        /**
         *
         * [FIXME] 目前似乎有機率沒考慮到「GMT+8」
         *
         * @param localDateTime 日期時間，格式為java.time.LocalDateTime 格式
         * @return 將 java.time.LocalDateTime 轉換成 java.util.Date
         */
        public static Date convert(LocalDateTime localDateTime) {
            return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }

        //以上這個可以改成 convert2UtilDateTime
        public static Date convert(LocalDate localDate) {
            return localDate == null ? null : java.sql.Date.valueOf(localDate);
        }
        //以上這個可以改成 convert2UtilDate

        /**
         * 詳細解說：<br>
         * https://stackoverflow.com/questions/22463062/how-to-parse-format-dates-with-localdatetime-java-8
         * <br>
         *
         * @param utilDateTime 舊的 Date API 的日期，格式為 java.util.Date
         * @return 將丟入的 java.util.Date轉成新的日期格式(java.time.LocalDateTime)。
         *
         */
        public static java.sql.Date covert2SqlDate(Date utilDate) {
            java.sql.Date sqlDate = utilDate == null ? null : new java.sql.Date(utilDate.getTime());
            return sqlDate;
        }

        public static java.sql.Timestamp convert2SqlTimestamp(Date utilDate) {
            Timestamp ts = utilDate == null ? null : new Timestamp(utilDate.getTime());
            return ts;
        }

        public static java.sql.Timestamp convert2SqlTimestamp(LocalDate date) {
            return date == null ? null : Timestamp.valueOf(date.atStartOfDay());
        }

        public static java.sql.Timestamp convert2SqlTimestamp(LocalDateTime dateTime) {
            return dateTime == null ? null : Timestamp.valueOf(dateTime);
        }

        /* ============================ 2023-01-30 15:24，從DateTimeProvider新增過來 ============================ */
        /**
         * 將 日期時間 轉成 日期 跟以前DateTimeProvider是用字串去拆解不同，這裡應用LocalDateTime 、
         * LocalDate
         *
         * @param utilDateTime 日期時間資訊，即資料中包含 年月日+時間 ，格式為 java.util.Date
         * @return 日期，擷取出年月日出來
         *
         */
        public static Date parseDateTime2Date(Date utilDateTime) {
            LocalDateTime dateTime = Converter.convert2DateTime(utilDateTime);
            LocalDate date = dateTime.toLocalDate();
            return Converter.convert(date);
        }

    }

    public static class Analyzer {

        public static Boolean inRange(Date utilTheDate, Date utilStartDate, Date utilEndDate) {
            return ((utilStartDate == null || !utilTheDate.before(utilStartDate))
                    && (utilEndDate == null || !utilTheDate.after(utilEndDate)));
        }

        public static Boolean inRange(LocalDate theDate, LocalDate startDate, LocalDate endDate) {
            return ((startDate == null || !theDate.isBefore(startDate))
                    && (endDate == null || !theDate.isAfter(endDate)));
        }

//        public static Boolean isOverlap(Date utilStartDate1, Date utilEndDate1, Date utilStartDate2, Date utilEndDate2) {
//            
//        }
        public static Boolean inRange(LocalDateTime theDateTime, LocalDateTime startDateTime, LocalDateTime endDateTime) {
            return ((startDateTime == null || !theDateTime.isBefore(startDateTime))
                    && (endDateTime == null || !theDateTime.isAfter(endDateTime)));
            //不在開始時間之前、不在開始時間之後-->在時間區間之間            
        }

        public static LocalDate analyze4SameDay(LocalDateTime theDateTime, LocalTime dayStartTime, LocalTime dayEndTime) {
            if (dayEndTime.isBefore(dayStartTime)) {
                throw new IllegalArgumentException("dayEndTime shoud not before dayStartTime");
            }
            LocalDate theDate = theDateTime.toLocalDate();
            LocalTime theTime = theDateTime.toLocalTime();
            throw new UnsupportedOperationException("Method Not Supported Yet");

        }

        public static LocalDate analyze4CrossDay(String strTheDateTime, String strDayStartTime, String strDayEndTime) {
            LocalDateTime theDateTime = DateTimeUtil.Provider.parse2DateTime(strTheDateTime);
            LocalTime dayStartTime = DateTimeUtil.Provider.parse2Time(strDayStartTime);
            LocalTime dayEndTime = DateTimeUtil.Provider.parse2Time(strDayEndTime);
            return DateTimeUtil.Analyzer.analyze4CrossDay(theDateTime, dayStartTime, dayEndTime);
        }

        /**
         * 實作有些狀況下，要統計的一天可能不是直接由實際的一天的00:00 ~ 23:59 <br>
         * 實際需求甚至會有跨日的狀況(比如第一天的 17:30 到第二天的 08:30 這種
         *
         * @param theDateTime 指定要判斷的日期時間
         * @param dayStartTime 指定單日的起始時間
         * @param dayEndTime 指定單日的結束時間
         * @return 指定要判斷的時間日期，應該要被分類到哪一個單日
         *
         */
        public static LocalDate analyze4CrossDay(LocalDateTime theDateTime, LocalTime dayStartTime, LocalTime dayEndTime) {
            if (dayStartTime.equals(dayEndTime)) {
                dayEndTime = dayEndTime.minusSeconds(1);
            }

            LocalDate theDate = theDateTime.toLocalDate();
            LocalDateTime theDateStartDateTIme = LocalDateTime.of(theDate, dayStartTime);
            LocalDateTime theDateEndDateTIme = LocalDateTime.of(theDate.plusDays(1), dayEndTime);

            LocalDate preDate = theDate.minusDays(1);
            LocalDateTime preDateStartDateTime = LocalDateTime.of(preDate, dayStartTime);
            LocalDateTime preDateEndDateTime = LocalDateTime.of(preDate.plusDays(1), dayEndTime);

            LocalDate sufDate = theDate.plusDays(1);
            LocalDateTime sufDateStartDateTime = LocalDateTime.of(sufDate, dayStartTime);
            LocalDateTime sufDateEndDateTime = LocalDateTime.of(sufDate.plusDays(1), dayEndTime);

            if (DateTimeUtil.Analyzer.inRange(theDateTime, theDateStartDateTIme, theDateEndDateTIme)) {
                return theDate;
            } else if (DateTimeUtil.Analyzer.inRange(theDateTime, preDateStartDateTime, preDateEndDateTime)) {
                return preDate;
            } else if (DateTimeUtil.Analyzer.inRange(theDateTime, sufDateStartDateTime, sufDateEndDateTime)) {
                return sufDate;
            } else {
                return null; //not in range
            }
        }
    }
    
    
//<editor-fold defaultstate="collapsed" desc="以下從 BPM Copy 過來">

    public static class Parser {
        public static Date doParse(String strDate, String format) throws ParseException{
            if(strDate == null){
                return null;
            }
            SimpleDateFormat timeFormat = new SimpleDateFormat(format);
            Date theDate = timeFormat.parse(strDate);
            return theDate;
        }
    }
    
    public static class Formatter {
        public static final String FORMAT_DATE = "yyyy-MM-dd";
        public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";                
        public static final String FORMAT_DATE_ZH = "yyyy年MM月dd日";
        public static final String FORMAT_DATETIME_ZH = "yyyy年MM月dd日 HH時mm分ss秒";        
        
        public static String doFormat(Date theDate, String format){
            if(theDate == null){
                return null;
            }
            SimpleDateFormat timeFormat = new SimpleDateFormat(format);
            String strTheDate = timeFormat.format(theDate);            
            return strTheDate;
        }
        
        public static String doFormat2Date(Date theDate){
            return doFormat(theDate, FORMAT_DATE);
        }        
        
        public static String doFormat2DateTime(Date theDate){
            return doFormat(theDate, FORMAT_DATETIME);
        }            
        
        public static String doFormat2ZhDate(Date theDate){
            return doFormat(theDate, FORMAT_DATE_ZH);
        }
        
        public static String doFormat2ZhDateTime(Date theDate){
            return doFormat(theDate, FORMAT_DATETIME_ZH);
        }        
    }    
//</editor-fold>
    

}
