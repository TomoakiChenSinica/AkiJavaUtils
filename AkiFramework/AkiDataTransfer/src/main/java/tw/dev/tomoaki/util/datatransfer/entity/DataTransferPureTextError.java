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
public class DataTransferPureTextError extends DataTrasnferError<String> {

    protected DataTransferPureTextError() {
    }

    public static class Factory {

        public static DataTransferPureTextError create(String dataPureText, Exception exception) {
            DataTransferPureTextError error = new DataTransferPureTextError();
            error.setData(dataPureText);
            error.setException(exception);
            return error;
        }
    }
}
