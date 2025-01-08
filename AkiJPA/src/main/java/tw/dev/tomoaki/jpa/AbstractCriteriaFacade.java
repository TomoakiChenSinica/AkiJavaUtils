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
public abstract class AbstractCriteriaFacade<T> extends AbstractFacade<T> {

    public AbstractCriteriaFacade(Class<T> entityClass) {
        super(entityClass);
    }

    // ----------------------------------------------------

}
