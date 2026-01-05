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
package tw.dev.tomoaki.localeext.entity.dict;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import tw.dev.tomoaki.localeext.LanguageInfoHelper;
import tw.dev.tomoaki.localeext.entity.CountryInfo;
import tw.dev.tomoaki.localeext.entity.LanguageInfo;
import tw.dev.tomoaki.localeext.source.LocaleSource;

/**
 *
 * @author tomoaki
 */
public class LanguageInfoMap {

    private Map<String, LanguageInfo> languageInfoKeyByISO2CodeMap; //可能要開 List
    private Map<String, LanguageInfo> languageInfoKeyByISO3CodeMap; //同上
    private Map<String, LanguageInfo> languageInfoKeyByLanguageTagMap;

    protected LanguageInfoMap() {
        this.languageInfoKeyByISO2CodeMap = new LinkedHashMap();
        this.languageInfoKeyByISO3CodeMap = new LinkedHashMap();
        this.languageInfoKeyByLanguageTagMap = new LinkedHashMap();
    }


    public static LanguageInfoMap create(Locale desigLang) {
        List<LanguageInfo> languageInfoList = LanguageInfoHelper.obtainLanguageInfoList(desigLang);
        return create(languageInfoList);
    }

    public static LanguageInfoMap create(Locale desigLang, LocaleSource localeSource) {
        List<LanguageInfo> languageInfoList = LanguageInfoHelper.obtainLanguageInfoList(desigLang, localeSource);
        return create(languageInfoList);
    }

    public static LanguageInfoMap create(List<LanguageInfo> languageInfoList) {
        LanguageInfoMap theMap = new LanguageInfoMap();
        theMap.addAll(languageInfoList);
        return theMap;
    }

    protected void addAll(List<LanguageInfo> languageInfoList) {
        if (languageInfoList != null) {
            languageInfoList.forEach(languageInfo -> this.add(languageInfo));
        }
    }

    protected void add(LanguageInfo languageInfo) {
        String iso2Code = languageInfo.getISO2Code();
        String iso3Code = languageInfo.getISO3Code();
        String languageTag = languageInfo.getLanguageTag();
        this.languageInfoKeyByISO2CodeMap.put(iso2Code, languageInfo);
        this.languageInfoKeyByISO3CodeMap.put(iso3Code, languageInfo);
        this.languageInfoKeyByLanguageTagMap.put(languageTag, languageInfo);

    }

    public LanguageInfo getByISO2Code(String iso2Code) {
        return this.languageInfoKeyByISO3CodeMap.get(iso2Code);
    }

    public LanguageInfo getByISO3Code(String iso3Code) {
        return this.languageInfoKeyByISO3CodeMap.get(iso3Code);
    }
    
    public LanguageInfo getByLanguageTag(String languageTag) {
        return this.languageInfoKeyByLanguageTagMap.get(languageTag);
    }    
    
    public List<LanguageInfo> getLanguageInfoList() {        
//        return new ArrayList(this.languageInfoKeyByISO2CodeMap.values());
        return new ArrayList(this.languageInfoKeyByLanguageTagMap.values());
    }    
}
