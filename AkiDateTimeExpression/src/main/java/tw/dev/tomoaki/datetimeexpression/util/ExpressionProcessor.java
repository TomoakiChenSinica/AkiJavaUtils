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
package tw.dev.tomoaki.datetimeexpression.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import tw.dev.tomoaki.datetimeexpression.bundle.LocalDateExpression;
import tw.dev.tomoaki.datetimeexpression.entity.date.DateExpression;
import tw.dev.tomoaki.datetimeexpression.entity.time.TimeExpression;

/**
 *
 * @author tomoaki
 */
public class ExpressionProcessor {

    private static Logger logger = Logger.getLogger(LocalDateExpression.class.getName());

    /**
     *
     *
     * @param strDate
     * @param expression
     * @param annualYear
     *
     */
    public static String processYearPart(String strDate, DateExpression expression, Integer annualYear) {
        if (annualYear == null) {
            return strDate;
        }

        if (expression.getIsYearFillable()) {
            // 將年分區塊先用 指定年度(annualYear)更換
            String match = expression.getYearPartMatchText();
            strDate = strDate.replace(match, annualYear.toString());
        } else {
            String msgFmt = "expression is %s, cannot fill year, will ignore";
            logger.log(Level.FINE, String.format(msgFmt, expression.getFormat()));
        }
        return strDate;
    }

    public static String processMonthPart(String strDate, DateExpression expression, Integer monthOfAnnual) {
        if (monthOfAnnual == null) {
            return strDate;
        }

        if (expression.getIsMonthFillable()) {
            //將月份區塊先用 指定年度(monthOfAnnual)更換
            String match = expression.getMonthPartMatchText();
            String strMonth = (monthOfAnnual >= 10) ? monthOfAnnual.toString() : "0" + monthOfAnnual.toString(); //補0上去
            strDate = strDate.replace(match, strMonth);
        } else {
            String msgFmt = "expression is %s, cannot fill month, will ignore";
            logger.log(Level.FINE, String.format(msgFmt, expression.getFormat()));
        }
        return strDate;
    }

    public static String processDayPart(String strDate, DateExpression expression, Integer dayOfMonth) {
        if (dayOfMonth == null) {
            return strDate;
        }
        if (expression.getIsDayOfMonthFillable()) {
            // 將日期區塊先用 指定年度(dayOfMonth)更換
            String match = expression.getDayOfMonthPartMatchText();
            String strDayOfMonth = (dayOfMonth >= 10) ? dayOfMonth.toString() : "0" + dayOfMonth.toString();
            strDate = strDate.replace(match, strDayOfMonth);
        } else {
            String msgFmt = "expression is %s, cannot fill dayOfMonth, will ignore";
            logger.log(Level.FINE, String.format(msgFmt, expression.getFormat()));
        }
        return strDate;
    }
    
    /**
     * 
     * 
     * @param strDate
     * @param cloze
     * @param hour
     * 
     */
    public static String processHourPart(String strDate, TimeExpression expression, Integer hour) {
        if (hour == null) {
            return strDate;
        }

        if (expression.getIsHourFillable()) {
            // 將年分區塊先用 指定年度(annualYear)更換
            String match = expression.getHourPartMatchText();
            String strHour = (hour >= 10) ? hour.toString() : "0" + hour.toString(); //補0上去
            strDate = strDate.replace(match, strHour); // strDate = strDate.replace(match, hour.toString());
        } else {
            String msgFmt = "expression is %s, cannot fill hour, will ignore";
            logger.log(Level.FINE, String.format(msgFmt, expression.getFormat()));            
        }
        return strDate;
    }

    public static String processMinutePart(String strDate, TimeExpression expression, Integer minute) {
        if (minute == null) {
            return strDate;
        }

        if (expression.getIsMinuteFillable()) {
            // 將月份區塊先用 指定年度(monthOfAnnual)更換
            String match = expression.getMinutePartMatchText();
            String strMonth = (minute >= 10) ? minute.toString() : "0" + minute.toString(); //補0上去
            strDate = strDate.replace(match, strMonth);
        } else {
            String msgFmt = "expression is %s, cannot fill minute, will ignore";
            logger.log(Level.FINE, String.format(msgFmt, expression.getFormat()));            
        }
        return strDate;
    }

    public static String processSecondPart(String strDate, TimeExpression expression, Integer second) {
        if (second == null) {
            return strDate;
        }
        if (expression.getIsSecondFillable()) {
            // 將日期區塊先用 指定年度(dayOfMonth)更換
            String match = expression.getSecondPartMatchText();
            String strSecond = (second >= 10) ? second.toString() : "0" + second.toString();
            strDate = strDate.replace(match, strSecond); // strDate = strDate.replace(match, dayOfMonth.toString());
        } else {
            String msgFmt = "expression is %s, cannot fill second, will ignore";
            logger.log(Level.FINE, String.format(msgFmt, expression.getFormat()));            
        }
        return strDate;
    }
        
}
