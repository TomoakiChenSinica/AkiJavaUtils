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
package tw.dev.tomoaki.util.datetime.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

/**
 *
 * @author tomoaki
 */
public class IncompleteYearMonth implements ChronoLocalYearMonth, Comparable<IncompleteYearMonth> { //implements Temporal {

    public static String DEFAULT_FORMAT_PATTERN = "yyyy-MM";
    public static String DEFAULT_ONLY_YEAR_FORMAT_PATTERN = "yyyy-";

    private final Integer year;

    private final Short month;

    private IncompleteYearMonth(int year) {
        this.year = year;
        this.month = null;
    }

    private IncompleteYearMonth(int year, int month) {
        this.year = year;
        this.month = (short) month;
    }

    public static IncompleteYearMonth of(int year) {
        if (year == 0) {
            return null;
        }
        YEAR.checkValidValue(year);
        return new IncompleteYearMonth(year);
    }

    public static IncompleteYearMonth of(int year, int month) {
        // if(year == default || month == default)  
        if (year == 0 || month == 0) {
            return null;
        }
        YEAR.checkValidValue(year);
        MONTH_OF_YEAR.checkValidValue(month);
        return new IncompleteYearMonth(year, month);
    }

    public static IncompleteYearMonth from(LocalDate date) {
        final int tempeYear = date.getYear();
        final int tempMonth = date.getMonthValue();
        return IncompleteYearMonth.of(tempeYear, tempMonth);
    }

    public static IncompleteYearMonth from(LocalDateTime dateTime) {
        final int tempYear = dateTime.getYear();
        final int tempMonth = dateTime.getMonthValue();
        return IncompleteYearMonth.of(tempYear, tempMonth);
    }

    public static IncompleteYearMonth now() {
        final LocalDate date = LocalDate.now();
        return from(date);
    }

    // ----------------------------------------------------------------------------------------
    public Integer getYear() {
        return year;
    }

    public Short getMonth() {
        return month;
    }
    
    public String format() {
        String formatPattern = month != null ? DEFAULT_FORMAT_PATTERN : DEFAULT_ONLY_YEAR_FORMAT_PATTERN;
        return format(formatPattern);
    }    
    

    public String format(String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        return format(formatter);
    }

    public String format(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        LocalDate date = month != null ? this.atDay(1) : this.atMonthAndDay(1, 1);
        return date.format(formatter);
    }    
        

    @Override
    public LocalDate atDay(Integer dayOfMonth) {
        if (month == null) {
            throw new IllegalArgumentException("Because month is null, cannot convert to LocalDate, you can try atMonthAndDay().");
        }
        return LocalDate.of(year, month, 1);
    }
    
    public LocalDate atMonthAndDay(Integer monthValue, Integer dayOfMonth) {
        if(month != null) {
            throw new IllegalArgumentException("Because month is not null, should use atDay() instead.");            
        }
        return LocalDate.of(year, monthValue, dayOfMonth);
    }

    @Override
    public boolean isBefore(ChronoLocalYearMonth otherYearMonth) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isAfter(ChronoLocalYearMonth otherYearMonth) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEqual(ChronoLocalYearMonth otherYearMonth) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public String toString() {
        return this.format();
    }    
    
    @Override
    public int compareTo(IncompleteYearMonth other) {
        if (this.year < other.getYear()) {
            return -1;
        }

        if (this.year > other.getYear()) {
            return 1;
        }

        // null 會因為這樣轉換成 0 (X)
        short selfMonth = (this.month != null) ? this.month : 0;
        short otherMonth = (other.getMonth() != null) ? other.getMonth() : 0;

        if (selfMonth < otherMonth) {
            return -1;
        }

        if (selfMonth > otherMonth) {
            return 1;
        }
        return 0;

    }
}
