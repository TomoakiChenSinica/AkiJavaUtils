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
package tw.dev.tomoaki.localeext;

import java.util.Locale;
import tw.dev.tomoaki.localeext.entity.LanguageInfo;
import tw.dev.tomoaki.localeext.entity.dict.LanguageInfoMap;
import tw.dev.tomoaki.localeext.entity.dict.LocaleMap;

/**
 *
 * @author tomoaki
 */
public class LocaleLanguageConverter {
 
    private static final LanguageInfoMap languageInfoKeyByISOCodeMap = LanguageInfoMap.Factory.create(Locale.TAIWAN);
    private static final LocaleMap localeKeyByISO3CodeMap = new LocaleMap();

    public static LanguageInfo convertLocale2Language(Locale desigLocale) {
        String iso3Code = desigLocale.getISO3Country();
        return languageInfoKeyByISOCodeMap.getByISO3Code(iso3Code);
    }
    
    public static Locale convertCountry2Locale(LanguageInfo desigLanguageInfo) {
        String iso3Code = desigLanguageInfo.getISO3Code();
        return localeKeyByISO3CodeMap.getLocale(iso3Code);
    }    
}
