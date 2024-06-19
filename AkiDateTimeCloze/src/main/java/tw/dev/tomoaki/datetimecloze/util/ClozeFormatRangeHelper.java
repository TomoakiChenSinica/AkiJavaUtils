/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datetimecloze.util;

import java.time.LocalDate;
import tw.dev.tomoaki.datetimecloze.bundle.LocalDateCloze;
import tw.dev.tomoaki.datetimecloze.entity.ClozeFormatRange;
import tw.dev.tomoaki.datetimecloze.entity.date.DateCloze;
import tw.dev.tomoaki.util.datetime.LocalYearMonth;
import tw.dev.tomoaki.util.datetime.entity.DateRange;

/**
 *
 * @author Tomoaki Chen
 */
public class ClozeFormatRangeHelper {

//<editor-fold defaultstate="collapsed" desc="這裡是產生 ClozeFormatRange 相關 Methods">
    // public static ClozeFormatRange 
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="這裡是關於產生 DateRange 相關的 Methods">
    public static DateRange calculateNowInRange(ClozeFormatRange formatRange) {
        LocalDate today = LocalDate.now();
        return ClozeFormatRangeHelper.calculateInRange(formatRange, today);
    }

//    public static DateRange calculateInRange(ClozeFormatRange formatRange, LocalDate desigDate) {
//        return ClozeFormatRangeHelper.doCovert(formatRange, desigDate.getYear(), desigDate.getMonthValue(), desigDate.getDayOfMonth());
//    }
    public static DateRange calculateInRange(ClozeFormatRange formatRange, LocalDate desigDate) {
        DateCloze shift = DateCloze.Factory.create(formatRange.getEndClozeFormat());
        DateRange range = ClozeFormatRangeHelper.doCovert(formatRange, desigDate);
        if (range.isBetween(desigDate)) {
            return range;
        }

        if (range.isBefore(desigDate)) {
            return range.minusYears(shift.getAddendYears().longValue()).minusMonths(shift.getAddendMonths().longValue()).minusDays(shift.getAddendDayOfMonth().longValue());
        }

        if (range.isAfter(desigDate)) {
            return range.plusYears(shift.getAddendYears().longValue()).plusMonths(shift.getAddendMonths().longValue()).plusDays(shift.getAddendDayOfMonth().longValue());
        }
        return null;
    }

    public static DateRange doCovert(ClozeFormatRange formatRange, LocalDate desigDate) {
        return ClozeFormatRangeHelper.doCovert(formatRange, desigDate.getYear(), desigDate.getMonthValue(), desigDate.getDayOfMonth());
    }
    
    public static DateRange doCovert(ClozeFormatRange formatRange, LocalYearMonth desigYearMonth) {
        return ClozeFormatRangeHelper.doCovert(formatRange, desigYearMonth.getYear(), (int)desigYearMonth.getMonth());
    }
    
    public static DateRange doCovert(ClozeFormatRange formatRange, Integer annual, Integer month) {
        String startFormat = formatRange.getStartClozeFormat();
        LocalDate startDate = LocalDateCloze.fillWith(startFormat, annual, month);

        String endFormat = formatRange.getEndClozeFormat();
        LocalDate endDate = LocalDateCloze.fillWith(endFormat, annual, month);

        return DateRange.Factory.create(startDate, endDate);
    }

    public static DateRange doCovert(ClozeFormatRange formatRange, Integer annual, Integer month, Integer dayOfMonth) {
        String startFormat = formatRange.getStartClozeFormat();
        LocalDate startDate = LocalDateCloze.fillWith(startFormat, annual, month, dayOfMonth);

        String endFormat = formatRange.getEndClozeFormat();
        LocalDate endDate = LocalDateCloze.fillWith(endFormat, annual, month, dayOfMonth);

        return DateRange.Factory.create(startDate, endDate);
    }
//</editor-fold>

}
