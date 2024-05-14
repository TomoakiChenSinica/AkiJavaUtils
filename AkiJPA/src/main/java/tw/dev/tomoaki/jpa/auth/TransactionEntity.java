/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.auth;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tomoaki
 */
@EntityListeners(TransactionListener.class)
@MappedSuperclass
public class TransactionEntity implements Transaction {

    private static ThreadLocal<String> IDENTIFIER = new ThreadLocal();

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "transactionid")
    private String transactionId;

    @Size(max = 2147483647)
    @Column(name = "lastmodifier")
    private String lastModifier;

    @Column(name = "lastmodifieddatetime")
    private LocalDateTime lastModifiedDateTime;
    
    /*@Transient
    private Boolean isSelfModified = true;*/

    public TransactionEntity() {
    }

    public TransactionEntity(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    /*public Boolean getIsSelfModified() {
        return isSelfModified;
    }
    
    public void setIsSelfModified(Boolean isSelfModified) {
        this.isSelfModified = isSelfModified;
    }*/
    
    @Override
    public void setupIdentifier(String identifier) {
        IDENTIFIER.set(identifier);
    }

    public String getIdentifier() {
        return IDENTIFIER.get();
    }
}
