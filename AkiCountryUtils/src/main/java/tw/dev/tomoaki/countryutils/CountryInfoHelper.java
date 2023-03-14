package tw.dev.tomoaki.countryutils;

import tw.dev.tomoaki.countryutils.entity.CountryInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import tw.dev.tomoaki.countryutils.entity.CountryInfoKeyByISOCodeMap;

/**
 *
 * @author tomoaki
 */
public class CountryInfoHelper {

//    private static final CountryInfoKeyByISOCodeMap countryInfoKeyByISOCodeMap = CountryInfoKeyByISOCodeMap.Factory.create(Locale.TAIWAN);
    
    public static List<CountryInfo> obtainCountryInfoList(Locale desigLocale) {
        if (desigLocale == null) {
            throw new IllegalArgumentException("desigLocale Is Null");
        }
        return obtainCountryInfoList(desigLocale, null);
    }

    public static List<CountryInfo> obtainCountryInfoList(Locale desigLocale1, Locale desigLocale2) {
        if (desigLocale1 == null) {
            throw new IllegalArgumentException("desigLocale1 Is Null");
        }

        List<CountryInfo> countryInfoList = new ArrayList();

        String[] countryISOCodes = Locale.getISOCountries();
        for (String iso2Code : countryISOCodes) {
            Locale locale = new Locale("", iso2Code);

            String iso3Code = locale.getISO3Country();
            String displayName;
            String displayCountryName1 = locale.getDisplayCountry(desigLocale1);
            String displayCountryName2 = (desigLocale2 == null) ? null : locale.getDisplayCountry(desigLocale2);
            displayName = displayCountryName2 == null ? displayCountryName1 : String.format("%s / %s", displayCountryName1, displayCountryName2);//(displayCountryName1 + " / " + displayCountryName2)

            CountryInfo countryInfo = new CountryInfo();
            countryInfo.setDisplayName(displayName);
            countryInfo.setISO2Code(iso2Code);
            countryInfo.setISO3Code(iso3Code);

            countryInfoList.add(countryInfo);
        }
        return countryInfoList;
    }

    public static List<CountryInfo> obtainTWCountryInfoList() {
        List<CountryInfo> countryInfoList = new ArrayList();

        Locale twLocale = Locale.TAIWAN;
        Locale enLocale = Locale.ENGLISH;

        String[] countryISOCodes = Locale.getISOCountries();
        for (String iso2Code : countryISOCodes) {
            Locale locale = new Locale("", iso2Code);

            String iso3Code = locale.getISO3Country();
            String displayName;
            String nameTW = locale.getDisplayCountry(twLocale);
            String nameEN = locale.getDisplayCountry(enLocale);
            displayName = nameTW + " / " + nameEN;

            CountryInfo countryInfo = new CountryInfo();
            countryInfo.setDisplayName(displayName);
            countryInfo.setISO2Code(iso2Code);
            countryInfo.setISO3Code(iso3Code);

            countryInfoList.add(countryInfo);
        }
        return countryInfoList;
    }

//    public static CountryInfo convert(Locale desigLocale) {
//        String iso3Code = desigLocale.getISO3Country();
//        return countryInfoKeyByISOCodeMap.getByISO3Code(iso3Code);
//    }
}
