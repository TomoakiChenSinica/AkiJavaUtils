/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datetimecloze.exception;

/**
 *
 * @author Tomoaki Chen
 */
public class DateTimeFormatParserException extends RuntimeException {
    
    public DateTimeFormatParserException() {
    }
    
    public DateTimeFormatParserException(String msg) {
        super(msg);
    }
    
    public DateTimeFormatParserException(Exception ex) {
        super(ex);
    }
    
}
