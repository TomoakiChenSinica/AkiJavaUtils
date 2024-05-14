/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.auth;

/**
 *
 * @author tomoaki
 */
public interface Transaction {

    //https://stackoverflow.com/questions/2430756/why-are-interface-variables-static-and-final-by-default
    public void setupIdentifier(String identifier);

}
