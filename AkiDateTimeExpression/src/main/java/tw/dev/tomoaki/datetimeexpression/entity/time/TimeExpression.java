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
package tw.dev.tomoaki.datetimeexpression.entity.time;

import tw.dev.tomoaki.datetimeexpression.util.ExpressionHelper;

/**
 *
 * @author tomoaki
 */
public final class TimeExpression {

    private String format;

    private ExpressionHourPart hourPart;
    private ExpressionMinutePart minutePart;
    private ExpressionSecondPart secondPart;

    protected TimeExpression() {
    }


    public static TimeExpression create(String format) {
        TimeExpression cloze = new TimeExpression();
        cloze.format = format;
        cloze.doSetupParts();
        return cloze;
    }

    protected void doSetupParts() {
        // clozeYearPart = clozeYearPart = ExpressionHelper.analyzeYearPart(format);
        hourPart = ExpressionHelper.analyzeHourPart(format);
        minutePart = ExpressionHelper.analyzeMinutePart(format);
        secondPart = ExpressionHelper.analyzeSecondPart(format);
    }

    public String getFormat() {
        return format;
    }

    public ExpressionHourPart getHourPart() {
        return hourPart;
    }

    public ExpressionMinutePart getMinutePart() {
        return minutePart;
    }

    public ExpressionSecondPart getSecondPart() {
        return secondPart;
    }

    public Boolean getIsHourFillable() {
        return hourPart.getIsFillable();
    }

    public Boolean getIsMinuteFillable() {
        return minutePart.getIsFillable();
    }

    public Boolean getIsSecondFillable() {
        return secondPart.getIsFillable();
    }

    public String getHourPartMatchText() {
        return hourPart.getMatchText();
    }

    public String getMinutePartMatchText() {
        return minutePart.getMatchText();
    }

    public String getSecondPartMatchText() {
        return secondPart.getMatchText();
    }

    public Integer getAddendHours() {
        return hourPart.getAddendNums();
    }

    public Integer getAddendMinutes() {
        return minutePart.getAddendNums();
    }

    public Integer getAddendSeconds() {
        return secondPart.getAddendNums();
    }

}
