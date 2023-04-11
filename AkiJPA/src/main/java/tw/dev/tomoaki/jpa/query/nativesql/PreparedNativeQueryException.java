/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.query.nativesql;

/**
 *
 * @author tomoaki
 */
public class PreparedNativeQueryException extends RuntimeException{
    
    public PreparedNativeQueryException() {
    }
    
    public PreparedNativeQueryException(String msg) {
        super(msg);
    }
    
    public PreparedNativeQueryException(Exception ex) {
        super(ex);
    }
}
