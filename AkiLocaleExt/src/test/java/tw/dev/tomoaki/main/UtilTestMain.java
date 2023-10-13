package tw.dev.tomoaki.main;

import java.util.Locale;
import tw.dev.tomoaki.LocalePrinter;
import tw.dev.tomoaki.localeext.CountryInfoHelper;
import tw.dev.tomoaki.localeext.LanguageInfoHelper;
import tw.dev.tomoaki.localeext.entity.dict.LocaleMap;
import tw.dev.tomoaki.localeext.source.LocaleSource;

/**
 *
 * @author tomoaki
 */
public class UtilTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        testUtilLocaleMap();
        testLanguageInfo();
//        testCountryInfo();
    }
    
    private static void testLanguageInfo() {
        LanguageInfoHelper.obtainLanguageInfoList(Locale.ENGLISH).forEach(System.out::println);
    }    

    private static void testCountryInfo() {
        CountryInfoHelper.obtainCountryInfoList(Locale.ENGLISH, LocaleSource.ISO3_COUNTRYS).forEach(System.out::println);
    }
    
    private static void testUtilLocaleMap() {
        LocaleMap codeMap = new LocaleMap();
//        LocaleMap codeMap = new LocaleMap(Locale.ENGLISH);
        System.out.println(codeMap.getLocaleDisplayCountryName("TWN", Locale.ENGLISH));
        System.out.println(codeMap.getLocaleDisplayCountryName("TWN", Locale.JAPANESE));
        System.out.println(codeMap.getLocaleDisplayCountryName("TWN", Locale.TAIWAN));
        System.out.println(codeMap.getLocaleDisplayLanguageName("TWN", Locale.TAIWAN));
        System.out.println(codeMap.getLocaleDisplayLanguageName("TWN", Locale.ENGLISH));
    }

    private static void test3() {
        LocaleMap codeMap = new LocaleMap();
        Locale localeTWN1 = codeMap.getLocale("TWN");
        Locale localeTWN2 = Locale.TAIWAN;
        Locale localeTWN3 = Locale.forLanguageTag("TWN");
        Locale localeTWN4 = Locale.forLanguageTag("zh-TW");
        Locale localeTWN5 = Locale.forLanguageTag("TW");

        LocalePrinter.doPrint(localeTWN1);
        System.out.println("=============================================");
        
        LocalePrinter.doPrint(localeTWN2);
        System.out.println("=============================================");
        
        LocalePrinter.doPrint(localeTWN3);
        System.out.println("=============================================");
        
        LocalePrinter.doPrint(localeTWN4);
        System.out.println("=============================================");
        
        LocalePrinter.doPrint(localeTWN5);
        System.out.println("=============================================");
        
    }

    private static void test2() {
        LocaleMap codeMap = new LocaleMap();
        Locale localeTWN1 = codeMap.getLocale("TWN");
        Locale localeTWN2 = Locale.TAIWAN;
        Locale localeTWN3 = Locale.forLanguageTag("TWN");
        Locale localeTWN4 = Locale.forLanguageTag("zh-TW");
        Locale localeTWN5 = Locale.forLanguageTag("TW");

        System.out.println(localeTWN1);
        System.out.println(localeTWN2);
        System.out.println(localeTWN3);
        System.out.println(localeTWN4);
        System.out.println(localeTWN5);
        System.out.println("=============================================");

        System.out.println(localeTWN1.getISO3Country());
        System.out.println(localeTWN2.getISO3Country());
        System.out.println(localeTWN3.getISO3Country());
        System.out.println(localeTWN4.getISO3Country());
        System.out.println(localeTWN5.getISO3Country());
        System.out.println("=============================================");

        System.out.println(localeTWN1.getISO3Language());
        System.out.println(localeTWN2.getISO3Language());
        System.out.println(localeTWN3.getISO3Language());
        System.out.println(localeTWN4.getISO3Language());
        System.out.println(localeTWN5.getISO3Language());
        System.out.println("=============================================");

        System.out.println(localeTWN1.getDisplayLanguage());
        System.out.println(localeTWN2.getDisplayLanguage());
        System.out.println(localeTWN3.getDisplayLanguage());
        System.out.println(localeTWN4.getDisplayLanguage());
        System.out.println(localeTWN5.getDisplayLanguage());
        System.out.println("=============================================");

        System.out.println(localeTWN1.getDisplayLanguage(Locale.TAIWAN));
        System.out.println(localeTWN2.getDisplayLanguage(Locale.TAIWAN));
        System.out.println(localeTWN3.getDisplayLanguage(Locale.TAIWAN));
        System.out.println(localeTWN4.getDisplayLanguage(Locale.TAIWAN));
        System.out.println(localeTWN5.getDisplayLanguage(Locale.TAIWAN));
        System.out.println("=============================================");

        System.out.println(localeTWN1.getDisplayLanguage(Locale.ENGLISH));
        System.out.println(localeTWN2.getDisplayLanguage(Locale.ENGLISH));
        System.out.println(localeTWN3.getDisplayLanguage(Locale.ENGLISH));
        System.out.println(localeTWN4.getDisplayLanguage(Locale.ENGLISH));
        System.out.println(localeTWN5.getDisplayLanguage(Locale.ENGLISH));

    }

}
