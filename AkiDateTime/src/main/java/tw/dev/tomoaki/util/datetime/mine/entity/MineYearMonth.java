/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.datetime.mine.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoField.YEAR;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import java.util.Objects;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;
import tw.dev.tomoaki.util.datetime.entity.ChronoLocalYearMonth;

/**
 *
 * @author tomoaki
 * 
 * 2024-10-29 發現有 java.time.YearMonth.....
 */
public class MineYearMonth implements ChronoLocalYearMonth, Comparable<MineYearMonth> { //implements Temporal {

    public static String DEFAULT_FORMAT_PATTERN = "yyyy-MM";

    private final int year;

    private final short month;

    private MineYearMonth(int year, int month) {
        this.year = year;
        this.month = (short) month;
    }

    public static MineYearMonth of(int year, int month) {
        // if(year == default || month == default)  
        if (year == 0 || month == 0) {
            return null;
        }
        YEAR.checkValidValue(year);
        MONTH_OF_YEAR.checkValidValue(month);
        return new MineYearMonth(year, month);
    }

    public static MineYearMonth from(LocalDate date) {
        final int tempeYear = date.getYear();
        final int tempMonth = date.getMonthValue();
        return MineYearMonth.of(tempeYear, tempMonth);
    }

    public static MineYearMonth from(LocalDateTime dateTime) {
        final int tempYear = dateTime.getYear();
        final int tempMonth = dateTime.getMonthValue();
        return MineYearMonth.of(tempYear, tempMonth);
    }

    public static MineYearMonth from(java.util.Date utilDate) {
        LocalDate date = DateTimeUtil.Converter.convert2Date(utilDate);
        return MineYearMonth.from(date);
    }

    public static MineYearMonth now() {
        final LocalDate date = LocalDate.now();
        return from(date);
    }

    /*
    Temporal TemporalAccessor TemporalQuery
    https://zq99299.github.io/java-tutorial/datetime/iso/Temporal.html#chronofield-%E5%92%8C-isofields
     */

 /*public static LocalYearMonth from(TemporalAccessor temporal) {
    
    public static LocalYearMonth parse(CharSequence text, DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.parse(text, LocalYearMonth::from);    
    }*/
    // -----------------------------------------------------------------------------------------------------------------
    public int getYear() {
        return year;
    }

    public short getMonth() {
        return month;
    }

    public String format() {
        return format(DEFAULT_FORMAT_PATTERN);
    }

    public String format(String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        return format(formatter);
    }

    public String format(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        LocalDate date = this.atDay(1);
        return date.format(formatter);
    }

    public MineYearMonth plusYears(int yearsToAdd) {
        int newYear = this.year + yearsToAdd;
        return MineYearMonth.of(newYear, month);
    }

    public MineYearMonth plusYears(long yearsToAdd) {
        return plusYears((int) yearsToAdd);
    }

    public MineYearMonth plusMonths(int monthsToAdd) {
        LocalDate resultDate = this.atDay(1).plusMonths(monthsToAdd);
        return MineYearMonth.from(resultDate);
    }

    public MineYearMonth plusMonths(long monthsToAdd) {
        return plusMonths((int) monthsToAdd);
    }

    public MineYearMonth minusYears(int yearsToSubstract) {
        int newYear = this.year - yearsToSubstract;
        return MineYearMonth.of(newYear, month);
    }

    public MineYearMonth minusYears(long yearsToSubstract) {
        return this.minusYears((int) yearsToSubstract);
    }

    public MineYearMonth minusMonths(int monthsToSubstract) {
        LocalDate resultDate = this.atDay(1).minusMonths(monthsToSubstract);
        return MineYearMonth.from(resultDate);
    }
    
    public MineYearMonth minusMonths(long monthsToSubstract) {
        return this.minusMonths((int) monthsToSubstract);
    }    

    @Override
    public LocalDate atDay(Integer dayOfMonth) {
        return LocalDate.of(year, month, 1);
    }

    @Override
    public boolean isBefore(ChronoLocalYearMonth otherYearMonth) {
        LocalDate selfDate = this.atDay(1);
        LocalDate otherDate = otherYearMonth.atDay(1);
        return selfDate.isBefore(otherDate);
    }

    @Override
    public boolean isAfter(ChronoLocalYearMonth otherYearMonth) {
        LocalDate selfDate = this.atDay(1);
        LocalDate otherDate = otherYearMonth.atDay(1);
        return selfDate.isAfter(otherDate);
    }

    @Override
    public boolean isEqual(ChronoLocalYearMonth otherYearMonth) {
        LocalDate selfDate = this.atDay(1);
        LocalDate otherDate = otherYearMonth.atDay(1);
        return selfDate.isEqual(otherDate);
    }

    @Override
    public boolean equals(Object otherYearMonth) {
        return isEqual((ChronoLocalYearMonth) otherYearMonth);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.year;
        hash = 59 * hash + this.month;
        return hash;
    }

    @Override
    public String toString() {
        return this.format();
    }

    @Override
    public int compareTo(MineYearMonth other) {
        if (this.isBefore(other)) {
            return -1;
        }

        if (this.isAfter(other)) {
            return 1;
        }
        return 0;
    }
}
