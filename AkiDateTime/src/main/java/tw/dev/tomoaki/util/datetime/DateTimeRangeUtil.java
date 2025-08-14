/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tw.dev.tomoaki.util.datetime.entity.range.DateRange;
import tw.dev.tomoaki.util.datetime.entity.range.DateTimeRange;
import tw.dev.tomoaki.util.datetime.util.NanoMathHelper;

/**
 *
 * @author Tomoaki Chen
 */
public class DateTimeRangeUtil {

    public static class Provider {

        public static DateRange obtainWeekDateRange(LocalDate theDate) {
            LocalDate weekStartDate = DateTimeUtil.Provider.obtainDayOfWeek(theDate, 1);
            LocalDate weekEndDate = DateTimeUtil.Provider.obtainDayOfWeek(theDate, 7);
            return DateRange.create(weekStartDate, weekEndDate);
        }
    }

    public static class Analyzer {

        public static List<LocalDate> obtainDateList(DateRange dateRange) {
            List<LocalDate> dateList = new ArrayList();

            LocalDate startDate = dateRange.getStartDate();
            LocalDate endDate = dateRange.getEndDate();            
            
            LocalDate nowDate = startDate;
            while (!nowDate.isAfter(endDate)) {
                dateList.add(nowDate);
                nowDate = nowDate.plusDays(1);
            }
            return dateList;
        }

        /**
         * 
         * 傳進標準組(standardGroup) 的起始、結束日期時間 和 比較組(cmopareGroup)的起始、結束日期時間
         *
         * @param standardStartDate 標準組起始時間
         * @param standardEndDate 標準組的結束時間
         * @param comparedStartDate 比較組的起始時間
         * @param comparedEndDate 比較組的結束時間
         * @return 第標準組時間 和 比較組時間 是否有重疊。
         *
         */
        public static Boolean isOverlap(Date standardStartDate, Date standardEndDate, Date comparedStartDate, Date comparedEndDate) {
            Date tempstandardStartDate = standardStartDate == null ? DateTimeUtil.MIN_UTIL_DATE : standardStartDate;
            Date tempstandardEndDate = standardEndDate == null ? DateTimeUtil.MAX_UTIL_DATE : standardEndDate;

            Date tempCompareStartDate = comparedStartDate == null ? DateTimeUtil.MIN_UTIL_DATE : comparedStartDate;
            Date tempCompareEndDate = comparedEndDate == null ? DateTimeUtil.MAX_UTIL_DATE : comparedEndDate;

            Long standardStart = tempstandardStartDate.getTime();
            Long standardEnd = tempstandardEndDate.getTime();
            Long comparedStart = tempCompareStartDate.getTime();
            Long comparedEnd = tempCompareEndDate.getTime();
            return NanoMathHelper.isOverlap(standardStart, standardEnd, comparedStart, comparedEnd);
        }
        
        
        public static Boolean isOverlap(LocalDate standardStartDate, LocalDate standardEndDate, LocalDate comparedStartDate, LocalDate comparedEndDate) {
            standardStartDate = standardStartDate == null ? LocalDate.MIN : standardStartDate;
            standardEndDate = standardEndDate == null ? LocalDate.MAX : standardEndDate;
            comparedStartDate = comparedStartDate == null ? LocalDate.MIN : comparedStartDate;
            comparedEndDate = comparedEndDate == null ? LocalDate.MAX : comparedEndDate;
            
            Long standardStart = standardStartDate.toEpochDay();
            Long standardEnd = standardEndDate.toEpochDay();
            Long comparedStart = comparedStartDate.toEpochDay();
            Long comparedEnd = comparedEndDate.toEpochDay();
            return NanoMathHelper.isOverlap(standardStart, standardEnd, comparedStart, comparedEnd);
        }
        
        
//        public static Boolean isOverlap(LocalDateTime standardStartDateTime, LocalDateTime standardEndDateTime, LocalDateTime comparedStartDateTime, LocalDateTime comparedEndDateTime) {
//            standardStartDateTime = standardStartDateTime == null ? LocalDateTime.MIN : standardStartDateTime;
//            standardEndDateTime = standardEndDateTime == null ? LocalDateTime.MAX : standardEndDateTime;
//            comparedStartDateTime = comparedStartDateTime == null ? LocalDateTime.MIN : comparedStartDateTime;
//            comparedEndDateTime = comparedEndDateTime == null ? LocalDateTime.MAX : comparedEndDateTime;
//            
//            Long standardStart = standardStartDateTime.to
//            Long standardEnd = standardEndDate.toEpochDay();
//            Long comparedStart = comparedStartDate.toEpochDay();
//            Long comparedEnd = comparedEndDate.toEpochDay();
//            return NanoMathHelper.isOverlap(standardStart, standardEnd, comparedStart, comparedEnd);
//        }        
    }
}
