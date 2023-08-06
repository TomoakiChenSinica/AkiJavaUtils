/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.helper;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author tomoaki
 */
public class FacadeHelper {
    
    
    protected  static  <T> CriteriaQuery<T> obtainInitCriteriaQuery(EntityManager em, Class<T> entityClass) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        return obtainInitCriteriaQuery(cb, entityClass);
    }
    
    protected  static  <T> CriteriaQuery<T> obtainInitCriteriaQuery(CriteriaBuilder cb, Class<T> entityClass) {
        return cb.createQuery(entityClass);
    }
    
    //https://stackoverflow.com/questions/6276122/can-i-get-the-sql-string-from-a-jpa-query-object
}
