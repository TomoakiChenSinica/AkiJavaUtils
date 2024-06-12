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
package tw.dev.tomoaki.datetimecloze.util;

import tw.dev.tomoaki.datetimecloze.entity.core.ClozePart;
import tw.dev.tomoaki.datetimecloze.entity.date.ClozeDayOfMonthPart;
import tw.dev.tomoaki.datetimecloze.entity.date.ClozeMonthPart;
import tw.dev.tomoaki.datetimecloze.entity.date.ClozeYearPart;
import tw.dev.tomoaki.datetimecloze.exception.ClozeFormatException;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;

/**
 *
 * @author tomoaki
 */
public class ClozeFormatHelper {

    private static final String YEAR_PART_PATTERN = "\\[(YYYY(\\+?\\-?)[0-9]*)\\]";
    private static final String MONTH_PART_PATTERN = "\\[(MM(\\+?\\-?)[0-9]*)\\]";
    private static final String DAY_OF_MONTH_PART_PATTERN = "\\[(DD(\\+?\\-?)[0-9]*)\\]";

    private static final RegExpProcessor yearPartRegExp;
    private static final RegExpProcessor monthPartRegExp;
    private static final RegExpProcessor dayOfMonthPartRegExp;

    static {  //initializer
        yearPartRegExp = RegExpProcessor.Factory.create(YEAR_PART_PATTERN);
        monthPartRegExp = RegExpProcessor.Factory.create(MONTH_PART_PATTERN);
        dayOfMonthPartRegExp = RegExpProcessor.Factory.create(DAY_OF_MONTH_PART_PATTERN);
    }

//<editor-fold defaultstate="collapsed" desc="這裡是對 Cloze Format 進行分析">
    public static ClozeYearPart analyzeYearPart(String clozeFormat) {
        ClozeYearPart yearPart = new ClozeYearPart();
        RegExpResult yearRegExpResult = yearPartRegExp.processMatch(clozeFormat);
        if (yearRegExpResult.isFind()) {
            yearPart.setIsFillable(Boolean.TRUE);
            yearPart.setMatchText(yearRegExpResult.getMatchResults().get(0));
            yearPart.setAddendNums(processAddendNums(yearRegExpResult)); // 紀錄下之後要加(可能是負)的年份
        }
        return yearPart;
    }

    public static ClozeMonthPart analyzeMonthPart(String clozeFormat) {
        ClozeMonthPart monthPart = new ClozeMonthPart();
        RegExpResult monthRegExpResult = monthPartRegExp.processMatch(clozeFormat);
        if (monthRegExpResult.isFind()) {
            monthPart.setIsFillable(Boolean.TRUE);
            monthPart.setMatchText(monthRegExpResult.getMatchResults().get(0));
            monthPart.setAddendNums(processAddendNums(monthRegExpResult)); // 紀錄下之後要加(可能是負)的月份
        }
        return monthPart;
    }

    public static ClozeDayOfMonthPart analyzeDayOfMonthPart(String clozeFormat) {
        ClozeDayOfMonthPart dayOfMonthPart = new ClozeDayOfMonthPart();
        RegExpResult dayOfMonthRegExpResult = dayOfMonthPartRegExp.processMatch(clozeFormat);
        if (dayOfMonthRegExpResult.isFind()) {
            dayOfMonthPart.setIsFillable(Boolean.TRUE);
            dayOfMonthPart.setMatchText(dayOfMonthRegExpResult.getMatchResults().get(0));
            dayOfMonthPart.setAddendNums(processAddendNums(dayOfMonthRegExpResult)); // 紀錄下之後要加(可能是負)的天數
        }
        return dayOfMonthPart;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="這裡是做判斷">
    public static Boolean validateYearFillable(String clozeFormat) {  // public class ClozeFormatHelper {
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
    /*protected static Integer processAddendYear(RegExpResult yearRegExpResult) {
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
                throw new ClozeFormatException(msg);
            }
        }
    }
//</editor-fold>   
}
