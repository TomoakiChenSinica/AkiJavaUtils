package tw.dev.tomoaki.util.datatransfer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import tw.dev.tomoaki.util.datatransfer.entity.DataTrasnferError;
import tw.dev.tomoaki.util.exception.ExceptionHandler;

/**
 *
 * @author Tomoaki Chen
 */
public class AbstractTransferImpl<T> extends ExceptionHandler {

    protected List<T> needCreatedList;
    protected List<T> needEditedList;
    protected List<T> notNeedEditedList;
    protected List<T> needRemovedList;    
    protected List<T> resultList;
    protected List<DataTrasnferError<T>> errorList;
    
    protected void doRecordNeedCreated(T data) {
        if(needCreatedList == null) {
            this.needCreatedList = new ArrayList();
        }
        this.needCreatedList.add(data);
        this.doRecordResult(data);
    }
    
    protected void doRecordNeedEdited(T data) {
        if(needEditedList == null) {
            this.needEditedList = new ArrayList();
        }
        this.needEditedList.add(data);
        this.doRecordResult(data);
    }    
    
    protected void doRecordNotNeedEdited(T data) {
        if(notNeedEditedList == null) {
            this.notNeedEditedList = new ArrayList();
        }
        this.notNeedEditedList.add(data);
        this.doRecordResult(data);
    }
    
    protected void doRecordNeedRemoved(T data) {
        if(this.needRemovedList == null) {
            this.needRemovedList = new ArrayList();
        }
        this.needRemovedList.add(data);
        //要不要從resultList移除....
    }
    
    protected void doRecordResult(T data) {
        if(this.resultList == null) {
            this.resultList = new ArrayList();
        }
        this.resultList.add(data);
    }
    
    protected void doRecordTransferError(T data, Exception ex) {
        if(this.errorList == null) {
            this.errorList = new ArrayList();
        }
        this.errorList.add(DataTrasnferError.Factory.create(data, ex));
    }

    public List<T> getNeedCreatedList() {
        return needCreatedList;
    }

    public List<T> getNeedEditedList() {
        return needEditedList;
    }

    public List<T> getNotNeedEditedList() {
        return notNeedEditedList;
    }

    public List<T> getNeedRemovedList() {
        return needRemovedList;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public List<DataTrasnferError<T>> getErrorList() {
        return errorList;
    }            
}
