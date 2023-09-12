/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa;

import tw.dev.tomoaki.jpa.entity.KeyValuePair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import tw.dev.tomoaki.util.commondatavalidator.ListValidator;

/**
 *
 * @author tomoaki
 * @deprecated 可能是舊的
 */
public abstract class AbstractQueryColumnNameFacade<T> {

//    public static Boolean EVICT_CACHE = Boolean.TRUE;
//
//    protected Class<T> entityClass;
//
//    protected abstract EntityManager getEntityManager();
//
//    public AbstractQueryColumnNameFacade(Class<T> entityClass) {
//        this.entityClass = entityClass;
//    }
//
//    public void flush() {
//        this.getEntityManager().flush();
//    }
//
//    public T find(Object id) {
//        EntityManager em = getEntityManager();
//        this.tryEvictCache(em, id);
//        return em.find(entityClass, id);
//    }
//
//    public List<T> findAllAscOrdered(String columnName) {
//        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
//        Root<T> root = cq.from(entityClass);
//        cq.orderBy(cb.asc(root.get(columnName.toLowerCase())));
//
//        Query query = this.getEntityManager().createQuery(cq);
//        return query.getResultList();
//    }
//
//    public List<T> findAll() {
//        EntityManager em = getEntityManager();
//        this.tryEvictAllCache(em);
//        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        return getEntityManager().createQuery(cq).getResultList();
//    }
//
//    public List<T> findRange(int[] range) {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//        return q.getResultList();
//    }
//
//    public int count() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
//        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        return ((Long) q.getSingleResult()).intValue();
//    }
//
////<editor-fold defaultstate="collapsed" desc="Equal系列">
//    
//    /*
//    https://stackoverflow.com/questions/39741718/java-lang-illegalargumentexception-the-attribute-state-id-is-not-present-in-t 
//    > The querying uses by default java names. If you have doubt you can setup a jpa metamodel generator and you will be able to use a typed version of the attributes instead of strings.
//    
//        */
//    
//    
//    public List<T> findByEquals(String columnName, Object columnValue) {
//        EntityManager em = this.getEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
//        this.tryEvictAllCache(em);
//        Root<T> root = cq.from(entityClass);
//        cq = cq.where(cb.equal(root.get(columnName.toLowerCase()), columnValue));
//        cq = cq.orderBy(cb.asc(root.get(columnName.toLowerCase())));
//
//        Query query = em.createQuery(cq);
//        return query.getResultList();
//    }
//    
//    public List<T> findByEquals(List<String> columnNameList, List<Object> columnValueList) {
//        return this.findByEquals(columnNameList, columnValueList, null);
//    }    
//
//    public List<T> findByEquals(List<String> columnNameList, List<Object> columnValueList, List<String> orderColNameList) {
//        if (!ListValidator.isListExist(columnNameList)) {
//            throw new IllegalArgumentException("columnNameList= %s Is Null Or Empty");
//        }
//
//        if (!ListValidator.isListExist(columnValueList)) {
//            throw new IllegalArgumentException("columnNameList= %s Is Null Or Empty");
//        }
//
//        Integer length4ColNameList = columnNameList.size();
//        Integer legnth4ColValueList = columnValueList.size();
//        if (!Objects.equals(length4ColNameList, legnth4ColValueList)) {
//            throw new IllegalArgumentException("columnNameList= %s, columnValueList= %s, There Length Is Not Equal");
//        }
//
//        List<KeyValuePair> pairList = new ArrayList();
//        for (Integer index = 0; index < legnth4ColValueList; index++) {
//            String colName = columnNameList.get(index);
//            Object colValue = columnValueList.get(index);
//            KeyValuePair pair = new KeyValuePair(colName, colValue);
//            pairList.add(pair);
//        }
//
//        orderColNameList = ListValidator.isListExist(orderColNameList) ? orderColNameList : columnNameList;
//        return this.findByEqualPairs(pairList, orderColNameList);
//
//    }
//
//    public List<T> findByEqualPairs(List<KeyValuePair> pairList, List<String> orderColNameList) {
//        EntityManager em = this.getEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
//        Root<T> root = cq.from(entityClass);
//
//        List<Expression> expressionList = new ArrayList();
//        for (KeyValuePair pair : pairList) {
//            String columnName = pair.getColName();
//            Object value = pair.getColValue();
//            Expression expression = cb.equal(root.get(columnName.toLowerCase()), value); //public interface Predicate extends Expression<Boolean> {
//            expressionList.add(expression);
//        }
//
//        orderColNameList = ListValidator.isListExist(orderColNameList) ? orderColNameList : pairList.stream().map(pair -> pair.getColName()).collect(Collectors.toList());
//
//        List<Order> orderList = orderColNameList.stream()
//                .map(columnName -> cb.asc(root.get(columnName.toLowerCase())))
//                .collect(Collectors.toList());
//
////        cq = cq.orderBy( cb.asc(root.get( orderColNameList.get(0).toLowerCase()) ) ) ;
////        轉call 更底層
////        Query query = em.createQuery(cq);
////        return query.getResultList();
//        return this.findBy(expressionList, orderList);
//    }
//
//    public List<T> findByEqualsAny(String columnName, List<Object> columnValueList) {
//        EntityManager em = this.getEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
//        Root<T> root = cq.from(entityClass);
//        cq.select(root).where(root.get(columnName).in(columnValueList));
////        Expression expression = cb.any(sbqr)
//        throw new UnsupportedOperationException("Method Not Supported Yet");
//    }
////</editor-fold>   
//    
//    public List<T> findIn(String columnName, List<?> columnValueList) {
//        EntityManager em = this.getEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
//        Root<T> root = cq.from(entityClass);
////        Expression expression = root.get(columnName.toLowerCase()).in(columnValueList);
//        cq = cq.where(root.get(columnName.toLowerCase()).in(columnValueList));
//        cq = cq.orderBy(cb.asc(root.get(columnName.toLowerCase())));
//        
//        Query query = em.createQuery(cq);
//        return query.getResultList();
//    }
//
//    protected List<T> findBy(List<Expression> expressionList, List<Order> orderList) {
//        EntityManager em = this.getEntityManager();
//        em.getEntityManagerFactory().getCache().evictAll();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
//
//        for (Expression exp : expressionList) {
//            cq = cq.where(exp);
//        }
//
//        cq = cq.orderBy(orderList);
//
//        Query query = em.createQuery(cq);
//        return query.getResultList();
//
//    }
//    
//
//    
//
//    private void tryEvictCache(EntityManager em, Object id) {
//        if (EVICT_CACHE) {
//            em.getEntityManagerFactory().getCache().evict(entityClass, id);
//        }
//    }
//
//    private void tryEvictAllCache(EntityManager em) {
//        if (EVICT_CACHE) {
//            em.getEntityManagerFactory().getCache().evictAll();
//        }
//    }
}
