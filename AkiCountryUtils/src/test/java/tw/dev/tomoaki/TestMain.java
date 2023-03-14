/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import tw.dev.tomoaki.countryutils.entity.LocaleKeyByISO3CodeMap;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testPrint();
    }

    private static void testUtil1() {
        LocaleKeyByISO3CodeMap codeMap = new LocaleKeyByISO3CodeMap();
//        LocaleKeyByISO3CodeMap codeMap = new LocaleKeyByISO3CodeMap(Locale.ENGLISH);
        System.out.println(codeMap.getLocaleDisplayCountryName("TWN", Locale.ENGLISH));
    }

    private static void testLength() {
        Locale[] localeList = Locale.getAvailableLocales();
        String[] isoCountryList = Locale.getISOCountries();
        String[] isoLanguageList = Locale.getISOLanguages();
        System.out.format("Length of localeList= %s, isoCountryList= %s, isoLanguageList= %s", localeList.length, isoCountryList.length, isoLanguageList.length);
    }

    private static void testPrint() {
//        Locale[] localeList = Locale.getAvailableLocales();
//        String[] isoCountryList = Locale.getISOCountries();
//        String[] isoLanguageList = Locale.getISOLanguages();
//        Integer length = localeList.length;
//        for (Integer index = 0; index < length; index++) {
//            Locale locale = localeList[index];
//            String isoCountry = isoCountryList[index];
//            String isoLanguage = isoLanguageList[index];
//            System.out.format("local= %s, isoCountry= %s, isoLanguage= %s", locale, isoCountry, isoLanguage);
//            System.out.format("local= %s, isoCountry= %ss", locale, isoCountry);
//        }
        Locale[] localeList = Locale.getAvailableLocales();
        System.out.println("localeList: ");
        printArray(localeList);
        System.out.println("=======================================");

        String[] isoCountryList = Locale.getISOCountries();
        System.out.println("isoCountryList: ");
        printArray(isoCountryList);
        System.out.println("=======================================");

        String[] isoLanguageList = Locale.getISOLanguages();
        System.out.println("isoLanguageList: ");
        printArray(isoLanguageList);
        System.out.println("=======================================");

    }

    private static void printArray(Object[] arr) {
        for (Integer index = 0; index < arr.length; index++) {
            System.out.println(arr[index]);
        }
    }

    private static void testPrintLocale() {
        Locale[] localeList = Locale.getAvailableLocales();
        for (Locale locale : localeList) {
            System.out.format("locale= %s, lang= %s, country= %s, variant= %s \n", locale, locale.getLanguage(), locale.getCountry(), locale.getVariant());
        }
    }
}
