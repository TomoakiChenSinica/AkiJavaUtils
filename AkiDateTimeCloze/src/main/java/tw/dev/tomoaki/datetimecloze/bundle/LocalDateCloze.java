/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.datetimecloze.bundle;

import java.time.LocalDate;
import tw.dev.tomoaki.datetimecloze.entity.date.DateCloze;
import tw.dev.tomoaki.datetimecloze.exception.ClozeFormatException;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;

/**
 *
 * @author tomoaki
 *
 * [FIXME202406110916] processXxxxPart 系列， isFind() 是 false 的時候看是不是要印訊息?
 * (在有傳入該位置的數值，但那欄沒開放填空)
 *
 *
 *
 */
public class LocalDateCloze {

    public static LocalDate fillWith(String clozeFormat, Integer annualYear) {
        return fillWith(clozeFormat, annualYear, null, null);
    }

    public static LocalDate fillWith(String clozeFormat, Integer annualYear, Integer monthOfAnnual) {
        return fillWith(clozeFormat, annualYear, monthOfAnnual, null);
    }

    public static LocalDate fillWith(String clozeFormat, Integer annualYear, Integer monthOfAnnual, Integer dayOfMonth) {
        String strDate = clozeFormat;
        DateCloze cloze = DateCloze.Factory.create(clozeFormat);

        strDate = processYearPart(strDate, cloze, annualYear);
        strDate = processMonthPart(strDate, cloze, monthOfAnnual);
        strDate = processDayPart(strDate, cloze, dayOfMonth);

        LocalDate standardDate = DateTimeUtil.Provider.parse2Date(strDate);
        standardDate = standardDate.plusYears(cloze.getAddendYears());
        standardDate = standardDate.plusMonths(cloze.getAddendMonths());
        standardDate = standardDate.plusDays(cloze.getAddendDayOfMonth());

        return standardDate;
    }

//<editor-fold defaultstate="collapsed" desc="內部輔助 Methods - 處理各部分">
    protected static String processYearPart(String strDate, DateCloze cloze, Integer annualYear) {
        if (annualYear == null) {
            return strDate;
        }

        if (cloze.getIsYearFillable()) {
            //將年分區塊先用 指定年度(annualYear)更換
            String match = cloze.getYearPartMatchText();
            strDate = strDate.replace(match, annualYear.toString());
        }
        return strDate;
    }

    protected static String processMonthPart(String strDate, DateCloze cloze, Integer monthOfAnnual) {
        if (monthOfAnnual == null) {
            return strDate;
        }

        if (cloze.getIsMonthFillable()) {
            //將月份區塊先用 指定年度(monthOfAnnual)更換
            String match = cloze.getMonthPartMatchText();
            String strMonth = (monthOfAnnual >= 10) ? monthOfAnnual.toString() : "0" + monthOfAnnual.toString(); //補0上去
            strDate = strDate.replace(match, strMonth);
        }
        return strDate;
    }

    protected static String processDayPart(String strDate, DateCloze cloze, Integer dayOfMonth) {
        if (dayOfMonth == null) {
            return strDate;
        }
        if (cloze.getIsDayOfMonthFillable()) {
            //將日期區塊先用 指定年度(dayOfMonth)更換
            String match = cloze.getDayOfMonthPartMatchText();
            strDate = strDate.replace(match, dayOfMonth.toString());
        }
        return strDate;
    }
//</editor-fold>
}
