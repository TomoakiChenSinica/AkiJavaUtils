/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web.webservice;

import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author Tomoaki Chen
 */
/**
 *
 * @author Tomoaki Chen
 */
public abstract class BasicRestClient {
//<editor-fold defaultstate="collapsed" desc="一些共用的底層">

    //<editor-fold defaultstate="collapsed" desc="doGet">
    protected Response doGet(WebTarget target, MediaType acceptMediaType) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doGet(target, acceptMediaTypes, null, null);
    }

    protected Response doGet(WebTarget target, MediaType[] acceptMediaTypes) {
        return this.doGet(target, acceptMediaTypes, null, null);
    }

    protected Response doGet(WebTarget target, MediaType acceptMediaType, String bearerToken) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doGet(target, acceptMediaTypes, bearerToken, null);
    }

    protected Response doGet(WebTarget target, MediaType acceptMediaType, String bearerToken, List<String> cookies) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doGet(target, acceptMediaTypes, bearerToken, cookies);
    }

    protected Response doGet(WebTarget target, MediaType[] acceptMediaTypes, String bearerToken, List<String> cookies) {
        Invocation.Builder builder = target.request().accept(acceptMediaTypes);
        if (bearerToken != null) {
            builder.header("Authorization", "Bearer " + bearerToken);
        }

        if (cookies != null) {
            for (String cookie : cookies) {
                builder.header("Cookie", cookie);
            }
        }
        return builder.get();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="doPut()">    
    protected <T> Response doPut(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPut(target, acceptMediaTypes, requestMediaType, null, entity);
    }

    protected <T> Response doPut(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPut(target, acceptMediaTypes, requestMediaType, bearerToken, null, entity);
    }

    protected <T> Response doPut(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken, List<String> cookies, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPut(target, acceptMediaTypes, requestMediaType, bearerToken, cookies, entity);
    }

    protected <T> Response doPut(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, T entity) {
        return this.doPut(target, acceptMediaTypes, requestMediaType, null, null, entity);
    }

    protected <T> Response doPut(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, List<String> cookies, T entity) {
        return this.doPut(target, acceptMediaTypes, requestMediaType, null, cookies, entity);
    }

    protected <T> Response doPut(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, String bearerToken, List<String> cookies, T entity) {
        Invocation.Builder builder = target.request().accept(acceptMediaTypes);
        if (bearerToken != null) {
            builder.header("Authorization", "Bearer " + bearerToken);
        }

        if (cookies != null) {
            for (String cookie : cookies) {
//                System.out.println("Cookie[" + cookie + "]" );
                builder.header("Cookie", cookie);
            }
        }
        return builder.put(Entity.entity(entity, requestMediaType));
    }
    //</editor-fold>        

    //<editor-fold defaultstate="collapsed" desc="doPost()">    
    protected <T> Response doPost(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPost(target, acceptMediaTypes, requestMediaType, null, entity);
    }

    protected <T> Response doPost(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPost(target, acceptMediaTypes, requestMediaType, bearerToken, null, entity);
    }

    protected <T> Response doPost(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, T entity) {
        return this.doPost(target, acceptMediaTypes, requestMediaType, null, null, entity);
    }

    protected <T> Response doPost(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, List<String> cookies, T entity) {
        return this.doPost(target, acceptMediaTypes, requestMediaType, null, cookies, entity);
    }

    protected <T> Response doPost(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken, List<String> cookies, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPost(target, acceptMediaTypes, requestMediaType, bearerToken, cookies, entity);
    }

    protected <T> Response doPost(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, String bearerToken, List<String> cookies, T entity) {
        Invocation.Builder builder = target.request().accept(acceptMediaTypes);
        if (bearerToken != null) {
            builder.header("Authorization", "Bearer " + bearerToken);
        }

        if (cookies != null) {
            for (String cookie : cookies) {
//                System.out.println("Cookie[" + cookie + "]" );
                builder.header("Cookie", cookie);
            }
        }
        return builder.post(Entity.entity(entity, requestMediaType));
    }
    //</editor-fold>            

    //<editor-fold defaultstate="collapsed" desc="doDelete()">    
    protected <T> Response doDelete(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType/*, T entity*/) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doDelete(target, acceptMediaTypes, requestMediaType, null/*, entity*/);
    }

    protected <T> Response doDelete(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken/*, T entity*/) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doDelete(target, acceptMediaTypes, requestMediaType, bearerToken, null/*, entity*/);
    }

    protected <T> Response doDelete(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType/*, T entity*/) {
        return this.doDelete(target, acceptMediaTypes, requestMediaType, null, null/*, entity*/);
    }

    protected <T> Response doDelete(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, List<String> cookies/*, T entity*/) {
        return this.doDelete(target, acceptMediaTypes, requestMediaType, null, cookies/*, entity*/);
    }

    protected <T> Response doDelete(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken, List<String> cookies/*, T entity*/) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doDelete(target, acceptMediaTypes, requestMediaType, bearerToken, cookies/*, entity*/);
    }

    protected <T> Response doDelete(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, String bearerToken, List<String> cookies/*, T entity*/) {
        Invocation.Builder builder = target.request().accept(acceptMediaTypes);
        if (bearerToken != null) {
            builder.header("Authorization", "Bearer " + bearerToken);
        }

        if (cookies != null) {
            for (String cookie : cookies) {
//                System.out.println("Cookie[" + cookie + "]" );
                builder.header("Cookie", cookie);
            }
        }
        return builder.delete();
    }
    //</editor-fold>         

//    protected Client processBasicAuth(Client client, String userName, String password) {
//        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().credentials(userName, password).build();
//        client.register(feature);
////        client.register(SseFeature.class);        
//        return client;
//    }

//<editor-fold defaultstate="collapsed" desc="以下等同於 JsonToJava">
//    protected static class JsonToJava {
//
//        public static <T> T getJavaObject(String json, Class<T> objectType) throws IOException {
//            if (json != null) {
//                T javaObject;
//                JsonFactory jsonFactory = new JsonFactory();
//                JsonParser jp = jsonFactory.createParser(json);
//                ObjectMapper mapper = new ObjectMapper();
//                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//                javaObject = mapper.readValue(jp, objectType);
//                return (T) javaObject;
//            } else {
//                return null;
//            }
//        }
//
//        public static <T> T getJavaObject(InputStream is, Class<T> objectType) throws IOException {
//            T javaObject;
//            JsonFactory jsonFactory = new JsonFactory();
//            JsonParser jp = jsonFactory.createParser(is);
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            javaObject = mapper.readValue(jp, objectType);
//            return javaObject;
//        }
//
//        /**
//         * type1 to get list object with jackson
//         *
//         * @param json
//         * @param objectType
//         * @return
//         */
//        public static <T> List<T> getJavaListObject(String json, Class<T> objectType) throws IOException {
//            List<T> javaListObject;
//            JsonFactory jsonFactory = new JsonFactory();
//            JsonParser jp = jsonFactory.createParser(json);
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, objectType);
//            javaListObject = mapper.readValue(jp, collectionType);
//            return javaListObject;
//        }
//
//        public static <Tmap, T> Map<Tmap, T> getJavaMapObject(String json, Class<Tmap> mapKeyType, Class<T> objectType) throws IOException {
//            Map<Tmap, T> javaListObject;
//            JsonFactory jsonFactory = new JsonFactory();
//            JsonParser jp = jsonFactory.createParser(json);
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, mapKeyType, objectType);
//            javaListObject = mapper.readValue(jp, mapType);
//            return javaListObject;
//        }
//
//        public static Object getJavaComplexObject(String json, TypeReference typeRef) throws IOException {
//            Object javaObject;
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            javaObject = mapper.readValue(json, typeRef);
//            return javaObject;
//        }
//    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="以下等同於 JavaToJson">   
//    protected static class JavaToJson {
//
//        /**
//         * 將 java object 轉成 json 字串
//         *
//         * @param javaObject : java 的 object <br/>
//         * @return 回傳該object 轉成 json 後的樣子，存成字串
//         */
//        public static String getJsonString(Object javaObject) throws JsonProcessingException {
//            String json = getJsonString(javaObject, true, true);
//            return json;
//        }
//
//        /**
//         * 將 java object 轉成 json 字串
//         *
//         * @param javaObject : java 的 object <br/>
//         * @param ignoreNull : 是否將 null 的忽略，不轉成json
//         * @return 回傳該object 轉成 json 後的樣子，存成字串
//         */
//        public static String getJsonString(Object javaObject, Boolean objectIgnoreNull, Boolean mapIgnoreNull) throws JsonProcessingException {
//            String json = "";
//            ObjectMapper mapper = new ObjectMapper();
//
//            if (objectIgnoreNull == true) {
//                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//            }
//
//            Boolean mapWriteNullValue = !mapIgnoreNull;
//            mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, mapWriteNullValue);
////            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//
//            json = mapper.writeValueAsString(javaObject);
//            return json;
//        }
//
//        public static String getJsonString(Map javaMap) throws JsonProcessingException {
//            String json = "";
//            json = getJsonString(javaMap, true);
//            return json;
//        }
//
//        public static String getJsonString(Map javaMap, Boolean ignoreNull) throws JsonProcessingException {
//            Boolean mapWriteNullValue = !ignoreNull;
//            String json = "";
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, mapWriteNullValue);
//
//            json = mapper.writeValueAsString(javaMap);
//            return json;
//        }
//    }
//</editor-fold>    
//</editor-fold>    
}
