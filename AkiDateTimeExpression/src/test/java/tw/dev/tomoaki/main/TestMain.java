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
import java.time.LocalTime;
import java.util.stream.Stream;
import tw.dev.tomoaki.datetimeexpression.bundle.LocalTimeExpression;
import tw.dev.tomoaki.datetimeexpression.entity.ExpressionFormatRange;
import tw.dev.tomoaki.datetimeexpression.util.ExpressionRangeHelper;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;
import tw.dev.tomoaki.util.datetime.entity.range.DateRange;

/**
 *
 * @author tomoaki
 */
public class TestMain {
    
    public static void main(String[] args) {
        test_calculateRange();
    }
    
    protected static void test_LocalTimeExpression() {
        LocalTime time = LocalTimeExpression.create("12:59:[ss+1]").ofSecond(59).resolve();        
        System.out.println(time);        
    }
    
    protected static void test_calculateRange() {
        ExpressionFormatRange formatRange = ExpressionFormatRange.create("[YYYY]-[MM]-07", "[YYYY]-[MM+1]-06");
        
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
}
