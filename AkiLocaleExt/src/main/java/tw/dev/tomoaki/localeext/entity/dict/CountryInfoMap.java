package tw.dev.tomoaki.localeext.entity.dict;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import tw.dev.tomoaki.localeext.CountryInfoHelper;
import tw.dev.tomoaki.localeext.entity.CountryInfo;

/**
 *
 * @author tomoaki
 */
public class CountryInfoMap {

    private Map<String, CountryInfo> countryInfoKeyByISO2CodeMap;
    private Map<String, CountryInfo> countryInfoKeyByISO3CodeMap;

    protected CountryInfoMap() {
        this.countryInfoKeyByISO2CodeMap = new LinkedHashMap();
        this.countryInfoKeyByISO3CodeMap = new LinkedHashMap();
    }

    public static class Factory {

        public static CountryInfoMap create(Locale desigLang) {
            CountryInfoMap theMap = new CountryInfoMap();
            List<CountryInfo> countryInfoList = CountryInfoHelper.obtainCountryInfoList(desigLang);
            theMap.addAll(countryInfoList);
            return theMap;
        }
        
        public static CountryInfoMap create(List<CountryInfo> countryInfoList) {
            CountryInfoMap theMap = new CountryInfoMap();
            theMap.addAll(countryInfoList);
            return theMap;
        }        
    }
    
    public void addAll(List<CountryInfo> countryInfoList) {
        if(countryInfoList != null) {
            countryInfoList.forEach(countryInfo -> add(countryInfo));
        }
    }

    public void add(CountryInfo countryInfo) {
        String iso2Code = countryInfo.getISO2Code();
        String iso3Code = countryInfo.getISO3Code();
        this.countryInfoKeyByISO2CodeMap.put(iso2Code, countryInfo);
        this.countryInfoKeyByISO3CodeMap.put(iso3Code, countryInfo);

    }

    public CountryInfo getByISO2Code(String iso2Code) {
        return this.countryInfoKeyByISO2CodeMap.get(iso2Code);
    }

    public CountryInfo getByISO3Code(String iso3Code) {
        return this.countryInfoKeyByISO3CodeMap.get(iso3Code);
    }
    
    public List<CountryInfo> getCountryInfoList() {        
        return new ArrayList(this.countryInfoKeyByISO2CodeMap.values());
    }
}
