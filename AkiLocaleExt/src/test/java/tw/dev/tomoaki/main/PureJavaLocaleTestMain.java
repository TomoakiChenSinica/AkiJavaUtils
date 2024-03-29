/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki.main;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import tw.dev.tomoaki.LocalePrinter;

/**
 *
 * @author Tomoaki Chen
 */
public class PureJavaLocaleTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        testPrint();
//        testList();
//        System.out.println(Locale.forLanguageTag("zh-TW"));
        testPrintLocale();
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
        System.out.println("availableLocaleList: ");
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
//            System.out.format("locale= %s, lang= %s, country= %s, variant= %s \n", locale, locale.getLanguage(), locale.getCountry(), locale.getVariant());
            LocalePrinter.doPrint(locale);
            System.out.println("=============================");
        }
    }

    private static void testLang() {
        Locale zhTWLocale = Locale.forLanguageTag("zh_TW");
        System.out.println(zhTWLocale.getDisplayName());
        System.out.println(zhTWLocale.getDisplayName(Locale.ENGLISH));
        System.out.println(zhTWLocale.getISO3Language());
        System.out.println(zhTWLocale.getISO3Country());

        Locale enUSLocale = Locale.forLanguageTag("en-US");
        System.out.println(enUSLocale.getDisplayName());
        System.out.println(enUSLocale.getDisplayName(Locale.ENGLISH));
        System.out.println(enUSLocale.getISO3Language());
        System.out.println(enUSLocale.getISO3Country());

        System.out.println("Locale.forLanguageTag(\"TWN\")= " + Locale.forLanguageTag("TWN"));
    }

    private static void testList() {
        String[] countryISOCodes = Locale.getISOCountries();
        for (String iso2Code : countryISOCodes) {
            Locale locale = new Locale("", iso2Code); //塞的 lang 僅影響 「DisplayLanguage」
            LocalePrinter.doPrint(locale);
            System.out.println("============================");
        }
    }

}
