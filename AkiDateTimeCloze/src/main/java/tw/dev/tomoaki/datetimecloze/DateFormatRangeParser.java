/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datetimecloze;

import java.time.LocalDate;
import java.util.Date;
import tw.dev.tomoaki.datetimecloze.entity.DateTimeFormatRange;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author Tomoaki Chen
 */
public class DateFormatRangeParser {
    
    public static Integer analyzeSimpleAnnual(DateTimeFormatRange range, Date utilDate) {
        return analyzeSimpleAnnual(range.getStartDateTimeFormat(), range.getEndDateTimeFormat(), DateTimeUtil.Converter.convert2Date(utilDate));
    }
    
    public static Integer analyzeSimpleAnnual(DateTimeFormatRange range, LocalDate date) {
        return analyzeSimpleAnnual(range.getStartDateTimeFormat(), range.getEndDateTimeFormat(), date);
    }
    
    public static Integer analyzeSimpleAnnual(String startDateTimeFormat, String endDateTimeFormat,Date utilDate) {
        return analyzeSimpleAnnual(startDateTimeFormat, endDateTimeFormat, DateTimeUtil.Converter.convert2Date(utilDate));
    }
    
    public static Integer analyzeSimpleAnnual(String startDateTimeFormat, String endDateTimeFormat,LocalDate date) {
        Integer desigYear = date.getYear();
        LocalDate startDate = LocalDateCloze.fillWith(startDateTimeFormat, desigYear);
        LocalDate endDate = LocalDateCloze.fillWith(endDateTimeFormat, desigYear);
        if(date.isBefore(startDate)) {
            //比起使時間還早，代表事是前一年度
            return desigYear - 1;
        } else if(date.isAfter(endDate)) {
            return desigYear + 1;
        } else {
            return desigYear;
        }
    }
    
    
//    public static String analyzeAnnual(String startDateTimeFormat, String endDateTimeFormat,LocalDate date) {
//        Integer desigYear = date.getYear();
//        LocalDate startDate = DateFormatParser.parseFormat2Date(startDateTimeFormat, desigYear);
//        LocalDate endDate = DateFormatParser.parseFormat2Date(endDateTimeFormat, desigYear);
//        if(date.isBefore(startDate)) {
//            //比起使時間還早，代表事是前一年度
//            return String.valueOf(desigYear - 1);
//        } else if(date.isAfter(endDate)) {
//            return String.valueOf(desigYear + 1);
//        } else {
//            return String.valueOf(desigYear);
//        }
//    }
    
}
