/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.fileexplorer.core;

/**
 *
 * @author arche
 */
public class FileAnalyzeException extends RuntimeException {
    
    public FileAnalyzeException() {
    }
    
    public FileAnalyzeException(String msg) {
        super(msg);
    }
    
    public FileAnalyzeException(Exception ex) {
        super(ex);
    }
}
