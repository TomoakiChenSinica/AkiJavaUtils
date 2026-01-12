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

import java.time.LocalTime;
import tw.dev.tomoaki.datetimeexpression.entity.time.TimeExpression;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author tomoaki
 */
public class LocalTimeExpression {
    
    private final String strExpression;
    private final TimeExpression expression;
    private Integer hour;
    private Integer minute;
    private Integer second;


    
    protected LocalTimeExpression(String strExpression) {
        this.strExpression = strExpression;
        this.expression = strExpression != null ? TimeExpression.create(this.strExpression) : null;
        this.init();
    }   
    
    public static LocalTimeExpression create(String strExpression) {
        return new LocalTimeExpression(strExpression);
    }
    
    public static LocalTimeExpression create() {
        return new LocalTimeExpression(null);
    }
    
    
    /**
     * 設定小時
     * 
     * @param hour 準備用來填空到 expression 中「小時」欄位的數值
     * @return (時間)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */    
    public LocalTimeExpression ofHour(Integer hour) {
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
    public LocalTimeExpression ofMinute(Integer minute) {
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
    public LocalTimeExpression ofSecond(Integer second) {
        this.second = second;
        return this;
    }    


    public LocalTime resolve() {
        if(this.expression == null) {
            throw new IllegalArgumentException("expression is null");
        }
        return this.resolve(this.expression);
    }
    
    public LocalTime resolve(String strExpression) {
        return this.resolve(TimeExpression.create(strExpression));
    }
    
 
    /**
     * 根據一開始給的 strExpression，完成時間

     * @return (時間)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */
    protected LocalTime resolve(TimeExpression finalExpression) { // public LocalTime resovle() {
        String strTime = finalExpression.getFormat();

        strTime = processHourPart(strTime, finalExpression, hour);
        strTime = processMinutePart(strTime, finalExpression, minute);
        strTime = processSecondPart(strTime, finalExpression, second);

        LocalTime standardTime= DateTimeUtil.Provider.parse2Time(strTime);
        standardTime = standardTime.plusHours(finalExpression.getAddendHours());
        standardTime = standardTime.plusMinutes(finalExpression.getAddendMinutes());
        standardTime = standardTime.plusSeconds(finalExpression.getAddendSeconds());
        
        return standardTime;
    }     
//<editor-fold defaultstate="collapsed" desc="內部輔助 Methods - 處理各「部分」，部分指的是年、月、日">
    
    /**
     * 
     * 
     * @param strDate
     * @param cloze
     * @param hour
     * 
     */
    protected static String processHourPart(String strDate, TimeExpression cloze, Integer hour) {
        if (hour == null) {
            return strDate;
        }

        if (cloze.getIsHourFillable()) {
            
            
            //將年分區塊先用 指定年度(annualYear)更換
            String match = cloze.getHourPartMatchText();
            String strHour = (hour >= 10) ? hour.toString() : "0" + hour.toString(); //補0上去
            strDate = strDate.replace(match, strHour); // strDate = strDate.replace(match, hour.toString());
        }
        return strDate;
    }

    protected static String processMinutePart(String strDate, TimeExpression cloze, Integer minute) {
        if (minute == null) {
            return strDate;
        }

        if (cloze.getIsMinuteFillable()) {
            //將月份區塊先用 指定年度(monthOfAnnual)更換
            String match = cloze.getMinutePartMatchText();
            String strMonth = (minute >= 10) ? minute.toString() : "0" + minute.toString(); //補0上去
            strDate = strDate.replace(match, strMonth);
        }
        return strDate;
    }

    protected static String processSecondPart(String strDate, TimeExpression cloze, Integer second) {
        if (second == null) {
            return strDate;
        }
        if (cloze.getIsSecondFillable()) {
            // 將日期區塊先用 指定年度(dayOfMonth)更換
            String match = cloze.getSecondPartMatchText();
            String strSecond = (second >= 10) ? second.toString() : "0" + second.toString();
            strDate = strDate.replace(match, strSecond); // strDate = strDate.replace(match, dayOfMonth.toString());
        }
        return strDate;
    }
    
      
//</editor-fold>    
    
    protected final void init() {
        this.hour = null;
        this.minute = null;
        this.second = null;
    }
}
