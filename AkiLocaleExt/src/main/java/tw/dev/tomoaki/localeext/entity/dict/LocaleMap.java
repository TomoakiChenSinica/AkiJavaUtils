/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.localeext.entity.dict;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author Tomoaki Chen
 */
public class LocaleMap {

    private Map<String, Locale> theMap;

    public LocaleMap() {
        theMap = new HashMap();
        String[] countryISOCodes = Locale.getISOCountries();
        for (String iso2Code : countryISOCodes) {
            Locale locale = new Locale("", iso2Code);
            String iso3Code = locale.getISO3Country();
            theMap.put(iso3Code, locale);
        }
    }

    public Locale getLocale(String iso3Code) {
        return theMap.get(iso3Code);
    }

    public String getLocaleDisplayCountryName(String iso3Code) {
        if (iso3Code == null || iso3Code.isEmpty()) {
            return "";
        }
        return this.getLocale(iso3Code).getDisplayCountry();
    }

    /**
     * @param iso3Code 要尋找的國家的 iso3 Code
     * @param displayInLang 指定國家名稱顯示語言， Locale.Xxxxx
     * @return iso3Code 所對應的 Locale，在指定的語系(desigLang)下，所顯示的國家名稱
     */
    public String getLocaleDisplayCountryName(String iso3Code, Locale displayInLang) {
        if (iso3Code == null || iso3Code.isEmpty()) {
            return "";
        }
        return this.getLocale(iso3Code).getDisplayCountry(displayInLang);
    }

    public String getLocaleDisplayLanguageName(String iso3Code, Locale displayInLang) {
        if (iso3Code == null || iso3Code.isEmpty()) {
            return "";
        }
        return this.getLocale(iso3Code).getDisplayLanguage(displayInLang);
    }

}
