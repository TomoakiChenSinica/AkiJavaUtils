/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.exception;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomoaki Chen
 */
public class ExceptionHandler {

    protected ExceptionHandler() {
        this.exceptionList = new ArrayList();
        this.childExceptionList = new ArrayList();
    }

    protected List<Exception> exceptionList;
    protected List<Exception> childExceptionList;

    protected void addException(Exception ex) {
        this.exceptionList.add(ex);
    }
    
    protected <T extends ExceptionHandler>void addChildException(T childExceptionHandler) {
        List<Exception> childHandlerExceptionList = childExceptionHandler.exceptionList;
        childHandlerExceptionList.forEach(e -> this.exceptionList.add(e));
    }
    

    public List<Exception> getExceptionList() {
        return this.exceptionList;
    }

    public Integer getNumsOfException() {
        return this.exceptionList.size();
    }

    public Boolean getIsExceptionOccur() {
        return (exceptionList != null && exceptionList.isEmpty() == false);
    }

    public void doProcessException() {
       if(this.getIsExceptionOccur()) {
          throw IntegratedException.Factory.create(this);
       }
    }
}
