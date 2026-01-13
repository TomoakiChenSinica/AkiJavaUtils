/*
 * Copyright 2024 tomoaki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tw.dev.tomoaki.util.datetime.entity.range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Date;
import java.util.Objects;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;
import tw.dev.tomoaki.util.datetime.util.RangeHelper;

/**
 *
 * @author tomoaki
 */
public class YearMonthRange {

    private YearMonth startYearMonth;
    private YearMonth endYearMonth;

    protected YearMonthRange() {
    }


    public static YearMonthRange create(Date utilStartDateTime, Date utilEndDateTime) {
        LocalDateTime startDateTime = DateTimeUtil.Converter.convert2DateTime(utilStartDateTime);
        LocalDateTime endDateTime = DateTimeUtil.Converter.convert2DateTime(utilEndDateTime);
        return create(YearMonth.from(startDateTime), YearMonth.from(endDateTime));
    }

    public static YearMonthRange create(LocalDate startDate, LocalDate endDate) {
        return create(YearMonth.from(startDate), YearMonth.from(endDate));
    }

    public static YearMonthRange create(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return create(YearMonth.from(startDateTime), YearMonth.from(endDateTime));
    }

    public static YearMonthRange create(YearMonth startYearMonth, YearMonth endYearMonth) {
        YearMonthRange range = new YearMonthRange();
        range.startYearMonth = startYearMonth;
        range.endYearMonth = endYearMonth;
        return range;
    }

    public static YearMonthRange create() {
        LocalDateTime start = null;
        LocalDateTime end = null;
        return create(start, end);
    }


    public YearMonth getStartYearMonth() {
        return startYearMonth;
    }

    public void setStartYearMonth(YearMonth startYearMonth) {
        this.startYearMonth = startYearMonth;
    }

    public YearMonth getEndYearMonth() {
        return endYearMonth;
    }

    public void setEndYearMonth(YearMonth endYearMonth) {
        this.endYearMonth = endYearMonth;
    }

    public YearMonthRange plusYears(long years) {
        return create(startYearMonth.plusYears(years), endYearMonth);
    }

    public YearMonthRange plusYears(int years) {
        return create(startYearMonth.plusYears(years), endYearMonth);
    }

    public YearMonthRange plusMonths(long months) {
        return create(startYearMonth, endYearMonth.plusMonths(months));
    }

    public YearMonthRange plusMonths(int months) {
        return create(startYearMonth, endYearMonth.plusMonths(months));
    }

    public YearMonthRange minusYears(long years) {
        return create(startYearMonth.minusYears(years), endYearMonth);
    }
    
    public YearMonthRange minusYears(int years) {
        return create(startYearMonth.minusYears(years), endYearMonth);
    }    

    public YearMonthRange minusMonths(long months) {
        return create(startYearMonth, endYearMonth.minusMonths(months));
    }
    
    public YearMonthRange minusMonths(int months) {
        return create(startYearMonth, endYearMonth.minusMonths(months));
    }    

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof YearMonthRange)) {
            return false;
        }

        YearMonthRange other = (YearMonthRange) obj;
        return Objects.equals(startYearMonth, other.startYearMonth) && Objects.equals(endYearMonth, other.endYearMonth);
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = 18 * hash + Objects.hashCode(this.startYearMonth);
        hash = 18 * hash + Objects.hashCode(this.endYearMonth);
        return hash;
    }
    
    @Override
    public String toString() {
        // return String.format("%s[%s ~ %s]", getClass().getName(), startYearMonth, endYearMonth);
        return RangeHelper.obtainString(this, startYearMonth, endYearMonth);
    }


}
