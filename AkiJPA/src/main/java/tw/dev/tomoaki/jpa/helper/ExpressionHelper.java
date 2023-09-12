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
package tw.dev.tomoaki.jpa.helper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 *
 * @author tomoaki
 */
public class ExpressionHelper {
    
    public static Expression createEqualExpression(Root root, CriteriaBuilder cb, String entityPropName, Object value) {
        return createEqualExpression(root, cb, entityPropName, value, true);
    }    
    
    public static Expression createEqualExpression(Root root, CriteriaBuilder cb, String entityPropName, Object value, Boolean valueNullAble) {
        if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Expression expression = cb.isNull(root.get(entityPropName));
             return expression;
        }
        return cb.equal(root.get(entityPropName), value);
    }
    
    public static Expression createNotEqualExpression(Root root, CriteriaBuilder cb, String entityPropName, Object value) {
        return createNotEqualExpression(root, cb, entityPropName, value, true);
    }       
    
    public static Expression createNotEqualExpression(Root root, CriteriaBuilder cb, String entityPropName, Object value, Boolean valueNullAble) {
        if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Expression expression = cb.isNotNull(root.get(entityPropName));
             return expression;
        }
        return cb.notEqual(root.get(entityPropName), value);
    }    
}
