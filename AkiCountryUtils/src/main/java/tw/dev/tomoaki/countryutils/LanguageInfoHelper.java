package tw.dev.tomoaki.countryutils;

import tw.dev.tomoaki.countryutils.entity.LanguageInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author tomoaki
 */
public class LanguageInfoHelper {

    public static List<LanguageInfo> obtainTWLanguageInfoList() {
        Locale twLocale = Locale.TAIWAN;
        Locale enLocale = Locale.ENGLISH;
        return obtainLanguageInfoList(twLocale, enLocale);
    }

//    private static final LanguageInfoKeyByISOCodeMap countryInfoKeyByISOCodeMap = LanguageInfoKeyByISOCodeMap.Factory.create(Locale.TAIWAN);
    public static List<LanguageInfo> obtainLanguageInfoList(Locale langLocale) {
        if (langLocale == null) {
            throw new IllegalArgumentException("langLocale Is Null");
        }
        return obtainLanguageInfoList(langLocale, null);
    }

    public static List<LanguageInfo> obtainLanguageInfoList(Locale langLocale1, Locale langLocale2) {
        if (langLocale1 == null) {
            throw new IllegalArgumentException("langLocale1 Is Null");
        }

        List<LanguageInfo> countryInfoList = new ArrayList();

        Locale[] localeArr = Locale.getAvailableLocales();
        for (Locale locale : localeArr) {
            try {
                String iso3Code = locale.getISO3Country();
                String displayLanguageName;
                String displayLanguageName1 = locale.getDisplayLanguage(langLocale1);
                String displayLanguageName2 = (langLocale2 == null) ? null : locale.getDisplayLanguage(langLocale2);
                displayLanguageName = displayLanguageName2 == null ? displayLanguageName1 : String.format("%s / %s", displayLanguageName1, displayLanguageName2);//(displayLanguageName1 + " / " + displayLanguageName2)

                String countryDisplayName = LocaleHelper.createCountryDisplayName(locale, langLocale1, langLocale2);
                LanguageInfo countryInfo = LanguageInfo.newInstance(displayLanguageName, locale, iso3Code, countryDisplayName);
                countryInfoList.add(countryInfo);
            } catch (java.util.MissingResourceException ex) {
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return countryInfoList;
    }

}
