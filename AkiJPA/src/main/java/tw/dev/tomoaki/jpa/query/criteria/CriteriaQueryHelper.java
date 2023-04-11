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

import java.time.LocalDateTime;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

/**
 *
 * @author tomoaki
 */
public class CriteriaQueryHelper {

//    public Expression obtainInRangeExp(String attrName4LowerBound, attrName4UpperBound, LocalDateTime desigDateTime) {
//
//    }
//    public Expression obtainInRangeExp(Path lowerBound, attrName4UpperBound, LocalDateTime desigDateTime) {
//
//    }    
//    public <T>Expression obtainAfter(CriteriaBuilder cb, Root<T> tableRoot, Class<T> entityClass, LocalDateTime startDateTime) {
//        cb.e
//    }
//   public <T>Expression obtainAfter(CriteriaBuilder cb, Class<T> entityClass, Path<T> entityProp, LocalDateTime startDateTime) {
////        cb.ge(exprsn, exprsn1)  //Expression 需要是 extends Number 
//        
//    }    
    
    
    /* 比較OK 
    public <T> Expression obtainAfter(CriteriaBuilder cb, Class<T> entityClass, Path<LocalDateTime> entityProp, LocalDateTime startDateTime) {
        return cb.greaterThanOrEqualTo(entityProp, startDateTime);
    }
    
    public <T> Expression obtainBefore(CriteriaBuilder cb, Class<T> entityClass, Path<LocalDateTime> entityProp, LocalDateTime endDateTime) {
        return cb.lessThanOrEqualTo(entityProp, endDateTime);
    }
     */
    
    public <T> Expression obtainAfter(CriteriaBuilder cb, Path<LocalDateTime> entityProp, LocalDateTime startDateTime) {
        return cb.greaterThanOrEqualTo(entityProp, startDateTime);
    }
    
    public <T> Expression obtainBefore(CriteriaBuilder cb, Path<LocalDateTime> entityProp, LocalDateTime endDateTime) {
        return cb.lessThanOrEqualTo(entityProp, endDateTime);
    }    
}
