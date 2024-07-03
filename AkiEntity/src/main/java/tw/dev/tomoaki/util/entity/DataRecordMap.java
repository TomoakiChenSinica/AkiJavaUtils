/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tomoaki
 * @param <T> 資料(Record) 的 (查詢)Key
 * @param <R> 資料(Record)
 */
public class DataRecordMap<T, R> {

    private Map<T, List<R>> theMap;
    private Comparator<R> recordComparator;

    public DataRecordMap() {
        theMap = new HashMap();
    }

    public static class Factory {

        public static <T, R> DataRecordMap<T, R> create() {
            DataRecordMap<T, R> dataMap = new DataRecordMap();
            return dataMap;
        }

        public static <T, R> DataRecordMap<T, R> create(Comparator<R> recordComparator) {
            DataRecordMap<T, R> dataMap = new DataRecordMap();
            dataMap.recordComparator = recordComparator;
            return dataMap;
        }
    }

    public void add(T key, R recordMemo) {
        List<R> records = theMap.get(key);
        if (records == null) {
            records = new ArrayList();
        }
        records.add(recordMemo);
        records = trySortRecords(records);
        theMap.put(key, records);
    }

    public void createEmptyRecord(T key) {
        List<R> records = theMap.get(key);
        if (records == null) {
            records = new ArrayList();
            theMap.put(key, records);
        } else {
            System.err.println("Record[" + key + "] already exist");
        }
    }

    public List<R> getRecords(T key) {
        return theMap.get(key);
    }

    public Set<Map.Entry<T, List<R>>> entrySet() {
        return theMap.entrySet();
    }

    public Set<T> keySet() {
        return theMap.keySet();
    }

    /**
     * 將資料轉成 Map<T, List<R>> 的形式
     *
     * @return Map
     */
    public Map<T, List<R>> asMap() {
        return this.theMap;
    }

    /**
     * 總共有幾種值，也就是統計 key 的數量
     *
     * @return 總共有幾種值，也就是統計 key 的數量
     */
    public Integer numsOfDatas() {
        return theMap.size();
    }
    
    
//<editor-fold defaultstate="collapsed" desc="內部輔助 Methods">
    protected List<R> trySortRecords(List<R> sortingList) {
        if(this.recordComparator != null) {
            sortingList.sort(recordComparator);
        }
        return sortingList;
    }
//</editor-fold>

}
