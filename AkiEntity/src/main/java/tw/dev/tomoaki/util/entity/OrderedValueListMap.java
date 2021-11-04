/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tomoaki
 */
//[CHN20171221] OrderedValueListMap
public class OrderedValueListMap<Tkey1, Tvalue> implements Serializable{
    private KeyPairMap<Tkey1, String, Tvalue> theMap = new KeyPairMap<Tkey1, String, Tvalue>();
        
    public void put(Tkey1 key1, String orderIndex, Tvalue value){
        theMap.put(key1, orderIndex, value);
    }
          
    public Tvalue get(Tkey1 key1, String orderIndex){
        return theMap.get(key1, orderIndex);
    }
    
    public Map<String, Tvalue> getValueList(Tkey1 key1){ //FIXME : 這是很舊的名稱問題，我們是用 map 來替代list ，名稱要怎麼辦?
        return theMap.getInnerMap(key1);
    }
    
    public KeyPairMap<Tkey1, String, Tvalue> getMap(){
        return theMap;
    }
    
    public void remove(Tkey1 key1, String removeOrderindex){
        Map<String, Tvalue> valueList = this.getValueList(key1);
        Map<String, Tvalue> newValueList = OrderedValueListMapUtils.removeValueInValueListInOrderedValueListMap(valueList, removeOrderindex);
        theMap.setInnerMap(key1, newValueList);
    }
    
    public void clear(Tkey1 key1, String removeOrderindex){
        Map<String, Tvalue> valueList = this.getValueList(key1);
        Map<String, Tvalue> newValueList = OrderedValueListMapUtils.clearValueInValueListInOrderedValueListMap(valueList, removeOrderindex);
        theMap.setInnerMap(key1, newValueList);    
    }
    
    
    
    public Set<Tkey1> keySet(){
        return this.theMap.getFirstPairKeys();
    }
}
