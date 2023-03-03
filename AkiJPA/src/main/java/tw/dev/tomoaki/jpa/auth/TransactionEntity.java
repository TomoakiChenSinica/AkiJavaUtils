/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.auth;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tomoaki
 */
@EntityListeners(TransactionListener.class)
@MappedSuperclass
public class TransactionEntity extends Transaction {//implements Serializable {
             
    private static final long serialVersionUID = 1L;
    //@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "transactionid")
    private String transactionid;
    
    @Size(max = 2147483647)
    @Column(name = "lastmodifier")
    private String lastmodifier;
    
    @Column(name = "lastmodifieddatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodifieddatetime;

    public TransactionEntity() {
    }

    public TransactionEntity(String transactionId) {
        this.transactionid = transactionId;
    }


    public String getTransactionId() {
        return transactionid;
    }

    public void setTransactionId(String transactionId) {
        this.transactionid = transactionId;
    }


    public String getLastModifier() {
        return lastmodifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastmodifier = lastModifier;
    }

    public Date getLastModifiedDateTime() {
        return lastmodifieddatetime;
    }

    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        this.lastmodifieddatetime = lastModifiedDateTime;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (dataactionid != null ? dataactionid.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof DataAction)) {
//            return false;
//        }
//        DataAction other = (DataAction) object;
//        if ((this.dataactionid == null && other.dataactionid != null) || (this.dataactionid != null && !this.dataactionid.equals(other.dataactionid))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "tw.edu.sinica.iis.acadres.ejb.entity.core.DataAction[ dataactionid=" + dataactionid + " ]";
//    }    

//    @Override
    public String getIdentifier() {
        return IDENTIFIER.get();
    }
}
