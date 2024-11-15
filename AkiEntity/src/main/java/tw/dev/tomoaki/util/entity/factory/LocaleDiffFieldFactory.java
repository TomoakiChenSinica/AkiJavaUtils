/*
 * Copyright 2024 tomoaki.
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
package tw.dev.tomoaki.util.entity.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tw.dev.tomoaki.util.entity.LocaleDiffField;

/**
 *
 * @author tomoaki
 */
public class LocaleDiffFieldFactory<VALUE> {

    private Class<VALUE> valueClazz;

    public LocaleDiffFieldFactory(Class<VALUE> valueClazz) {
        // [FIXME202411071103] 可能要看看能不能檢查傳進來的 Class<VALUE> 是否與希望的 VALUE 符合
        this.valueClazz = valueClazz;
    }

    public LocaleDiffField<VALUE> create(Locale... langLocales) {
        return create(Stream.of(langLocales).map(Locale::toLanguageTag).collect(Collectors.toList()));
    }

    public LocaleDiffField<VALUE> create(String... langTags) {
        return create(Arrays.asList(langTags));
    }

    public LocaleDiffField<VALUE> create(List langList) {
        LocaleDiffField<VALUE> dataMap = new LocaleDiffField<VALUE>();
        if (langList != null) {
            langList.forEach(lang -> appendEmpty(dataMap, lang));
        }
        return dataMap;
    }

    public LocaleDiffField<VALUE> appendEmpty(LocaleDiffField<VALUE> dataMap, Object lang) {
        try {
            if (lang instanceof String) {
                String langTag = (String) lang;
                dataMap.put(langTag, valueClazz.getConstructor().newInstance());
            } else if (lang instanceof Locale) {
                Locale langLocale = (Locale) lang;
                dataMap.put(langLocale.toLanguageTag(), valueClazz.getConstructor().newInstance());
            } else {

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataMap;
    }

}
