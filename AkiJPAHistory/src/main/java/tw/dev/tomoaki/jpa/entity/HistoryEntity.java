/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import tw.dev.tomoaki.jpa.enumoption.HistoryOperationStatus;

/**
 *
 * @author tomoaki
 */
@MappedSuperclass
public abstract class HistoryEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "operationid")
    protected String operationId;

    /*@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "operationstatus")
    private String operationStatus;*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "operationstatus")
    protected String operationStatusCode;    

    @Column(name = "operationtime")    
    protected LocalDateTime operationDateTime;
    
    @Size(max = 2147483647)
    @Column(name = "operationmemo")    
    protected String operationMemo;
    
    @Size(max = 2147483647)
    @Column(name = "lastmodifier")    
    protected String lastModifer;
    
    @Column(name = "lastmodifieddatetime")
    protected LocalDateTime lastModifiedDateTime;

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationStatusCode() {
        return operationStatusCode;
    }

    public void setOperationStatus(String operationStatusCode) {
        this.operationStatusCode = operationStatusCode;
    }
    
    public HistoryOperationStatus getOperationStatus() {
        /*if(this.operationStatusCode == null) {
            return null;
        }*/
        return HistoryOperationStatus.codeOf(operationStatusCode);
    }

    public void setOperationStatus(HistoryOperationStatus operationStatus) {
        if(operationStatus != null) {
            this.operationStatusCode = operationStatus.getCode();
        }
    }    

    public LocalDateTime getOperationDateTime() {
        return operationDateTime;
    }

    public void setOperationDateTime(LocalDateTime operationDateTime) {
        this.operationDateTime = operationDateTime;
    }

    public String getOperationMemo() {
        return operationMemo;
    }

    public void setOperationMemo(String operationMemo) {
        this.operationMemo = operationMemo;
    }

    public String getLastModifer() {
        return lastModifer;
    }

    public void setLastModifer(String lastModifer) {
        this.lastModifer = lastModifer;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

}
