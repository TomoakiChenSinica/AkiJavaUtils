/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki;

import java.util.Locale;
import tw.dev.tomoaki.countryutils.LocaleKeyByISO3CodeMap;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LocaleKeyByISO3CodeMap codeMap = new LocaleKeyByISO3CodeMap();        
//        LocaleKeyByISO3CodeMap codeMap = new LocaleKeyByISO3CodeMap(Locale.ENGLISH);
        System.out.println(codeMap.getLocaleDisplayCountryName("TWN", Locale.ENGLISH));
    }
    
}
