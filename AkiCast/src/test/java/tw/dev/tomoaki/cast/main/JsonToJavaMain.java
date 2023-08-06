/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.cast.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import tw.dev.tomoaki.cast.main.entity.Batter;
import tw.dev.tomoaki.util.cast.JavaToJson;
import tw.dev.tomoaki.util.cast.JsonToJava;

/**
 *
 * @author arche
 */
public class JsonToJavaMain {
    
    public static void main(String[] args) throws JsonProcessingException, IOException {

    }

    private static void test1() throws IOException {
        
    }

    protected static void test1() throws IOException {
        Batter ackley = new Batter("Dustin", "Ackley", 0.253);
        Batter smoak = new Batter("Justin", "Smoak", 0.232);
        List<Batter> batterList = Arrays.asList(ackley, smoak);
//        String jsonData = JavaToJson.getJsonString(batterList);
//        List<Map> mapList = JsonToJava.getJavaListObject(jsonData, Map.class);
        System.out.println(JsonToJava.convertListObjectToListMap(batterList));
    }
    
    protected static void test2() {
//        String jsonData = "{""firstName"": ""Dustin""}";
        String jsonData = "{\"firstName\": \"Dustin\"}";

    }
}
