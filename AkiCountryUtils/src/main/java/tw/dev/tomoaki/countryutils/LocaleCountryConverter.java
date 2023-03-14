package tw.dev.tomoaki.countryutils;

import java.util.Locale;
import tw.dev.tomoaki.countryutils.entity.CountryInfo;
import tw.dev.tomoaki.countryutils.entity.CountryInfoKeyByISOCodeMap;
import tw.dev.tomoaki.countryutils.entity.LocaleKeyByISO3CodeMap;

/**
 *
 * @author tomoaki
 */
public class LocaleCountryConverter {

    private static final CountryInfoKeyByISOCodeMap countryInfoKeyByISOCodeMap = CountryInfoKeyByISOCodeMap.Factory.create(Locale.TAIWAN);
    private static final LocaleKeyByISO3CodeMap localeKeyByISO3CodeMap = new LocaleKeyByISO3CodeMap();

    public static CountryInfo convertLocale2Country(Locale desigLocale) {
        String iso3Code = desigLocale.getISO3Country();
        return countryInfoKeyByISOCodeMap.getByISO3Code(iso3Code);
    }
    
    public static Locale convertCountry2Locale(CountryInfo desigCountryInfo) {
        String iso3Code = desigCountryInfo.getISO3Code();
        return localeKeyByISO3CodeMap.getLocale(iso3Code);
    }
}
