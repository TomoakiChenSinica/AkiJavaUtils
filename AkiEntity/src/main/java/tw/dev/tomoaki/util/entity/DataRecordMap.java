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
 */
public class DataRecordMap<T, R> {
    
    private Map<T, List<R>> theMap = new HashMap();    
    
    public void add(T key, R recordMemo) {
        List<R> records = theMap.get(key);
        if (records == null) {
            records = new ArrayList();
        }
        records.add(recordMemo);
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

}
