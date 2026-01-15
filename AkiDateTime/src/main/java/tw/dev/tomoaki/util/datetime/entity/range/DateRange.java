/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.datetime.entity.range;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import tw.dev.tomoaki.util.datetime.DateTimeRangeUtil;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;
import tw.dev.tomoaki.util.datetime.util.RangeHelper;

/**
 *
 * @author Tomoaki Chen Java8 的 java.time 已經有 Period 幾乎一樣的功能，此則可以考慮廢除??? (X)
 * Period 比較適用在計算隔幾天
 */
public class DateRange extends AbstractRange<LocalDate> {

    /*
    private LocalDate since;
    private LocalDate until;*/

    protected DateRange() {
    }

//<editor-fold defaultstate="collapsed" desc="static factory pattern">
    public static DateRange create(LocalDate since, LocalDate until) {
        DateRange dateRange = new DateRange();
        dateRange.since = since;
        dateRange.until = until;
        return dateRange;
    }

    public static DateRange create(Date since, Date until) {
        return create(DateTimeUtil.Converter.convert2Date(since), DateTimeUtil.Converter.convert2Date(until));
    }
    
    public static DateRange create() {
        // return DateRange.create(null, null);
        DateRange dateRange = new DateRange();
        dateRange.since = null;
        dateRange.until = null;
        return dateRange;        
    }    
//</editor-fold>


    public LocalDate getStartDate() {
        return since;
    }

    public void setStartDate(LocalDate since) {
        this.since = since;
    }    

    public void setStartDate(Date utilStartDate) {
        this.since = DateTimeUtil.Converter.convert2Date(utilStartDate);
    }    
    
    public LocalDate getEndDate() {
        return until;
    }

    public void setEndDate(LocalDate until) {
        this.until = until;
    }

    public void setEndDate(Date utilEndDate) {
        this.until = DateTimeUtil.Converter.convert2Date(utilEndDate);
    } 
    
    public Date toUtilStartDate() {
        return DateTimeUtil.Converter.convert(since);
    }    
    
    public Date toUtilEndDate() {
        return DateTimeUtil.Converter.convert(until);
    }    

    /*拉到 AbstractRange 實作
    public LocalDate getSince() {
        return since;
    }

    public void setSince(LocalDate since) {
        this.since = since;
    }

    public LocalDate getUntil() {
        return until;
    }

    public void setUntil(LocalDate until) {
        this.until = until;
    }*/
    
    
//<editor-fold defaultstate="collapsed" desc="一些輔助計算 methods">    
    public DateRange plusYears(long years) {
        LocalDate newStartDate = this.since == null ? null : this.since.plusYears(years);
        LocalDate newEndDate = this.until == null ? null : this.until.plusYears(years);
        return DateRange.create(newStartDate, newEndDate);
    }

    public DateRange minusYears(long years) {
        LocalDate newStartDate = this.since == null ? null : this.since.minusYears(years);
        LocalDate newEndDate = this.until == null ? null : this.until.minusYears(years);
        return DateRange.create(newStartDate, newEndDate);
    }

    public DateRange plusMonths(long months) {
        LocalDate newStartDate = this.since == null ? null : this.since.plusMonths(months);
        LocalDate newEndDate = this.until == null ? null : this.until.plusMonths(months);
        return DateRange.create(newStartDate, newEndDate);
    }

    public DateRange minusMonths(long months) {
        LocalDate newStartDate = this.since == null ? null : this.since.minusMonths(months);
        LocalDate newEndDate = this.until == null ? null : this.until.minusMonths(months);
        return DateRange.create(newStartDate, newEndDate);
    }
    
    public DateRange plusWeeks(long weeks) {
        LocalDate newStartDate = this.since == null ? null : this.since.plusWeeks(weeks);
        LocalDate newEndDate = this.until == null ? null : this.until.plusWeeks(weeks);
        return DateRange.create(newStartDate, newEndDate);
    }

    public DateRange minusWeeks(long weeks) {
        LocalDate newStartDate = this.since == null ? null : this.since.minusWeeks(weeks);
        LocalDate newEndDate = this.until == null ? null : this.until.minusWeeks(weeks);
        return DateRange.create(newStartDate, newEndDate);
    }    

    public DateRange plusDays(long days) {
        LocalDate newStartDate = this.since == null ? null : this.since.plusDays(days);
        LocalDate newEndDate = this.until == null ? null : this.until.plusDays(days);
        return DateRange.create(newStartDate, newEndDate);
    }

    public DateRange minusDays(long days) {
        LocalDate newStartDate = this.since == null ? null : this.since.minusDays(days);
        LocalDate newEndDate = this.until == null ? null : this.until.minusDays(days);
        return DateRange.create(newStartDate, newEndDate);
    }

    @Override
    public Boolean isBefore(LocalDate desigDate) {
        return desigDate.isBefore(since);
    }

    @Override
    public Boolean isBetween(LocalDate desigDate) {
        //不早於since --> 在since相等或之後 && 不婉瑜until -->等於或早於until
        return !desigDate.isBefore(since) && !desigDate.isAfter(until);
    }

    @Override
    public Boolean isAfter(LocalDate desigDate) {
        return desigDate.isAfter(until);
    }
//</editor-fold>
    
    /*
    @Override
    public String toString() {
        return RangeHelper.obtainClassString(this, since, until);
    } */
    
    public List<LocalDate> toDateList() {
        return DateTimeRangeUtil.Analyzer.obtainDateList(this);
    }
    
    public Stream<LocalDate> toDateStream() {
        return DateTimeRangeUtil.Analyzer.obtainDateList(this).stream();
    }    

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DateRange)) {
            return false;
        }

        DateRange other = (DateRange) obj;
        return Objects.equals(since, other.since) && Objects.equals(until, other.until);
    }

    @Override
    public int hashCode() {
        int hash = 15;
        hash = 31 * hash + Objects.hashCode(this.since);
        hash = 31 * hash + Objects.hashCode(this.until);
        return hash;
    }    
}
