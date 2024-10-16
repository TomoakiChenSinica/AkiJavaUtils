/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.cast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import tw.dev.tomoaki.util.cast.helper.ObjectMapperHelper;

/**
 *
 * @author tomoaki 將 Java object 轉成json字串
 *
 */
public class JavaToJson {

    private final static String DEFAULT_DATETIME_PATTREN = "yyyy-MM-dd HH:mm";

    public static String dateTimePattern = DEFAULT_DATETIME_PATTREN;
    public static Boolean allowJavaTime = Boolean.FALSE;

    //mapper有一些參數，因此很有可能之後改成非static
    /**
     * 將 java object 轉成 json 字串
     *
     * @param javaObject : java 的 object <br>
     * @return 回傳該object 轉成 json 後的樣子，存成字串
     * @throws JsonProcessingException 轉換成 JSON 有錯時會跳出此Exception
     */
    public static String getJsonString(Object javaObject) throws JsonProcessingException {
        String json = getJsonString(null, javaObject, true, true);
        return json;
    }

    /**
     * 將 java object 轉成 json 字串
     *
     * @param desigMapper ObjectMapper 是此Lib轉換的關鍵，有些特殊Module可add進去
     * @param javaObject java 的 object <br>
     * @param objectIgnoreNull 是否將 null 的忽略，不轉成json <br>
     * @param mapIgnoreNull
     * @return 回傳該object 轉成 json 後的樣子，存成字串
     */
    public static String getJsonString(ObjectMapper desigMapper, Object javaObject, Boolean objectIgnoreNull, Boolean mapIgnoreNull) throws JsonProcessingException {
        ObjectMapper mapper = ObjectMapperHelper.tryObtainObjectMapper(desigMapper, allowJavaTime);
        if (objectIgnoreNull == true) {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        Boolean mapWriteNullValue = !mapIgnoreNull;
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, mapWriteNullValue);

        if(dateTimePattern != null) {
            DateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
            mapper.setDateFormat(dateFormat);
        }

        String json = mapper.writeValueAsString(javaObject);
        return json;
    }

    public static String getJsonString(Map<?, ?> javaMap) throws JsonProcessingException {
        String json = getJsonString(javaMap, true);
        return json;
    }

    public static String getJsonString(Map<?, ?> javaMap, Boolean ignoreNull) throws JsonProcessingException {
        return getJsonString(null, javaMap, ignoreNull);
    }

    public static String getJsonString(ObjectMapper desigMapper, Map<?, ?> javaMap, Boolean ignoreNull) throws JsonProcessingException {
        Boolean mapWriteNullValue = !ignoreNull;
        ObjectMapper mapper = ObjectMapperHelper.tryObtainObjectMapper(desigMapper, allowJavaTime);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, mapWriteNullValue);

//        DateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
//        mapper.setDateFormat(dateFormat);
        if(dateTimePattern != null) {
            DateFormat dateFormat = new SimpleDateFormat(dateTimePattern);
            mapper.setDateFormat(dateFormat);
        }

        String json = mapper.writeValueAsString(javaMap);
        return json;
    }
    


}
