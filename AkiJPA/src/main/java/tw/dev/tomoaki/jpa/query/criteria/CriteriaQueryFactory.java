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
package tw.dev.tomoaki.jpa.query.criteria;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author tomoaki
 */
public class CriteriaQueryFactory<T> {

    private EntityManager em;
    private Class<T> entityClass;
    private CriteriaBuilder cb;

//    private Class<T> entityClass;
    private CriteriaQuery<T> cq;
    private Root<T> root;

    protected CriteriaQueryFactory() {
    }

    public static class Factory {

        public static <T> CriteriaQueryFactory create(EntityManager em, Class<T> entityClass) {
            CriteriaQueryFactory queryFactory = new CriteriaQueryFactory();
            queryFactory.em = em;
            queryFactory.entityClass = entityClass;
            return queryFactory;
        }
    }

}
