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
package tw.dev.tomoaki.localeext.source;

import tw.dev.tomoaki.localeext.source.LocaleProvider;

/**
 *
 * @author tomoaki
 */
public enum LocaleSource {

    ISO3_COUNTRYS("ISO3_COUNTRYS", ISO3CountryLocale.class), 
    AVAILABLE_LOCALES("AVAILABLE_LOCALES", AvailableLocale.class);
    
    
    private String code;
    private Class<? extends LocaleProvider> provider;

    private LocaleSource(String code, Class<? extends LocaleProvider> provider) {
        this.code = code;
        this.provider = provider;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Class<? extends LocaleProvider> getProvider() {
        return provider;
    }

    public void setProvider(Class<? extends LocaleProvider> provider) {
        this.provider = provider;
    }
    
    
    

}
