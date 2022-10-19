/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.datetimeformat;

import java.time.LocalDate;
import tw.dev.tomoaki.datetimeformat.exception.DateTimeFormatParserException;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;

/**
 *
 * @author tomoaki
 */
public class DateTimeFormatParser {
//

//    private static final String YEAR_BLOCK_PATTERN = "\\[(YYYY(\\+?\\-?)[0-9]*)\\]";
//    private static final String YEAR_KEY = "YYYY(\\+)?(\\-)?[0-9]*";

//    //貞緯擴充
//    private static final String MONTH_BLOCK_KEY = "\\[MM(\\+)?(\\-)?[0-9]*\\]";
//    private static final String MONTH_KEY = "MM(\\+)?(\\-)?[0-9]*";
//
//    private static final String DATE_BLOCK_KEY = "\\[DD(\\+)?(\\-)?[0-9]*\\]";
//    private static final String DATE_KEY = "DD(\\+)?(\\-)?[0-9]*";   
    
    
    private static final String YEAR_BLOCK_PATTERN = "\\[(YYYY(\\+?\\-?)[0-9]*)\\]";
    private static final String MONTH_BLOCK_PATTERN = "\\[(MM(\\+?\\-?)[0-9]*)\\]";
    
    private static final RegExpProcessor yearBlockRegExp;
    private static final RegExpProcessor monthBlockRegExp;
    
    static {  //initializer
        yearBlockRegExp = RegExpProcessor.Factory.create(YEAR_BLOCK_PATTERN);
        monthBlockRegExp = RegExpProcessor.Factory.create(MONTH_BLOCK_PATTERN);
    }

    public static LocalDate parseFormat2Date(String desigFormat, Integer annualYear) {
        return parseFormat2Date(desigFormat, annualYear, null, null);
    }    
    
    public static LocalDate parseFormat2Date(String desigFormat, Integer annualYear, Integer annualMonth) {
        return parseFormat2Date(desigFormat, annualYear, annualMonth, null);
    }    
    
    public static LocalDate parseFormat2Date(String desigFormat, Integer annualYear, Integer annualMonth, Integer annualDay) {
        String strDate = desigFormat;
        strDate = processYearPart(annualYear, strDate);
        strDate = processMonthPart(annualMonth, strDate);        
        return DateTimeUtil.Provider.parse2Date(strDate);
    }    
    
//<editor-fold defaultstate="collapsed" desc="處理年">
    protected static String processYearPart(Integer annualYear, String desigFormat) {
        String strDate = desigFormat;        
        if(annualYear == null) {
            return strDate;
        }
        
        RegExpResult yearRegExpResult = yearBlockRegExp.processMatch(desigFormat);
        if(yearRegExpResult.isFind()) {
            String match = yearRegExpResult.getMatchResults().get(0);
            strDate = strDate.replace(match, parseYear(yearRegExpResult, annualYear).toString());
        }     
        return strDate;
    }
        
    protected static Integer parseYear(RegExpResult yearRegExpResult, Integer annualYear) {
        String descriptionPart = yearRegExpResult.getCaptureResults().get(0); //YYYY+1
        String operatorPart = yearRegExpResult.getCaptureResults(2).get(0);
        if (StringValidator.isValueExist(operatorPart)) {
            String[] parts = descriptionPart.split("\\" + operatorPart);
            String strNum = parts[1];
            return calculate(annualYear, operatorPart, strNum);
        } else {
            return annualYear;
        }

    }    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="處理月">
    protected static String processMonthPart(Integer annualMonth, String desigFormat) {
        String strDate = desigFormat;      
        System.out.println(annualMonth);
        if(annualMonth == null) {
            return strDate;
        }
                
        
        RegExpResult monthRegExpResult = monthBlockRegExp.processMatch(desigFormat);
        if(monthRegExpResult.isFind()) {
            String match = monthRegExpResult.getMatchResults().get(0);
            strDate = strDate.replace(match, parseMonth(monthRegExpResult, annualMonth).toString());
        }     
        return strDate;
    }
        
    protected static Integer parseMonth(RegExpResult monthRegExpResult, Integer annualMonth) {
        String descriptionPart = monthRegExpResult.getCaptureResults().get(0); //DD+1
        String operatorPart = monthRegExpResult.getCaptureResults(2).get(0);
        if (StringValidator.isValueExist(operatorPart)) {
            String[] parts = descriptionPart.split("\\" + operatorPart);
            String strNum = parts[1];
            return calculate(annualMonth, operatorPart, strNum);
        } else {
            return annualMonth;
        }
    }
//</editor-fold>
    
    protected static Integer calculate(Integer num1, String operator, String strNum2) {
        Integer num2 = (StringValidator.isValueExist(strNum2)) ? Integer.parseInt(strNum2) : 0;
        switch (operator) {
            case "+": {
                return num1 + num2;
            }
            case "-": {
                return num1 - num2;
            }
            default: {
                String msg = String.format("Operrator= %s Not Found", operator);
                throw new DateTimeFormatParserException(msg);
            }
        }        
    }
    

//    public static Date parseFormat2Date(Integer annualYear, String format) {
//        RegularExpression regex = new RegularExpression();
//        List<String> findList = regex.doTestGroup(YEAR_KEY, format, RegularExpression.CHOICE_MATCHES);
//        Integer theYear = null;
//        if (findList != null && findList.size() > 0) {
//            String word = findList.get(0);
//            Boolean containPlus = word.contains("+");
//            if (containPlus == true) {
//                String[] infos = word.split("\\+"); //<-- contains 不管 regular expression ，但是 split 有
//                String plusYearPart = infos[1];
//                Integer plusYear = Integer.parseInt(plusYearPart);
//                theYear = annualYear + plusYear;
//            } else {
//                theYear = annualYear;
//            }
//            String result = regex.doTestReplace(YEAR_BLOCK_KEY, Integer.toString(theYear), format, RegularExpression.CHOICE_MATCHES);
//            Date date = DateTimeProvider.StringToDate(result);
//            return date;
//        }
//        return null;
//    }
//    //貞緯擴充
//    public static Date formatDateComplete(Integer designatedYear, Date designatedDate, String format) {
//        Date resultDate = null;
//
//        RegularExpression regex = new RegularExpression();
//        Calendar dateCal = Calendar.getInstance();
//        dateCal.setTime(designatedDate);//用來抓若要代換month、date的標準數字
//
//        Integer plusMonthValue = 0;
//        Integer minusMonthValue = 0;
//        Integer plusDateValue = 0;
//        Integer minusDateValue = 0;
//
//        //年
//        List<String> findYearList = regex.doTestGroup(YEAR_KEY, format, RegularExpression.CHOICE_MATCHES);
//        Integer YEAR = designatedYear;
//        if (findYearList != null && findYearList.size() > 0) {//要代換，不代換略過
//            dateCal.set(Calendar.YEAR, YEAR);
//            resultDate = dateCal.getTime();
//
//            String yearPart = findYearList.get(0);
//            Boolean containPlus = yearPart.contains("+");
//            Boolean containMinus = yearPart.contains("-");
//
//            if (containPlus) {
//                String[] splitPlusString = yearPart.split("\\+");
//                String plusYearPart = splitPlusString[1];
//                Integer plusYearValue = Integer.parseInt(plusYearPart);
//                YEAR = designatedYear + plusYearValue;
//            } else if (containMinus) {
//                String[] splitMinusString = yearPart.split("\\-");
//                String minusYearPart = splitMinusString[1];
//                Integer minusYearValue = Integer.parseInt(minusYearPart);
//                YEAR = designatedYear - minusYearValue;
//            }
//            format = regex.doTestReplace(YEAR_BLOCK_KEY, Integer.toString(YEAR), format, RegularExpression.CHOICE_MATCHES);
//        }
//        //月
//        List<String> findMonthList = regex.doTestGroup(MONTH_KEY, format, RegularExpression.CHOICE_MATCHES);
//        if (findMonthList != null && findMonthList.size() > 0) {//要代換，不代換略過
//            Integer MONTH = dateCal.get(Calendar.MONTH) + 1;//註:1月為0，故需+1
//
//            String monthPart = findMonthList.get(0);
//            Boolean containPlus = monthPart.contains("+");
//            Boolean containMinus = monthPart.contains("-");
//
//            if (containPlus) {
//                String[] splitPlusString = monthPart.split("\\+");
//                String plusMonthPart = splitPlusString[1];
//                plusMonthValue = Integer.parseInt(plusMonthPart);
//            } else if (containMinus) {
//                String[] splitMinusString = monthPart.split("\\-");
//                String minusMonthPart = splitMinusString[1];
//                minusMonthValue = Integer.parseInt(minusMonthPart);
//            }
//            format = regex.doTestReplace(MONTH_BLOCK_KEY, Integer.toString(MONTH), format, RegularExpression.CHOICE_MATCHES);
//        }
//
//        List<String> findDateList = regex.doTestGroup(DATE_KEY, format, RegularExpression.CHOICE_MATCHES);
//        Integer DATE = dateCal.get(Calendar.DATE);
//        if (findDateList != null && findDateList.size() > 0) {//要代換，不代換略過
//            String datePart = findDateList.get(0);
//            Boolean containPlus = datePart.contains("+");
//            Boolean containMinus = datePart.contains("-");
//
//            if (containPlus) {
//                String[] splitPlusString = datePart.split("\\+");
//                String plusDatePart = splitPlusString[1];
//                plusDateValue = Integer.parseInt(plusDatePart);
//            } else if (containMinus) {
//                String[] splitMinusString = datePart.split("\\-");
//                String minusDatePart = splitMinusString[1];
//                minusDateValue = Integer.parseInt(minusDatePart);
//            }
//            format = regex.doTestReplace(DATE_BLOCK_KEY, Integer.toString(DATE), format, RegularExpression.CHOICE_MATCHES);
//        }
//        resultDate = DateTimeProvider.StringToDate(format);
//        resultDate = DateTimeProvider.plusMonths(resultDate, plusMonthValue);
//        resultDate = DateTimeProvider.minusMonths(resultDate, minusMonthValue);
//        resultDate = DateTimeProvider.plusDays(resultDate, plusDateValue);
//        resultDate = DateTimeProvider.minusDays(resultDate, minusDateValue);
//
//        return resultDate;
//    }
//    //貞緯擴充
 
    
    
    

}
