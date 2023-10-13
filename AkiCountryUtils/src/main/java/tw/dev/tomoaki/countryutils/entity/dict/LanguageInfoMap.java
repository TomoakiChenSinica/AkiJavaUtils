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
package tw.dev.tomoaki.countryutils.entity.dict;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import tw.dev.tomoaki.countryutils.LanguageInfoHelper;
import tw.dev.tomoaki.countryutils.entity.LanguageInfo;

/**
 *
 * @author tomoaki
 */
public class LanguageInfoMap {

    private Map<String, LanguageInfo> languageInfoKeyByISO3CodeMap;

    protected LanguageInfoMap() {
        this.languageInfoKeyByISO3CodeMap = new LinkedHashMap();
    }

    public static class Factory {

        public static LanguageInfoMap create(Locale desigLang) {
            List<LanguageInfo> languageInfoList = LanguageInfoHelper.obtainLanguageInfoList(desigLang);
            return Factory.create(languageInfoList);
        }

        public static LanguageInfoMap create(List<LanguageInfo> languageInfoList) {
            LanguageInfoMap theMap = new LanguageInfoMap();
            theMap.addAll(languageInfoList);
            return theMap;
        }
    }

//    protected void init(Locale desigLang) {
//        List<LanguageInfo> languageInfoList = LanguageInfoHelper.obtainLanguageInfoList(desigLang);
//        for (LanguageInfo languageInfo : languageInfoList) {
//            String iso3Code = languageInfo.getISO3Code();
//            this.languageInfoKeyByISO3CodeMap.put(iso3Code, languageInfo);
//        }
//    }
    protected void addAll(List<LanguageInfo> languageInfoList) {
        if (languageInfoList != null) {
            languageInfoList.forEach(languageInfo -> this.add(languageInfo));
        }
    }

    protected void add(LanguageInfo languageInfo) {
        String iso3Code = languageInfo.getISO3Code();
        this.languageInfoKeyByISO3CodeMap.put(iso3Code, languageInfo);
    }

    public LanguageInfo getByISO3Code(String iso3Code) {
        return this.languageInfoKeyByISO3CodeMap.get(iso3Code);
    }
}
