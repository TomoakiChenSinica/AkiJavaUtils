/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.commondatavalidator;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Tomoaki Chen
 */
public class ListValidator {

    public static Boolean isListExist(List theList) {
        return theList != null && !theList.isEmpty();
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

}
