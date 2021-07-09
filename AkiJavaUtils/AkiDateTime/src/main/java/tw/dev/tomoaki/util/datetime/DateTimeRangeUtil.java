/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.datetime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import tw.dev.tomoaki.util.datetime.entity.DateRange;

/**
 *
 * @author Tomoaki Chen
 */
public class DateTimeRangeUtil {
    
    public static class Provider {
        public static DateRange obtainWeekDateRange(LocalDate theDate) {
            LocalDate weekStartDate = DateTimeUtil.Provider.obtainDayOfWeek(theDate, 1);
            LocalDate weekEndDate = DateTimeUtil.Provider.obtainDayOfWeek(theDate, 7);
            return DateRange.Factory.create(weekStartDate, weekEndDate);
        }
    }    
    
    public static class Analyzer {
        public static List<LocalDate> obtainDateList(DateRange dateRange) {
            List<LocalDate> dateList = new ArrayList();
            
            LocalDate startDate = dateRange.getStartDate();
            LocalDate endDate = dateRange.getEndDate();
            
            LocalDate nowDate = startDate;
            while(!nowDate.isAfter(endDate)) {
                dateList.add(nowDate);
                nowDate = nowDate.plusDays(1);
            }
            return dateList;
        }
    }
}
