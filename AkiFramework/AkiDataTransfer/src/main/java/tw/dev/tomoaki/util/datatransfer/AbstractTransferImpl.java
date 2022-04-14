package tw.dev.tomoaki.util.datatransfer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import tw.dev.tomoaki.util.datatransfer.entity.DataTrasnferError;
import tw.dev.tomoaki.util.datatransfer.entity.DataTransferPureTextError;
import tw.dev.tomoaki.util.exception.ExceptionHandler;

/**
 *
 * @author Tomoaki Chen
 * @param <S> 來源的 class type
 * @param <T> 結果的 class type

 */
public abstract class AbstractTransferImpl<S, T> extends ExceptionHandler {

    protected List<T> needCreatedList;
    protected List<T> needEditedList;
    protected List<T> notNeedEditedList;
    protected List<T> needRemovedList;    
    protected List<T> resultList;
//    protected List<DataTrasnferError<T>> errorList;
    protected List<DataTrasnferError<S>> errorList;
    protected List<DataTransferPureTextError> pureTextErrorList;
    
    
    protected void doRecordNeedCreated(T targetData) {
        if(needCreatedList == null) {
            this.needCreatedList = new ArrayList();
        }
        this.needCreatedList.add(targetData);
        this.doRecordResult(targetData);
    }
    
    protected void doRecordNeedEdited(T targetData) {
        if(needEditedList == null) {
            this.needEditedList = new ArrayList();
        }
        this.needEditedList.add(targetData);
        this.doRecordResult(targetData);
    }    
    
    protected void doRecordNotNeedEdited(T targetData) {
        if(notNeedEditedList == null) {
            this.notNeedEditedList = new ArrayList();
        }
        this.notNeedEditedList.add(targetData);
        this.doRecordResult(targetData);
    }
    
    protected void doRecordNeedRemoved(T targetData) {
        if(this.needRemovedList == null) {
            this.needRemovedList = new ArrayList();
        }
        this.needRemovedList.add(targetData);
        //要不要從resultList移除....
    }
    
    protected void doRecordResult(T targetData) {
        if(this.resultList == null) {
            this.resultList = new ArrayList();
        }
        this.resultList.add(targetData);
    }
    
    protected void doRecordTransferError(S sourceData, Exception ex) {
        if(this.errorList == null) {
            this.errorList = new ArrayList();
        }
        this.errorList.add(DataTrasnferError.Factory.create(sourceData, ex));
    }
    
    protected void doRecordTransferPureTextError(S sourceData, Exception ex) {
        try {
            if(this.pureTextErrorList == null) {
                this.pureTextErrorList = new ArrayList();
            }
            this.pureTextErrorList.add(DataTransferPureTextError.Factory.create(obtainTransferPureTextError(sourceData), ex));
        } catch(UnsupportedOperationException uoe) {
            System.out.format("[%s] obtainTransferPureTextError() Is Not Implemented Yet", this.getClass().getSimpleName());
        }
    }    
    
    protected abstract String obtainTransferPureTextError(S sourceData);        
   
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

    public List<DataTrasnferError<S>> getErrorList() {
        return errorList;
    }    

    public List<DataTransferPureTextError> getPureTextErrorList() {
        return pureTextErrorList;
    }
}
