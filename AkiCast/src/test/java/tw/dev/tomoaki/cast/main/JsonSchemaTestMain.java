/*
 * Copyright 2024 tomoaki.
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
package tw.dev.tomoaki.cast.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import tw.dev.tomoaki.cast.main.entity.Batter;

/**
 *
 * @author tomoaki
 */
public class JsonSchemaTestMain {

    public static void main(String[] args) {
        try {
            test2();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected static void test1() throws JsonProcessingException {
        System.out.println(obtainPureSchema(Batter.class));
    }
    
    protected static void test2() throws JsonProcessingException {
        System.out.println(obtainFixedSchema(Batter.class));
    }

    
//<editor-fold defaultstate="collapsed" desc="以下為輔助 Methods">
    // https://github.com/FasterXML/jackson-module-jsonSchema/blob/master/javax/src/test/java/com/fasterxml/jackson/module/jsonSchema/TestGenerateJsonSchema.java#L136
    protected static String obtainPureSchema(Class<?> schemaEntityType) throws JsonMappingException, JsonProcessingException {
        //這種好像是「把定義寫在 Class 裡」 --> 應該不是，是利用 Entity 再加上程式產生 Schema
        ObjectMapper mapper = new ObjectMapper();
        // configure mapper, if necessary, then create schema generator
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
        JsonSchema schema = schemaGen.generateSchema(schemaEntityType);
        /*Map<String, JsonSchema> childSchemas = schema.asObjectSchema().getProperties();
        for (Entry<String, JsonSchema> childSchema : childSchemas.entrySet()) {
            String msgFmt = "Key= %s, Schema= %s";
            System.out.println(String.format(msgFmt, childSchema.getKey(), childSchema.getValue()));
        }*/
        return mapper.writeValueAsString(schema);
    }
    
    protected static String obtainFixedSchema(Class<?> schemaEntityType) throws JsonMappingException, JsonProcessingException {        
        ObjectMapper mapper = new ObjectMapper();
        // configure mapper, if necessary, then create schema generator
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
        JsonSchema schema = schemaGen.generateSchema(schemaEntityType);
        Map<String, JsonSchema> childSchemaMap = schema.asObjectSchema().getProperties();
        for (Entry<String, JsonSchema> childSchemaEntry : childSchemaMap.entrySet()) {
//            String msgFmt = "Key= %s, Schema= %s";
//            System.out.println(String.format(msgFmt, childSchemaEntry.getKey(), childSchemaEntry.getValue()));
            String msgFmt = "%s is %s";
            String propertyName = childSchemaEntry.getKey();
            UUID uuid = UUID.randomUUID();
            JsonSchema childSchema = childSchemaEntry.getValue();
            childSchema.setRequired(Boolean.TRUE);
            childSchema.setDescription(String.format(msgFmt, propertyName, uuid));
        }
        return mapper.writeValueAsString(schema);
    }       
//</editor-fold>
 

}
