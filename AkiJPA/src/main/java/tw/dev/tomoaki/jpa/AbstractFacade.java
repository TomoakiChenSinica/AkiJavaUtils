/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa;

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
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
}
