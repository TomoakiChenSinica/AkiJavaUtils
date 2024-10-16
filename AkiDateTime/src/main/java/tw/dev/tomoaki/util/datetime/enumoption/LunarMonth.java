/*
 * Copyright 2023 tomoaki.
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
package tw.dev.tomoaki.util.datetime.enumoption;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author tomoaki
 */
public enum LunarMonth {

    @JsonProperty("JANUARY")
    JANUARY(java.time.Month.JANUARY),
    @JsonProperty("FEBRUARY")
    FEBRUARY(java.time.Month.FEBRUARY),
    @JsonProperty("MARCH")
    MARCH(java.time.Month.MARCH),
    @JsonProperty("APRIL")
    APRIL(java.time.Month.APRIL),
    @JsonProperty("MAY")
    MAY(java.time.Month.MAY),
    @JsonProperty("JUNE")
    JUNE(java.time.Month.JUNE),
    @JsonProperty("JULY")
    JULY(java.time.Month.JULY),
    @JsonProperty("AUGUST")
    AUGUST(java.time.Month.AUGUST),
    @JsonProperty("SEPTEMBER")
    SEPTEMBER(java.time.Month.SEPTEMBER),
    @JsonProperty("OCTOBER")
    OCTOBER(java.time.Month.OCTOBER),
    @JsonProperty("NOVEMBER")
    NOVEMBER(java.time.Month.NOVEMBER),
    @JsonProperty("DECEMBER")
    DECEMBER(java.time.Month.DECEMBER);

    private java.time.Month enumMonth;
    private Integer monthValue;
    private String displayFullNameTW;
    private String displayFullNameEN;
    private String displayShortNameTW;
    private String displayShortNameEN;

    private LunarMonth(java.time.Month enumMonth) {
        Locale localeTW = Locale.TAIWAN;
        Locale localeEN = Locale.US;
        this.enumMonth = enumMonth;
        this.monthValue = enumMonth.getValue();
        this.displayFullNameTW = enumMonth.getDisplayName(TextStyle.FULL, localeTW);
        this.displayFullNameEN = enumMonth.getDisplayName(TextStyle.FULL, localeEN);
        this.displayShortNameTW = enumMonth.getDisplayName(TextStyle.SHORT, localeTW);
        this.displayShortNameEN = enumMonth.getDisplayName(TextStyle.SHORT, localeEN);

    }

    public Month getEnumMonth() {
        return enumMonth;
    }

    public void setEnumMonth(Month enumMonth) {
        this.enumMonth = enumMonth;
    }

//    @JsonValue
    public Integer getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(Integer monthValue) {
        this.monthValue = monthValue;
    }

    public String getDisplayFullNameTW() {
        return displayFullNameTW;
    }

    public void setDisplayFullNameTW(String displayFullNameTW) {
        this.displayFullNameTW = displayFullNameTW;
    }

    public String getDisplayFullNameEN() {
        return displayFullNameEN;
    }

    public void setDisplayFullNameEN(String displayFullNameEN) {
        this.displayFullNameEN = displayFullNameEN;
    }

    public String getDisplayShortNameTW() {
        return displayShortNameTW;
    }

    public void setDisplayShortNameTW(String displayShortNameTW) {
        this.displayShortNameTW = displayShortNameTW;
    }

    public String getDisplayShortNameEN() {
        return displayShortNameEN;
    }

    public void setDisplayShortNameEN(String displayShortNameEN) {
        this.displayShortNameEN = displayShortNameEN;
    }

    public static LunarMonth of(Month desigTimeMonth) {
        LunarMonth desigLunarMonth = null;
        for (LunarMonth lunarMonth : LunarMonth.values()) {
            if (Objects.equals(lunarMonth.getEnumMonth(), desigTimeMonth)) {
                desigLunarMonth = lunarMonth;
                break;
            }
        }

        if (desigLunarMonth == null) {
            throw new IllegalArgumentException(String.format("Cannot Found LunarMonth[desigTimeMonth= %s]", desigTimeMonth));
        }
        return desigLunarMonth;
    }

    public static LunarMonth of(Integer desigMonth) {
        LunarMonth desigLunarMonth = null;
        for (LunarMonth lunarMonth : LunarMonth.values()) {
            if (Objects.equals(lunarMonth.getMonthValue(), desigMonth)) {
                desigLunarMonth = lunarMonth;
                break;
            }
        }

        if (desigLunarMonth == null) {
            throw new IllegalArgumentException(String.format("Cannot Found LunarMonth[desigMonth= %s]", desigMonth));
        }
        return desigLunarMonth;
    }

}
