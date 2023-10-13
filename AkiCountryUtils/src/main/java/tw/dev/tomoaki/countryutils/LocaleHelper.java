/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.countryutils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Tomoaki Chen
 */
public class LocaleHelper {

    private static final String DEFAULT_DISPLAY_NAME_SPLIT_WORD = " / ";
    
    public static Boolean looseEquals(Locale locale1, Locale locale2) {
        if (locale1 == null && locale2 == null) {
            throw new IllegalArgumentException("Both Locale Is Null");
        }

        if (locale1 == null || locale2 == null) {
            return Boolean.FALSE;
        }

        return Objects.equals(locale1.getCountry(), locale2.getCountry()) && Objects.equals(locale1.getISO3Country(), locale2.getISO3Country());
    }

    public static List<String> createCountryLangNameList(Locale countryLocale, Locale[] displayLangLocales) {
        List<String> countryLangNameList = Arrays.asList(displayLangLocales).stream()
                .filter(langLocale -> langLocale != null)
                .map(langLocale -> countryLocale.getDisplayCountry(langLocale))
                .collect(Collectors.toList());
        return countryLangNameList;
    }
    
    public static String createCountryDisplayName(Locale countryLocale, Locale... displayLangLocales) {
        return createCountryDisplayName(countryLocale, DEFAULT_DISPLAY_NAME_SPLIT_WORD, displayLangLocales);
    }    

    public static String createCountryDisplayName(Locale countryLocale, String splitWord, Locale... displayLangLocales) {
        List<String> countryLangNameList = createCountryLangNameList(countryLocale, displayLangLocales);
        return countryLangNameList.stream().collect(Collectors.joining(splitWord));
    }

    
    
    public static List<String> createLanguageLangNameList(Locale languageLocale, Locale[] displayLangLocales) {
        List<String> languageLangNameList = Arrays.asList(displayLangLocales).stream()
                .filter(langLocale -> langLocale != null)
                .map(langLocale -> languageLocale.getDisplayLanguage(langLocale))
                .collect(Collectors.toList());
        return languageLangNameList;
    }

    public static String createLanguageDisplayName(Locale languageLocale, Locale... displayLangLocales) {
        return createLanguageDisplayName(languageLocale, DEFAULT_DISPLAY_NAME_SPLIT_WORD, displayLangLocales);
    }    
    
    public static String createLanguageDisplayName(Locale languageLocale, String splitWord, Locale... displayLangLocales) {
        List<String> languageLangNameList = createLanguageLangNameList(languageLocale, displayLangLocales);
        return languageLangNameList.stream().collect(Collectors.joining(splitWord));
    }
}
