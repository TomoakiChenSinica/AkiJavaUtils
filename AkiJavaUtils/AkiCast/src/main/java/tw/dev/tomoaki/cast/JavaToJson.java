/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.cast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author tomoaki
 * 將 Java object 轉成json字串
 * 
 */
public class JavaToJson {
    
    //mapper有一些參數，因此很有可能之後改成非static
    
    /** 
     * 將 java object 轉成 json 字串 
     * @param javaObject : java 的 object <br/>
     * @return 回傳該object 轉成 json 後的樣子，存成字串
     */    
    public static String getJsonString(Object javaObject) throws JsonProcessingException {
        String json = getJsonString(javaObject, true, true);
        return json;
    }
        
    
    /** 
     * 將 java object 轉成 json 字串 
     * @param javaObject : java 的 object <br/>
     * @param ignoreNull : 是否將 null 的忽略，不轉成json
     * @return 回傳該object 轉成 json 後的樣子，存成字串
     */    
    public static String getJsonString(Object javaObject, Boolean objectIgnoreNull, Boolean mapIgnoreNull) throws JsonProcessingException {
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        
        if(objectIgnoreNull == true)
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        Boolean mapWriteNullValue = !mapIgnoreNull;
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, mapWriteNullValue);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");       
        mapper.setDateFormat(dateFormat);       
        
        
        json = mapper.writeValueAsString(javaObject);
        return json;
    }
    
    public static String getJsonString(Map javaMap) throws JsonProcessingException{
        String json = "";
        json = getJsonString(javaMap, true);
        return json;
    }    
    
    
    public static String getJsonString(Map javaMap, Boolean ignoreNull) throws JsonProcessingException{
        Boolean mapWriteNullValue = !ignoreNull;
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, mapWriteNullValue);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");       
        mapper.setDateFormat(dateFormat);                     
        
        json = mapper.writeValueAsString(javaMap);
        return json;
    }
    
//    public static void main(String []args) throws JsonProcessingException{
//        Map<String, Object> testMap = new LinkedHashMap();
//        testMap.put("Param1", 1);
//        testMap.put("Param2", null);
//        testMap.put("Param3", "this is a test");
//        System.out.println("testMap = " + JavaToJson.getJsonString(testMap));
//        
//        
//        
//    }      
}
