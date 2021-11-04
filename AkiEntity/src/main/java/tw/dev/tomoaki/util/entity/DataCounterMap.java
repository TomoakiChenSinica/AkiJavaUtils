/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tomoaki
 */
public class DataCounterMap<T> {
    private Map<T, Long> dataCounterMap = new HashMap();
    
    public DataCounterMap(){
    }
    
    public DataCounterMap(Collection<T> dataList){
        if(dataList!= null){
            for(T data : dataList){
                this.add(data);
            }
        }
    }
    
    public void useLinkedMap(){
        this.dataCounterMap = new LinkedHashMap();
    }
    
    public Long getCount(T data){
        Long count = this.dataCounterMap.get(data);
        if(count == null){
            count = 0L;
        }
        return count;
    }
    
    public void add(T data){
        Long nowCount = this.getCount(data);
        dataCounterMap.put(data, nowCount + 1);
    }
    
    //
    public void minus(T data){
        Long nowCount = this.getCount(data);
        dataCounterMap.put(data, nowCount - 1);
    }
    //
    public void remove(T data){
        dataCounterMap.remove(data);
    }
    
    public Boolean contains(T data){
        if(this.dataCounterMap != null && this.getCount(data) > 0){
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean isEmpty(){
        return this.dataCounterMap.isEmpty();
    }

    
   
    public Map<T, Long> getDataCounterMap() {
        return this.dataCounterMap;
    }
    
    public Set<T> keySet(){
        return this.dataCounterMap.keySet();
    }


    
        
}
