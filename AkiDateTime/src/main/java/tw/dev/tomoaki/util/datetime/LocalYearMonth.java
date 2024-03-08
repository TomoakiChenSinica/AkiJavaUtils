/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datatime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoField.YEAR;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import java.util.Objects;

/**
 *
 * @author tomoaki
 */
public class LocalYearMonth implements ChronoLocalYearMonth, Comparable<LocalYearMonth> { //implements Temporal {

    public static String DEFAULT_FORMAT_PATTERN = "yyyy-MM";

    private final int year;

    private final short month;

    private LocalYearMonth(int year, int month) {
        this.year = year;
        this.month = (short) month;
    }

    public static LocalYearMonth of(int year, int month) {
        YEAR.checkValidValue(year);
        MONTH_OF_YEAR.checkValidValue(month);
        return new LocalYearMonth(year, month);
    }

    public static LocalYearMonth from(LocalDate date) {
        final int tempeYear = date.getYear();
        final int tempMonth = date.getMonthValue();
        return LocalYearMonth.of(tempeYear, tempMonth);
    }

    public static LocalYearMonth from(LocalDateTime dateTime) {
        final int tempYear = dateTime.getYear();
        final int tempMonth = dateTime.getMonthValue();
        return LocalYearMonth.of(tempYear, tempMonth);
    }

    public static LocalYearMonth now() {
        final LocalDate date = LocalDate.now();
        return from(date);
    }

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
        // LocalDate date = LocalDate.of(year, (int) month, 1);
        LocalDate date = this.atDay(1);
        return date.format(formatter);
    }

    public LocalYearMonth plusYears(/*int plusedYear*/int yearsToAdd) {
        int newYear = this.year + yearsToAdd;
        return LocalYearMonth.of(newYear, month);
    }

    public LocalYearMonth plusMonths(int monthsToAdd) {
        // LocalDate resultDate = LocalDate.of(year, month, 1).plusMonths(monthsToAdd);
        LocalDate resultDate = this.atDay(1).plusMonths(monthsToAdd);
        return LocalYearMonth.from(resultDate);
    }

    public LocalYearMonth minusYears(int yearsToSubstract) {
        int newYear = this.year - yearsToSubstract;
        return LocalYearMonth.of(newYear, month);
    }

    public LocalYearMonth minusMonths(int monthsToSubstract) {
        // LocalDate resultDate = LocalDate.of(year, month, 1).minusMonths(monthsToSubstract);
        LocalDate resultDate = this.atDay(1).minusMonths(monthsToSubstract);
        return LocalYearMonth.from(resultDate);
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
    public String toString() {
        return this.format();
    }

    @Override
    public int compareTo(LocalYearMonth other) {
        if (this.isBefore(other)) {
            return -1;
        }

        if (this.isAfter(other)) {
            return 1;
        }

        return 0;
    }
}
