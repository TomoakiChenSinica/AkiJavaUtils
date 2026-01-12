/*
 * Copyright 2024 tomoaki.
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
package tw.dev.tomoaki.datetimeexpression.util;

import tw.dev.tomoaki.datetimeexpression.entity.core.ExpressionPart;
import tw.dev.tomoaki.datetimeexpression.entity.date.ExpressionDayOfMonthPart;
import tw.dev.tomoaki.datetimeexpression.entity.date.ExpressionMonthPart;
import tw.dev.tomoaki.datetimeexpression.entity.date.ExpressionYearPart;
import tw.dev.tomoaki.datetimeexpression.entity.time.ExpressionHourPart;
import tw.dev.tomoaki.datetimeexpression.entity.time.ExpressionMinutePart;
import tw.dev.tomoaki.datetimeexpression.entity.time.ExpressionSecondPart;
import tw.dev.tomoaki.datetimeexpression.exception.ExpressionException;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;

/**
 *
 * @author tomoaki
 */
public class ExpressionHelper {

    private static final String YEAR_PART_PATTERN = "\\[(YYYY(\\+?\\-?)[0-9]*)\\]";
    private static final String MONTH_PART_PATTERN = "\\[(MM(\\+?\\-?)[0-9]*)\\]";
    private static final String DAY_OF_MONTH_PART_PATTERN = "\\[(DD(\\+?\\-?)[0-9]*)\\]";
    
    private static final String HOUR_PART_PATTERN = "\\[(HH(\\+?\\-?)[0-9]*)\\]";
    private static final String MINUTE_PART_PATTERN = "\\[(mm(\\+?\\-?)[0-9]*)\\]";
    private static final String SECOND_PART_PATTERN = "\\[(ss(\\+?\\-?)[0-9]*)\\]";

    private static final RegExpProcessor yearPartRegExp;
    private static final RegExpProcessor monthPartRegExp;
    private static final RegExpProcessor dayOfMonthPartRegExp;
    
    private static final RegExpProcessor hourPartRegExp;
    private static final RegExpProcessor minutePartRegExp;
    private static final RegExpProcessor secondPartRegExp;    

    static {  //initializer
        yearPartRegExp = RegExpProcessor.Factory.create(YEAR_PART_PATTERN);
        monthPartRegExp = RegExpProcessor.Factory.create(MONTH_PART_PATTERN);
        dayOfMonthPartRegExp = RegExpProcessor.Factory.create(DAY_OF_MONTH_PART_PATTERN);
        
        hourPartRegExp = RegExpProcessor.Factory.create(HOUR_PART_PATTERN);
        minutePartRegExp = RegExpProcessor.Factory.create(MINUTE_PART_PATTERN);
        secondPartRegExp = RegExpProcessor.Factory.create(SECOND_PART_PATTERN);
        
    }

//<editor-fold defaultstate="collapsed" desc="這裡是對 Cloze Format 進行分析">
    
    /**
     * 從填空題/克漏字(clozeFormat)中解析出「年」的部分
     * 
     * @param clozeFormat 填空題/克漏字
     * @return 「年」的克漏字題
     */
    public static ExpressionYearPart analyzeYearPart(String clozeFormat) {
        ExpressionYearPart yearPart = new ExpressionYearPart();
        RegExpResult yearRegExpResult = yearPartRegExp.processMatch(clozeFormat);
        if (yearRegExpResult.isFind()) {
            yearPart.setIsFillable(Boolean.TRUE);
            yearPart.setMatchText(yearRegExpResult.getMatchResults().get(0));
            yearPart.setAddendNums(processAddendNums(yearRegExpResult)); // 紀錄下之後要加(可能是負)的年份
        }
        return yearPart;
    }

    /**
     * 從填空題/克漏字(clozeFormat)中解析出「月」的部分
     * 
     * @param clozeFormat 填空題/克漏字
     * @return 「月」的克漏字題
     */    
    public static ExpressionMonthPart analyzeMonthPart(String clozeFormat) {
        ExpressionMonthPart monthPart = new ExpressionMonthPart();
        RegExpResult monthRegExpResult = monthPartRegExp.processMatch(clozeFormat);
        if (monthRegExpResult.isFind()) {
            monthPart.setIsFillable(Boolean.TRUE);
            monthPart.setMatchText(monthRegExpResult.getMatchResults().get(0));
            monthPart.setAddendNums(processAddendNums(monthRegExpResult)); // 紀錄下之後要加(可能是負)的月份
        }
        return monthPart;
    }

    /**
     * 從填空題/克漏字(clozeFormat)中解析出「日」的部分
     * 
     * @param clozeFormat 填空題/克漏字
     * @return 「日」的克漏字題
     */    
    public static ExpressionDayOfMonthPart analyzeDayOfMonthPart(String clozeFormat) {
        ExpressionDayOfMonthPart dayOfMonthPart = new ExpressionDayOfMonthPart();
        RegExpResult dayOfMonthRegExpResult = dayOfMonthPartRegExp.processMatch(clozeFormat);
        if (dayOfMonthRegExpResult.isFind()) {
            dayOfMonthPart.setIsFillable(Boolean.TRUE);
            dayOfMonthPart.setMatchText(dayOfMonthRegExpResult.getMatchResults().get(0));
            dayOfMonthPart.setAddendNums(processAddendNums(dayOfMonthRegExpResult)); // 紀錄下之後要加(可能是負)的天數
        }
        return dayOfMonthPart;
    }
    
    
    /**
     * 從填空題/克漏字(clozeFormat)中解析出「小時」的部分
     * 
     * @param clozeFormat 填空題/克漏字
     * @return 「小時」的克漏字題
     */
    public static ExpressionHourPart analyzeHourPart(String clozeFormat) {
        ExpressionHourPart hourPart = new ExpressionHourPart();
        RegExpResult hourRegExpResult = hourPartRegExp.processMatch(clozeFormat);
        if (hourRegExpResult.isFind()) {
            hourPart.setIsFillable(Boolean.TRUE);
            hourPart.setMatchText(hourRegExpResult.getMatchResults().get(0));
            hourPart.setAddendNums(processAddendNums(hourRegExpResult)); // 紀錄下之後要加(可能是負)的年份
        }
        return hourPart;
    }
    
    /**
     * 從填空題/克漏字(clozeFormat)中解析出「分鐘」的部分
     * 
     * @param clozeFormat 填空題/克漏字
     * @return 「分鐘」的克漏字題
     */
    public static ExpressionMinutePart analyzeMinutePart(String clozeFormat) {
        ExpressionMinutePart minutePart = new ExpressionMinutePart();
        RegExpResult minuteRegExpResult = minutePartRegExp.processMatch(clozeFormat);
        if (minuteRegExpResult.isFind()) {
            minutePart.setIsFillable(Boolean.TRUE);
            minutePart.setMatchText(minuteRegExpResult.getMatchResults().get(0));
            minutePart.setAddendNums(processAddendNums(minuteRegExpResult)); // 紀錄下之後要加(可能是負)的年份
        }
        return minutePart;
    }  

    /**
     * 從填空題/克漏字(clozeFormat)中解析出「秒」的部分
     * 
     * @param clozeFormat 填空題/克漏字
     * @return 「秒」的克漏字題
     */
    public static ExpressionSecondPart analyzeSecondPart(String clozeFormat) {
        ExpressionSecondPart secondPart = new ExpressionSecondPart();
        RegExpResult secondRegExpResult = secondPartRegExp.processMatch(clozeFormat);
        if (secondRegExpResult.isFind()) {
            secondPart.setIsFillable(Boolean.TRUE);
            secondPart.setMatchText(secondRegExpResult.getMatchResults().get(0));
            secondPart.setAddendNums(processAddendNums(secondRegExpResult)); // 紀錄下之後要加(可能是負)的年份
        }
        return secondPart;
    }      
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="輔助 Methods">
    
//</editor-fold>
    

//<editor-fold defaultstate="collapsed" desc="這裡是做「判斷是否符合(某)條件」">
    public static Boolean validateYearFillable(String clozeFormat) {  // public class ExpressionHelper {
        RegExpResult yearRegExpResult = yearPartRegExp.processMatch(clozeFormat);
        return yearRegExpResult.isFind();
    }

    public static Boolean validateMonthFillable(String clozeFormat) {
        RegExpResult yearRegExpResult = monthPartRegExp.processMatch(clozeFormat);
        return yearRegExpResult.isFind();
    }

    public static Boolean validateDayOfMonthFillable(String clozeFormat) {
        RegExpResult yearRegExpResult = dayOfMonthPartRegExp.processMatch(clozeFormat);
        return yearRegExpResult.isFind();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="這裡是處理 cloze 中對日期時間的加減">
    /* FIXME: 準備刪除
    protected static Integer processAddendYear(RegExpResult yearRegExpResult) {
        Integer addendYear = 0;
        String descriptionPart = yearRegExpResult.getCaptureResults().get(0); //YYYY+1
        String operatorPart = yearRegExpResult.getCaptureResults(2).get(0);
        if (StringValidator.isValueExist(operatorPart)) {
            String[] parts = descriptionPart.split("\\" + operatorPart);
            String strNum = parts[1];
            addendYear = processAddendNums(operatorPart, strNum);
        }
        return addendYear;
    }

    protected static Integer processAddendMonth(RegExpResult monthRegExpResult) {
        Integer addendMonth = 0;
        String descriptionPart = monthRegExpResult.getCaptureResults().get(0); //DD+1
        String operatorPart = monthRegExpResult.getCaptureResults(2).get(0);
        if (StringValidator.isValueExist(operatorPart)) {
            String[] parts = descriptionPart.split("\\" + operatorPart);
            String strNum = parts[1];
            addendMonth = processAddendNums(operatorPart, strNum);
        }
        return addendMonth;
    }

    protected static Integer processAddendDay(RegExpResult dayRegExpResult) {
        Integer addendDay = 0;
        String descriptionPart = dayRegExpResult.getCaptureResults().get(0); //DD+1
        String operatorPart = dayRegExpResult.getCaptureResults(2).get(0);
        if (StringValidator.isValueExist(operatorPart)) {
            String[] parts = descriptionPart.split("\\" + operatorPart);
            String strNum = parts[1];
            addendDay = processAddendNums(operatorPart, strNum);
        }
        return addendDay;
    }*/
    public static Integer processAddendNums(RegExpResult partRegExpResult) {
        Integer addendNums = 0;
        String descriptionPart = partRegExpResult.getCaptureResults().get(0); //YYYY+1、MM+1 或 DD+1
        String operatorPart = partRegExpResult.getCaptureResults(2).get(0);
        if (StringValidator.isValueExist(operatorPart)) {
            String[] parts = descriptionPart.split("\\" + operatorPart);
            String strNum = parts[1];
            addendNums = processAddendNums(operatorPart, strNum);
        }
        return addendNums;
    }

    public static Integer processAddendNums(String operator, String strNum) {
        Integer num2 = (StringValidator.isValueExist(strNum)) ? Integer.valueOf(strNum) : 0;
        switch (operator) {
            case "+": {
                return num2;
            }
            case "-": {
                return -num2;
            }
            default: {
                String msg = String.format("Operrator= %s Not Exist", operator);
                throw new ExpressionException(msg);
            }
        }
    }
//</editor-fold>   
}
