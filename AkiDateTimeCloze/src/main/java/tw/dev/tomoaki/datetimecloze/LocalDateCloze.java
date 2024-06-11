/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.datetimecloze;

import java.time.LocalDate;
import tw.dev.tomoaki.datetimecloze.exception.DateTimeFormatParserException;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;

/**
 *
 * @author tomoaki
 */
public class LocalDateCloze {
    private static final String YEAR_BLOCK_PATTERN = "\\[(YYYY(\\+?\\-?)[0-9]*)\\]";
    private static final String MONTH_BLOCK_PATTERN = "\\[(MM(\\+?\\-?)[0-9]*)\\]";
    private static final String DAY_BLOCK_PATTERN = "\\[(DD(\\+?\\-?)[0-9]*)\\]";

    private static final RegExpProcessor yearBlockRegExp;
    private static final RegExpProcessor monthBlockRegExp;
    private static final RegExpProcessor dayBlockRegExp;
    
    private static Integer addendYear;
    private static Integer addendMonth;
    private static Integer addendDay;

    static {  //initializer
        yearBlockRegExp = RegExpProcessor.Factory.create(YEAR_BLOCK_PATTERN);
        monthBlockRegExp = RegExpProcessor.Factory.create(MONTH_BLOCK_PATTERN);
        dayBlockRegExp = RegExpProcessor.Factory.create(DAY_BLOCK_PATTERN);
        initVariables();
    }

    public static LocalDate fillWith(String clozeFormat, Integer annualYear) {
        return fillWith(clozeFormat, annualYear, null, null);
    }

    public static LocalDate fillWith(String clozeFormat, Integer annualYear, Integer monthOfAnnual) {
        return fillWith(clozeFormat, annualYear, monthOfAnnual, null);
    }

     public static LocalDate fillWith(String clozeFormat, Integer annualYear, Integer monthOfAnnual, Integer dayOfMonth) {
        String strDate = clozeFormat;
        strDate = processYearPart(annualYear, strDate);
        strDate = processMonthPart(monthOfAnnual, strDate);
        strDate = processDayPart(dayOfMonth, strDate);
        
        LocalDate standardDate = DateTimeUtil.Provider.parse2Date(strDate);
        standardDate = standardDate.plusYears(addendYear);
        standardDate = standardDate.plusMonths(addendMonth);
        standardDate = standardDate.plusDays(addendDay);
        
        //將公共變數初始化回去
        initVariables();
        return standardDate;
    }

//<editor-fold defaultstate="collapsed" desc="處理年份">
    protected static String processYearPart(Integer annualYear, String clozeFormat) {
        String strDate = clozeFormat;
        if (annualYear == null) {
            return strDate;
        }
        RegExpResult yearRegExpResult = yearBlockRegExp.processMatch(clozeFormat);
        if (yearRegExpResult.isFind()) {
            //將年分區塊先用 指定年度(annualYear)更換
            String match = yearRegExpResult.getMatchResults().get(0);
            strDate = strDate.replace(match, annualYear.toString());
            
            //紀錄下之後要加(可能是負)的年份
            processAddendYear(yearRegExpResult);
        }
        return strDate;
    }

    protected static void processAddendYear(RegExpResult yearRegExpResult) {
        String descriptionPart = yearRegExpResult.getCaptureResults().get(0); //YYYY+1
        String operatorPart = yearRegExpResult.getCaptureResults(2).get(0);
        if (StringValidator.isValueExist(operatorPart)) {
            String[] parts = descriptionPart.split("\\" + operatorPart);
            String strNum = parts[1];
            addendYear = processAddend(operatorPart, strNum);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="處理月份">
    protected static String processMonthPart(Integer monthOfAnnual, String clozeFormat) {
        String strDate = clozeFormat;
        if (monthOfAnnual == null) {
            return strDate;
        }

        RegExpResult monthRegExpResult = monthBlockRegExp.processMatch(clozeFormat);
        if (monthRegExpResult.isFind()) {
            //將月份區塊先用 指定年度(monthOfAnnual)更換
            String match = monthRegExpResult.getMatchResults().get(0);
            String strMonth = (monthOfAnnual >= 10) ? monthOfAnnual.toString() : "0" + monthOfAnnual.toString(); //補0上去
            strDate = strDate.replace(match, strMonth);
            //紀錄下之後要加(可能是負)的月份
            processAddendMonth(monthRegExpResult);
            
        }
        return strDate;
    }

    protected static void processAddendMonth(RegExpResult monthRegExpResult) {
        String descriptionPart = monthRegExpResult.getCaptureResults().get(0); //DD+1
        String operatorPart = monthRegExpResult.getCaptureResults(2).get(0);
        if (StringValidator.isValueExist(operatorPart)) {
            String[] parts = descriptionPart.split("\\" + operatorPart);
            String strNum = parts[1];
            addendMonth = processAddend(operatorPart, strNum);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="處理日期(Day of Month)">
    protected static String processDayPart(Integer dayOfMonth, String clozeFormat) {
        String strDate = clozeFormat;
        if (dayOfMonth == null) {
            return strDate;
        }
        RegExpResult dayRegExpResult = dayBlockRegExp.processMatch(clozeFormat);
        if (dayRegExpResult.isFind()) {
            //將日期區塊先用 指定年度(dayOfMonth)更換
            String match = dayRegExpResult.getMatchResults().get(0);
            strDate = strDate.replace(match, dayOfMonth.toString());
            
            //紀錄下之後要加(可能是負)的日子
            processAddendDay(dayRegExpResult);
        }
        return strDate;
    }

    protected static void processAddendDay(RegExpResult dayRegExpResult) {
        String descriptionPart = dayRegExpResult.getCaptureResults().get(0); //DD+1
        String operatorPart = dayRegExpResult.getCaptureResults(2).get(0);
        if (StringValidator.isValueExist(operatorPart)) {
            String[] parts = descriptionPart.split("\\" + operatorPart);
            String strNum = parts[1];
            addendDay = processAddend(operatorPart, strNum);
        }
    }
//</editor-fold>    
    
    protected static Integer processAddend(String operator, String strNum) {
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
                throw new DateTimeFormatParserException(msg);
            }
        }
    }

    protected static void initVariables() {
        addendYear = 0;
        addendMonth = 0;
        addendDay = 0;
    }


}