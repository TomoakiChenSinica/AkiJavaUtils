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
package tw.dev.tomoaki.datetimecloze.entity.date;

import tw.dev.tomoaki.datetimecloze.util.ClozeFormatHelper;

/**
 *
 * @author tomoaki
 */
public final class DateCloze {

    private String format;

//    private Boolean isYearFillable;
//    private Boolean isMonthFillable;
//    private Boolean isDayOfMonthFillable;
//
//    private Integer addendYears = 0;
//    private Integer addendMonths = 0;
//    private Integer addendDays = 0;
    private ClozeYearPart clozeYearPart;
    private ClozeMonthPart clozeMonthPart;
    private ClozeDayOfMonthPart clozeDayOfMonthPart;

    protected DateCloze() {
    }

    public static class Factory {

        public static DateCloze create(String format) {
            DateCloze cloze = new DateCloze();
            cloze.format = format;
            cloze.doSetupParts();
            return cloze;
        }
    }

    protected void doSetupParts() {
//        clozeYearPart = clozeYearPart = ClozeFormatHelper.analyzeYearPart(format);
        clozeYearPart = ClozeFormatHelper.analyzeYearPart(format);
        clozeMonthPart = ClozeFormatHelper.analyzeMonthPart(format);
        clozeDayOfMonthPart = ClozeFormatHelper.analyzeDayOfMonthPart(format);
    }

    public String getFormat() {
        return format;
    }

    public ClozeYearPart getClozeYearPart() {
        return clozeYearPart;
    }

    public ClozeMonthPart getClozeMonthPart() {
        return clozeMonthPart;
    }

    public ClozeDayOfMonthPart getClozeDayOfMonthPart() {
        return clozeDayOfMonthPart;
    }

    public Boolean getIsYearFillable() {
        return clozeYearPart.getIsFillable();
    }

    public Boolean getIsMonthFillable() {
        return clozeMonthPart.getIsFillable();
    }

    public Boolean getIsDayOfMonthFillable() {
        return clozeDayOfMonthPart.getIsFillable();
    }

    public String getYearPartMatchText() {
        return clozeYearPart.getMatchText();
    }

    public String getMonthPartMatchText() {
        return clozeMonthPart.getMatchText();
    }

    public String getDayOfMonthPartMatchText() {
        return clozeDayOfMonthPart.getMatchText();
    }

    public Integer getAddendYears() {
        return clozeYearPart.getAddendNums();
    }

    public Integer getAddendMonths() {
        return clozeMonthPart.getAddendNums();
    }

    public Integer getAddendDayOfMonth() {
        return clozeDayOfMonthPart.getAddendNums();
    }

}
