/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.commondatavalidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Tomoaki Chen
 */
public class ListValidator {

    private class DataExistMap {
    }
    
    public static Boolean isListExist(List theList) {
        return theList != null && !theList.isEmpty();
    }
    
    /**
     * 判斷兩個List 是否完全相等，即資料相同，且資料順序相同。
     *     
     * @param list1 資料清單一
     * @param list2 資料二
     * @param clazzType 資料類型的class
     * @return 兩個List 是否完全相等，即資料及順序接相同
     */
    public static <T>Boolean isOrderedEqual(List<T> list1, List<T> list2, Class<T> clazzType) {
        Integer size1 = list1.size();
        Integer size2 = list2.size();
        if(size1.equals(size2) == false) {
            return false;
        } else {
            Integer startIndex = 0;
            Integer endIndex = size1 - 1;
            for(Integer index = startIndex ; index <= endIndex ; index++) {
                T data1 = list1.get(index);
                T data2 = list2.get(index);
                if( data1.equals(data2) == false ) {
                    return false;
                }
            }
            return true;
        }
    }
    
    public static <T> Boolean isDataEqual(List<T> list1, List<T> list2, Class<T> clazzType) {
        Integer size1 = list1.size();
        Integer size2 = list2.size();
        if( size1.equals(size2) == false ) {
            return false;
        } else {
            Map<T, Boolean> dataExistMap = new HashMap();
            for(T data1 : list1) {
                dataExistMap.put(data1, Boolean.TRUE);
            }
            for(T data2 : list2) {
                if(dataExistMap.get(data2) == null) {
                    return false; //有一個找不到就可以false
                }
            }
            return true;
        }
    }
    
    /**
     * 參考資料：https://www.baeldung.com/java-lists-intersection <br>
     * 
     *  不寫成template 形式，似乎List會被當成普通 Object。 <br>
     *  
     *  檢查兩個 list 是否有資料重疊 <br>
     * 
     * @param list1
     * @param list2
     * @return 是否有重疊
     */
    public static <T>Boolean isEqualOverlap(List<T> list1, List<T> list2) {
//        System.out.println(list1.stream().distinct().collect(Collectors.toList()));        

        //兩者應該等價
//        System.out.println(list1.stream().distinct().filter(list2::contains).collect(Collectors.toList()));
//        System.out.println(list1.stream().distinct().filter((data1) -> {
//            return list2.contains(data1);
//        }).collect(Collectors.toList()));
//        list1.stream().distinct().filter(list2::contains).collect(Collectors.toList());
        
        return !list1.stream().distinct().filter(list2::contains).collect(Collectors.toList()).isEmpty();        
    }
    
   /**
     * 參考資料：https://www.baeldung.com/java-lists-intersection <br>
     * 
     *  不寫成template 形式，似乎List會被當成普通 Object。 <br>
     *  
     *  檢查兩個 list 是否有資料重疊 <br>
     * 
     * @param list1
     * @param list2
     * @return 是否有重疊
     */
    public static <T>Boolean isMatchOverlap(List<T> list1, List<T> list2) {
        return !list1.stream().distinct().filter(list2::contains).collect(Collectors.toList()).isEmpty();        
    }
    //忘了 isMatchOverlap 和 isEqualOverlap 的用途差別了，可能要選一個留
}
