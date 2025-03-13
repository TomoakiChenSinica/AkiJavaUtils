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
package tw.dev.tomoaki.jpa.query.criteria.helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import tw.dev.tomoaki.jpa.entity.KeyValuePair;

/**
 *
 * @author tomoaki
 */
public class ExpressionHelper {

    public static List<Expression> createEqualExpressionList(Root root, CriteriaBuilder cb, List<KeyValuePair> pairList) {
        List<Expression> expressionList = new ArrayList();
        for (KeyValuePair pair : pairList) {
            String propName = pair.getColName();
            Object value = pair.getColValue();
            Expression expression = ExpressionHelper.createEqualExpression(root, cb, propName, value); //cb.equal(root.get(propName), value); //public interface Predicate extends Expression<Boolean> {
            expressionList.add(expression);
        }
        return expressionList;
    }
    
    public static Expression createEqualExpression(Root root, CriteriaBuilder cb, String entityPropName, Object value) {
        return createEqualExpression(root, cb, entityPropName, value, true);
    }    
    
    public static Expression createEqualExpression(Root root, CriteriaBuilder cb, String entityPropName, Object value, Boolean valueNullAble) {
        /*if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Expression expression = cb.isNull(root.get(entityPropName));
             return expression;
        }
        return cb.equal(root.get(entityPropName), value);*/ 
        Path entityProp = root.get(entityPropName);
        return ExpressionHelper.createEqualExpression(root, cb, entityProp, value, valueNullAble);
    }
    
    public static Expression createEqualExpression(Root root, CriteriaBuilder cb, Path entityProp, Object value, Boolean valueNullAble) {
        if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Expression expression = cb.isNull(entityProp);
             return expression;
        }
        return cb.equal(entityProp, value);
    }    
    
    // -------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static Expression createNotEqualExpression(Root root, CriteriaBuilder cb, String entityPropName, Object value) {
        return createNotEqualExpression(root, cb, entityPropName, value, true);
    }       
    
    public static List<Expression> createNotEqualExpressionList(Root root, CriteriaBuilder cb, List<KeyValuePair> pairList) {
        List<Expression> expressionList = new ArrayList();
        for (KeyValuePair pair : pairList) {
            String propName = pair.getColName();
            Object value = pair.getColValue();
            Expression expression = ExpressionHelper.createNotEqualExpression(root, cb, propName, value); //cb.equal(root.get(propName), value); //public interface Predicate extends Expression<Boolean> {
            expressionList.add(expression);
        }
        return expressionList;
    }      
    
    public static Expression createNotEqualExpression(Root root, CriteriaBuilder cb, String entityPropName, Object value, Boolean valueNullAble) {
        Path entityProp = root.get(entityPropName);
        return ExpressionHelper.createNotEqualExpression(root, cb, entityProp, value, valueNullAble);
    }
    
    public static Expression createNotEqualExpression(Root root, CriteriaBuilder cb, Path entityProp, Object value, Boolean valueNullAble) {
        if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Expression expression = cb.isNotNull(entityProp);
             return expression;
        }
        return cb.notEqual(entityProp, value);
    }    
    
    // -------------------------------------------------------------------------------------------------------------------------------------------------------
           
    public static <VALUE extends Comparable> Expression createLessThanOrEqualToExpression(Root root, CriteriaBuilder cb, String entityPropName, VALUE value) {
        return ExpressionHelper.createLessThanOrEqualToExpression(root, cb, entityPropName, value, true);
    }       
    
    public static <VALUE extends Comparable> Expression createLessThanOrEqualToExpression(Root root, CriteriaBuilder cb, String entityPropName, VALUE value, Boolean valueNullAble) {
        Path entityProp = root.get(entityPropName);
        return ExpressionHelper.createLessThanOrEqualToExpression(root, cb, entityProp, value, valueNullAble);
    }
    
    public static <VALUE extends Comparable> Expression createLessThanOrEqualToExpression(Root root, CriteriaBuilder cb, Path entityProp, VALUE value, Boolean valueNullAble) {
        // 指定 VALUE extends Comparable，編譯就 OK
        if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Expression expression = cb.isNotNull(entityProp);
             return expression;
        }
        return cb.lessThanOrEqualTo(entityProp, value);
    }    
    
    // -------------------------------------------------------------------------------------------------------------------------------------------------------    
    
    public static <VALUE extends Comparable> Expression createLessThanExpression(Root root, CriteriaBuilder cb, String entityPropName, VALUE value) {
        return ExpressionHelper.createLessThanExpression(root, cb, entityPropName, value, true);
    }      
    
    public static <VALUE extends Comparable> Expression createLessThanExpression(Root root, CriteriaBuilder cb, String entityPropName, VALUE value, Boolean valueNullAble) {
        Path enttiyProp = root.get(entityPropName); // Expression enttiyProp = root.get(entityPropName);
        return ExpressionHelper.createLessThanExpression(root, cb, enttiyProp, value, valueNullAble);
    }
    
    public static <VALUE extends Comparable> Expression createLessThanExpression(Root root, CriteriaBuilder cb, Path entityProp, VALUE value, Boolean valueNullAble) {
        if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Expression expression = cb.isNotNull(entityProp);
             return expression;
        }
        return cb.lessThan(entityProp, value);
    }    
    
    // -------------------------------------------------------------------------------------------------------------------------------------------------------
           
    public static <VALUE extends Comparable> Expression createGreaterThanOrEqualToExpression(Root root, CriteriaBuilder cb, String entityPropName, VALUE value) {
        return ExpressionHelper.createGreaterThanOrEqualToExpression(root, cb, entityPropName, value, true);
    }    
    
    public static <VALUE extends Comparable> Expression createGreaterThanOrEqualToExpression(Root root, CriteriaBuilder cb, String entityPropName, VALUE value, Boolean valueNullAble) {
        Path enttiyProp = root.get(entityPropName);
        return ExpressionHelper.createGreaterThanOrEqualToExpression(root, cb, enttiyProp, value, valueNullAble);
    } 
    
    public static <VALUE extends Comparable> Expression createGreaterThanOrEqualToExpression(Root root, CriteriaBuilder cb, Path<VALUE> entityProp, VALUE value, Boolean valueNullAble) {
        // 指定 VALUE extends Comparable，編譯就 OK
        if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Expression expression = cb.isNotNull(entityProp);
             return expression;
        }
        return cb.greaterThanOrEqualTo(entityProp, value);
    }     
    
    // -------------------------------------------------------------------------------------------------------------------------------------------------------    
    
    public static <VALUE extends Comparable> Expression createGreaterThanExpression(Root root, CriteriaBuilder cb, String entityPropName, VALUE value) {
        return ExpressionHelper.createGreaterThanExpression(root, cb, entityPropName, value, true);
    }
    
    public static <VALUE extends Comparable> Expression createGreaterThanExpression(Root root, CriteriaBuilder cb, String entityPropName, VALUE value, Boolean valueNullAble) {
        Path entityProp = root.get(entityPropName);
        return ExpressionHelper.createGreaterThanExpression(root, cb, entityProp, value, valueNullAble);
    }
    
    public static <VALUE extends Comparable> Expression createGreaterThanExpression(Root root, CriteriaBuilder cb, Path<VALUE> entityProp, VALUE value, Boolean valueNullAble) {
        // [FIXME202501081617] Path 不指定 VALUE 似乎也沒差? 猜測跟 Type Erasure 有關?
        if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Expression expression = cb.isNotNull(entityProp);
             return expression;
        }
        return cb.greaterThan(entityProp, value);
    }      
    
    // -------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static Expression createAfterDateTimeExpression(Root root, CriteriaBuilder cb, Path<LocalDateTime> entityProp, LocalDateTime startDateTime, Boolean valueNullAble) {
        // return cb.greaterThanOrEqualTo(entityProp, startDateTime);
        return ExpressionHelper.createGreaterThanOrEqualToExpression(root, cb, entityProp, startDateTime, valueNullAble);
    }
    
    public static Expression createBeforeDateTimeExpression(Root root, CriteriaBuilder cb, Path<LocalDateTime> entityProp, LocalDateTime endDateTime, Boolean valueNullAble) {
        // return cb.lessThanOrEqualTo(entityProp, endDateTime);
        return ExpressionHelper.createLessThanOrEqualToExpression(root, cb, entityProp, endDateTime, valueNullAble);
    }    
    
    // -------------------------------------------------------------------------------------------------------------------------------------------------------    
    
//<editor-fold defaultstate="collapsed" desc="產生 Predicate，Predicate 是繼承於 Expression 的(也就是說是它一種 Expression?)">
    public static Predicate createEqualPredicate(Root root, CriteriaBuilder cb, String entityPropName, Object value) {
        return createEqualPredicate(root, cb, entityPropName, value, true);
    }    
    
    public static Predicate createEqualPredicate(Root root, CriteriaBuilder cb, String entityPropName, Object value, Boolean valueNullAble) {
        if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Predicate Predicate = cb.isNull(root.get(entityPropName));
             return Predicate;
        }
        return cb.equal(root.get(entityPropName), value);
    }
    
    public static Predicate createNotEqualPredicate(Root root, CriteriaBuilder cb, String entityPropName, Object value) {
        return createNotEqualPredicate(root, cb, entityPropName, value, true);
    }       
    
    public static Predicate createNotEqualPredicate(Root root, CriteriaBuilder cb, String entityPropName, Object value, Boolean valueNullAble) {
        if(!valueNullAble && value == null) {
            throw new IllegalArgumentException("value Is Null");
        }
        
        if(value == null) {
             Predicate Predicate = cb.isNotNull(root.get(entityPropName));
             return Predicate;
        }
        return cb.notEqual(root.get(entityPropName), value);
    }
        
//</editor-fold>   
    
//    public static <T>Predicate convert2Predicate(Expression expression) {
//        
//    }
}
