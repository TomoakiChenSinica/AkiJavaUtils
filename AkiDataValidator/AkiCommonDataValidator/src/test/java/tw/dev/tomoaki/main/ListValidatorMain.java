/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import tw.dev.tomoaki.util.commondatavalidator.ListValidator;

/**
 *
 * @author Tomoaki Chen
 */
public class ListValidatorMain {

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("Ackley","Seager", "Smoak");
        List<String> list2 = Arrays.asList("Seager", "Haniger", "France");
//        list1.stream().distinct().filter(list2::contains).collect(Collectors.toList());
        List<String> list3 = Arrays.asList( "Smoak", "Ackley","Seager");
        doTestOverlap(list1, list2);
        doTestOrderedEqual(list1, list2);
        doTestOrderedEqual(list1, list3);        
        doTestDataEqual(list1, list3);
    }
    protected static void doTestOverlap(List<String> list1, List<String> list2) {
        System.out.format("list1 = %s, list2 = %s, isEqualOverlap = %s \n", list1, list2, ListValidator.isEqualOverlap(list1, list2) );         
    }
    
    protected static void doTestOrderedEqual(List<String> list1, List<String> list2) {
        System.out.format("list1 = %s, list2 = %s, isOrderedEqual = %s \n", list1, list2, ListValidator.isOrderedEqual(list1, list2, String.class) );    
    }
    
    protected static void doTestDataEqual(List<String> list1, List<String> list2) {
        System.out.format("list1 = %s, list2 = %s, isDataEqual = %s \n", list1, list2, ListValidator.isDataEqual(list1, list2, String.class) );    
    }    
}
