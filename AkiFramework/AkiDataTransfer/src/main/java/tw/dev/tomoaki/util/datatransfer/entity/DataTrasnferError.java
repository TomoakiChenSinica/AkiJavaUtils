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

    private T data;
    private Exception exception;

    protected DataTrasnferError() {
    }

    public static class Factory {

        public static <T>DataTrasnferError create(T data, Exception exception) {
            DataTrasnferError result = new DataTrasnferError();
            result.setData(data);
            result.setException(exception);
            return result;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

}
