/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.localeext;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
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
    
    
    /**
     * 參考自 https://stackoverflow.com/questions/3684747/how-to-validate-a-locale-in-java
     * 
     * @param desigLocale 指定的，想檢查的 Locale 資料
     * @return 指定的 Locale 是否是「實際存在」的
     */
    public static Boolean isExist(Locale desigLocale) {
        try {
            desigLocale.toLanguageTag();
            String iso3Lang = desigLocale.getISO3Language();
            String iso3Country = desigLocale.getISO3Country();
            // System.out.println(String.format("locale.getISO3Language()= %s, locale.getISO3Country()= %s", locale.getISO3Language(), locale.getISO3Country()));            
            return iso3Lang != null && iso3Country != null && !iso3Lang.trim().isBlank() && !iso3Country.trim().isBlank();
        } catch (MissingResourceException ex) {
            // System.out.println("locale.toLanguageTag()= " + locale.toLanguageTag());
            return false;
        }        
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
