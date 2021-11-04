/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.tomoaki.imageutils;

/**
 *
 * @author arche_000
 */
public class ImageUtilsException extends Exception{
    public ImageUtilsException(String msg){
        super(msg);
    }
    
    public ImageUtilsException(Exception ex){
        super(ex);
    }    
}
