/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa;

import java.lang.reflect.InvocationTargetException;
import tw.dev.tomoaki.jpa.entity.KeyValuePair;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import tw.dev.tomoaki.jpa.exception.JPAFacadeException;
import tw.dev.tomoaki.jpa.helper.JPAEntityHelper;
import tw.dev.tomoaki.jpa.helper.JPAFacadeHelper;
import tw.dev.tomoaki.jpa.helper.KeyValuePairHelper;
import tw.dev.tomoaki.jpa.query.criteria.helper.ExpressionHelper;
import tw.dev.tomoaki.jpa.query.criteria.helper.OrderHelper;
import tw.dev.tomoaki.util.commondatavalidator.ListValidator;

/**
 *
 * @author tomoaki
 *
 * [JPA Criteria Queries](https://www.baeldung.com/hibernate-criteria-queries)
 * @param <T>
 *
 */
public abstract class AbstractQueryFacade<T> implements QueryFacade<T> {

    public static Boolean EVICT_CACHE = Boolean.FALSE;
    public static Boolean PRINT_LOG = Boolean.FALSE;

    protected Class<T> entityClass;

//    @Resource(lookup="UserTransaction")
//    private UserTransaction jtaTransaction;   

    public AbstractQueryFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }    
    
//<editor-fold defaultstate="collapsed" desc="取得資源(?)的 Methods">
    protected abstract EntityManager getEntityManager();

    protected UserTransaction getUserTransaction() {
        return null;
    }
//</editor-fold>
    


//<editor-fold defaultstate="collapsed" desc="最底層，也對應到 Postgres 一些底層">
    public T manage(T entity) {
        return this.isManaged(entity) ? entity : this.merge(entity);
    }

    /**
     * 將 entity 從 Detached 轉成 Managed， 所以要注意的是 entity 如果「非 Detached 」則沒用。
     *
     * 從 Eclipse 官網的討論串: https://www.eclipse.org/forums/index.php/t/499393/
     * ，有以下描述: The JPA specification requires that merge() returns the managed
     * version of the detached object (it must be a new reference).
     *
     *
     * @param entity 想要管控(manage)的資料
     *
     */
    public T /*void*/ merge(T entity) {
        return this.getEntityManager().merge(entity);
    }

    /**
     * 將 Managed 的資料存到 DB
     */
    public void flush() {
        this.getEntityManager().flush();
    }    
    
    // -------------------------------------------------------------------------
    
    public void commit() {
        UserTransaction jtaTransaction = getUserTransaction();
        if(jtaTransaction != null) {
            try {
                jtaTransaction.commit();
            } catch(Exception ex) {
                throw new JPAFacadeException(ex);
            }
        } else {
            this.getEntityManager().getTransaction().commit();
        }
    }

    
    /**
     * 最終會觸發 DB 本身的 Rollback 機制
     */
    public void rollback() {
        UserTransaction jtaTransaction = getUserTransaction();        
        if(jtaTransaction != null) {
            try {
                jtaTransaction.rollback();
            } catch(Exception ex) {
                throw new JPAFacadeException(ex);
            }
        } else {
            this.getEntityManager().getTransaction().rollback();            
        }
    }
//</editor-fold>

    /**
     * @param entity 要刷新的原資料庫映射的 enttiy  
     * @throws JPAFacadeException
     */
    public T refresh(T entity) {// void refresh(T entity) {
//        this.getEntityManager().refresh(entity);
        if (this.isManaged(entity)) {
            // JPA 中， entity 的 (JPA?) State 要是 Managed 才能 refresh
            this.getEntityManager().refresh(entity);
        } else {
            try {
                this.clear();
                entity = this.findSelf(entity);
                // System.err.println(String.format("[%s] entity= %s Is Not Managed, Cannot Be Refresh", getClass().getSimpleName(), entity));
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
                throw new JPAFacadeException(ex);
            }
        }
        return entity;
    }

    @Override
    public T find(Object id) {
        EntityManager em = getEntityManager();
        tryEvictCache(id);
        return em.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        if(EVICT_CACHE) {
            // JPAFacadeHelper.evictAllCache(em, entityClass);
            evictAllCache();
        }
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery(); // javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」
     * @return 查詢結果
     */
    @Override
    public List<T> findAllAscOrdered(String entityPropName) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        cq.orderBy(OrderHelper.createAscOrder(root, cb, entityPropName));

        Query query = this.getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<T> findAllAscOrdered(String... entityPropNames) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        cq.orderBy(OrderHelper.createAscOrderList(root, cb, entityPropNames));

        Query query = this.getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    /**
     *
     * @param entityPropNameList 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」
     * @return 查詢結果
     */
    @Override
    public List<T> findAllAscOrdered(List<String> entityPropNameList) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

//        List<Order> orderList = entityPropNameList.stream()
//                .map(propName -> cb.asc(root.get(propName)))
//                .collect(Collectors.toList());
        List<Order> orderList = OrderHelper.createAscOrderList(root, cb, entityPropNameList);
        cq.orderBy(orderList);

        Query query = this.getEntityManager().createQuery(cq);
        return query.getResultList();
    }
    
    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」
     * @return 查詢結果
     */
    @Override
    public List<T> findAllDescOrdered(String entityPropName) {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        cq.orderBy(OrderHelper.createDescOrder(root, cb, entityPropName));

        Query query = this.getEntityManager().createQuery(cq);
        return query.getResultList();
    }    
    

    @Override
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

//<editor-fold defaultstate="collapsed" desc="findByEqual系列">
    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」
     * @param value Table Column (即 Entity 之 Property)數值
     * @return 查詢結果
     */
    @Override
    public List<T> findByEquals(String entityPropName, Object value) {
        /*
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        this.tryEvictAllCache(em);
        Root<T> root = cq.from(entityClass);
        cq = cq.where(ExpressionHelper.createEqualExpression(root, cb, entityPropName, value));
        cq = cq.orderBy(cb.asc(root.get(entityPropName)));

        Query query = em.createQuery(cq);
        return query.getResultList();
         */
        List<String> orderEntityPropNameList = null;
        return this.findByEquals(entityPropName, value, orderEntityPropNameList);
    }

    @Override
    public List<T> findByEquals(String entityPropName, Object value, String... orderEntityPropNames) {
        /*
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        this.tryEvictAllCache(em);
        Root<T> root = cq.from(entityClass);
        cq = cq.where(ExpressionHelper.createEqualExpression(root, cb, entityPropName, value));
        cq = cq.orderBy(OrderHelper.createAscOrderList(root, cb, orderEntityPropNames));

        Query query = em.createQuery(cq);
        return query.getResultList();
         */
        return this.findByEquals(entityPropName, value, Arrays.asList(orderEntityPropNames));
    }

    @Override
    public List<T> findByEquals(String entityPropName, Object value, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        this.tryEvictAllCache();//(em);      
        Root<T> root = cq.from(entityClass);

        orderEntityPropNameList = (ListValidator.isListExist(orderEntityPropNameList)) ? orderEntityPropNameList : Arrays.asList(entityPropName);

        cq = cq.where(ExpressionHelper.createEqualExpression(root, cb, entityPropName, value));
        cq = cq.orderBy(OrderHelper.createAscOrderList(root, cb, orderEntityPropNameList));

        Query query = em.createQuery(cq);
        return query.getResultList();

    }

    @Override
    public List<T> findByNotEquals(String entityPropName, Object value) {
        /*
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        this.tryEvictAllCache(em);
        Root<T> root = cq.from(entityClass);
        
        cq = cq.where(ExpressionHelper.createNotEqualExpression(root, cb, entityPropName, value));
        cq = cq.orderBy(cb.asc(root.get(entityPropName)));

        Query query = em.createQuery(cq);
        return query.getResultList();
         */
        List<String> orderEntityPropNameList = null;
        return this.findByNotEquals(entityPropName, value, orderEntityPropNameList);
    }

    @Override
    public List<T> findByNotEquals(String entityPropName, Object value, String... orderEntityPropNames) {
        return this.findByNotEquals(entityPropName, value, Arrays.asList(orderEntityPropNames));
    }

    @Override
    public List<T> findByNotEquals(String entityPropName, Object value, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);        
        this.tryEvictAllCache(); //(em);
        Root<T> root = cq.from(entityClass);

        orderEntityPropNameList = (ListValidator.isListExist(orderEntityPropNameList)) ? orderEntityPropNameList : Arrays.asList(entityPropName);

        cq = cq.where(ExpressionHelper.createNotEqualExpression(root, cb, entityPropName, value));
        cq = cq.orderBy(OrderHelper.createAscOrderList(root, cb, orderEntityPropNameList));

        Query query = em.createQuery(cq);
        return query.getResultList();
    }

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="findByAndEqual系列">
    /*
    https://stackoverflow.com/questions/39741718/java-lang-illegalargumentexception-the-attribute-state-id-is-not-present-in-t 
    > The querying uses by default java names. If you have doubt you can setup a jpa metamodel generator and you will be able to use a typed version of the attributes instead of strings.
     */
    @Override
    public List<T> findByAndEquals(List<String> entityPropNameList, List<Object> valueList) {
        return this.findByAndEquals(entityPropNameList, valueList, null);
    }

    /**
     *
     * @param entityPropNameList 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」 ，Property Name 清單
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    @Override
    public List<T> findByAndEquals(List<String> entityPropNameList, List<Object> valueList, List<String> orderEntityPropNameList) {
        List<KeyValuePair> pairList = KeyValuePairHelper.obtainKeyValuePairList(entityPropNameList, valueList);
        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);//orderEntityPropNameList;
        return this.findByAndEqualPairs(pairList, orderEntityPropNameList);

    }

    /**
     *
     * @param pairList 資料對應組
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    @Override
    public List<T> findByAndEqualPairs(List<KeyValuePair> pairList, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        List<Expression> expressionList = ExpressionHelper.createEqualExpressionList(root, cb, pairList);

        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);//pairList.stream().map(pair -> pair.getColName()).collect(Collectors.toList());

        List<Order> orderList = orderEntityPropNameList.stream()
                .map(entityPropName -> cb.asc(root.get(entityPropName)))
                .collect(Collectors.toList());

        // 轉call 更底層
        return this.findByAnd(expressionList, orderList);
    }

    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @return 查詢結果
     */
    public List<T> findByEqualsAny(String entityPropName, List<Object> valueList) {
        throw new UnsupportedOperationException("Method Not Supported Yet");
        /*
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root).where(root.get(entityPropName).in(valueList));
         */
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="findByOrEqual系列">    
    @Override
    public List<T> findByOrEquals(List<String> entityPropNameList, List<Object> valueList) {
        return this.findByOrEquals(entityPropNameList, valueList, null);
    }

    /**
     *
     * @param entityPropNameList 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」 ，Property Name 清單
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    @Override
    public List<T> findByOrEquals(List<String> entityPropNameList, List<Object> valueList, List<String> orderEntityPropNameList) {
        List<KeyValuePair> pairList = KeyValuePairHelper.obtainKeyValuePairList(entityPropNameList, valueList);
        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);//orderEntityPropNameList;
        return this.findByOrEqualPairs(pairList, orderEntityPropNameList);
    }

    /**
     *
     * @param pairList 資料對應組
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    @Override
    public List<T> findByOrEqualPairs(List<KeyValuePair> pairList, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        List<Expression> expressionList = ExpressionHelper.createEqualExpressionList(root, cb, pairList);

        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);//pairList.stream().map(pair -> pair.getColName()).collect(Collectors.toList());

        List<Order> orderList = orderEntityPropNameList.stream()
                .map(entityPropName -> cb.asc(root.get(entityPropName)))
                .collect(Collectors.toList());

        // 轉call 更底層
        return this.findByOr(expressionList, orderList);
    }
//    public List<T> findBy
//</editor-fold>        

//<editor-fold defaultstate="collapsed" desc="Pattern Match、Not march 系列">    
    @Override
    public List<T> findByMatch(String entityPropName, String value, String... orderEntityPropNames) {
        return this.findByMatch(entityPropName, value, Arrays.asList(orderEntityPropNames));
    }

    @Override
    public List<T> findByMatch(String entityPropName, String value, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        String pattern = new StringBuilder().append("%").append(value).append("%").toString();
        cq = cq.where(cb.like(root.get(entityPropName), pattern));
        cq = cq.orderBy(OrderHelper.createAscOrderList(root, cb, orderEntityPropNameList));

        Query query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<T> findByNotMatch(String entityPropName, String value, String... orderEntityPropNames) {
        return this.findByNotMatch(entityPropName, value, Arrays.asList(orderEntityPropNames));
    }

    @Override
    public List<T> findByNotMatch(String entityPropName, String value, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        String pattern = new StringBuilder().append("%").append(value).append("%").toString();
        cq = cq.where(cb.notLike(root.get(entityPropName), pattern));
        cq = cq.orderBy(OrderHelper.createAscOrderList(root, cb, orderEntityPropNameList));

        Query query = em.createQuery(cq);
        return query.getResultList();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="IN 系列">
    /**
     *
     * @param entityPropName 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @return 查詢結果
     */
    @Override
    public List<T> findIn(String entityPropName, List<?> valueList) {
        List<String> orderEntityPropNameList = null;
        return this.findIn(entityPropName, valueList, orderEntityPropNameList);
    }

    @Override
    public List<T> findIn(String entityPropName, List<?> valueList, String... orderEntityPropNames) {
        return this.findIn(entityPropName, valueList, Arrays.asList(orderEntityPropNames));
    }

    @Override
    public List<T> findIn(String entityPropName, List<?> valueList, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        orderEntityPropNameList = (ListValidator.isListExist(orderEntityPropNameList)) ? orderEntityPropNameList : Arrays.asList(entityPropName);

        cq = cq.where(root.get(entityPropName).in(valueList)); // [FIXME202503131622] 要不要用 ExpressionHelper.createInExpression()
        cq = cq.orderBy(OrderHelper.createAscOrderList(root, cb, orderEntityPropNameList));

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

//<editor-fold defaultstate="collapsed" desc="findByAndNotEqual系列">
    @Override
    public List<T> findByAndNotEquals(List<String> entityPropNameList, List<Object> valueList) {
        return this.findByAndNotEquals(entityPropNameList, valueList, null);
    }

    /**
     * 意思是 findBy AndNotEquals， findBy 搜尋一筆， 邏輯是 Not Equals， 用 And 連結。 <br>
     *
     * @param entityPropNameList 請注意是要使用「(Table所對應的) Entity 之 (Table Column所對應的)
     * Property名稱 」 ，Property Name 清單
     * @param valueList Table Column (即 Entity 之 Property)數值清單
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    @Override
    public List<T> findByAndNotEquals(List<String> entityPropNameList, List<Object> valueList, List<String> orderEntityPropNameList) {
        List<KeyValuePair> pairList = KeyValuePairHelper.obtainKeyValuePairList(entityPropNameList, valueList);
        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);//orderEntityPropNameList;
        return this.findByAndNotEqualPairs(pairList, orderEntityPropNameList);

    }

    /**
     *
     * 意思是 findBy AndNotEquals， findBy 搜尋一筆， 邏輯是 Not Equals， 用 And 連結。
     *
     * @param pairList 資料對應組
     * @param orderEntityPropNameList 排序方式 Property Name 清單
     * @return 查詢結果
     */
    @Override
    public List<T> findByAndNotEqualPairs(List<KeyValuePair> pairList, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        List<Expression> expressionList = new ArrayList();
        for (KeyValuePair pair : pairList) {
            String propName = pair.getColName();
            Object value = pair.getColValue();
            Expression expression = ExpressionHelper.createNotEqualExpression(root, cb, propName, value);//cb.notEqual(root.get(propName), value); //public interface Predicate extends Expression<Boolean> {
            expressionList.add(expression);
        }

        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);

        List<Order> orderList = orderEntityPropNameList.stream()
                .map(entityPropName -> cb.asc(root.get(entityPropName)))
                .collect(Collectors.toList());

//        轉call 更底層
        return this.findByAnd(expressionList, orderList);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="findByOrNotEquals系列">
    @Override
    public List<T> findByOrNotEquals(List<String> entityPropNameList, List<Object> valueList) {
        return this.findByOrNotEquals(entityPropNameList, valueList, null);
    }

    @Override
    public List<T> findByOrNotEquals(List<String> entityPropNameList, List<Object> valueList, List<String> orderEntityPropNameList) {
        List<KeyValuePair> pairList = KeyValuePairHelper.obtainKeyValuePairList(entityPropNameList, valueList);
        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);//orderEntityPropNameList;
        return this.findByOrNotEqualPairs(pairList, orderEntityPropNameList);
    }

    @Override
    public List<T> findByOrNotEqualPairs(List<KeyValuePair> pairList, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);

        List<Expression> expressionList = ExpressionHelper.createNotEqualExpressionList(root, cb, pairList);

        orderEntityPropNameList = ListValidator.isListExist(orderEntityPropNameList) ? orderEntityPropNameList : obtainEntityPropNameList(pairList);//pairList.stream().map(pair -> pair.getColName()).collect(Collectors.toList());

        List<Order> orderList = orderEntityPropNameList.stream()
                .map(entityPropName -> cb.asc(root.get(entityPropName)))
                .collect(Collectors.toList());

        // 轉 call 更底層
        return this.findByOr(expressionList, orderList);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="findByGreaterThan 系列">    
    @Override
    public <C extends Comparable> List<T> findByLessThanOrEqualsTo(String entityPropName, C comparedValue, String... orderEntityPropNames) {
        return this.findByLessThanOrEqualsTo(entityPropName, comparedValue, Arrays.asList(orderEntityPropNames));
    }
    
    @Override
    public <C extends Comparable> List<T> findByLessThanOrEqualsTo(String entityPropName, C comparedValue, List<String> orderEntityPropNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        this.tryEvictAllCache();//(em);      
        Root<T> root = cq.from(entityClass);

        orderEntityPropNameList = (ListValidator.isListExist(orderEntityPropNameList)) ? orderEntityPropNameList : Arrays.asList(entityPropName);

        cq = cq.where(ExpressionHelper.createLessThanOrEqualToExpression(root, cb, entityPropName, comparedValue));
        cq = cq.orderBy(OrderHelper.createAscOrderList(root, cb, orderEntityPropNameList));

        Query query = em.createQuery(cq);
        return query.getResultList();        
    }    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="核心(?)，可以給無限(?)的查詢條件及排序條件">
    protected List<T> findByAnd(List<Expression> expressionList, List<Order> orderList) {
        EntityManager em = this.getEntityManager();
        // em.getEntityManagerFactory().getCache().evictAll();
        this.tryEvictAllCache();//(em);        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);

        Predicate predicate = cb.and(expressionList.toArray(Predicate[]::new)); // https://www.techiedelight.com/convert-list-to-array-java/ List To Array
        cq = cq.where(predicate);
        cq = cq.orderBy(orderList);

        Query query = em.createQuery(cq);
        return query.getResultList();

    }

    protected List<T> findByOr(List<Expression> expressionList, List<Order> orderList) {
        EntityManager em = this.getEntityManager();
        
        // em.getEntityManagerFactory().getCache().evictAll();        
        this.tryEvictAllCache();//(em);
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);

        Predicate predicate = cb.or(expressionList.toArray(Predicate[]::new));
        cq = cq.where(predicate);
        cq = cq.orderBy(orderList);

        Query query = em.createQuery(cq);
        return query.getResultList();
    }

    // ---------------------------------------------------------------------------------------------
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getByEquals 系列">
    @Override
    public T getByEquals(String columnName, Object columnValue) {
        List<T> resultList = this.findByEquals(columnName, columnValue);
        return (resultList == null || resultList.isEmpty()) ? null : resultList.get(0);
    }

    @Override
    public T getByAndEquals(List<String> columnNameList, List<Object> columnValueList) {
        List<T> resultList = this.findByAndEquals(columnNameList, columnValueList);
        return (resultList == null || resultList.isEmpty()) ? null : resultList.get(0);
    }

    @Override
    public T getByOrEquals(List<String> columnNameList, List<Object> columnValueList) {
        List<T> resultList = this.findByOrEquals(columnNameList, columnValueList);
        return (resultList == null || resultList.isEmpty()) ? null : resultList.get(0);
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="其他輔助methods">
    @Override
    public void clear() {
        this.getEntityManager().clear();
    }

    // tomoaki 20241108: 在底層定義此 Method (注意這樣會變成不能變成 proected，[FIXME202411081103] 思考一下這個 public 是否不好)
    @Override
    public void evictAllCache() {
        this.getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }

    // tomoaki 20241108: 在底層定義此 Method (注意這樣會變成不能變成 proected，[FIXME202411081103] 思考一下這個 public 是否不好)
    /**
     * 清空指定的 id 相關的資料 enttiy 的快取
     * @param id entity 的 Id(一般就是 Primary Key)
     */
    @Override
    public void evictCache(Object id) {
        this.getEntityManager().getEntityManagerFactory().getCache().evict(entityClass, id);
    }

    /*public void getEntityState(T entity) {
        return this.getEntityManager().getR
    }*/
    /**
     * https://stackoverflow.com/questions/13135309/how-to-find-out-whether-an-entity-is-detached-in-jpa-hibernate
     * 注意這只能判斷 entity 是不是 Managed，但有可能是 Detached、Transient、Removed
     *
     * @param entity 確認 entity 是否是 Managed 狀態
     * @return 是否是 Managed 狀態
     */
    public Boolean isManaged(T entity) {
        return this.getEntityManager().contains(entity);
    }    
    
    /**
     *
     * 執行 JPA evict，此方法會讓 Entity 由 Managed 轉為 Detached
     */
    private void tryEvictCache(Object id) { //(EntityManager em, Object id) {
        tryPrintLog("tryEvictCache(): EVICT_CACHE= %s", EVICT_CACHE);
        if (EVICT_CACHE) {
            tryPrintLog("tryEvictCache(): Will Evict Cache With id= %s", id);
            // em.getEntityManagerFactory().getCache().evict(entityClass, id);
            this.evictCache(id);
        }
    }
              
    /**
     *
     * 執行 JPA evict，此方法會讓 Entity 由 Managed 轉為 Detached
     */
    private void tryEvictAllCache() { // EntityManager em) {
        tryPrintLog("tryEvictAllCache(): EVICT_CACHE= %s", EVICT_CACHE);
        if (EVICT_CACHE) {
            tryPrintLog("tryEvictAllCache(): Will Evict All Cache");
            // em.getEntityManagerFactory().getCache().evictAll();
            this.evictAllCache();
        }
    }

    private List<String> obtainEntityPropNameList(List<KeyValuePair> pairList) {
        return pairList.stream().map(pair -> pair.getColName()).collect(Collectors.toList());
    }

    public Boolean isDataUpdate(T entity) {
        return JPAEntityHelper.isDataUpdate(this.getEntityManager(), entity);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="其他輔助 Methods，Log 類">
    protected void tryPrintLog(String appendMsgFmt, Object... params) {
        if (PRINT_LOG) {
            String appendMsg = String.format(appendMsgFmt, params);
            String msgFmt = "[%s]%s";
            System.out.format(msgFmt, this.getClass().getSimpleName(), appendMsg);
        }
    }
//</editor-fold>

}
