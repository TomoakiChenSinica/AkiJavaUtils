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

import javax.persistence.EntityManager;

/**
 *
 * @author tomoaki
 */
public class JPAFacadeHelper {

// 後來發現 Facade 內已有    
//    public static void evictCache(EntityManager em, Class entityClass, Object entityId) {
//        em.getEntityManagerFactory().getCache().evict(entityClass, entityId);
//    }
//
//    public static void evictAllCache(EntityManager em, Class entityClass) {
//        em.getEntityManagerFactory().getCache().evictAll();
//    }

}
