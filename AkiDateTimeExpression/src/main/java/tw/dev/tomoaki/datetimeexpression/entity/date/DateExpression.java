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
package tw.dev.tomoaki.datetimeexpression.entity.date;

import tw.dev.tomoaki.datetimeexpression.util.ExpressionHelper;

/**
 *
 * @author tomoaki
 */
public final class DateExpression {

    private String format;

    private ExpressionYearPart clozeYearPart;
    private ExpressionMonthPart clozeMonthPart;
    private ExpressionDayOfMonthPart clozeDayOfMonthPart;

    protected DateExpression() {
    }


    public static DateExpression create(String format) {
        DateExpression cloze = new DateExpression();
        cloze.format = format;
        cloze.doSetupParts();
        return cloze;
    }

    protected void doSetupParts() {
        // clozeYearPart = clozeYearPart = ExpressionHelper.analyzeYearPart(format);
        clozeYearPart = ExpressionHelper.analyzeYearPart(format);
        clozeMonthPart = ExpressionHelper.analyzeMonthPart(format);
        clozeDayOfMonthPart = ExpressionHelper.analyzeDayOfMonthPart(format);
    }

    public String getFormat() {
        return format;
    }

    public ExpressionYearPart getClozeYearPart() {
        return clozeYearPart;
    }

    public ExpressionMonthPart getClozeMonthPart() {
        return clozeMonthPart;
    }

    public ExpressionDayOfMonthPart getClozeDayOfMonthPart() {
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
