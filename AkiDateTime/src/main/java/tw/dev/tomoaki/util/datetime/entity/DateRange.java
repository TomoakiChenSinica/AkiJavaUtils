/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.datetime.entity;

import java.time.LocalDate;
import java.util.Date;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author Tomoaki Chen Java8 的 java.time 已經有 Period 幾乎一樣的功能，此則可以考慮廢除??? (X)
 * Period 比較適用在計算隔幾天
 */
public class DateRange {

    private LocalDate startDate;
    private LocalDate endDate;

    protected DateRange() {
    }

    public static class Factory {

        public static DateRange create(LocalDate startDate, LocalDate endDate) {
            DateRange dateRange = new DateRange();
            dateRange.startDate = startDate;
            dateRange.endDate = endDate;
            return dateRange;
        }

        public static DateRange create() {
            return DateRange.Factory.create(null, null);
        }

    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Date getUtilStartDate() {
        return DateTimeUtil.Converter.convert(startDate);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Date getUtilEndDate() {
        return DateTimeUtil.Converter.convert(endDate);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public DateRange plusWeeks(Long weeks) {
        LocalDate newStartDate = this.startDate == null ? null : this.startDate.plusWeeks(weeks);
        LocalDate newEndDate = this.endDate == null ? null : this.endDate.plusWeeks(weeks);
        return DateRange.Factory.create(newStartDate, newEndDate);
    }

    public DateRange minusWeeks(Long weeks) {
        LocalDate newStartDate = this.startDate == null ? null : this.startDate.minusWeeks(weeks);
        LocalDate newEndDate = this.endDate == null ? null : this.endDate.minusWeeks(weeks);
        return DateRange.Factory.create(newStartDate, newEndDate);
    }

    public DateRange plusYears(Long years) {
        LocalDate newStartDate = this.startDate == null ? null : this.startDate.plusYears(years);
        LocalDate newEndDate = this.endDate == null ? null : this.endDate.plusYears(years);
        return DateRange.Factory.create(newStartDate, newEndDate);
    }

    public DateRange minusYears(Long years) {
        LocalDate newStartDate = this.startDate == null ? null : this.startDate.minusYears(years);
        LocalDate newEndDate = this.endDate == null ? null : this.endDate.minusYears(years);
        return DateRange.Factory.create(newStartDate, newEndDate);
    }

    public DateRange plusMonths(Long years) {
        LocalDate newStartDate = this.startDate == null ? null : this.startDate.plusMonths(years);
        LocalDate newEndDate = this.endDate == null ? null : this.endDate.plusMonths(years);
        return DateRange.Factory.create(newStartDate, newEndDate);
    }

    public DateRange minusMonths(Long years) {
        LocalDate newStartDate = this.startDate == null ? null : this.startDate.minusMonths(years);
        LocalDate newEndDate = this.endDate == null ? null : this.endDate.minusMonths(years);
        return DateRange.Factory.create(newStartDate, newEndDate);
    }

    public DateRange plusDays(Long years) {
        LocalDate newStartDate = this.startDate == null ? null : this.startDate.plusDays(years);
        LocalDate newEndDate = this.endDate == null ? null : this.endDate.plusDays(years);
        return DateRange.Factory.create(newStartDate, newEndDate);
    }

    public DateRange minusDays(Long years) {
        LocalDate newStartDate = this.startDate == null ? null : this.startDate.minusDays(years);
        LocalDate newEndDate = this.endDate == null ? null : this.endDate.minusDays(years);
        return DateRange.Factory.create(newStartDate, newEndDate);
    }

    public Boolean isBefore(LocalDate desigDate) {
        return desigDate.isBefore(startDate);
    }

    public Boolean isBetween(LocalDate desigDate) {
        //不早於startDate --> 在startDate相等或之後 && 不婉瑜endDate -->等於或早於endDate
        return !desigDate.isBefore(startDate) && !desigDate.isAfter(endDate);
    }

    public Boolean isAfter(LocalDate desigDate) {
        return desigDate.isAfter(endDate);
    }

    @Override
    public String toString() {
        return this.startDate + " ~ " + this.endDate;
    }

}
