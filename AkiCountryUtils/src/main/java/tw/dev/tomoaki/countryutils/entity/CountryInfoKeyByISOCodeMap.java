package tw.dev.tomoaki.countryutils.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import tw.dev.tomoaki.countryutils.CountryInfoHelper;

/**
 *
 * @author tomoaki
 */
public class CountryInfoKeyByISOCodeMap {

    private Map<String, CountryInfo> countryInfoKeyByISO2CodeMap;
    private Map<String, CountryInfo> countryInfoKeyByISO3CodeMap;

    protected CountryInfoKeyByISOCodeMap() {
        this.countryInfoKeyByISO2CodeMap = new LinkedHashMap();
        this.countryInfoKeyByISO3CodeMap = new LinkedHashMap();
    }

    public static class Factory {

        public static CountryInfoKeyByISOCodeMap create(Locale desigLang) {
            CountryInfoKeyByISOCodeMap theMap = new CountryInfoKeyByISOCodeMap();
            theMap.init(desigLang);
            return theMap;
        }
    }
    
    protected void init(Locale desigLang) {
        List<CountryInfo> countryInfoList = CountryInfoHelper.obtainCountryInfoList(desigLang);
        for(CountryInfo countryInfo : countryInfoList) {
            String iso2Code = countryInfo.getISO2Code();
            String iso3Code = countryInfo.getISO3Code();
            this.countryInfoKeyByISO2CodeMap.put(iso2Code, countryInfo);
            this.countryInfoKeyByISO3CodeMap.put(iso3Code, countryInfo);
        }
    }
    
    public CountryInfo getByISO2Code(String iso2Code) {
        return this.countryInfoKeyByISO2CodeMap.get(iso2Code);
    }
    
    public CountryInfo getByISO3Code(String iso3Code) {
        return this.countryInfoKeyByISO3CodeMap.get(iso3Code);
    }    
}
