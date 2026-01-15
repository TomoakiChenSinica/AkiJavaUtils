/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.datetime.entity.range;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author Tomoaki Chen
 */
public class TimeRange extends AbstractRange<LocalTime> {

    /*
    private LocalTime since;
    private LocalTime until; */
    protected TimeRange() {
    }

//<editor-fold defaultstate="collapsed" desc="static factory pattern">

    public static TimeRange create(LocalTime since, LocalTime until) {
        TimeRange timeRange = new TimeRange();
        timeRange.since = since;
        timeRange.until = until;
        return timeRange;
    }

    public static TimeRange create() {
        return TimeRange.create(null, null);
    }
    
//</editor-fold>
    
    public LocalTime getStartTime() {
        return since;
    }

    public void setStartTime(LocalTime startTime) {
        this.since = startTime;
    }    

    public LocalTime getEndTime() {
        return until;
    }

    public void setEndTime(LocalTime endTime) {
        this.until = endTime;
    }

//<editor-fold defaultstate="collapsed" desc="實作 AbtractRange<T> 所需 Methods">
    @Override
    public Boolean isBefore(LocalTime standard) {
        return standard.isBefore(this.since);
    }

    @Override
    public Boolean isBetween(LocalTime standard) {
        return !standard.isBefore(since) && !standard.isAfter(until);
    }

    @Override
    public Boolean isAfter(LocalTime standard) {
        return standard.isAfter(until);
    }    
//</editor-fold>
 
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimeRange)) {
            return false;
        }

        TimeRange other = (TimeRange) obj;
        return Objects.equals(since, other.since) && Objects.equals(until, other.until);
    }

    @Override
    public int hashCode() {
        int hash = 33;
        hash = 87 * hash + Objects.hashCode(this.since);
        hash = 87 * hash + Objects.hashCode(this.until);
        return hash;
    }    
}
