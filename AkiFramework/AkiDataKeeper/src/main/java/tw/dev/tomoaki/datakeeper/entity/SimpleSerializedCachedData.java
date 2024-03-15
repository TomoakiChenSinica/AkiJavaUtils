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
package tw.dev.tomoaki.datakeeper.entity;

import java.util.Map;

/**
 *
 * @author tomoaki
 *
 * 利用 toString 達到
 */
public abstract class SimpleSerializedCachedData<T> implements CachedData<T> {

    protected Map<String, T> dataKeyByDataSerializationMap;

    public void addSerialization(T data) {
        this.dataKeyByDataSerializationMap.put(data.toString(), data);
    }

    public T getByDataSerialization(String simpleSerialization) {
        return dataKeyByDataSerializationMap.get(simpleSerialization);
    }

}
/*
public interface SimpleSerializedCachedData<T> extends CachedData<T> {
    
     private Map<String, T> dataKeyBySerializationMap;
    
//    private static <T> String obtainSerialization(T data) {
//        return data.toString();
//    }
    
    public T getBySerialization(String simpleSerialization);
    
}
 */
