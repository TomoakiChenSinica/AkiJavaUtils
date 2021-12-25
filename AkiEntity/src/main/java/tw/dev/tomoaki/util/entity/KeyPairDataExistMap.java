/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.util.List;
import java.util.Map;

/**
 *
 * @author tomoaki
 */
public class KeyPairDataExistMap<Tkey1, Tkey2> {
    private KeyPairMap<Tkey1, Tkey2, Boolean> dataExistMap = new KeyPairMap<Tkey1, Tkey2, Boolean>();
    
    public KeyPairDataExistMap(){
    }
    
    //new
//    public DataExistMap(DataExistMap<T> sourceDataExistMap){
//        this.dataExistMap = new HashMap(sourceDataExistMap.getDataExistMap());
//    }
    //
    
//    public DataExistMap(Collection<T> dataList){
//        if(dataList!= null){
//            for(T data : dataList){
//                this.dataExistMap.put(data, Boolean.TRUE);
//            }
//        }
//    }
    
//    public void useLinkedMap(){
//        this.dataExistMap = new LinkedHashMap();
//    }
    
    
    public void add(Tkey1 data1, Tkey2 data2){
        dataExistMap.put(data1, data2, Boolean.TRUE);
    }
    
//    public void addAll(List<T> datas){
//        if(datas != null){
//            for(T data : datas){
//                this.dataExistMap.put(data, Boolean.TRUE);
//            }
//        }
//    }
    
    public void remove(Tkey1 data1, Tkey2 data2){
        dataExistMap.remove(data1, data2);
    }
    
    public Boolean contains(Tkey1 data1, Tkey2 data2){
        if(this.dataExistMap.get(data1, data2) != null && this.dataExistMap.get(data1, data2)){
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean isEmpty(){
        List<Boolean> valueList = this.dataExistMap.getAllValueList();
        if(valueList == null || valueList.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

//    public List<T> getDataList(){
//        if(this.dataExistMap.keySet() != null){
//            List<T> dataList = new ArrayList(this.dataExistMap.keySet());
//            return dataList;
//        }
//        return new ArrayList();
//    }
   
//    public Map<T, Boolean> getDataExistMap() {
//        return dataExistMap;
//    }
//    
//    public DataExistMap merge(DataExistMap<T> anotherDataExistMap){
//        if(anotherDataExistMap != null){
//            List<T> dataList = anotherDataExistMap.getDataList();
//            //因為以上method 在list會為null的狀況會回傳 new array list ，所以不用再特地判斷這邊是否為NULL
//            for(T data : dataList){
//                this.add(data);
//            }            
//        }
//        return this;
//    }
//
//    @Override
//    public String toString() {
//        return this.dataExistMap.toString();
//    }

    /**
     *   notice that this will cause refrence  
     */
//    public void setDataExistMap(Map<T, Boolean> dataExistMap) {
//        this.dataExistMap = dataExistMap;
//    }
//    
//    public KeyPairDataExistMap<T> getCopy(){
//        DataExistMap<T> dataExistMap = new DataExistMap<T>(this);
//        return dataExistMap;
//    }    
    
    public KeyPairMap<Tkey1, Tkey2, Boolean> getAsKeyPariMap(){
        return this.dataExistMap;
    }
}
