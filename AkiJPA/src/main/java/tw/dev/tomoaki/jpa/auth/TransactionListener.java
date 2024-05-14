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
 * @param <T>
 */
public class TransactionListener<T extends TransactionEntity> {

    /*@Inject
    private EntityManager em;*/
 /*@PersistenceContext(unitName = "AcademicResearchMgmt-ejbPU")
    private EntityManager em;*/
    @PrePersist
    public T beforeCreate(T entity) {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        entity.setTransactionId(uuidAsString);
        entity.setLastModifiedDateTime(LocalDateTime.now());
        entity.setLastModifier(entity.getIdentifier());
        return entity;
    }

    @PreUpdate
    @PreRemove
    public T beforeEdit(T entity) {
        entity.setLastModifiedDateTime(LocalDateTime.now());
        entity.setLastModifier(entity.getIdentifier());
        return entity;
    }

    /*@PreUpdate
    @PreRemove
    public T beforeEdit(T entity) {
        Boolean isSelfModified = this.validateIsSelfModified(entity);
        isSelfModified = (isSelfModified != null) ? isSelfModified : true;
        System.out.println(String.format("beforeEdit(): entity= %s, isSelfModified= %s", entity, isSelfModified));
        if (isSelfModified) {
            entity.setLastModifiedDateTime(LocalDateTime.now());
            entity.setLastModifier(entity.getIdentifier());
        }
        return entity;
    }

    protected Boolean validateIsSelfModified(T entity) {
        System.out.println(String.format("validateIsSelfModified(): entity= %s, entity.isSelfModified= %s", entity, entity.getIsSelfModified()));
        return entity.getIsSelfModified();
    }*/
}
