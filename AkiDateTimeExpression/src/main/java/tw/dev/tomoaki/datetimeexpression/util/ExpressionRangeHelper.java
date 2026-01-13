/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datetimeexpression.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import tw.dev.tomoaki.datetimeexpression.bundle.LocalDateExpression;
import tw.dev.tomoaki.datetimeexpression.bundle.LocalDateTimeExpression;
import tw.dev.tomoaki.datetimeexpression.entity.ExpressionFormatRange;
import tw.dev.tomoaki.datetimeexpression.entity.date.DateExpression;
import tw.dev.tomoaki.datetimeexpression.entity.time.TimeExpression;
import tw.dev.tomoaki.datetimeexpression.exception.BadExpressionRangeException;
import tw.dev.tomoaki.util.datetime.mine.entity.MineYearMonth;
import tw.dev.tomoaki.util.datetime.entity.range.DateRange;
import tw.dev.tomoaki.util.datetime.entity.range.DateTimeRange;

/**
 *
 * @author Tomoaki Chen
 */
public class ExpressionRangeHelper {

//<editor-fold defaultstate="collapsed" desc="這裡是產生 ClozeFormatRange 相關 Methods">
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="外部呼叫 Methods - 產生 DateRange">
    public static DateRange calculateNowInRange(ExpressionFormatRange formatRange) {
        LocalDate today = LocalDate.now();
        return ExpressionRangeHelper.calculateInRange(formatRange, today);
    }

    public static DateRange calculateInRange(ExpressionFormatRange formatRange, LocalDate desigDate) {
        DateExpression shift = DateExpression.create(formatRange.getEndClozeFormat());
        DateRange range = ExpressionRangeHelper.straightCovert(formatRange, desigDate);
        if (range.isBetween(desigDate)) {
            return range;
        }

        if (range.isBefore(desigDate)) {
            return range.minusYears(shift.getAddendYears().longValue())
                        .minusMonths(shift.getAddendMonths().longValue())
                        .minusDays(shift.getAddendDayOfMonth().longValue());
        }

        if (range.isAfter(desigDate)) {
            return range.plusYears(shift.getAddendYears().longValue())
                        .plusMonths(shift.getAddendMonths().longValue())
                        .plusDays(shift.getAddendDayOfMonth().longValue());
        }
        return null;
    }
    
    public static DateRange calculateAsRangeStart(ExpressionFormatRange formatRange, MineYearMonth labledYearMonth) {
        // 把 labledYearMonth 當成起始條件
        doValidateFormatRange4YearMonth(formatRange);
        return ExpressionRangeHelper.straightCovert(formatRange, labledYearMonth);
    }
    
    public static DateTimeRange calculateInRange(ExpressionFormatRange formatRange, LocalDateTime desigDateTime) {
        String[] strExps = formatRange.getEndClozeFormat().split(" ");
        String strDateExp = strExps[0];
        String strTimeExp = strExps[0];
        DateExpression dateShift = DateExpression.create(strDateExp);
        TimeExpression timeShift = TimeExpression.create(strTimeExp);
        
        DateTimeRange range = ExpressionRangeHelper.straightCovert(formatRange, desigDateTime);
        if (range.isBetween(desigDateTime)) {
            return range;
        }

        if (range.isBefore(desigDateTime)) {
            return range.minusYears(dateShift.getAddendYears())
                    .minusMonths(dateShift.getAddendMonths())
                    .minusDays(dateShift.getAddendDayOfMonth())
                    .minusHours(timeShift.getAddendHours())
                    .minusMinutes(timeShift.getAddendMinutes())
                    .minusSeconds(timeShift.getAddendSeconds());
                        
        }

        if (range.isAfter(desigDateTime)) {
            return range.plusYears(dateShift.getAddendYears())
                    .plusMonths(dateShift.getAddendMonths())
                    .plusDays(dateShift.getAddendDayOfMonth())
                    .plusHours(timeShift.getAddendHours())
                    .plusMinutes(timeShift.getAddendMinutes())
                    .plusSeconds(timeShift.getAddendSeconds());
        }
        return range;
    }
//    
//    public static DateRange calculateAsRangeStart(ClozeFormatRange formatRange, MineYearMonth labledYearMonth) {
//        // 把 labledYearMonth 當成起始條件
//        doValidateFormatRange4YearMonth(formatRange);
//        return ExpressionRangeHelper.straightCovert(formatRange, labledYearMonth);
//    }    
    

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="內部輔助 Methods - 產生 DateRange">
        
    // 以下幾項調整成「不開放直接使用，因為 ClozeFormatRange 跟 LocalDate、MineYearMonth 等」
    // doConvert 系列是直接將 LocalDate、MineYearMonth 的 年、月、日 無腦(?)填入。
    // 配合上述說明， doConvert 系列改名 straightCovert
    
    protected static DateRange straightCovert(ExpressionFormatRange formatRange, MineYearMonth desigYearMonth) {
        return ExpressionRangeHelper.straightCovert(formatRange, desigYearMonth.getYear(), (int) desigYearMonth.getMonth(), null);
    }
    
    protected static DateRange straightCovert(ExpressionFormatRange formatRange, LocalDate desigDate) {
        return ExpressionRangeHelper.straightCovert(formatRange, desigDate.getYear(), desigDate.getMonthValue(), desigDate.getDayOfMonth());
    }

    /*
    protected static DateRange straightCovert(ExpressionFormatRange formatRange, Integer annual, Integer month) {
        LocalDateExpression expression = LocalDateExpression.of().ofYear(annual).ofMonth(month);
        
        String startFormat = formatRange.getStartClozeFormat();
        LocalDate startDate = expression.resolve(startFormat); // LocalDateExpression.fillWith(startFormat, annual, month);

        String endFormat = formatRange.getEndClozeFormat();
        LocalDate endDate = expression.resolve(endFormat); // LocalDateExpression.fillWith(endFormat, annual, month);

        return DateRange.create(startDate, endDate);
    }*/

    protected static DateRange straightCovert(ExpressionFormatRange formatRange, Integer annual, Integer month, Integer dayOfMonth) {
        LocalDateExpression expression = LocalDateExpression.of().ofYear(annual).ofMonth(month).ofDay(dayOfMonth);
        
        String startFormat = formatRange.getStartClozeFormat();
        LocalDate startDate = expression.resolve(startFormat); // LocalDateExpression.fillWith(startFormat, annual, month, dayOfMonth);

        String endFormat = formatRange.getEndClozeFormat();
        LocalDate endDate = expression.resolve(endFormat); // LocalDateExpression.fillWith(endFormat, annual, month, dayOfMonth);

        return DateRange.create(startDate, endDate);
    }
    
    protected static DateTimeRange straightCovert(ExpressionFormatRange formatRange, LocalDateTime desigDateTime) {
        LocalDateTime ddt = desigDateTime;
        return straightCovert(formatRange, ddt.getYear(), ddt.getMonthValue(), ddt.getDayOfMonth(), ddt.getHour(), ddt.getMinute(), ddt.getSecond());
    }    
    
    
    protected static DateTimeRange straightCovert(ExpressionFormatRange formatRange, Integer annual, Integer month, Integer dayOfMonth, Integer hour, Integer minute, Integer second) {
        LocalDateTimeExpression expression = LocalDateTimeExpression.of().ofYear(annual).ofMonth(month).ofDay(dayOfMonth).ofHour(hour).ofMinute(minute).ofSecond(second);
        ExpressionTextSplitterFunction splitterFunc = text -> text.split(" ");
        
        String startFormat = formatRange.getStartClozeFormat();
        LocalDateTime startDate = expression.resolve(startFormat, splitterFunc); // LocalDateExpression.fillWith(startFormat, annual, month, dayOfMonth);

        String endFormat = formatRange.getEndClozeFormat();
        LocalDateTime endDate = expression.resolve(endFormat, splitterFunc); // LocalDateExpression.fillWith(endFormat, annual, month, dayOfMonth);

        return DateTimeRange.create(startDate, endDate);
    }    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="內部輔助 Methods - 產生 DateTimeRange">
    
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="內部輔助 Method - 協助檢查">
    
    /**
     *  
     */
    protected static void doValidateFormatRange4YearMonth(ExpressionFormatRange formatRange) {
        if(!ExpressionRangeHelper.validateIsFormatRange4YearMonth(formatRange)) {
            throw new BadExpressionRangeException();
        }
    }
    
    protected static Boolean validateIsFormatRange4YearMonth(ExpressionFormatRange formatRange) {
        // [FIXME202407011731] 需要實作，先都回 true
        return true;
    }
//</editor-fold>
}
