/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.cast.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.cast.main.entity.Batter;
import tw.dev.tomoaki.cast.main.entity.Transaction;
import tw.dev.tomoaki.util.cast.JavaToJson;

/**
 *
 * @author Tomoaki Chen
 */
public class JavaToJsonMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JsonProcessingException, IOException {
        // TODO code application logic here     
        test3();
    }

    protected static void test1() throws JsonProcessingException {
        List list1 = Arrays.asList(1, 2, 3);
        System.out.println(JavaToJson.getJsonString(list1));
    }
    
    protected static void test2() throws JsonProcessingException, IOException {
        Batter ackley = new Batter("Dustin", "Ackley", 0.253);
        ackley.setLastModifiedDateTime(LocalDateTime.now());
        Batter smoak = new Batter("Justin", "Smoak", 0.232);
        smoak.setLastModifiedDateTime(LocalDateTime.now());
        List<Batter> batterList = Arrays.asList(ackley, smoak);
        String jsonData = JavaToJson.getJsonString(batterList);
        System.out.println("jsonData= " + jsonData);
//        List<Map> mapList = JsonToJava.getJavaListObject(jsonData, Map.class);
//        System.out.println(JsonToJava.convertListObjectToListMap(batterList));        
    }
    
    protected static void test3() throws JsonProcessingException {
        Transaction t = new Transaction();
        t.setIssueDateTime(LocalDateTime.now());
        JavaToJson.allowJavaTime = true;
        System.out.println(JavaToJson.getJsonString(t));
    }

}
