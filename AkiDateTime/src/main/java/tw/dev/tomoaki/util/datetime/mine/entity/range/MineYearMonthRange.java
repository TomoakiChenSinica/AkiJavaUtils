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
package tw.dev.tomoaki.util.datetime.mine.entity.range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import tw.dev.tomoaki.util.datetime.mine.entity.MineYearMonth;

/**
 *
 * @author tomoaki
 */
public class MineYearMonthRange {

    private MineYearMonth startYearMonth;
    private MineYearMonth endYearMonth;

    protected MineYearMonthRange() {
    }

    public static class Factory {

        /*
        public static YearMonthRange create(Date utilStartDateTime, Date utilEndDateTime) {
            YearMonthRange range = new YearMonthRange();
            range.startYearMonth = LocalYearMonth.from(utilStartDateTime);
            range.endYearMonth = LocalYearMonth.from(utilEndDateTime);
            return range;
        }

        public static YearMonthRange create(LocalDate startDate, LocalDate endDate) {
            YearMonthRange range = new YearMonthRange();
            range.startYearMonth = LocalYearMonth.from(startDate);
            range.endYearMonth = LocalYearMonth.from(endDate);
            return range;
        }        
        
        public static YearMonthRange create(LocalDateTime startDateTime, LocalDateTime endDateTime) {
            YearMonthRange range = new YearMonthRange();
            range.startYearMonth = LocalYearMonth.from(startDateTime);
            range.endYearMonth = LocalYearMonth.from(endDateTime);
            return range;
        }*/
        public static MineYearMonthRange create(Date utilStartDateTime, Date utilEndDateTime) {
            return Factory.create(MineYearMonth.from(utilStartDateTime), MineYearMonth.from(utilEndDateTime));
        }

        public static MineYearMonthRange create(LocalDate startDate, LocalDate endDate) {
            return Factory.create(MineYearMonth.from(startDate), MineYearMonth.from(endDate));
        }

        public static MineYearMonthRange create(LocalDateTime startDateTime, LocalDateTime endDateTime) {
            return Factory.create(MineYearMonth.from(startDateTime), MineYearMonth.from(endDateTime));
        }

        public static MineYearMonthRange create(MineYearMonth startYearMonth, MineYearMonth endYearMonth) {
            MineYearMonthRange range = new MineYearMonthRange();
            range.startYearMonth = startYearMonth;
            range.endYearMonth = endYearMonth;
            return range;
        }

        public static MineYearMonthRange create() {
            LocalDateTime start = null;
            LocalDateTime end = null;
            return Factory.create(start, end);
        }

    }

    public MineYearMonth getStartYearMonth() {
        return startYearMonth;
    }

    public void setStartYearMonth(MineYearMonth startYearMonth) {
        this.startYearMonth = startYearMonth;
    }

    public MineYearMonth getEndYearMonth() {
        return endYearMonth;
    }

    public void setEndYearMonth(MineYearMonth endYearMonth) {
        this.endYearMonth = endYearMonth;
    }

    public MineYearMonthRange plusYears(Long years) {
        return Factory.create(startYearMonth.plusYears(years), endYearMonth);
    }

    public MineYearMonthRange plusMonths(Long months) {
        return Factory.create(startYearMonth, endYearMonth.plusMonths(months));
    }

    public MineYearMonthRange minusYears(Long years) {
        return Factory.create(startYearMonth.minusYears(years), endYearMonth);
    }

    public MineYearMonthRange minusMonths(Long months) {
        return Factory.create(startYearMonth, endYearMonth.minusMonths(months));
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  MineYearMonthRange)) {
            return false;
        }
        
        MineYearMonthRange other = (MineYearMonthRange) obj;
        return Objects.equals(startYearMonth, other.startYearMonth) && Objects.equals(endYearMonth, other.endYearMonth);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.startYearMonth);
        hash = 29 * hash + Objects.hashCode(this.endYearMonth);
        return hash;
    }

}
