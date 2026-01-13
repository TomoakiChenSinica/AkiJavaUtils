/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.datetime.entity.range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;
import tw.dev.tomoaki.util.datetime.util.RangeHelper;

/**
 *
 * @author Tomoaki Chen
 */
public class DateTimeRange {

    private LocalDateTime since;
    private LocalDateTime until;

    protected DateTimeRange() {
    }

    public static DateTimeRange create(Date utilStartDateTime, Date utilEndDateTime) {
        DateTimeRange dateTimeRange = new DateTimeRange();
        dateTimeRange.since = DateTimeUtil.Converter.convert2DateTime(utilStartDateTime);
        dateTimeRange.until = DateTimeUtil.Converter.convert2DateTime(utilEndDateTime);
        return dateTimeRange;
    }

    public static DateTimeRange create(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        DateTimeRange dateTimeRange = new DateTimeRange();
        dateTimeRange.since = startDateTime;
        dateTimeRange.until = endDateTime;
        return dateTimeRange;
    }

    public static DateTimeRange create() {
        LocalDateTime start = null;
        LocalDateTime end = null;
        return DateTimeRange.create(start, end);
    }

    public LocalDateTime getStartDateTime() {
        return since;
    }

    public Date getUtilStartDateTime() {
        return DateTimeUtil.Converter.convert(since);
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.since = startDateTime;
    }

    public void setUtilStartDateTime(Date utilStartDateTime) {
        this.since = DateTimeUtil.Converter.convert2DateTime(utilStartDateTime);
    }

    public LocalDateTime getEndDateTime() {
        return until;
    }

    public Date getUtilEndDateTime() {
        return DateTimeUtil.Converter.convert(this.until);
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.until = endDateTime;
    }

    public void setUtilEndDateTime(Date utilEndDateTime) {
        this.until = DateTimeUtil.Converter.convert2DateTime(utilEndDateTime);
    }

    public LocalDateTime getSince() {
        return since;
    }

    public void setSince(LocalDateTime since) {
        this.since = since;
    }

    public LocalDateTime getUntil() {
        return until;
    }

    public void setUntil(LocalDateTime until) {
        this.until = until;
    }

    @Override
    public String toString() {
        // String result = this.startDateTime + " ~ " + this.endDateTime;
        // return result;
        return RangeHelper.obtainString(this, since, until);
    }

//<editor-fold defaultstate="collapsed" desc="一些輔助計算 methods">    
    public DateTimeRange plusYears(long years) {
        LocalDateTime newSince = this.since == null ? null : this.since.plusYears(years);
        LocalDateTime newUntil = this.until == null ? null : this.until.plusYears(years);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange minusYears(long years) {
        LocalDateTime newSince = this.since == null ? null : this.since.minusYears(years);
        LocalDateTime newUntil = this.until == null ? null : this.until.minusYears(years);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange plusMonths(long months) {
        LocalDateTime newSince = this.since == null ? null : this.since.plusMonths(months);
        LocalDateTime newUntil = this.until == null ? null : this.until.plusMonths(months);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange minusMonths(long months) {
        LocalDateTime newSince = this.since == null ? null : this.since.minusMonths(months);
        LocalDateTime newUntil = this.until == null ? null : this.until.minusMonths(months);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange plusWeeks(long weeks) {
        LocalDateTime newSince = since == null ? null : this.since.plusWeeks(weeks);
        LocalDateTime newUntil = until == null ? null : this.until.plusWeeks(weeks);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange minusWeeks(long weeks) {
        LocalDateTime newSince = since == null ? null : this.since.minusWeeks(weeks);
        LocalDateTime newUntil = until == null ? null : this.until.minusWeeks(weeks);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange plusDays(long days) {
        LocalDateTime newSince = this.since == null ? null : this.since.plusDays(days);
        LocalDateTime newUntil = this.until == null ? null : this.until.plusDays(days);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange minusDays(long days) {
        LocalDateTime newSince = this.since == null ? null : this.since.minusDays(days);
        LocalDateTime newUntil = this.until == null ? null : this.until.minusDays(days);
        return DateTimeRange.create(newSince, newUntil);
    }
    
    public DateTimeRange plusHours(long hours) {
        LocalDateTime newSince = this.since == null ? null : this.since.plusHours(hours);
        LocalDateTime newUntil = this.until == null ? null : this.until.plusHours(hours);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange minusHours(long hours) {
        LocalDateTime newSince = this.since == null ? null : this.since.minusHours(hours);
        LocalDateTime newUntil = this.until == null ? null : this.until.minusHours(hours);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange plusMinutes(long minutes) {
        LocalDateTime newSince = this.since == null ? null : this.since.plusMinutes(minutes);
        LocalDateTime newUntil = this.until == null ? null : this.until.plusMinutes(minutes);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange minusMinutes(long minutes) {
        LocalDateTime newSince = this.since == null ? null : this.since.minusMinutes(minutes);
        LocalDateTime newUntil = this.until == null ? null : this.until.minusMinutes(minutes);
        return DateTimeRange.create(newSince, newUntil);
    }
    
    public DateTimeRange plusSeconds(long seconds) {
        LocalDateTime newSince = this.since == null ? null : this.since.plusSeconds(seconds);
        LocalDateTime newUntil = this.until == null ? null : this.until.plusSeconds(seconds);
        return DateTimeRange.create(newSince, newUntil);
    }

    public DateTimeRange minusSeconds(long seconds) {
        LocalDateTime newSince = this.since == null ? null : this.since.minusSeconds(seconds);
        LocalDateTime newUntil = this.until == null ? null : this.until.minusSeconds(seconds);
        return DateTimeRange.create(newSince, newUntil);
    }
    
    
    public Boolean isBefore(LocalDateTime desigDateTime) {
        return desigDateTime.isBefore(since);
    }

    public Boolean isBetween(LocalDateTime desigDateTime) {
        //不早於since --> 在since相等或之後 && 不婉瑜until -->等於或早於until
        return !desigDateTime.isBefore(since) && !desigDateTime.isAfter(until);
    }

    public Boolean isAfter(LocalDateTime desigDateTime) {
        return desigDateTime.isAfter(until);
    }    
//</editor-fold>
}
