/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.dataminer;

/**
 *
 * @author Tomoaki Chen
 */
public class DataMinerException extends RuntimeException {
    
    public DataMinerException(String msg) {
        super(msg);
    }
    
    public DataMinerException(Exception ex) {
        super(ex);
    }
    
}
