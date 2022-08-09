/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.countryutils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author Tomoaki Chen
 */
public class LocaleKeyByISO3CodeMap {

    private Map<String, Locale> theMap;

    public LocaleKeyByISO3CodeMap() {
        theMap = new HashMap();
//        Locale taiwanLocale = Locale.TAIWAN;
        String[] countryISOCodes = Locale.getISOCountries();
        for (String iso2Code : countryISOCodes) {
            Locale locale = new Locale("", iso2Code);
            //            System.out.println("displayName : " + locale.getDisplayCountry(taiwanLocale) + ", ISO3 Country : " + " " + locale.getISO3Country());
            String iso3Code =  locale.getISO3Country();
            theMap.put(iso3Code, locale);            
        }
    }
    
    public Locale getLocale(String iso3Code){
        return theMap.get(iso3Code);
    }
    
    public String getLocaleDisplayCountryName(String iso3Code){
        if(iso3Code == null || iso3Code.isEmpty()) {
            return "";
        }
        return this.getLocale(iso3Code).getDisplayCountry();
    }
    
    public String getLocaleDisplayCountryName(String iso3Code, Locale locale){
        if(iso3Code == null || iso3Code.isEmpty()) {
            return "";
        }        
        return this.getLocale(iso3Code).getDisplayCountry(locale);
    }    
      
}