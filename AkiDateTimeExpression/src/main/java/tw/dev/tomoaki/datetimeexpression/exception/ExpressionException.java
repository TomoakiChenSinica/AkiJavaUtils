/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datetimeexpression.exception;

/**
 *
 * @author Tomoaki Chen
 */
public class ExpressionException extends RuntimeException {
    
    public ExpressionException() {
    }
    
    public ExpressionException(String msg) {
        super(msg);
    }
    
    public ExpressionException(Exception ex) {
        super(ex);
    }
    
}
