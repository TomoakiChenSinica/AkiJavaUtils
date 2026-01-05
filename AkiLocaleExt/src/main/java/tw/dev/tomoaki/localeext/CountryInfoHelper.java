package tw.dev.tomoaki.localeext;

import tw.dev.tomoaki.localeext.entity.CountryInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import tw.dev.tomoaki.localeext.entity.dict.CountryInfoMap;
import tw.dev.tomoaki.localeext.exception.LocaleSourceException;
import tw.dev.tomoaki.localeext.source.LocaleSource;

/**
 *
 * @author tomoaki
 */
public class CountryInfoHelper {

    public static Boolean PRINT_LOG = false;

    public static List<CountryInfo> obtainTWCountryInfoList() {
        Locale twLocale = Locale.TAIWAN;
        Locale enLocale = Locale.ENGLISH;
        return obtainCountryInfoList(twLocale, enLocale, null);
    }

    public static List<CountryInfo> obtainTWCountryInfoList(LocaleSource localeSource) {
        Locale twLocale = Locale.TAIWAN;
        Locale enLocale = Locale.ENGLISH;
        return obtainCountryInfoList(twLocale, enLocale, localeSource);
    }

//    private static final CountryInfoKeyByISOCodeMap countryInfoKeyByISOCodeMap = CountryInfoKeyByISOCodeMap.Factory.create(Locale.TAIWAN);
    public static List<CountryInfo> obtainCountryInfoList(Locale desigLocale) {
        if (desigLocale == null) {
            throw new IllegalArgumentException("desigLocale Is Null");
        }
        return obtainCountryInfoList(desigLocale, null, null);
    }

    public static List<CountryInfo> obtainCountryInfoList(Locale desigLocale, LocaleSource localeSource) {
        if (desigLocale == null) {
            throw new IllegalArgumentException("desigLocale Is Null");
        }
        return obtainCountryInfoList(desigLocale, null, localeSource);
    }

    public static List<CountryInfo> obtainCountryInfoList(Locale desigLocale1, Locale desigLocale2, LocaleSource localeSource) {
        if (desigLocale1 == null) {
            throw new IllegalArgumentException("desigLocale1 Is Null");
        }
        localeSource = (localeSource == null) ? LocaleSource.AVAILABLE_LOCALES : localeSource;

        List<CountryInfo> countryInfoList = new ArrayList();

        Locale[] localeArr;
        try {
            localeArr = localeSource.getProvider().getDeclaredConstructor().newInstance().createLocaleList();
        } catch (Exception ex) {
            throw new LocaleSourceException(ex);
        }

        for (Locale locale : localeArr) {
            try {
                String iso2Code = locale.getCountry();
                String iso3Code = locale.getISO3Country();
                String displayName = LocaleHelper.createCountryDisplayName(locale, desigLocale1, desigLocale2);

                CountryInfo countryInfo = CountryInfo.newInstance(displayName, locale, iso2Code, iso3Code);
                countryInfoList.add(countryInfo);
            } catch (java.util.MissingResourceException ex) {
                if (PRINT_LOG) {
                    System.out.println("[CountryInfoHelper]obtainCountryInfoList(): [Warning] " + ex.getMessage());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return countryInfoList;
    }

    private static final CountryInfoMap countryInfoKeyByISOCodeMap = CountryInfoMap.create(Locale.getDefault());

    public static CountryInfo convertLocale2Country(Locale desigLocale) {
        String iso3Code = desigLocale.getISO3Country();
        return countryInfoKeyByISOCodeMap.getByISO3Code(iso3Code);
    }
}
