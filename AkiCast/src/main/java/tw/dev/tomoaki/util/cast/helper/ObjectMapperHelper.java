/*
 * Copyright 2023 tomoaki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tw.dev.tomoaki.util.cast.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static tw.dev.tomoaki.util.cast.JavaToJson.allowJavaTime;

/**
 *
 * @author tomoaki
 */
public class ObjectMapperHelper {

    public static ObjectMapper tryObtainObjectMapper(ObjectMapper userDesigMapper, Boolean allowJavaTime) {
        ObjectMapper mapper = (userDesigMapper != null) ? userDesigMapper : ((allowJavaTime) ? obtainSupportJavaTime() : new ObjectMapper());
        return mapper;
    }

    public static ObjectMapper obtainSupportJavaTime() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();

    }

    
//    public static JavaTimeModule obtainJavaLoca
    
//    public static <T extends ObjectMapper> T tryObtainObjectMapper(T userDesigMapper, Boolean allowJavaTime) {
//        
//        ObjectMapper mapper = (userDesigMapper != null) ? userDesigMapper : ((allowJavaTime) ? obtainSupportJavaTime() : new ObjectMapper());
//        return mapper;
//    }    
//    
//    public static ObjectMapper obtainSupportJavaTime() {
//        return JsonMapper.builder()
//                .addModule(new JavaTimeModule())
//                .build();
//        
//    }    
  
    
    
//    public static XmlMapper tryObtainObjectMapper(XmlMapper userDesigMapper, Boolean allowJavaTime) {
//        XmlMapper mapper = (userDesigMapper != null) ? userDesigMapper : ((allowJavaTime) ? obtainSupportJavaTime() : new ObjectMapper());
//        return mapper;
//    }
//
//    public static XmlMapper obtainSupportJavaTime() {
//        return JsonMapper.builder()
//                .addModule(new JavaTimeModule())
//                .build();
//
//    }    
}
