/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.ejb.auth;

import tw.dev.tomoaki.ejb.auth.TransactionEntity;
import java.util.Date;
import java.util.UUID;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author tomoaki
 */
public class TransactionListener<T extends TransactionEntity> {
    
    
    @PrePersist
    public T beforeCreate(T entity) {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();        
        entity.setTransactionId(uuidAsString);
        entity.setLastModifiedDateTime(new Date());        
        entity.setLastModifier(entity.getIdentifier());
        return entity;
    }
    
    @PreUpdate
    public T beforeEdit(T entity) {
        entity.setLastModifiedDateTime(new Date());
        entity.setLastModifier(entity.getIdentifier());
        return entity;
    }
}
