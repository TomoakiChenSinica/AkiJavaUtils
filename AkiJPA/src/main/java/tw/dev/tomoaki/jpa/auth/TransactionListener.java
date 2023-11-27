/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.auth;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
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
//        entity.setLastModifiedDateTime(new Date());
        entity.setLastModifiedDateTime(LocalDateTime.now());
        entity.setLastModifier(entity.getIdentifier());
        return entity;
    }
    
    @PreUpdate
    @PreRemove
    public T beforeEdit(T entity) {
//        entity.setLastModifiedDateTime(new Date());
        entity.setLastModifiedDateTime(LocalDateTime.now());
        entity.setLastModifier(entity.getIdentifier());
        return entity;
    }
}
