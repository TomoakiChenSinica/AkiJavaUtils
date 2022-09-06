/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tomoaki
 */
//[CHN20171221]
public class DataExistMap<T> implements Serializable {

    private Map<T, Boolean> dataExistMap;

//<editor-fold defaultstate="collapsed" desc="以下是constructor 相關">
    public DataExistMap() {
        this(false);
    }
    
    protected DataExistMap(Boolean isOrdered) {
        dataExistMap = isOrdered ?  new LinkedHashMap() : new HashMap();
    }
    
    public DataExistMap(Collection<T> dataList) {
        this(false, dataList); 
    }
        
    public DataExistMap(Boolean isOrdered, Collection<T> dataList) {
        this(isOrdered);
        if (dataList != null) {
            for (T data : dataList) {
                this.dataExistMap.put(data, Boolean.TRUE);
            }
        }
    }    
    
//    public DataExistMap(DataExistMap<T> sourceDataExistMap) {
//        this.dataExistMap = new HashMap(sourceDataExistMap.getDataExistMap());
//    }


//    public void useLinkedMap() {
//        this.dataExistMap = new LinkedHashMap();
//    }
//</editor-fold>

    public static class Factory {

        public static <T> DataExistMap<T> create(Collection<T> dataList) {
            DataExistMap<T> dataExistMap = new DataExistMap(dataList);
            return dataExistMap;
        }
        
        public static <T> DataExistMap<T> create(T... datas) {
            T[] dataArr = datas;
            return Factory.create(Arrays.asList(dataArr));
        }
        
        public static <T> DataExistMap<T> createOrdered() {
            DataExistMap<T> dataExistMap = new DataExistMap(true);            
            return dataExistMap;
        }
        
        public static <T> DataExistMap<T> createOrdered(Collection<T> dataList) {
            DataExistMap<T> dataExistMap = new DataExistMap(true, dataList);
            return dataExistMap;
        }        
    }
    
    
    

    public void add(T data) {
        dataExistMap.put(data, Boolean.TRUE);
    }

    public void addAll(List<T> datas) {
        if (datas != null) {
            for (T data : datas) {
                this.dataExistMap.put(data, Boolean.TRUE);
            }
        }
    }

    public void remove(T data) {
        dataExistMap.remove(data);
    }

    public Boolean contains(T data) {
        if (this.dataExistMap.get(data) != null && this.dataExistMap.get(data)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isEmpty() {
        return this.dataExistMap.isEmpty();
    }

    public List<T> getDataList() {
        if (this.dataExistMap.keySet() != null) {
            List<T> dataList = new ArrayList(this.dataExistMap.keySet());
            return dataList;
        }
        return new ArrayList();
    }

    public Map<T, Boolean> getDataExistMap() {
        return dataExistMap;
    }

    public DataExistMap merge(DataExistMap<T> anotherDataExistMap) {
        if (anotherDataExistMap != null) {
            List<T> dataList = anotherDataExistMap.getDataList();
            //因為以上method 在list會為null的狀況會回傳 new array list ，所以不用再特地判斷這邊是否為NULL
            for (T data : dataList) {
                this.add(data);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return this.dataExistMap.toString();
    }

    /**
     * notice that this will cause refrence
     */
    public void setDataExistMap(Map<T, Boolean> dataExistMap) {
        this.dataExistMap = dataExistMap;
    }

    public DataExistMap<T> getCopy() {        
        DataExistMap<T> copiedDataExistMap;
        Class clazz = this.dataExistMap.getClass();
        if(LinkedHashMap.class.equals(clazz)) {
            copiedDataExistMap = Factory.createOrdered(this.dataExistMap.keySet());
        } else {
            copiedDataExistMap = Factory.create(this.dataExistMap.keySet());
        }
        return copiedDataExistMap;
    }

    public List<T> existList() {
        List<T> existList = new ArrayList(this.dataExistMap.keySet());
        return existList;
    }

    public Integer size(){
        return dataExistMap.size();
    }    
}
