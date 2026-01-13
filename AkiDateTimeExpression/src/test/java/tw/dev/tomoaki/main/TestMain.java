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
package tw.dev.tomoaki.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.stream.Stream;
import tw.dev.tomoaki.datetimeexpression.bundle.LocalDateExpression;
import tw.dev.tomoaki.datetimeexpression.bundle.LocalDateTimeExpression;
import tw.dev.tomoaki.datetimeexpression.bundle.LocalTimeExpression;
import tw.dev.tomoaki.datetimeexpression.entity.ExpressionFormatRange;
import tw.dev.tomoaki.datetimeexpression.util.ExpressionRangeHelper;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;
import tw.dev.tomoaki.util.datetime.entity.range.DateRange;
import tw.dev.tomoaki.util.datetime.entity.range.DateTimeRange;

/**
 *
 * @author tomoaki
 */
public class TestMain {

    public static void main(String[] args) {
        // test_LocalTimeExpression();
        // test_calculateRange();
        // test_LocalDateTimeExpression();
        // test_LocalDateExpression();
        test_ExpressionRange_withDateTime();
    }

    protected static void test_LocalDateExpression() {
        LocalDate date = LocalDateExpression.of("[yyyy]-[MM]-[dd+1]").ofYear(2025).ofMonth(1).ofDay(31).resolve();
        System.out.println(date);
    }

    protected static void test_LocalTimeExpression() {
        LocalTime time = LocalTimeExpression.of("12:59:[ss+1]").ofSecond(59).resolve();
        System.out.println(time);
    }

    protected static void test_calculateRange() {
        ExpressionFormatRange formatRange = ExpressionFormatRange.create("[yyyy]-[MM]-07", "[yyyy]-[MM+1]-06");

        // 2024-05-07 ~ 2024-06-06
        // 2024-06-07 ~ 2024-07-06
        // 2024-07-07 ~ 2024-08-07        
        // -----------------------------------------------------------------------------------------------------------
        // String endClozeFormat = formatRange.getStartClozeFormat();        
        // DateCloze cloze = DateCloze.Factory.create(endClozeFormat);
        Stream.of("2024-06-01", "2024-06-07", "2024-07-01").forEach(strDate -> {
            LocalDate date = DateTimeUtil.Provider.parse2Date(strDate);
            DateRange range = ExpressionRangeHelper.calculateInRange(formatRange, date);
            System.out.println(range);
        });

        // -----------------------------------------------------------------------------------------------------------
        // LocalDate desigDate = DateTimeUtil.Provider.parse2Date("2024-06-07");
        // LocalDate resultDate = desigDate.plusMonths(1);
        // System.out.println(String.format("desigDate= %s, resultDate= %s", desigDate, resultDate));
    }

    protected static void test_LocalDateTimeExpression() {
        LocalDateTimeExpression exp1 = LocalDateTimeExpression.of("[yyyy]-[MM]-[dd+1]", "[hh]:[mm]:[ss+1]");
        LocalDateTime dateTime1 = exp1.ofYear(2025).ofMonth(1).ofDay(31).ofHour(23).ofMinute(59).ofSecond(59).resolve();
        System.out.println(dateTime1);
    }
    
    protected static void test_ExpressionRange_withDateTime() {
        ExpressionFormatRange formatRange = ExpressionFormatRange.create("[yyyy]-08-01 00:00:00", "[yyyy+1]-07-31 23:59:59");
        DateTimeRange range = ExpressionRangeHelper.calculateInRange(formatRange, LocalDateTime.of(2025, 7, 1, 0, 0, 0));
        System.out.println(range);
    }
}
