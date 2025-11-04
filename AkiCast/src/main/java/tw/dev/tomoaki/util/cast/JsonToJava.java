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
//import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import tw.dev.tomoaki.util.cast.helper.ObjectMapperHelper;

/**
 *
 * @author tomoaki 將json字串轉乘 java entity
 *
 * 2016-11-xx 新增 method getJavaListObject : 轉換 list包裝的
 *
 * [FIXME202411081617] 因為內部都是 static Method，似乎不用在這裡定義 Generic Type
 */
public class JsonToJava<T> {   //要加在這裡

    /* 以下從 JavaToJson 過來 */
    private final static String DEFAULT_DATETIME_PATTREN = "yyyy-MM-dd HH:mm";

    public static String dateTimePattern = DEFAULT_DATETIME_PATTREN;

    public static Boolean allowJavaTime = Boolean.FALSE;

    public static ObjectMapper obtainCleanObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    public static <T> T getJavaObject(String json, Class<T> objectType) throws IOException {
        return getJavaObject(null, json, objectType);
    }

    public static <T> T getJavaObject(ObjectMapper desigMapper, String json, Class<T> objectType) throws IOException {
        /* 例外狀況(json不存在等)處理
        if (json != null) {
            T javaObject;
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jp = jsonFactory.createParser(json);
            ObjectMapper mapper = ObjectMapperHelper.tryObtainObjectMapper(desigMapper, allowJavaTime); // new ObjectMapper();

            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
           
            if (dateTimePattern != null) {
                DateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
                mapper.setDateFormat(dateFormat);
            }

            javaObject = mapper.readValue(jp, objectType);
            return (T) javaObject;
        } else {
            return null;
        } */
        if (json == null) {
            return null;
        }
        T javaObject;
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jp = jsonFactory.createParser(json);
        ObjectMapper mapper = ObjectMapperHelper.tryObtainObjectMapper(desigMapper, allowJavaTime); // new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (dateTimePattern != null) {
            DateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
            mapper.setDateFormat(dateFormat);
        }

        javaObject = mapper.readValue(jp, objectType);
        return (T) javaObject;
    }

    public static <T> T getJavaObject(InputStream is, Class<T> objectType) throws IOException {
        return getJavaObject(null, is, objectType);
    }

    public static <T> T getJavaObject(ObjectMapper desigMapper, InputStream is, Class<T> objectType) throws IOException {
        T javaObject;
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jp = jsonFactory.createParser(is);

        ObjectMapper mapper = ObjectMapperHelper.tryObtainObjectMapper(desigMapper, allowJavaTime); // new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (dateTimePattern != null) {
            DateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
            mapper.setDateFormat(dateFormat);
        }

        javaObject = mapper.readValue(jp, objectType);
        return javaObject;
    }

    public static <T> List<T> getJavaListObject(String json, Class<T> objectType) throws IOException {
        return getJavaListObject(null, json, objectType);
    }

    /**
     * type1 to get list object with jackson
     *
     * @param desigMapper ObjectMapper 是Jackson Lib轉換JSON用的，可以加入模組。
     * @param json
     * @param objectType
     * @return
     */
    public static <T> List<T> getJavaListObject(ObjectMapper desigMapper, String json, Class<T> objectType) throws IOException {
        if (json == null) {
            return null;
        }

        List<T> javaListObject;
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jp = jsonFactory.createParser(json);
//        ObjectMapper mapper = new ObjectMapper();
        /*
        https://stackoverflow.com/questions/27952472/serialize-deserialize-java-8-java-time-with-jackson-json-mapper
        https://github.com/FasterXML/jackson-modules-java8/wiki#artifacts  --> jackson-datatype-jsr310
        https://github.com/FasterXML/jackson-modules-java8/tree/2.14/datetime

        範例
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        
         */
        ObjectMapper mapper = ObjectMapperHelper.tryObtainObjectMapper(desigMapper, allowJavaTime);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (dateTimePattern != null) {
            DateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
            mapper.setDateFormat(dateFormat);
        }

        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, objectType);
        javaListObject = mapper.readValue(jp, collectionType);
        return javaListObject;
    }

    public static <T> T getJavaMap(String json, Class keyType, Class valueType) throws IOException {
        if (json == null) {
            return null;
        }

        T javaObject;
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
        javaObject = getJavaObject(json, javaType);
        return javaObject;
    }

    public static <Tmap, T> Map<Tmap, T> getJavaMapObject(String json, Class<Tmap> mapKeyType, Class<T> objectType) throws IOException {
        if (json == null) {
            return null;
        }

        Map<Tmap, T> javaListObject;
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jp = jsonFactory.createParser(json);
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (dateTimePattern != null) {
            DateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
            mapper.setDateFormat(dateFormat);
        }

        MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, mapKeyType, objectType);
        javaListObject = mapper.readValue(jp, mapType);
        return javaListObject;
    }

    public static Object getJavaComplexObject(String json, TypeReference typeRef) throws IOException {
        if (json == null) {
            return null;
        }

        Object javaObject;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (dateTimePattern != null) {
            DateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
            mapper.setDateFormat(dateFormat);
        }

        javaObject = mapper.readValue(json, typeRef);
        return javaObject;
    }

    private static <T> T getJavaObject(String json, JavaType javaType) throws IOException {
        if (json == null) {
            return null;
        }

        T javaObject;
        ObjectMapper mapper = new ObjectMapper();

        if (dateTimePattern != null) {
            DateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
            mapper.setDateFormat(dateFormat);
        }

        javaObject = mapper.readValue(json, javaType);
        return javaObject;
    }

    public static <T> T convertMapToObject(Map jsonMap, Class<T> objectType) throws JsonProcessingException, IOException {
        String strMapJson = JavaToJson.getJsonString(jsonMap);
        return JsonToJava.getJavaObject(strMapJson, objectType);
    }

    public static List<Map> convertListObjectToListMap(List objList) throws JsonProcessingException, IOException {
        String strObjListKson = JavaToJson.getJsonString(objList);
        return JsonToJava.getJavaListObject(strObjListKson, Map.class);
    }

}
