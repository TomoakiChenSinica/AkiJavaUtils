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
public class DataExistMap<T> implements Serializable{
    private Map<T, Boolean> dataExistMap = new HashMap();
    
    public DataExistMap(){
    }
       
    public DataExistMap(DataExistMap<T> sourceDataExistMap){
        this.dataExistMap = new HashMap(sourceDataExistMap.getDataExistMap());
    }   
    
    public DataExistMap(Collection<T> dataList){
        if(dataList!= null){
            for(T data : dataList){
                this.dataExistMap.put(data, Boolean.TRUE);
            }
        }
    }
    
    public static class Factory {
        public static <T>DataExistMap create(T... datas) {
            List<T> dataList = Arrays.asList(datas);
            DataExistMap<T> dataMap = new DataExistMap(dataList);            
            return dataMap;
        }
    }
    
    
    public void useLinkedMap(){
        this.dataExistMap = new LinkedHashMap();
    }
    
    
    public void add(T data){
        dataExistMap.put(data, Boolean.TRUE);
    }
    
    public void addAll(List<T> datas){
        if(datas != null){
            for(T data : datas){
                this.dataExistMap.put(data, Boolean.TRUE);
            }
        }
    }
    
    public void remove(T data){
        dataExistMap.remove(data);
    }
    
    public Boolean contains(T data){
        if(this.dataExistMap.get(data) != null && this.dataExistMap.get(data)){
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean isEmpty(){
        return this.dataExistMap.isEmpty();
    }

    public List<T> getDataList(){
        if(this.dataExistMap.keySet() != null){
            List<T> dataList = new ArrayList(this.dataExistMap.keySet());
            return dataList;
        }
        return new ArrayList();
    }
   
    public Map<T, Boolean> getDataExistMap() {
        return dataExistMap;
    }
    
    public DataExistMap merge(DataExistMap<T> anotherDataExistMap){
        if(anotherDataExistMap != null){
            List<T> dataList = anotherDataExistMap.getDataList();
            //因為以上method 在list會為null的狀況會回傳 new array list ，所以不用再特地判斷這邊是否為NULL
            for(T data : dataList){
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
     *   notice that this will cause refrence  
     */
    public void setDataExistMap(Map<T, Boolean> dataExistMap) {
        this.dataExistMap = dataExistMap;
    }
    
    public DataExistMap<T> getCopy(){
        DataExistMap<T> dataExistMap = new DataExistMap<T>(this);
        return dataExistMap;
    }
    
    
    public List<T> existList(){
        List<T> existList = new ArrayList(this.dataExistMap.keySet());
        return existList;
    }
                
}
