/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datetimecloze.exception;

/**
 *
 * @author Tomoaki Chen
 */
public class ClozeFormatException extends RuntimeException {
    
    public ClozeFormatException() {
    }
    
    public ClozeFormatException(String msg) {
        super(msg);
    }
    
    public ClozeFormatException(Exception ex) {
        super(ex);
    }
    
}
