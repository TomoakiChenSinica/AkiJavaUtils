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
package tw.dev.tomoaki.article.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tomoaki
 */
public class ArticleTokenOptionMap {
    
    private Map<String, ArticleTokenOption> dataKeyByTokenMap;
    
    protected ArticleTokenOptionMap() {
        this.dataKeyByTokenMap = new HashMap();
    }
    
    public static class Factory {
    
        public static ArticleTokenOptionMap create(ArticleTokenOption... options) {
            return Factory.create(Arrays.asList(options));
        }

        public static ArticleTokenOptionMap create(List<ArticleTokenOption> optionList) {
            ArticleTokenOptionMap dataMap = new ArticleTokenOptionMap();
            if(optionList != null) {
                optionList.forEach(dataMap::add);
            }
            return dataMap;
        }
    }
    
    public void add(ArticleTokenOption tokenOption) {
        this.dataKeyByTokenMap.put(tokenOption.getToken(), tokenOption);
    }
    
    public ArticleTokenOption getByToken(String token) {
        return this.dataKeyByTokenMap.get(token);
    }
}
