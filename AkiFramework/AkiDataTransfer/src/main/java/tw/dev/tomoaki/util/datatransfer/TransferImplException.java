package tw.dev.tomoaki.util.datatransfer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import tw.dev.tomoaki.util.cast.ExceptionPrinter;

/**
 *
 * @author Tomoaki Chen
 */
public class TransferImplException extends Exception{
    
    public static class Factory {
        public static TransferImplException create(AbstractTransferImpl transferImpl) {
            Integer exceptionNum = transferImpl.getNumsOfException();
            String msg = obtainMsg(transferImpl.getExceptionList());//transferImpl.getExceptionList().toString();
            return new TransferImplException("資料轉換完成，但過程遇到錯誤。 數量 : " +  exceptionNum + " 訊息 : " + msg);
        }
        
        private static String obtainMsg(List<Exception> exceptionList) {
            String msg = "";
            for(Exception exception : exceptionList) {
                msg += ExceptionPrinter.obtainStackTrace(exception) + "\r\n";
            }
            return msg;
        }
    }
    
    public TransferImplException(String msg) {
        super(msg);
    }
    
    public TransferImplException(Exception ex) {
        super(ex);
    }
       
}
