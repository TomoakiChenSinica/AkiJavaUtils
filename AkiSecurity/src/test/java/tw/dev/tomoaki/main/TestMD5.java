/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMD5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String theInput = "tomoaki";
        byte[] byteOfTheInput = theInput.getBytes();
        System.out.println(new String(byteOfTheInput));
        printBytes(byteOfTheInput);
        byte[] result = md.digest(byteOfTheInput);
        printBytes(result);
        System.out.println(new String(result));        
    }
    
    protected static void printBytes(byte[] theBytes) {       
        for(byte theByte : theBytes) {
            System.out.print(theByte + " ");
        }
        System.out.println("\r\n");
    } 
    
}
