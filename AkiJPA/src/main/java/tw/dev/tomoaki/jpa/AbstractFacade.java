/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa;

import javax.validation.ConstraintViolationException;
import tw.dev.tomoaki.jpa.helper.ConstraintViolationHelper;
import tw.dev.tomoaki.jpa.helper.JPAEntityHelper;

/**
 *
 * @author tomoaki
 */
public abstract class AbstractFacade<T> extends AbstractQueryFacade<T> {

    public AbstractFacade(Class<T> entityClass) {
        super(entityClass);
    }

    public void create(T entity) {
        try {
            getEntityManager().persist(entity);
        } catch (ConstraintViolationException ex) {
            ConstraintViolationHelper.handleException(ex);
        }
    }

    public void edit(T entity) {
        try {
            getEntityManager().merge(entity);
        } catch (ConstraintViolationException ex) {
            ConstraintViolationHelper.handleException(ex);
        }
    }

    public void remove(T entity) {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
        } catch (ConstraintViolationException ex) {
            ConstraintViolationHelper.handleException(ex);
        }
    }
}
