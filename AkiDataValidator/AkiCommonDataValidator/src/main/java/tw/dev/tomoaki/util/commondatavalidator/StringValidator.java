/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.commondatavalidator;

/**
 *
 * @author Tomoaki Chen
 */
public class StringValidator {
    
    public static Boolean isValueExist(String text) {
        return text != null && !text.isEmpty();
    }
    
}
