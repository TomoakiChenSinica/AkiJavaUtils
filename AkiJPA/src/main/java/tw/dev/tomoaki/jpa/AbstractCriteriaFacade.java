/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import tw.dev.tomoaki.jpa.query.criteria.helper.ExpressionHelper;

/**
 *
 * @author tomoaki
 */
public abstract class AbstractCriteriaFacade<T> extends AbstractFacade<T> implements CUDFacade<T> {

    public AbstractCriteriaFacade(Class<T> entityClass) {
        super(entityClass);
    }

    // ----------------------------------------------------
    @Override
    public <C extends Comparable> int removeByLessThanOrEqual(String entityPropName, C comparedValue) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<T> cq = cb.createCriteriaDelete(entityClass);
        Root<T> root = cq.from(entityClass);
        
        cq.where(ExpressionHelper.createLessThanOrEqualToExpression(root, cb, entityPropName, comparedValue));
        return em.createQuery(cq).executeUpdate();
    }

    @Override
    public <C extends Comparable> int removeByEquals(String entityPropName, C comparedValue) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<T> cq = cb.createCriteriaDelete(entityClass);
        Root<T> root = cq.from(entityClass);

        cq.where(ExpressionHelper.createEqualExpression(root, cb, entityPropName, comparedValue));
        return em.createQuery(cq).executeUpdate();
    }

    @Override
    public <C extends Comparable> int removeByLessThan(String entityPropName, C comparedValue) {
        EntityManager em = this.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<T> cq = cb.createCriteriaDelete(entityClass);
        Root<T> root = cq.from(entityClass);

        cq.where(ExpressionHelper.createLessThanExpression(root, cb, entityPropName, comparedValue));
        return em.createQuery(cq).executeUpdate();        
    }

}
