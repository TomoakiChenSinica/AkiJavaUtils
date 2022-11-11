/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.datetime.util;

/**
 *
 * @author Tomoaki Chen
 */
public class NanoMathHelper {
      
    public static Boolean isOverlap(Integer standardGroupStart, Integer standardGroupEnd, Integer compareGroupStart, Integer compareGroupEnd){
        return compareGroupEnd >= standardGroupStart && compareGroupStart <= standardGroupEnd;
    }
    
//    //vacationtypedetail年資區間使用，年資範圍若為1~12個月，是不包含滿12個月整的(去尾數)
//    public static Boolean isOverlapNotIncludeEndCount(Integer standardGroupStart, Integer standardGroupEnd, Integer compareGroupStart, Integer compareGroupEnd){
//        return compareGroupEnd >= standardGroupStart && compareGroupStart < standardGroupEnd;
//    }
//    
    public static Boolean isOverlap(Long standardGroupStart, Long standardGroupEnd, Long compareGroupStart, Long compareGroupEnd){
        return compareGroupEnd >= standardGroupStart &&  compareGroupStart <= standardGroupEnd;
    }    
}
