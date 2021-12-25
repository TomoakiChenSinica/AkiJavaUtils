/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.tomoaki.imageutils.entity;

/**
 *
 * @author arche_000
 */
public class ImagePixelARGB {
    private Integer alpha;
    private Integer red;
    private Integer green;
    private Integer blue;

    public ImagePixelARGB(){
    }
    
    public ImagePixelARGB(Integer alpha, Integer red, Integer green, Integer blue){
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public Integer getAlpha() {
        return alpha;
    }

    public void setAlpha(Integer alpha) {
        this.alpha = alpha;
    }

    public Integer getRed() {
        return red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    public Integer getGreen() {
        return green;
    }

    public void setGreen(Integer green) {
        this.green = green;
    }

    public Integer getBlue() {
        return blue;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "(" + this.red + ", " + this.green + ", " + this.blue + ")";
    }
    
    
}
