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

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    protected DateTimeRange() {
    }
    
    public static class Factory {
        
        public static DateTimeRange create(Date utilStartDateTime, Date utilEndDateTime) {
            DateTimeRange dateTimeRange = new DateTimeRange();            
            dateTimeRange.startDateTime = DateTimeUtil.Converter.convert2DateTime(utilStartDateTime);
            dateTimeRange.endDateTime = DateTimeUtil.Converter.convert2DateTime(utilEndDateTime);
            return dateTimeRange;
        }        
        
        public static DateTimeRange create(LocalDateTime startDateTime, LocalDateTime endDateTime) {
            DateTimeRange dateTimeRange = new DateTimeRange();
            dateTimeRange.startDateTime = startDateTime;
            dateTimeRange.endDateTime = endDateTime;
            return dateTimeRange ;
        }
        
        public static DateTimeRange create() {
            LocalDateTime start = null;
            LocalDateTime end = null;
            return DateTimeRange.Factory.create(start, end);
        }           
    
    }    
    
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
    
    public Date getUtilStartDateTime() {
        return DateTimeUtil.Converter.convert(startDateTime);
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
    
    public void setUtilStartDateTime(Date utilStartDateTime) {
        this.startDateTime = DateTimeUtil.Converter.convert2DateTime(utilStartDateTime);
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
    
    public Date getUtilEndDateTime() {
        return DateTimeUtil.Converter.convert(this.endDateTime);
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;       
    }

    public void setUtilEndDateTime(Date utilEndDateTime) {
        this.endDateTime = DateTimeUtil.Converter.convert2DateTime(utilEndDateTime);
    }    
    
    @Override
    public String toString() {
//        String result = this.startDateTime + " ~ " + this.endDateTime;
//        return result;
        return RangeHelper.obtainString(this, startDateTime, endDateTime);
    }
    
//<editor-fold defaultstate="collapsed" desc="一些輔助計算 methods">
    public DateTimeRange plusWeeks(Long weeks) {        
        LocalDateTime newStartDateTime = this.startDateTime.plusWeeks(weeks);
        LocalDateTime newEndDateTime = this.endDateTime.plusWeeks(weeks);
        return DateTimeRange.Factory.create(newStartDateTime, newEndDateTime);
    }
    
    public DateTimeRange minusWeeks(Long weeks) {
        LocalDateTime newStartDateTime = this.startDateTime.minusWeeks(weeks);
        LocalDateTime newEndDateTime = this.endDateTime.minusWeeks(weeks);
        return DateTimeRange.Factory.create(newStartDateTime, newEndDateTime);
    }    
//</editor-fold>
}
