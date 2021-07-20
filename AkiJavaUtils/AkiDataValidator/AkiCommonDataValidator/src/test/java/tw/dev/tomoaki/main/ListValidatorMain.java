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
        System.out.println(ListValidator.isEqualOverlap(list1, list2));
        
//        list1.stream().distinct().filter(list2::contains).collect(Collectors.toList());
    }
}
