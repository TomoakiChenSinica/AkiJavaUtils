/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.datatransfer.entity;

/**
 *
 * @author Tomoaki Chen
 */
public class DataTrasnferError<T> {

    protected T data;
    protected Exception exception;
    protected String errorMsg;

    protected DataTrasnferError() {
    }

    public static class Factory {

        public static <T>DataTrasnferError create(T data, Exception exception) {
            DataTrasnferError result = new DataTrasnferError();
            result.data = data;
            result.exception = exception;
            result.errorMsg = DataTransferErrorHelper.obtainStackTrace(exception);
            return result;
        }
    }

    public T getData() {
        return data;
    }

    public Exception getException() {
        return exception;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

}
