/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.countryutils;

import java.util.Locale;
import java.util.Objects;
import tw.dev.tomoaki.countryutils.entity.CountryInfo;
import tw.dev.tomoaki.countryutils.entity.LocaleKeyByISO3CodeMap;


/**
 *
 * @author Tomoaki Chen
 */
public class LocaleHelper {

//    private static final LocaleKeyByISO3CodeMap localeKeyByISO3CodeMap = new LocaleKeyByISO3CodeMap();
    
    public Boolean looseEquals(Locale locale1, Locale locale2) {
        if(locale1 == null && locale2 == null) {
            throw new IllegalArgumentException("Both Locale Is Null");
        }
        
        if(locale1 == null || locale2 == null) {
            return Boolean.FALSE;
        }
        
        return Objects.equals(locale1.getCountry(), locale2.getCountry()) && Objects.equals(locale1.getISO3Country(), locale2.getISO3Country());
    }
    
//    public static convert2CountryInfo(Locale desigLocale) {
//    
//    }
    
//    public static CountryInfo convert2CountryInfo(Locale desigLocale) {
//        String iso3Code = desigLocale.getISO3Country();
//        return countryInfoKeyByISOCodeMap.getByISO3Code(iso3Code);
//    }    
}
