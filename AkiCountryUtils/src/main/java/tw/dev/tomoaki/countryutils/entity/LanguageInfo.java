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
package tw.dev.tomoaki.countryutils.entity;

import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author tomoaki
 */
public class LanguageInfo {
    
    private String displayName;    
    private Locale locale;
    private String iso3Code;
    private String countryDisplayName;

    protected LanguageInfo() {

    }

    public static LanguageInfo newInstance(String displayName, Locale locale, /*String iso2Code, */String iso3Code, String countryDisplayName) {
        LanguageInfo info = new LanguageInfo();
        info.displayName = displayName;
        info.locale = locale;
//        info.iso2Code = iso2Code;
        info.iso3Code = iso3Code;
        info.countryDisplayName = countryDisplayName;
        return info;
    }  
    
    public String getDisplayName() {
        return displayName;
    }

    public Locale getLocale() {
        return locale;
    }

//    public String getISO2Code() {
//        return iso2Code;
//    }

    public String getISO3Code() {
        return iso3Code;
    }

    public String getCountryDisplayName() {
        return countryDisplayName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 234 * hash + Objects.hashCode(this.iso3Code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LanguageInfo other = (LanguageInfo) obj;
        return Objects.equals(this.iso3Code, other.iso3Code);
    }

    @Override
    public String toString() {
        return "LanguageInfo{" + "displayName=" + displayName + ", iso3Code=" + iso3Code + " , countryDisplayName= " + countryDisplayName + '}';
    }    
}
