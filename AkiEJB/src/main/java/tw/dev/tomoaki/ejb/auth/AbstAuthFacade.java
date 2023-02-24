/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.ejb.auth;

import tw.dev.tomoaki.ejb.AbstractQueryFacade;

/**
 *
 * @author tomoaki
 */
public abstract class AbstAuthFacade<T, AuthInfo> extends AbstractQueryFacade<T> {
    
    public AbstAuthFacade(Class<T> entityClass) {
        super(entityClass);
    }
    
//    public void doCreate(T entity, AuthInfo authInfo) {
//        
//        this.getEntityManager().persist();
//    }
    
}
