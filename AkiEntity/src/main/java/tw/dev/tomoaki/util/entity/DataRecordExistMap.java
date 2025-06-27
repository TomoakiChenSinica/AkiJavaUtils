/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tomoaki 此entity 是用來記錄某個物質/資料(對不起我想不到用甚麼詞) T 出現過幾次，及每次出現的詳細狀況R， <br>
 * 與DataExistRecordMap 不同的是，此entity 會檢查出現的狀況R是否重複 <br>
 * @param <T> Key 的類型
 * @param <R> 紀錄、資料的類型
 */
public class DataRecordExistMap<T, R> {

    //拉出
    private Map<T, DataExistMap<R>> container; // = new HashMap();

//<editor-fold defaultstate="collapsed" desc="以下是跟 constrctor 相關">

    public DataRecordExistMap() {
        this(false);
    }
    
    public DataRecordExistMap(Boolean isKeepOrdered) {
        container = isKeepOrdered ? new LinkedHashMap() : new HashMap();
    }

    public static DataRecordExistMap create(Boolean isKeepOrdered) {
        DataRecordExistMap existMap = new DataRecordExistMap(isKeepOrdered);
        return existMap;
    }

    /* 之前用 TomoakiChen 好像漏改
    public static class Factory {
        
        public static DataRecordExistMap create() {
            DataRecordExistMap existMap = new DataRecordExistMap(false);
            return existMap;
        }
        
        public static DataRecordExistMap create(Boolean isKeepOrdered) {
            DataRecordExistMap existMap = new DataRecordExistMap(isKeepOrdered);
            return existMap;
        }
    } */
    
//<editor-fold defaultstate="collapsed" desc="外部呼叫 Methods">    

    public void add(T key, R recordMemo) {
        DataExistMap<R> recordExistMap = container.get(key);
        if (recordExistMap == null) {
            recordExistMap = DataExistMap.createOrdered(); // 讓它保持原有順序
        }
        recordExistMap.add(recordMemo);
        container.put(key, recordExistMap);
    }

    public void addAll(T key, List<R> recordMemoList) {
        if (recordMemoList != null) {
            for (R record : recordMemoList) {
                this.add(key, record);
            }
        }
    }

    public List<R> getRecords(T key) {
        DataExistMap<R> recordExistMap = this.container.get(key);
        if (recordExistMap == null) {
            return null;
        } else {
            return recordExistMap.existList();
        }
    }
    
    public int getRecordCount(T key) {
        List<R> recordList = this.getRecords(key);
        return recordList != null ? recordList.size() : 0;
    }
    
    public Set<T> keySet() {
        return container.keySet();
    }

//    public Set<Map.Entry<T, List<R>>> entrySet() {
//        return theMap.entrySet();
//    }
    public Map<T, List<R>> asMap() {
        Map<T, List<R>> theMap = new LinkedHashMap();
        for (T key : this.keySet()) {
            List<R> records = this.getRecords(key);
            theMap.put(key, records);
        }
        return theMap;
    }

    /**
     * 總共有幾種值，也就是 key 的數量
     *
     * @return 總共有幾種值，也就是 key 的數量
     */
    public Integer numsOfDatas() {
        return container.size();
    }
    
    public Boolean contains(T key, R reord) {
        DataExistMap<R> dataExistMap = this.container.get(key);
        if (dataExistMap == null) {
            return false;
        } else {
            return dataExistMap.contains(reord);
        }
    }    
//</editor-fold>

}
