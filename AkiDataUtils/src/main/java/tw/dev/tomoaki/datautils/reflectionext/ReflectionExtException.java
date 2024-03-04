/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datautils.reflectionext;

/**
 *
 * @author tomoaki
 */
public class ReflectionExtException extends RuntimeException {

    public ReflectionExtException(Exception ex) {
        super(ex);
    }
    
    public ReflectionExtException(String msg) {
        super(msg);
    }
}
