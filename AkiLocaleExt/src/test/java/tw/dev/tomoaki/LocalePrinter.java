/*
 * Copyright 2023 tomoaki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tw.dev.tomoaki;

import java.util.Locale;

/**
 *
 * @author tomoaki
 */
public class LocalePrinter {

    public static void doPrint(Locale desigLocale) {
        try {
            System.out.println(String.format("Locale= %s", desigLocale));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println(String.format("Locale.Language= %s", desigLocale.getLanguage()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println(String.format("Locale.Country= %s", desigLocale.getCountry()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println(String.format("Locale.ISO3Country= %s", desigLocale.getISO3Country()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println(String.format("Locale.ISO3Language= %s", desigLocale.getISO3Language()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println(String.format("Locale.DisplayLanguage= %s", desigLocale.getDisplayLanguage()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println(String.format("Locale.DisplayCountry= %s", desigLocale.getDisplayCountry()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println(String.format("Locale.DisplayLanguage(TWN)= %s", desigLocale.getDisplayLanguage(Locale.TAIWAN)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println(String.format("Locale.DisplayLanguage(USA)= %s", desigLocale.getDisplayLanguage(Locale.ENGLISH)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            System.out.println(String.format("Locale.Script= %s", desigLocale.getScript()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println(String.format("Locale.Variant= %s", desigLocale.getVariant()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }        

    }
}
