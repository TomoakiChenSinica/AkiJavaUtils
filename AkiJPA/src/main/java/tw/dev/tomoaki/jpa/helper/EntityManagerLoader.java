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
package tw.dev.tomoaki.jpa.helper;

import java.util.ServiceLoader;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tomoaki
 */
public class EntityManagerLoader {

//    public static String getUnitName() {
//        EntityManagerFactory factory = loadEntityManagerFactory();
//        System.out.println(String.format("[EntityManagerLoader] getUnitName(): factory= %s", factory));
//        if(factory != null) {
//            return factory.getProperties().get("javax.persistence.unit.name").toString();
//        }
//        return null;
//    }
//    
//    public static EntityManagerFactory loadEntityManagerFactory() {
//        System.out.println(String.format("[EntityManagerLoader] persistenceUnit= %s", Persistence.getPersistenceUtil().));
//        return ServiceLoader.load(EntityManagerFactory.class).findFirst().orElse(null);        
//    }
}
