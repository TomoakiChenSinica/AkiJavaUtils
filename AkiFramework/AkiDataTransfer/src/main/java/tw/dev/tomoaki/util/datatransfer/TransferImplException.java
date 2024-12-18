package tw.dev.tomoaki.util.datatransfer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import tw.dev.tomoaki.util.cast.ExceptionPrinter;
import tw.dev.tomoaki.util.datatransfer.entity.DataTransferPureTextError;
import tw.dev.tomoaki.util.string.AkiStringUtil;
//import tw.dev.tomoaki.util.stringutils.AkiStringUtil;

/**
 *
 * @author Tomoaki Chen
 */
public class TransferImplException extends Exception{
    
    public static class Factory {
        public static TransferImplException create(AbstractTransferImpl transferImpl) {
            Integer exceptionNum = transferImpl.getNumsOfException();
            // String msg = obtainMsg(transferImpl.getExceptionList());//transferImpl.getExceptionList().toString();
            String msg = obtainMsg(transferImpl);
            return new TransferImplException("資料轉換完成，但過程遇到錯誤，數量: " +  exceptionNum + "。完整訊息: \r\n" + msg);
        }
        

        private static String obtainMsg(AbstractTransferImpl transferImpl) {
            String msg;
            List<DataTransferPureTextError> errorList = transferImpl.getPureTextErrorList();
            List<Exception> exceptionList = transferImpl.getExceptionList();
            if(errorList != null && !errorList.isEmpty()) {
                msg = Factory.obtainMsgByPureTextErrors(errorList.toArray(DataTransferPureTextError[]::new));
            } else {
                msg = Factory.obtainMsgByExceptions(exceptionList.toArray(Exception[]::new));
            }
            return msg;
        }
        
        private static String obtainMsgByExceptions(Exception[] exceptions) {
            String msg = "";
            for (Exception exception : exceptions) {
                msg += ExceptionPrinter.obtainStackTrace(exception) + "\r\n";
            }
            return msg;
        }
        
        private static String obtainMsgByPureTextErrors(DataTransferPureTextError[] errors) {
            String msg = "";
            Integer counter = 1;
            for(DataTransferPureTextError error : errors) {
                msg += String.format("第%s筆 Data= %s, ExceptionStackTrace Is: %s \r\n", counter, error.getData(), error.getErrorMsg());
                counter++;
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
    
    public String getMessage4HTML() {
        return AkiStringUtil.parseHtmlFormat(this.getMessage());
    }
       
}
