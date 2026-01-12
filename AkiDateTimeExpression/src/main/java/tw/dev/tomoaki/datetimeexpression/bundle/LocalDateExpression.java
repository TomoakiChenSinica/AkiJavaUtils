/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.datetimeexpression.bundle;

import java.time.LocalDate;
import tw.dev.tomoaki.datetimeexpression.entity.date.DateExpression;
import tw.dev.tomoaki.datetimeexpression.entity.time.TimeExpression;
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
public class LocalDateExpression {

    private final String strExpression;
    private final DateExpression expression;
    private Integer annualYear;
    private Integer monthOfAnnual;
    private Integer dayOfMonth;    
    
    
    protected LocalDateExpression(String strExpression) {
        this.strExpression = strExpression;
        this.expression = (strExpression != null) ? DateExpression.create(strExpression) : null;
    }
    
    public static LocalDateExpression of() {
        return new LocalDateExpression(null);
    } 
    
    public static LocalDateExpression of(String strExpression) {
        return new LocalDateExpression(strExpression);
    }
    
   
    public LocalDateExpression ofYear(Integer annualYear) {
        this.annualYear = annualYear;
        return this;
    }

  
    public LocalDateExpression ofMonth(Integer monthOfAnnual) {
       this.monthOfAnnual = monthOfAnnual;
       return this;
    }
    
    public LocalDateExpression ofDay(Integer dayOfMonth) {
       this.dayOfMonth = dayOfMonth;
       return this;
    }    

    public LocalDate resolve() {
        if(this.expression == null) {
            throw new IllegalArgumentException("expression is null");
        }
        return this.resolve(this.expression);
    }    
    
    
    public LocalDate resolve(String strExpression) {
        return this.resolve(DateExpression.create(strExpression));
    }

    private LocalDate resolve(DateExpression finalExpression) {
        String strDate = finalExpression.getFormat();

        strDate = processYearPart(strDate, finalExpression, annualYear);
        strDate = processMonthPart(strDate, finalExpression, monthOfAnnual);
        strDate = processDayPart(strDate, finalExpression, dayOfMonth);

        LocalDate standardDate = DateTimeUtil.Provider.parse2Date(strDate);
        standardDate = standardDate.plusYears(finalExpression.getAddendYears());
        standardDate = standardDate.plusMonths(finalExpression.getAddendMonths());
        standardDate = standardDate.plusDays(finalExpression.getAddendDayOfMonth());

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
    protected static String processYearPart(String strDate, DateExpression cloze, Integer annualYear) {
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

    protected static String processMonthPart(String strDate, DateExpression cloze, Integer monthOfAnnual) {
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

    protected static String processDayPart(String strDate, DateExpression cloze, Integer dayOfMonth) {
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

    private void init() {
        this.annualYear = null;
        this.monthOfAnnual = null;
        this.dayOfMonth = null;
    }
}
