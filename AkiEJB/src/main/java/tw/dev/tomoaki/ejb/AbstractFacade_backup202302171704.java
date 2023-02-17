/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import tw.dev.tomoaki.util.commondatavalidator.ListValidator;

/**
 *
 * @author tomoaki
 */
public abstract class AbstractFacade_backup202302171704<T> {

    private Class<T> entityClass;

    public AbstractFacade_backup202302171704(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
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

    public List<T> findBy(String columnName, Object value) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);
        cq = cq.where(cb.equal(root.get(columnName.toLowerCase()), value));
        cq = cq.orderBy(cb.asc(root.get(columnName.toLowerCase())));

        Query query = em.createQuery(cq);
        return query.getResultList();
    }
    
    public List<T> findBy(List<String> columnNameList, List<Object> columnValueList,  List<String> orderColNameList) {
        if(!ListValidator.isListExist(columnNameList)) {
            throw new IllegalArgumentException("columnNameList= %s Is Null Or Empty");
        }
        
        if(!ListValidator.isListExist(columnValueList)) {
            throw new IllegalArgumentException("columnNameList= %s Is Null Or Empty");
        }        
        
        
        
        Integer length4ColNameList = columnNameList.size();
        Integer legnth4ColValueList = columnValueList.size();
        if(!Objects.equals(length4ColNameList, legnth4ColValueList)) {
            throw new IllegalArgumentException("columnNameList= %s, columnValueList= %s, There Length Is Not Equal");
        }
        
        List<KeyValuePair> pairList = new ArrayList();
        for(Integer index = 0 ; index < legnth4ColValueList ; index++) {
            String colName = columnNameList.get(index);
            Object colValue = columnValueList.get(index);
            KeyValuePair pair = new KeyValuePair(colName, colValue);
            pairList.add(pair);
        }
        
        orderColNameList = ListValidator.isListExist(orderColNameList) ? orderColNameList : columnNameList;
        return this.findBy(pairList, orderColNameList);
        
    }
    
    public List<T> findBy(List<KeyValuePair> pairList, List<String> orderColNameList) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(entityClass);
        
        for(KeyValuePair pair : pairList) {
            String columnName = pair.getColName();
            Object value = pair.getColValue();
            cq = cq.where(cb.equal(root.get(columnName.toLowerCase()), value));        
        }
        
        orderColNameList = ListValidator.isListExist(orderColNameList) ? orderColNameList : pairList.stream().map(pair -> pair.getColName()).collect(Collectors.toList());
        
        for(String columnName : orderColNameList) {
            cq = cq.orderBy( cb.asc(root.get(columnName.toLowerCase()) ) ) ;
        }
//        cq = cq.orderBy( cb.asc(root.get( orderColNameList.get(0).toLowerCase()) ) ) ;
        Query query = em.createQuery(cq);
        return query.getResultList();
    }    
    
//    public List<T> search(List<Expression> expressionList, List<String> orderColNameList) {
//        EntityManager em = this.getEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
//        Root<T> root = cq.from(entityClass);
//        
//        for(Expression exp : expressionList) {
//            cq = cq.where(exp);        
//        }
//        
//        orderColNameList = ListValidator.isListExist(orderColNameList) ? orderColNameList : pairList.stream().map(pair -> pair.getColName()).collect(Collectors.toList());
//        
//        for(String columnName : orderColNameList) {
//            cq = cq.orderBy( cb.asc(root.get(columnName.toLowerCase()) ) ) ;
//        }
////        cq = cq.orderBy( cb.asc(root.get( orderColNameList.get(0).toLowerCase()) ) ) ;
//        Query query = em.createQuery(cq);
//        return query.getResultList();
//    }        
    

}
