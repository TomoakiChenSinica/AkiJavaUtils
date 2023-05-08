/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author tomoaki
 */
public class OrderedValueListMapUtils {
//    public static <Tvalue>Map<String, Tvalue> completeReorderedValueListInOrderedValueListMap(Map<String, Tvalue> valueListInOrderedValueListMap){ //上次發現(JavaToJson)
//        Long listSize = ((Integer)valueListInOrderedValueListMap.size()).longValue();
//        Long startOrderIndex = 1L; //這裡可能有問題，起始點一定要是 1?
//        for(Long orderIndex = startOrderIndex ; orderIndex <= listSize ; orderIndex++){
//            
//        }
//    }    
    
    
    
    /**
     * remove 其實有兩種角度
     * 1. 刪除指定orderindex。
     * 2. 其後面往前排隊
     * 
     * 
     * 1. 刪除最後面一筆
     * 2. 中間的update成後一筆的值
     * 
     * 但後者比較是已畫面角度來看(指定誰要update)。
     * 以資料角度來看反而不好處理
     * 
     * 
     */
    public static <Tvalue>Map<String, Tvalue> removeValueInValueListInOrderedValueListMap(Map<String, Tvalue> valueListInOrderedValueListMap, String strRemoveValueOrderIndex){ //上次發現(JavaToJson)
        Map<String, Tvalue> newList = new HashMap();
        Long removeValueOrderIndex = Long.parseLong(strRemoveValueOrderIndex);
        
        Set<Entry<String, Tvalue>> entrySet = valueListInOrderedValueListMap.entrySet();
        for(Entry<String, Tvalue> entry : entrySet){
            String strOrderIndex = entry.getKey();
            Tvalue value = entry.getValue();
            Long orderIndex = Long.parseLong(strOrderIndex);
            if(orderIndex < removeValueOrderIndex){ //在指定刪除前的不用特別處理，直接轉進新的
                newList.put(strOrderIndex, value);
            } else if(orderIndex.equals(removeValueOrderIndex)){ //就是指定那筆的話，要remove，所以在此不用做任何事
                //不用喔!
            } else { //在指定的後面，往前排一個，即減1
                Long reorderedOrderIndex = orderIndex - 1;
                String strReorderedOrderIndex = reorderedOrderIndex.toString();
                newList.put(strReorderedOrderIndex, value);
            }
        }
        return newList;
    }       
    
    /**
     * 變成null，但不刪除 
     */
    public static <Tvalue>Map<String, Tvalue> clearValueInValueListInOrderedValueListMap(Map<String, Tvalue> valueListInOrderedValueListMap, String strClearValueOrderIndex){ //上次發現(JavaToJson)
       Map<String, Tvalue> newList = new HashMap();
        Long removeValueOrderIndex = Long.parseLong(strClearValueOrderIndex);
                
        Boolean findFlag = false;
               
        Set<Entry<String, Tvalue>> entrySet = valueListInOrderedValueListMap.entrySet();
        for(Entry<String, Tvalue> entry : entrySet){
            String strOrderIndex = entry.getKey();
            Tvalue value = entry.getValue();
            Long orderIndex = Long.parseLong(strOrderIndex);
            if(orderIndex.equals(removeValueOrderIndex)){ //要將資料清空那筆，設為null
                value = null;
                findFlag = true;
            } 
            newList.put(strOrderIndex, value);
        }
//        
//        if(!findFlag){
//            newList.put(strClearValueOrderIndex, null);
//        }
//        
        return newList;        
    }      
}
