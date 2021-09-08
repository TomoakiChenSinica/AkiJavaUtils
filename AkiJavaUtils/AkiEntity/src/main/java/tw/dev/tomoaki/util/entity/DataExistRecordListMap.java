/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tomoaki
 * 此entity 是用來記錄某個物質/資料(對不起我想不到用甚麼詞) T 出現過幾次，及每次出現的詳細狀況R， <br>
 * 與DataExistRecordMap 不同的是，此entity 會檢查出現的狀況R是否重複 <br>
 */
public class DataExistRecordListMap<T, R> {
    //拉出
    private Map<T, DataExistMap<R>> theMap = new HashMap();

    public void add(T key, R recordMemo) {
        DataExistMap<R> recordExistMap = theMap.get(key);
        if (recordExistMap == null) {
            recordExistMap = new DataExistMap();
            recordExistMap.useLinkedMap();
        }
        recordExistMap.add(recordMemo);
        theMap.put(key, recordExistMap);
    }
    
    public void addAll(T key, List<R> recordMemoList) {
        if (recordMemoList != null) {
            for (R record : recordMemoList) {
                this.add(key, record);
            }
        }
    }    

    public List<R> getRecords(T key) {
        DataExistMap<R> recordExistMap = this.theMap.get(key);
        if(recordExistMap == null){
            return null;
        } else {
            return recordExistMap.existList();
        }
    }
    

//    public Set<Map.Entry<T, List<R>>> entrySet() {
//        return theMap.entrySet();
//    }

    public Set<T> keySet() {
        return theMap.keySet();
    }
    
//    public Map<T, List<R>> asMap(){
//        return this.theMap;
//    }
    
    /**
     * 總共有幾種值，<br/>
     * 也就是 key 的數量
     * 
     */
    public Integer numsOfDatas(){
        return theMap.size();
    }
    
}
