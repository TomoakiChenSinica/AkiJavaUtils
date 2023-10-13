package tw.dev.tomoaki.localeext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import tw.dev.tomoaki.localeext.entity.LanguageInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import tw.dev.tomoaki.localeext.exception.LocaleSourceException;
import tw.dev.tomoaki.localeext.source.LocaleSource;

/**
 *
 * @author tomoaki
 * 
 * Language Tag 清單: https://www.oracle.com/java/technologies/javase/jdk8-jre8-suported-locales.html
 * 重電參考: https://www.baeldung.com/java-8-localization
 * 其他參考 https://www.iana.org/assignments/language-subtag-registry/language-subtag-registry
 * 
 */
public class LanguageInfoHelper {

    public static Boolean PRINT_LOG = false;    
    
    public static List<LanguageInfo> obtainTWLanguageInfoList() {
        Locale twLocale = Locale.TAIWAN;
        Locale enLocale = Locale.ENGLISH;
        return obtainLanguageInfoList(twLocale, enLocale, null);
    }

    public static List<LanguageInfo> obtainTWLanguageInfoList(LocaleSource localeSource) {
        Locale twLocale = Locale.TAIWAN;
        Locale enLocale = Locale.ENGLISH;
        return obtainLanguageInfoList(twLocale, enLocale, localeSource);
    }

//    private static final LanguageInfoKeyByISOCodeMap countryInfoKeyByISOCodeMap = LanguageInfoKeyByISOCodeMap.Factory.create(Locale.TAIWAN);
    public static List<LanguageInfo> obtainLanguageInfoList(Locale langLocale) {
        if (langLocale == null) {
            throw new IllegalArgumentException("langLocale Is Null");
        }
        return obtainLanguageInfoList(langLocale, null, null);
    }

    public static List<LanguageInfo> obtainLanguageInfoList(Locale langLocale, LocaleSource localeSource) {
        if (langLocale == null) {
            throw new IllegalArgumentException("langLocale Is Null");
        }
        return obtainLanguageInfoList(langLocale, null, localeSource);
    }

    public static List<LanguageInfo> obtainLanguageInfoList(Locale langLocale1, Locale langLocale2, LocaleSource localeSource) {
        if (langLocale1 == null) {
            throw new IllegalArgumentException("langLocale1 Is Null");
        }

        localeSource = (localeSource == null) ? LocaleSource.AVAILABLE_LOCALES : localeSource;

        List<LanguageInfo> countryInfoList = new ArrayList();

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
                String languageDisplayName = LocaleHelper.createLanguageDisplayName(locale, langLocale1, langLocale2);
                String countryDisplayName = LocaleHelper.createCountryDisplayName(locale, langLocale1, langLocale2);
                String languageTag = locale.toLanguageTag();
                LanguageInfo countryInfo = LanguageInfo.newInstance(languageDisplayName, locale, iso2Code, iso3Code, languageTag, countryDisplayName);
                countryInfoList.add(countryInfo);
            } catch (java.util.MissingResourceException ex) {
                if(PRINT_LOG) System.out.println("[CountryInfoHelper]obtainCountryInfoList(): [Warning] " + ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return countryInfoList;
    }

//    public static List<LanguageInfo> obtainLanguageInfoList(Locale langLocale1, Locale langLocale2) {
//        if (langLocale1 == null) {
//            throw new IllegalArgumentException("langLocale1 Is Null");
//        }
//
//        List<LanguageInfo> countryInfoList = new ArrayList();
//
//        Locale[] localeArr = Locale.getAvailableLocales();
//        for (Locale locale : localeArr) {
//            try {
//                String iso3Code = locale.getISO3Country();
//                String displayLanguageName;
//                String displayLanguageName1 = locale.getDisplayLanguage(langLocale1);
//                String displayLanguageName2 = (langLocale2 == null) ? null : locale.getDisplayLanguage(langLocale2);
//                displayLanguageName = displayLanguageName2 == null ? displayLanguageName1 : String.format("%s / %s", displayLanguageName1, displayLanguageName2);//(displayLanguageName1 + " / " + displayLanguageName2)
//
//                String countryDisplayName = LocaleHelper.createCountryDisplayName(locale, langLocale1, langLocale2);
//                LanguageInfo countryInfo = LanguageInfo.newInstance(displayLanguageName, locale, iso3Code, countryDisplayName);
//                countryInfoList.add(countryInfo);
//            } catch (java.util.MissingResourceException ex) {
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return countryInfoList;
//    }
}
