/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author arche
 */
public class StreamTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Integer> numsList = Arrays.asList(3, 1, 7, 2, 5, 6);
        System.out.println(numsList.stream().collect(Collectors.toList()));
 
        Set<Integer> numsSet = new TreeSet(numsList);
        System.out.println(numsSet.stream().collect(Collectors.toList()));
        
    }
    
    
}
