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
        String[] countryISOCodes = Locale.getISOCountries();
        for (String iso2Code : countryISOCodes) {
            Locale locale = new Locale("", iso2Code);
            String iso3Code =  locale.getISO3Country();
            theMap.put(iso3Code, locale);            
        }
    }
    
//    public LocaleKeyByISO3CodeMap(Locale desigLocale) {
//        theMap = new HashMap();
//        String[] countryISOCodes = Locale.getISOCountries();
//        for (String iso2Code : countryISOCodes) {
//            Locale locale = new Locale(desigLocale.getDisplayLanguage(), iso2Code);            
//            String iso3Code =  locale.getISO3Country();
//            theMap.put(iso3Code, locale);            
//        }
//    }    
        
    public Locale getLocale(String iso3Code){
        return theMap.get(iso3Code);
    }
    
    public String getLocaleDisplayCountryName(String iso3Code){
        if(iso3Code == null || iso3Code.isEmpty()) {
            return "";
        }
        return this.getLocale(iso3Code).getDisplayCountry();
    }
    
    /**
     * @param iso3Code 要尋找的國家的 iso3 Code 
     * @param desigLang 指定國家名稱顯示語言， Locale.Xxxxx
     */
    public String getLocaleDisplayCountryName(String iso3Code, Locale desigLang){
        if(iso3Code == null || iso3Code.isEmpty()) {
            return "";
        }        
        return this.getLocale(iso3Code).getDisplayCountry(desigLang);
    }    
      
}