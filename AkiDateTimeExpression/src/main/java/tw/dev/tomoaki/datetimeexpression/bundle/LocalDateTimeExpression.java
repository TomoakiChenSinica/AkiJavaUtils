/*
 * Copyright 2026 tomoaki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tw.dev.tomoaki.datetimeexpression.bundle;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import tw.dev.tomoaki.datetimeexpression.entity.date.DateExpression;
import tw.dev.tomoaki.datetimeexpression.entity.time.TimeExpression;
import tw.dev.tomoaki.datetimeexpression.util.ExpressionProcessor;
import tw.dev.tomoaki.datetimeexpression.util.ExpressionTextSplitter;
import tw.dev.tomoaki.datetimeexpression.util.ExpressionTextSplitterFunction;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;


/**
 *
 * @author tomoaki
 */
public class LocalDateTimeExpression {

    private static Logger logger = Logger.getLogger(LocalDateTimeExpression.class.getName());


    private DateExpression dateExp;
    private TimeExpression timeExp;    
    private Integer annualYear;
    private Integer monthOfAnnual;
    private Integer dayOfMonth;
    private Integer hour;
    private Integer minute;
    private Integer second;    

    
    protected LocalDateTimeExpression(String strDateExp, String strTimeExp) {
        this.dateExp = (strDateExp != null) ? DateExpression.create(strDateExp) : null;
        this.timeExp = (strTimeExp != null) ? TimeExpression.create(strTimeExp) : null;        
    }
    
    public static LocalDateTimeExpression of() {
        return new LocalDateTimeExpression(null, null);
    }
    
    public static LocalDateTimeExpression of(String strDateExp, String strTimeExp) {
        return new LocalDateTimeExpression(strDateExp, strTimeExp);
    }
    
    public static LocalDateTimeExpression of(String strExpression, ExpressionTextSplitterFunction splitterFunc) {
        String[] exps = splitterFunc.apply(strExpression);
        return of(exps[0], exps[1]);
    }
    
    public static LocalDateTimeExpression of(String strExpression, ExpressionTextSplitter splitter) {
        String[] exps = splitter.split(strExpression);
        return of(exps[0], exps[1]);
    }    
    
    public LocalDateTimeExpression ofYear(Integer annualYear) {
        this.annualYear = annualYear;
        return this;
    }

  
    public LocalDateTimeExpression ofMonth(Integer monthOfAnnual) {
       this.monthOfAnnual = monthOfAnnual;
       return this;
    }
    
    public LocalDateTimeExpression ofDay(Integer dayOfMonth) {
       this.dayOfMonth = dayOfMonth;
       return this;
    }      
    
    
    /**
     * 設定小時
     * 
     * @param hour 準備用來填空到 expression 中「小時」欄位的數值
     * @return (時間)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */    
    public LocalDateTimeExpression ofHour(Integer hour) {
        this.hour = hour;
        return this;
    }

    /**
     * 設定分鐘
     * 
     * @param minute 準備用來填空到 expression 中「分鐘」欄位的數值
     * @return (時間)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */    
    public LocalDateTimeExpression ofMinute(Integer minute) {
        this.minute = minute;
        return this;
    }
    
    /**
     * 設定秒數
     * 
     * @param second 填空到秒的數值
     * @return (時間)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */    
    public LocalDateTimeExpression ofSecond(Integer second) {
        this.second = second;
        return this;
    }
    
    public LocalDateTime resolve() {
        if(dateExp == null) {
            throw new IllegalArgumentException("dateExp is null");
        }
        
        if(timeExp == null) {
            throw new IllegalArgumentException("timeExp is null");
        }        
        return this.resolve(dateExp, timeExp);
    }
    
    
    public LocalDateTime resolve(String finalStrExp, ExpressionTextSplitter splitter) {
        String[] strExps = splitter.split(finalStrExp);
        return this.resolve(strExps[0], strExps[1]);
    }
    
    public LocalDateTime resolve(String finalStrExp, ExpressionTextSplitterFunction splitterFunc) {
        String[] strExps = splitterFunc.apply(finalStrExp);
        return this.resolve(strExps[0], strExps[1]);
    }    

    public LocalDateTime resolve(String finalStrDateExp, String finalStrTimeExp) {
        return this.resolve(DateExpression.create(finalStrDateExp), TimeExpression.create(finalStrTimeExp));
    }
    
    public LocalDateTime resolve(DateExpression finalDateExp, TimeExpression finalTimeExp) {
        String strDate = finalDateExp.getFormat();
        String strTime = finalTimeExp.getFormat();
        
        String strDateTime = String.format("%s %s", strDate, strTime);
        strDateTime = ExpressionProcessor.processYearPart(strDateTime, finalDateExp, annualYear);
        strDateTime = ExpressionProcessor.processMonthPart(strDateTime, finalDateExp, monthOfAnnual);
        strDateTime = ExpressionProcessor.processDayPart(strDateTime, finalDateExp, dayOfMonth);
        strDateTime = ExpressionProcessor.processHourPart(strDateTime, finalTimeExp, hour);
        strDateTime = ExpressionProcessor.processMinutePart(strDateTime, finalTimeExp, minute);
        strDateTime = ExpressionProcessor.processSecondPart(strDateTime, finalTimeExp, second);
//
        LocalDateTime standardDateTime = DateTimeUtil.Provider.parse2DateTime(strDateTime);;
        standardDateTime = standardDateTime.plusYears(finalDateExp.getAddendYears());
        standardDateTime = standardDateTime.plusMonths(finalDateExp.getAddendMonths());
        standardDateTime = standardDateTime.plusDays(finalDateExp.getAddendDayOfMonth());
        standardDateTime = standardDateTime.plusHours(finalTimeExp.getAddendHours());
        standardDateTime = standardDateTime.plusMinutes(finalTimeExp.getAddendMinutes());
        standardDateTime = standardDateTime.plusSeconds(finalTimeExp.getAddendSeconds());        
        return standardDateTime;
    }    
}
