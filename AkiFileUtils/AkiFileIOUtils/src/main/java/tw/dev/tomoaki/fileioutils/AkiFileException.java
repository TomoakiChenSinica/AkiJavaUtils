/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileioutils;

/**
 *
 * @author tomoaki
 */
public class AkiFileException extends RuntimeException{
    
    public AkiFileException() {
        super();
    }
    
    public AkiFileException(String msg) {
        super(msg);
    }
    
    public AkiFileException(Exception ex) {
        super(ex);
    }
}
