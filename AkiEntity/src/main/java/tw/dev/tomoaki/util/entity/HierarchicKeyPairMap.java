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
package tw.dev.tomoaki.util.entity;

import java.util.Map;

/**
 *
 * @author tomoaki
 */
public abstract class HierarchicKeyPairMap<KEY1, KEY2, VALUE>  implements KeyPairMap<KEY1, KEY2, VALUE> {
    
//
//    /**
//     * 2017-10-16 ，暫時由protected 改為 public ，因為 StageWebServiceResult
//     * 應用此，且有狀況需要用到....
//     *
//     * @param key1 第一層的 key
//     * @return 內層的 Map<Tkey2, Tvalue> Map
//     */
//    public abstract Map<KEY2, VALUE> getInnerMap(KEY1 key1);
//
//    public abstract void setInnerMap(KEY1 key1, Map<KEY2, VALUE> innerMap);
//
//    protected abstract Map<KEY2, Tvalue> getInnerMap(Tkey1 key1, Boolean nullCreate) {
//        Map<Tkey2, Tvalue> innerMap = containerMap.get(key1);
//        if (innerMap == null && nullCreate) {
//            innerMap = this.obtainInnerMap();
//        }
//        return innerMap;
//    }
//
//    public Collection<Map<Tkey2, Tvalue>> getInnerMaps() {
//        return this.containerMap.values();
//    }
//
//    public Set<Tkey1> getFirstPairKeys() {
//        return containerMap.keySet();
//    }
//
//    public List<Tkey2> getSecondKeys() {
//        List<Tkey2> tempSecondKeys = new ArrayList();
//        Collection<Map<Tkey2, Tvalue>> innerMaps = this.getInnerMaps();
//
//        for (Map<Tkey2, Tvalue> innerMap : innerMaps) {
//            tempSecondKeys.addAll(innerMap.keySet());
//        }
//        DataExistMap<Tkey2> key2ExistMap = new DataExistMap(tempSecondKeys);
//        return key2ExistMap.existList();
//    }        
}
