/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.auth;

/**
 *
 * @author tomoaki
 */
public abstract class Transaction {//implements AuthInfoIntf {
 
    protected static ThreadLocal<String> IDENTIFIER = new ThreadLocal();
    
    public void setupIdentifier(String identifier) {
        IDENTIFIER.set(identifier);
    }

}
