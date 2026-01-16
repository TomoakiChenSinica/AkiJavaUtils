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
import tw.dev.tomoaki.util.datetime.entity.range.DateRange;
import tw.dev.tomoaki.util.datetime.entity.range.DateTimeRange;

/**
 *
 * @author tomoaki
 */
public class RangeTestMain {

    public static void main(String[] args) {
        // test_DateRange();
        // test_DateTimeRange();
        test_DateTimeRange_Compare();
    }

    private static void test_DateRange() {
        DateRange range = DateRange.create(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31));
        System.out.println("range= " + range);
        System.out.println("range+1y= " + range.plusYears(1l));
        System.out.println("range+1M= " + range.plusMonths(1l));
        System.out.println("range+1d= " + range.plusDays(1l));
        System.out.println("range-1y= " + range.minusYears(1l));
        System.out.println("range-1M= " + range.minusMonths(1l));
        System.out.println("range-1d= " + range.minusDays(1l));
        
        System.out.println("range+1week= " + range.plusWeeks(1l));
        System.out.println("range-1week= " + range.minusWeeks(1l));        
    }
    
    private static void test_DateTimeRange() {
        DateTimeRange range = DateTimeRange.create(LocalDateTime.of(2025, 1, 1, 0, 0, 0), LocalDateTime.of(2025, 1, 31, 23, 59, 59));
        System.out.println("range= " + range);
        System.out.println("range+1y= " + range.plusYears(1l));
        System.out.println("range+1M= " + range.plusMonths(1l));
        System.out.println("range+1d= " + range.plusDays(1l));
        System.out.println("range-1y= " + range.minusYears(1l));
        System.out.println("range-1M= " + range.minusMonths(1l));
        System.out.println("range-1d= " + range.minusDays(1l));
        
        System.out.println("range+1H= " + range.plusHours(1l));
        System.out.println("range-1H= " + range.minusHours(1l));
        System.out.println("range+1m= " + range.plusMinutes(1l));
        System.out.println("range-1m= " + range.minusMinutes(1l));        
        System.out.println("range+1s= " + range.plusSeconds(1l));
        System.out.println("range-1s= " + range.plusSeconds(1l));        
        
        System.out.println("range+1week= " + range.plusWeeks(1l));
        System.out.println("range-1week= " + range.minusWeeks(1l));        
    }
    
    private static void test_DateTimeRange_Compare() {
        DateTimeRange range = DateTimeRange.create(LocalDateTime.of(2025, 1, 1, 0, 0, 0), LocalDateTime.of(2025, 1, 31, 23, 59, 59));
        LocalDateTime d1 = LocalDateTime.of(2024, 12, 31, 0, 0 , 0);
        LocalDateTime d2 = LocalDateTime.of(2025, 1, 15, 0, 0 , 0);
        LocalDateTime d3 = LocalDateTime.of(2026, 1, 15, 0, 0 , 0);
        
        
        System.out.println("range= " + range.toDisplayText());
        System.out.println("d1= " + d1);
        System.out.println("d2= " + d2);
        System.out.println("d3= " + d3);
        
        System.out.println("range.isBefore(d1)? " +  range.isBefore(d1));
        System.out.println("range.isBetween(d1)? " +  range.isBetween(d1));
        System.out.println("range.isAfter(d1)? " +  range.isAfter(d1));

        System.out.println("range.isBefore(d2)? " +  range.isBefore(d2));
        System.out.println("range.isBetween(d2)? " +  range.isBetween(d2));
        System.out.println("range.isAfter(d2)? " +  range.isAfter(d2));

        System.out.println("range.isBefore(d3)? " +  range.isBefore(d3));
        System.out.println("range.isBetween(d3)? " +  range.isBetween(d3));
        System.out.println("range.isAfter(d3)? " +  range.isAfter(d3));        
    }
}
