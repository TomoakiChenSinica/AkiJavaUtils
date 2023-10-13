package tw.dev.tomoaki.localeext;

import java.util.Locale;
import tw.dev.tomoaki.localeext.entity.CountryInfo;
import tw.dev.tomoaki.localeext.entity.dict.CountryInfoMap;
import tw.dev.tomoaki.localeext.entity.dict.LocaleMap;

/**
 *
 * @author tomoaki
 */
public class LocaleCountryConverter {

    private static final CountryInfoMap countryInfoKeyByISOCodeMap = CountryInfoMap.Factory.create(Locale.TAIWAN);
    private static final LocaleMap localeMap = new LocaleMap();

    public static CountryInfo convertLocale2Country(Locale desigLocale) {
        String iso3Code = desigLocale.getISO3Country();
        return countryInfoKeyByISOCodeMap.getByISO3Code(iso3Code);
    }
    
    public static Locale convertCountry2Locale(CountryInfo desigCountryInfo) {
        String iso3Code = desigCountryInfo.getISO3Code();
        return localeMap.getLocale(iso3Code);
    }
}
