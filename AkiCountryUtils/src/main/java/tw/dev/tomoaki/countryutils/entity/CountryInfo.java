/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.countryutils.entity;

import java.util.Objects;

/**
 *
 * @author Tomoaki Chen
 */
public class CountryInfo {

    private String displayName;
    private String iso2Code;
    private String iso3Code;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getISO2Code() {
        return iso2Code;
    }

    public void setISO2Code(String iso2Code) {
        this.iso2Code = iso2Code;
    }

    public String getISO3Code() {
        return iso3Code;
    }

    public void setISO3Code(String iso3Code) {
        this.iso3Code = iso3Code;
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
