/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import tw.dev.tomoaki.util.commondatavalidator.ListValidator;

/**
 *
 * @author tomoaki
 */
public abstract class AbstractQueryFacade<T> {

    public static Boolean EVICT_CACHE = Boolean.TRUE;

    protected Class<T> entityClass;

    protected abstract EntityManager getEntityManager();

    public AbstractQueryFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void flush() {
        this.getEntityManager().flush();
    }

    public T find(Object id) {
        EntityManager em = getEntityManager();
        this.tryEvictCache(em, id);
        return em.find(entityClass, id);
    }

    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的) Property名稱 」
     * @return 查詢結果
     */
    public List<T> findAllAscOrdered(String entityPropName) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);
        cq.orderBy(cb.asc(root.get(entityPropName)));

        Query query = this.getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    public List<T> findAll() {
        EntityManager em = getEntityManager();
        this.tryEvictAllCache(em);
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

//<editor-fold defaultstate="collapsed" desc="findByEqual系列">
    /*
    https://stackoverflow.com/questions/39741718/java-lang-illegalargumentexception-the-attribute-state-id-is-not-present-in-t 
    > The querying uses by default java names. If you have doubt you can setup a jpa metamodel generator and you will be able to use a typed version of the attributes instead of strings.
    
     */
    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的) Property名稱 」
     * @param value Table Column (即 Entity 之 Property)數值
     * @return 查詢結果
     */
    public List<T> findByEquals(String entityPropName, Object value) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        this.tryEvictAllCache(em);
        Root<T> root = cq.from(entityClass);
        cq = cq.where(cb.notEqual(root.get(entityPropName), value));
        cq = cq.orderBy(cb.asc(root.get(entityPropName)));

        Query query = em.createQuery(cq);
        return query.getResultList();
    }

    
    public List<T> findByEquals(List<String> entityPropNameList, List<Object> valueList) {
        return this.findByEquals(entityPropNameList, valueList, null);
    }    
    
    
    /**
     *
     * @param entityPropNameList 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的) Property名稱 」 ，Property Name  清單
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */    
    public List<T> findByEquals(List<String> entityPropNameList, List<Object> valueList, List<String> orderEntityPropNameList) {
        if (!ListValidator.isListExist(entityPropNameList)) {
            throw new IllegalArgumentException("entityPropNameList= %s Is Null Or Empty");
        }

        if (!ListValidator.isListExist(valueList)) {
            throw new IllegalArgumentException("valueList= %s Is Null Or Empty");
        }

        Integer length4ColNameList = entityPropNameList.size();
        Integer legnth4ColValueList = valueList.size();
        if (!Objects.equals(length4ColNameList, legnth4ColValueList)) {
            throw new IllegalArgumentException("entityPropertyNameList= %s, valueList= %s, There Length Is Not Equal");
        }

        List<KeyValuePair> pairList = new ArrayList();
        for (Integer index = 0; index < legnth4ColValueList; index++) {
            String propName = entityPropNameList.get(index);
            Object colValue = valueList.get(index);
            KeyValuePair pair = new KeyValuePair(propName, colValue);
            pairList.add(pair);
        }

        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);//orderEntityPropNameList;
        return this.findByEqualPairs(pairList, orderEntityPropNameList);

    }

    /**
     *
     * @param pairList 資料對應組
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */        
    public List<T> findByEqualPairs(List<KeyValuePair> pairList, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        List<Expression> expressionList = new ArrayList();
        for (KeyValuePair pair : pairList) {
            String propName = pair.getColName();
            Object value = pair.getColValue();
            Expression expression = cb.equal(root.get(propName), value); //public interface Predicate extends Expression<Boolean> {
            expressionList.add(expression);
        }

        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);//pairList.stream().map(pair -> pair.getColName()).collect(Collectors.toList());

        List<Order> orderList = orderEntityPropNameList.stream()
                .map(entityPropName -> cb.asc(root.get(entityPropName)))
                .collect(Collectors.toList());

//        轉call 更底層
//        Query query = em.createQuery(cq);
//        return query.getResultList();
        return this.findBy(expressionList, orderList);
    }

    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的) Property名稱 」
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @return 查詢結果
     */     
    public List<T> findByEqualsAny(String entityPropName, List<Object> valueList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root).where(root.get(entityPropName).in(valueList));
//        Expression expression = cb.any(sbqr)
        throw new UnsupportedOperationException("Method Not Supported Yet");
    }    
//</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="IN 系列">
    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的) Property名稱 」
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @return 查詢結果
     */         
    public List<T> findIn(String entityPropName, List<?> valueList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);
        cq = cq.where(root.get(entityPropName).in(valueList));
        cq = cq.orderBy(cb.asc(root.get(entityPropName)));

        Query query = em.createQuery(cq);
        return query.getResultList();
    }    
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="Between 或類似「範圍之內」">
    
    public List<T> findInDateTimeRange(String entityPropName4Since, String entityPropName4Until, LocalDateTime desigDateTime) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

//        cq.select(root).where(prdcts)
        throw new UnsupportedOperationException("Method Not Supported Yet");
    }
    
//    public List<T> findBetweenNotNullRange(String entityPropName, Number start, Number end) {
//        EntityManager em = this.getEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
//        Root<T> root = cq.from(entityClass);
//        Expression expression = cb.between(root.get(entityPropName), start, end);
//        cq = cq.select(root).where(expression);
//        javax.persistence.Query query = getEntityManager().createQuery(cq);
//        return query.getResultList();
//    }
    public List<T> findBetweenNotNullRange(String entityPropName, Integer start, Integer end) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);
        Expression expression = cb.between(root.get(entityPropName), start, end);
        cq = cq.select(root).where(expression);
        javax.persistence.Query query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }
    
    public List<T> findBetweenNotNullRange(String entityPropName, Long start, Long end) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);
        Expression expression = cb.between(root.get(entityPropName), start, end);
        cq = cq.select(root).where(expression);
        javax.persistence.Query query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }
    
    public List<T> findBetweenNotNullRange(String entityPropName, Double start, Double end) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);
        Expression expression = cb.between(root.get(entityPropName), start, end);
        cq = cq.select(root).where(expression);
        javax.persistence.Query query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }        
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="findByNotEqual系列">
    
    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的) Property名稱 」
     * @param value Table Column (即 Entity 之 Property)數值
     * @return 查詢結果
     */
    public List<T> findByNotEquals(String entityPropName, Object value) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        this.tryEvictAllCache(em);
        Root<T> root = cq.from(entityClass);
        cq = cq.where(cb.notEqual(root.get(entityPropName), value));
        cq = cq.orderBy(cb.asc(root.get(entityPropName)));

        Query query = em.createQuery(cq);
        return query.getResultList();
    }    
    
    public List<T> findByNotEquals(List<String> entityPropNameList, List<Object> valueList) {
        return this.findByNotEquals(entityPropNameList, valueList, null);
    }      
    
    /**
     *
     * @param entityPropNameList 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的) Property名稱 」 ，Property Name  清單
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */    
    public List<T> findByNotEquals(List<String> entityPropNameList, List<Object> valueList, List<String> orderEntityPropNameList) {
        if (!ListValidator.isListExist(entityPropNameList)) {
            throw new IllegalArgumentException("entityPropNameList= %s Is Null Or Empty");
        }

        if (!ListValidator.isListExist(valueList)) {
            throw new IllegalArgumentException("valueList= %s Is Null Or Empty");
        }

        Integer length4ColNameList = entityPropNameList.size();
        Integer legnth4ColValueList = valueList.size();
        if (!Objects.equals(length4ColNameList, legnth4ColValueList)) {
            throw new IllegalArgumentException("entityPropertyNameList= %s, valueList= %s, There Length Is Not Equal");
        }

        List<KeyValuePair> pairList = new ArrayList();
        for (Integer index = 0; index < legnth4ColValueList; index++) {
            String propName = entityPropNameList.get(index);
            Object colValue = valueList.get(index);
            KeyValuePair pair = new KeyValuePair(propName, colValue);
            pairList.add(pair);
        }

        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);//orderEntityPropNameList;
        return this.findByNotEqualPairs(pairList, orderEntityPropNameList);

    }
    
    /**
     *
     * @param pairList 資料對應組
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */        
    public List<T> findByNotEqualPairs(List<KeyValuePair> pairList, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);
        
        List<Expression> expressionList = new ArrayList();
        for (KeyValuePair pair : pairList) {
            String propName = pair.getColName();
            Object value = pair.getColValue();
            Expression expression = cb.notEqual(root.get(propName), value); //public interface Predicate extends Expression<Boolean> {
            expressionList.add(expression);
        }

        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);

        List<Order> orderList = orderEntityPropNameList.stream()
                .map(entityPropName -> cb.asc(root.get(entityPropName)))
                .collect(Collectors.toList());

//        轉call 更底層
        return this.findBy(expressionList, orderList);
    }        
//</editor-fold>

    
    
//<editor-fold defaultstate="collapsed" desc="核心(?)，可以給無限(?)的查詢條件及排序調建">
    protected List<T> findBy(List<Expression> expressionList, List<Order> orderList) {
        EntityManager em = this.getEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);

        for (Expression exp : expressionList) {
            cq = cq.where(exp);
        }

        cq = cq.orderBy(orderList);

        Query query = em.createQuery(cq);
        return query.getResultList();

    }    
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="getByEquals 系列">
    public T getByEquals(String columnName, Object columnValue) {
        List<T> resultList = this.findByEquals(columnName, columnValue);
        return (resultList == null || resultList.isEmpty()) ? null : resultList.get(0);
    }
    
    public T getByEquals(List<String> columnNameList, List<Object> columnValueList) {
        List<T> resultList = this.findByEquals(columnNameList, columnValueList);
        return (resultList == null || resultList.isEmpty()) ? null : resultList.get(0);
        
    }
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="其他輔助methods">
    private void tryEvictCache(EntityManager em, Object id) {
        if (EVICT_CACHE) {
            em.getEntityManagerFactory().getCache().evict(entityClass, id);
        }
    }

    private void tryEvictAllCache(EntityManager em) {
        if (EVICT_CACHE) {
            em.getEntityManagerFactory().getCache().evictAll();
        }
    }    
    
    private List<String> obtainEntityPropNameList(List<KeyValuePair> pairList) {
        return pairList.stream().map(pair -> pair.getColName()).collect(Collectors.toList());
    }
//</editor-fold>    
}
