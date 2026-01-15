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
public class YearMonthRange extends AbstractRange<YearMonth> {



    protected YearMonthRange() {
    }


    public static YearMonthRange create(Date since, Date until) {
        LocalDateTime startDateTime = DateTimeUtil.Converter.convert2DateTime(since);
        LocalDateTime endDateTime = DateTimeUtil.Converter.convert2DateTime(until);
        return create(YearMonth.from(startDateTime), YearMonth.from(endDateTime));
    }

    public static YearMonthRange create(LocalDate since, LocalDate until) {
        return create(YearMonth.from(since), YearMonth.from(until));
    }

    public static YearMonthRange create(LocalDateTime since, LocalDateTime until) {
        return create(YearMonth.from(since), YearMonth.from(until));
    }

    public static YearMonthRange create(YearMonth since, YearMonth until) {
        YearMonthRange range = new YearMonthRange();
        range.since = since;
        range.until = until;
        return range;
    }

    public static YearMonthRange create() {
        LocalDateTime start = null;
        LocalDateTime end = null;
        return create(start, end);
    }


    public YearMonth getStartYearMonth() {
        return since;
    }

    public void setStartYearMonth(YearMonth since) {
        this.since = since;
    }

    public YearMonth getEndYearMonth() {
        return until;
    }

    public void setEndYearMonth(YearMonth until) {
        this.until = until;
    }
    
//<editor-fold defaultstate="collapsed" desc="實作 AbstractRange<T> 所需 Methods">
    /*
    @Override
    public String toString() {
        // return String.format("%s[%s ~ %s]", getClass().getName(), startYearMonth, endYearMonth);
        return RangeHelper.obtainClassString(this, since, until);
    }*/

    @Override
    public Boolean isBefore(YearMonth standard) {
        return standard.isBefore(since);
    }

    @Override
    public Boolean isBetween(YearMonth standard) {
        return !standard.isBefore(since) && !standard.isAfter(until);
    }

    @Override
    public Boolean isAfter(YearMonth standard) {
        return standard.isAfter(until);
    }    
//</editor-fold>
    

//<editor-fold defaultstate="collapsed" desc="輔助計算 Methods">
    public YearMonthRange plusYears(long years) {
        final YearMonth finalSince = (since != null) ? since.plusYears(years) : null;
        final YearMonth finalUntil = (until != null) ? until.plusYears(years) : null;
        return create(finalSince, finalUntil);
    }

    public YearMonthRange plusMonths(long months) {
        final YearMonth finalSince = (since != null) ? since.plusMonths(months) : null;
        final YearMonth finalUntil = (until != null) ? until.plusMonths(months) : null;        
        return create(finalSince, finalUntil);
    }

    public YearMonthRange minusYears(long years) {
        final YearMonth finalSince = (since != null) ? since.minusYears(years) : null;
        final YearMonth finalUntil = (until != null) ? until.minusYears(years) : null;
        return create(finalSince, finalUntil);
    }

    public YearMonthRange minusMonths(long months) {
        final YearMonth finalSince = (since != null) ? since.minusMonths(months) : null;
        final YearMonth finalUntil = (until != null) ? until.minusMonths(months) : null;          
        return create(finalSince, finalUntil);
    }    
//</editor-fold>
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof YearMonthRange)) {
            return false;
        }

        YearMonthRange other = (YearMonthRange) obj;
        return Objects.equals(since, other.since) && Objects.equals(until, other.until);
    }

    @Override
    public int hashCode() {
        int hash = 91;
        hash = 13 * hash + Objects.hashCode(this.since);
        hash = 13 * hash + Objects.hashCode(this.until);
        return hash;
    }



}
