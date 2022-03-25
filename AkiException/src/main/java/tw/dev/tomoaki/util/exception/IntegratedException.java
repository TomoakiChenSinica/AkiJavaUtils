/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.exception;

import java.util.List;
import tw.dev.tomoaki.util.cast.ExceptionPrinter;

/**
 *
 * @author Tomoaki Chen
 */
public class IntegratedException extends RuntimeException { 

    public static class Factory {
        public static IntegratedException create(ExceptionHandler handler) {
            Integer exceptionNum = handler.getNumsOfException();
            String msg = obtainMsg(handler.getExceptionList());//transferImpl.getExceptionList().toString();
            return new IntegratedException("資料轉換完成，但過程遇到錯誤。 數量 : " +  exceptionNum + " 訊息 : " + msg);
        }
        
        private static String obtainMsg(List<Exception> exceptionList) {
            String msg = "";
            for(Exception exception : exceptionList) {
                msg += ExceptionPrinter.obtainStackTrace(exception) + "\r\n";
            }
            return msg;
        }
    }
    
    public IntegratedException(String msg) {
        super(msg);
    }
    
    public IntegratedException(Exception ex) {
        super(ex);
    }    
}
