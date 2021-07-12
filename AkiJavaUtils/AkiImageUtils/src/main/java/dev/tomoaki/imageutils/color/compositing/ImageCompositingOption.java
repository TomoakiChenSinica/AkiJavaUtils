/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.tomoaki.imageutils.color.compositing;

/**
 *
 * @author arche_000
 */
public enum ImageCompositingOption {
    
    LIMIT_INSIDE_BACKGROND("Limit Inside Backgroud", "LimitInsideBackground");
    
    private String name;
    private String code;

    private ImageCompositingOption(String name, String code){
        this.name = name;
        this.code = code;
    }
    
}
