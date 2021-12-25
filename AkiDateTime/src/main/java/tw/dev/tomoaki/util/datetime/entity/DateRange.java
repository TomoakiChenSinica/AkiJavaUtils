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
 * @author Tomoaki Chen
 */
public class DateRange {
    
    private LocalDate startDate;
    private LocalDate endDate;
    
    protected DateRange(){
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
        LocalDate newEndDate =  this.endDate == null ? null : this.endDate.plusWeeks(weeks);
        return DateRange.Factory.create(newStartDate, newEndDate);
    }
    
    public DateRange minusWeeks(Long weeks) {
        LocalDate newStartDate =  this.startDate == null ? null : this.startDate.minusWeeks(weeks);
        LocalDate newEndDate = this.endDate == null ? null : this.endDate.minusWeeks(weeks);
        return DateRange.Factory.create(newStartDate, newEndDate);
    }    

    @Override
    public String toString() {
        return this.startDate + " ~ " + this.endDate;
    }
    
    
}
