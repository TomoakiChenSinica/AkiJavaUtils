/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.localeext.entity;

import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author Tomoaki Chen
 */
public class CountryInfo {

    private String displayName;
    private Locale locale;
    private String iso2Code;
    private String iso3Code;

    protected CountryInfo() {

    }

    public static CountryInfo newInstance(String displayName, Locale locale, String iso2Code, String iso3Code) {
        CountryInfo info = new CountryInfo();
        info.displayName = displayName;
        info.locale = locale;
        info.iso2Code = iso2Code;
        info.iso3Code = iso3Code;
        return info;
    }  
    
    public String getDisplayName() {
        return displayName;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getISO2Code() {
        return iso2Code;
    }

    public String getISO3Code() {
        return iso3Code;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.iso2Code);
        hash = 79 * hash + Objects.hashCode(this.iso3Code);
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
        final CountryInfo other = (CountryInfo) obj;
        if (!Objects.equals(this.iso2Code, other.iso2Code)) {
            return false;
        }
        return Objects.equals(this.iso3Code, other.iso3Code);
    }

    @Override
    public String toString() {
        return "CountryInfo{" + "displayName=" + displayName + ", iso2Code=" + iso2Code + ", iso3Code=" + iso3Code + '}';
    }

}
