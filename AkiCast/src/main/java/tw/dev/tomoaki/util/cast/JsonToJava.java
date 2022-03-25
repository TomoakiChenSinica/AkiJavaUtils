/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.cast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;





/**
 *
 * @author tomoaki
 * 將json字串轉乘 java entity
 * 
  * 2016-11-xx 
 * 新增 method
 * getJavaListObject : 轉換 list包裝的
 * 
 * 
 */
public class JsonToJava<T> {   //要加在這裡
    
    
   public static <T>T getJavaObject(String json,Class<T> objectType) throws IOException {
        if(json != null){
            T javaObject;
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jp = jsonFactory.createParser(json);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            javaObject = mapper.readValue(jp, objectType);        
            return (T)javaObject;
        }else{
            return null;
        }
    }
   public static <T>T getJavaObject(InputStream is,Class<T> objectType) throws IOException {
        T javaObject;
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jp = jsonFactory.createParser(is);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        javaObject = mapper.readValue(jp, objectType);        
        return javaObject;
   }
   /*
   public Object getJavObject(JsonParser jp,Class<T> )
   {
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object javaObject = mapper.readValue(jp, objectType);        
        return javaObject;
   }*/
   
//   public static Map<String, Object> getJavaMap(String json){
//       Map<String, Object> 
//   }
//  
   
   /**
    * type1 to get list object with jackson 
    * 
     * @param json
     * @param objectType
     * @return 
    */
   public static <T> List<T> getJavaListObject(String json, Class<T> objectType) throws IOException{
        List<T> javaListObject;
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jp = jsonFactory.createParser(json);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, objectType);
        javaListObject = mapper.readValue(jp, collectionType);        
        return javaListObject;   
   }
   
   public static <T>T getJavaMap(String json, Class keyType, Class valueType) throws IOException{
        T javaObject;        
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
        javaObject = getJavaObject(json, javaType);
        return javaObject;
    }    
   
   public static <Tmap, T> Map<Tmap, T> getJavaMapObject(String json, Class<Tmap>  mapKeyType, Class<T> objectType) throws IOException{
        Map<Tmap, T> javaListObject;
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jp = jsonFactory.createParser(json);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, mapKeyType, objectType);
        javaListObject = mapper.readValue(jp, mapType);              
        return javaListObject;   
   }   
   
   
    public static Object getJavaComplexObject(String json, TypeReference typeRef) throws IOException {
        Object javaObject;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        javaObject = mapper.readValue(json, typeRef);
        return javaObject;
    }
   
         
//   public static <T>T convertMapToObject(Map jsonMap, Class<T> objectType) {
//        T javaObject;
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        javaObject = mapper.convertValue(jsonMap, objectType);
//        return (T)javaObject; 
//   }   
   public static <T>T convertMapToObject(Map jsonMap, Class<T> objectType) throws JsonProcessingException, IOException {
        String strMapJson = JavaToJson.getJsonString(jsonMap);
        return JsonToJava.getJavaObject(strMapJson, objectType);
   }    
   

   
    private static <T>T getJavaObject(String json, JavaType javaType) throws IOException{
        T javaObject;
        ObjectMapper mapper = new ObjectMapper();
        javaObject = mapper.readValue(json, javaType);
        return javaObject;
    }
}
