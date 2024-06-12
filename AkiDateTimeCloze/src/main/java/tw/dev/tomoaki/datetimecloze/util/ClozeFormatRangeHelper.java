/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datetimecloze.util;

import java.time.LocalDate;
import tw.dev.tomoaki.datetimecloze.bundle.LocalDateCloze;
import tw.dev.tomoaki.datetimecloze.entity.ClozeFormatRange;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;
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
        DateRange range = ClozeFormatRangeHelper.doCovert(formatRange, desigDate.getYear(), desigDate.getMonthValue(), desigDate.getDayOfMonth());
        return range;
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
