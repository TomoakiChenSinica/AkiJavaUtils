/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.datetime.entity.range;

import java.time.LocalTime;
import tw.dev.tomoaki.util.datetime.util.RangeHelper;

/**
 *
 * @author Tomoaki Chen
 */
public class TimeRange {

    private LocalTime startTime;
    private LocalTime endTime;

    protected TimeRange() {
    }


    public static TimeRange create(LocalTime startTime, LocalTime endTime) {
        TimeRange timeRange = new TimeRange();
        timeRange.startTime = startTime;
        timeRange.endTime = endTime;
        return timeRange;
    }

    public static TimeRange create() {
        return TimeRange.create(null, null);
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

    @Override
    public String toString() {
        return RangeHelper.obtainString(this, startTime, endTime);
    }
}
