/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.countryutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Tomoaki Chen
 */
public class LocaleHelper {

    public static List<CountryInfo> obtainChCountryInfoList() {
        List<CountryInfo> countryInfoList = new ArrayList();
        
        Locale twLocale = Locale.TAIWAN;        
        Locale enLocale = Locale.ENGLISH;
        
        String[] countryISOCodes = Locale.getISOCountries();
        for (String iso2Code : countryISOCodes) {
            Locale locale = new Locale("", iso2Code);
            //            System.out.println("displayName : " + locale.getDisplayCountry(taiwanLocale) + ", ISO3 Country : " + " " + locale.getISO3Country());
            String iso3Code = locale.getISO3Country();
            String displayName;                    
            String chName = locale.getDisplayCountry(twLocale);
           String enName = locale.getDisplayCountry(enLocale);
           displayName = chName + " / " + enName; 
            
            CountryInfo countryInfo = new CountryInfo();
            countryInfo.setDisplayName(displayName);
            countryInfo.setIso2Code(iso2Code);
            countryInfo.setIso3Code(iso3Code);
            
            countryInfoList.add(countryInfo);
        }
        return countryInfoList;
    }
}
