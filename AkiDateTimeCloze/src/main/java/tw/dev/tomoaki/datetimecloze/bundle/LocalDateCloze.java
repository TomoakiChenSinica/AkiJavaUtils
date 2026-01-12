/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.datetimecloze.bundle;

import java.time.LocalDate;
import tw.dev.tomoaki.datetimecloze.entity.date.DateCloze;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;


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

    /**
     * 填寫填空題/克漏字(Cloze)。
     * 
     * @param clozeFormat (日期)填空題
     * @param annualYear 填空到年度的數值
     * @return (日期)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */    
    public static LocalDate fillWith(String clozeFormat, Integer annualYear) {
        return fillWith(clozeFormat, annualYear, null, null);
    }

    /**
     * 填寫填空題/克漏字(Cloze)。
     * 
     * @param clozeFormat (日期)填空題
     * @param annualYear 填空到年度的數值
     * @param monthOfAnnual 填空到月份的數值
     * @return (日期)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */    
    public static LocalDate fillWith(String clozeFormat, Integer annualYear, Integer monthOfAnnual) {
        return fillWith(clozeFormat, annualYear, monthOfAnnual, null);
    }

    /**
     * 填寫填空題/克漏字(Cloze)。
     * 
     * @param clozeFormat (日期)填空題
     * @param annualYear 填空到年度的數值
     * @param monthOfAnnual 填空到月份的數值
     * @param dayOfMonth 填空到日期的數值
     * @return (日期)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */
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

//<editor-fold defaultstate="collapsed" desc="內部輔助 Methods - 處理各「部分」，部分指的是年、月、日">
    
    /**
     * 
     * 
     * @param strDate
     * @param cloze
     * @param annualYear
     * 
     */
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
            // 將日期區塊先用 指定年度(dayOfMonth)更換
            String match = cloze.getDayOfMonthPartMatchText();
            String strDayOfMonth = (dayOfMonth >= 10) ? dayOfMonth.toString() : "0" + dayOfMonth.toString();
            strDate = strDate.replace(match, strDayOfMonth);
        }
        return strDate;
    }
//</editor-fold>

}
