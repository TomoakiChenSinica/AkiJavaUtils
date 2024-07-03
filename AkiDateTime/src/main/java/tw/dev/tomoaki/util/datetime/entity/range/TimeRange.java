/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.datetime.entity.range;

import java.time.LocalTime;

/**
 *
 * @author Tomoaki Chen
 */
public class TimeRange {
    
     private LocalTime startTime;
     private LocalTime endTime;
     
     protected TimeRange() {
     }
     
    public static class Factory {
        
        public static TimeRange create(LocalTime startTime, LocalTime endTime) {
            TimeRange timeRange = new TimeRange();
            timeRange.startTime = startTime;
            timeRange.endTime = endTime;
            return timeRange;
        }
        
        public static TimeRange create() {
            return TimeRange.Factory.create(null, null);
        }           
    
    }     

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
     
     
}
