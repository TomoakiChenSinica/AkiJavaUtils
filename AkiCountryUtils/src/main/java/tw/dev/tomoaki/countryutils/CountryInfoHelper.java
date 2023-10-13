package tw.dev.tomoaki.countryutils;

import tw.dev.tomoaki.countryutils.entity.CountryInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import tw.dev.tomoaki.countryutils.entity.dict.CountryInfoMap;

/**
 *
 * @author tomoaki
 */
public class CountryInfoHelper {

    public static List<CountryInfo> obtainTWCountryInfoList() {
        Locale twLocale = Locale.TAIWAN;
        Locale enLocale = Locale.ENGLISH;
        return obtainCountryInfoList(twLocale, enLocale);
    }

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
//            String displayName;
//            String displayCountryName1 = locale.getDisplayCountry(desigLocale1);
//            String displayCountryName2 = (desigLocale2 == null) ? null : locale.getDisplayCountry(desigLocale2);
//            displayName = displayCountryName2 == null ? displayCountryName1 : String.format("%s / %s", displayCountryName1, displayCountryName2);//(displayCountryName1 + " / " + displayCountryName2)
            String displayName = LocaleHelper.createCountryDisplayName(locale, desigLocale1, desigLocale2);

            CountryInfo countryInfo = CountryInfo.newInstance(displayName, locale, iso2Code, iso3Code);
            countryInfoList.add(countryInfo);
        }
        return countryInfoList;
    }

}
