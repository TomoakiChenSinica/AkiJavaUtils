/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.datetimeexpression.bundle;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import tw.dev.tomoaki.datetimeexpression.entity.date.DateExpression;
import tw.dev.tomoaki.datetimeexpression.entity.time.TimeExpression;
import tw.dev.tomoaki.datetimeexpression.util.ExpressionProcessor;
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
    
    private static Logger logger = Logger.getLogger(LocalDateExpression.class.getName());

    private final DateExpression expression;
    private Integer annualYear;
    private Integer monthOfAnnual;
    private Integer dayOfMonth;    
    
    
    protected LocalDateExpression(String strExpression) {
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

        strDate = ExpressionProcessor.processYearPart(strDate, finalExpression, annualYear);
        strDate = ExpressionProcessor.processMonthPart(strDate, finalExpression, monthOfAnnual);
        strDate = ExpressionProcessor.processDayPart(strDate, finalExpression, dayOfMonth);

        LocalDate standardDate = DateTimeUtil.Provider.parse2Date(strDate);
        standardDate = standardDate.plusYears(finalExpression.getAddendYears());
        standardDate = standardDate.plusMonths(finalExpression.getAddendMonths());
        standardDate = standardDate.plusDays(finalExpression.getAddendDayOfMonth());

        return standardDate;
    }

    private void init() {
        this.annualYear = null;
        this.monthOfAnnual = null;
        this.dayOfMonth = null;
    }
}
