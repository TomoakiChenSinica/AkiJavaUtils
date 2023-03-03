/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.auth;

import tw.dev.tomoaki.jpa.helper.ConstraintViolationHelper;
import javax.validation.ConstraintViolationException;
import tw.dev.tomoaki.jpa.AbstractQueryFacade;

/**
 *
 * @author tomoaki
 */
public abstract class AbstAuthFacade<ENTITY extends Transaction, AUTHINFO extends AuthInfoAbst> extends AbstractQueryFacade<ENTITY> {

    public AbstAuthFacade(Class<ENTITY> entityClass) {
        super(entityClass);
    }

    public void doCreate(ENTITY entity, AUTHINFO authInfo) {
        try {
            String identifier = authInfo.getIdentifier();
            entity.setupIdentifier(identifier);
            this.getEntityManager().persist(entity);
        } catch (ConstraintViolationException ex) {
            ConstraintViolationHelper.handleException(ex);
        }
    }

    public void doEdit(ENTITY entity, AUTHINFO authInfo) {
        try {
            String identifier = authInfo.getIdentifier();
            entity.setupIdentifier(identifier);
            this.getEntityManager().merge(entity);
        } catch (ConstraintViolationException ex) {
            ConstraintViolationHelper.handleException(ex);
        }
    }

    public void doRemove(ENTITY entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

}
