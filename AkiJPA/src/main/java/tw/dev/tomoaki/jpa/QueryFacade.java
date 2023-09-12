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
package tw.dev.tomoaki.jpa;

import tw.dev.tomoaki.jpa.entity.KeyValuePair;
import java.util.List;

/**
 *
 * @author tomoaki
 */
public interface QueryFacade<T> {

    public T find(Object id);

    public List<T> findAllAscOrdered(String entityPropName);

    public List<T> findAll();

    public List<T> findRange(int[] range);

    public int count();

//<editor-fold defaultstate="collapsed" desc="單一條件查詢系列 findByEqual、findByNotEqual、getByEqual 等等">
     /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」
     * @param value Table Column (即 Entity 之 Property)數值
     * @return 查詢結果
     */
    public List<T> findByEquals(String entityPropName, Object value);   
    
    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」
     * @param value Table Column (即 Entity 之 Property)數值
     * @return 查詢結果
     */
    public List<T> findByNotEquals(String entityPropName, Object value);    
    
    public T getByEquals(String columnName, Object columnValue);    
//</editor-fold>
 
//<editor-fold defaultstate="collapsed" desc="findByAndEqual系列">    
    public List<T> findByAndEquals(List<String> entityPropNameList, List<Object> valueList);

    /**
     *
     * @param entityPropNameList 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」 ，Property Name 清單
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    public List<T> findByAndEquals(List<String> entityPropNameList, List<Object> valueList, List<String> orderEntityPropNameList);

    /**
     *
     * @param pairList 資料對應組
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    public List<T> findByAndEqualPairs(List<KeyValuePair> pairList, List<String> orderEntityPropNameList);

//    public List<T> findBy
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="findByOrEqual系列">    


    public List<T> findByOrEquals(List<String> entityPropNameList, List<Object> valueList);

    /**
     *
     * @param entityPropNameList 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」 ，Property Name 清單
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    public List<T> findByOrEquals(List<String> entityPropNameList, List<Object> valueList, List<String> orderEntityPropNameList);

    /**
     *
     * @param pairList 資料對應組
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    public List<T> findByOrEqualPairs(List<KeyValuePair> pairList, List<String> orderEntityPropNameList);
//    public List<T> findBy
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="IN 系列">
    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @return 查詢結果
     */
    public List<T> findIn(String entityPropName, List<?> valueList);
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="findByAndNotEquals系列">
    public List<T> findByAndNotEquals(List<String> entityPropNameList, List<Object> valueList);

    /**
     *
     * @param entityPropNameList 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」 ，Property Name 清單
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    public List<T> findByAndNotEquals(List<String> entityPropNameList, List<Object> valueList, List<String> orderEntityPropNameList);

    /**
     *
     * @param pairList 資料對應組
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    public List<T> findByAndNotEqualPairs(List<KeyValuePair> pairList, List<String> orderEntityPropNameList);
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="findByOrNotEquals系列">
    public List<T> findByOrNotEquals(List<String> entityPropNameList, List<Object> valueList);

    /**
     *
     * @param entityPropNameList 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」 ，Property Name 清單
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    public List<T> findByOrNotEquals(List<String> entityPropNameList, List<Object> valueList, List<String> orderEntityPropNameList);

    /**
     *
     * @param pairList 資料對應組
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    public List<T> findByOrNotEqualPairs(List<KeyValuePair> pairList, List<String> orderEntityPropNameList);
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="getByAndEquals 系列">
    public T getByAndEquals(List<String> columnNameList, List<Object> columnValueList);
//</editor-fold>        

//<editor-fold defaultstate="collapsed" desc="getByOrEquals 系列">
    public T getByOrEquals(List<String> columnNameList, List<Object> columnValueList);
//</editor-fold>
}
