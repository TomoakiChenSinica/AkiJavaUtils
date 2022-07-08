/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.excel.entity;

/**
 *
 * @author arche
 */
public class ExcelTableOperationException extends RuntimeException {
    
    public ExcelTableOperationException() {
        super();
    }
    
    public ExcelTableOperationException(String msg) {
        super(msg);
    }
    
    public ExcelTableOperationException(String msgFormat, Object... params) {
        super(String.format(msgFormat, params));
    }    
    
    public ExcelTableOperationException(Exception ex) {
        super(ex);
    }
}
