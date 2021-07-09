/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.cast;

/**
 *
 * @author tomoaki
 */
public class ValueCastException extends Exception{
    public ValueCastException(){
        super();
    }
    
    public ValueCastException(String msg){
        super(msg);
    }
    
    public ValueCastException(Exception ex){
        super(ex);
    }
}
