/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.cast.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.util.cast.JavaToJson;

/**
 *
 * @author Tomoaki Chen
 */
public class JavaToJsonMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JsonProcessingException {
        // TODO code application logic here
        List list1 = Arrays.asList(1, 2, 3);
        System.out.println(JavaToJson.getJsonString(list1));
        
    }
    
}
