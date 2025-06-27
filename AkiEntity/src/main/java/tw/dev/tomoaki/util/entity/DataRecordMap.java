/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    private Map<T, List<R>> containerMap;
    private Comparator<R> recordComparator;

    public DataRecordMap() {
        this(false);
    }

    public DataRecordMap(Boolean isKeepOrdered) {
        containerMap = isKeepOrdered ? new LinkedHashMap() : new HashMap();
    }

    public static <T, R> DataRecordMap<T, R> create(Boolean isKeepOrdered) {
        DataRecordMap<T, R> dataMap = new DataRecordMap(isKeepOrdered);
        return dataMap;
    }

    public static <T, R> DataRecordMap<T, R> create(Boolean isKeepOrdered, Comparator<R> recordComparator) {
        DataRecordMap<T, R> dataMap = new DataRecordMap(isKeepOrdered);
        dataMap.recordComparator = recordComparator;
        return dataMap;
    }

    public void add(T key, R recordMemo) {
        List<R> records = containerMap.get(key);
        if (records == null) {
            records = new ArrayList();
        }
        records.add(recordMemo);
        records = trySortRecords(records);
        containerMap.put(key, records);
    }

    public void addAll(T key, List<R> recordMemoList) {
        if (recordMemoList != null) {
            recordMemoList.forEach(memo -> {
                this.add(key, memo);
            });
        }
    }

// ------------------------------------------------------------------------    
    public void createEmptyRecord(T key) {
        List<R> records = containerMap.get(key);
        if (records == null) {
            records = new ArrayList();
            containerMap.put(key, records);
        } else {
            System.err.println("Record[" + key + "] already exist");
        }
    }

    public List<R> getRecords(T key) {
        return containerMap.get(key);
    }

    public Set<Map.Entry<T, List<R>>> entrySet() {
        return containerMap.entrySet();
    }

    public Set<T> keySet() {
        return containerMap.keySet();
    }

    /**
     * 將資料轉成 Map<T, List<R>> 的形式
     *
     * @return Map
     */
    public Map<T, List<R>> asMap() {
        return this.containerMap;
    }

    /**
     * 總共有幾種值，也就是統計 key 的數量
     *
     * @return 總共有幾種值，也就是統計 key 的數量
     */
    public Integer numsOfDatas() {
        return containerMap.size();
    }

//<editor-fold defaultstate="collapsed" desc="內部輔助 Methods">
    protected List<R> trySortRecords(List<R> sortingList) {
        if (this.recordComparator != null) {
            sortingList.sort(recordComparator);
        }
        return sortingList;
    }
//</editor-fold>

}
