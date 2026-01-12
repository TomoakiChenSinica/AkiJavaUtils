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
package tw.dev.tomoaki.datetimecloze.bundle;

import java.time.LocalTime;
import tw.dev.tomoaki.datetimecloze.entity.time.TimeCloze;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author tomoaki
 */
public class LocalTimeCloze {
    
    private static Integer hour;
    private static Integer minute;
    private static Integer second;
    
    /**
     * 填寫填空題/克漏字(Cloze)。
     * 
     * @param clozeFormat (日期)填空題
     * @param hour 填空到小時的數值
     * @return (時間)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */    
    public static LocalTime fillWith(String clozeFormat, Integer hour) {
        return fillWith(clozeFormat, hour, null, null);
    }

    /**
     * 填寫填空題/克漏字(Cloze)。
     * 
     * @param clozeFormat (日期)填空題
     * @param hour 填空到小時的數值
     * @param minute 填空到分鐘的數值
     * @return (時間)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */    
    public static LocalTime fillWith(String clozeFormat, Integer hour, Integer minute) {
        return fillWith(clozeFormat, hour, minute, null);
    }

    /**
     * 填寫填空題/克漏字(Cloze)。
     * 
     * @param clozeFormat (日期)填空題
     * @param hour 填空到小時的數值
     * @param minute 填空到分鐘的數值
     * @param second 填空到秒數的數值
     * @return (時間)填空題(clozeFormat)填入各項數值後的結果。
     * 
     */
    public static LocalTime fillWith(String clozeFormat, Integer hour, Integer minute, Integer second) {
        String strDate = clozeFormat;
        TimeCloze cloze = TimeCloze.create(clozeFormat);

        strDate = processHourPart(strDate, cloze, hour);
        strDate = processMinutePart(strDate, cloze, minute);
        strDate = processSecondPart(strDate, cloze, second);

        LocalTime standardTime= DateTimeUtil.Provider.parse2Time(strDate);
        standardTime = standardTime.plusHours(cloze.getAddendHours());
        standardTime = standardTime.plusMinutes(cloze.getAddendMinutes());
        standardTime = standardTime.plusSeconds(cloze.getAddendSeconds());

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
    protected static String processHourPart(String strDate, TimeCloze cloze, Integer hour) {
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

    protected static String processMinutePart(String strDate, TimeCloze cloze, Integer minute) {
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

    protected static String processSecondPart(String strDate, TimeCloze cloze, Integer second) {
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
}
