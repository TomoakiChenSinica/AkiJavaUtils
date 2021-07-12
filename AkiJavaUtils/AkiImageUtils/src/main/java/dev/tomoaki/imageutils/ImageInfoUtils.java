/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.tomoaki.imageutils;

import dev.tomoaki.imageutils.entity.ImagePixelARGB;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

/**
 *
 * @author arche_000
 */
public class ImageInfoUtils {
    
    public static Integer imageARGB2IntPixcel(ImagePixelARGB imageARGB){
        Integer a = imageARGB.getAlpha();
        Integer r = imageARGB.getRed();
        Integer g = imageARGB.getGreen();
        Integer b = imageARGB.getBlue();
        return (a * 256 * 256 * 256) + (r * 256* 256) + (g * 256) + b;
    }
    
    public static ImagePixelARGB intPixcel2ARGB(Integer pixelRGBInfo){
        byte[] rgbByte = ByteBuffer.allocate(4).putInt(pixelRGBInfo).array();   //用多少byte 表示 要決定 
        return intPixcel2ARGB(rgbByte);
    }
    
    public static ImagePixelARGB intPixcel2ARGB(byte[] argbBytes){
        ImagePixelARGB imageARGB = new ImagePixelARGB();
        for(Integer index = 0 ; index < 4 ; index++){
            Byte theByte = argbBytes[index];
            switch(index){
                case 0 : { //a
                    imageARGB.setAlpha(byte2decimalARGB(theByte));
                    break;
                }
                case 1 : {//r
                    imageARGB.setRed(byte2decimalARGB(theByte));                    
                    break;
                }
                
                case 2 : {//g
                    imageARGB.setGreen(byte2decimalARGB(theByte));                                            
                    break;
                }                
                case 3 :{//b
                    imageARGB.setBlue(byte2decimalARGB(theByte));                                        
                    break;
                }
                
            }
        }    
        return imageARGB;
    }
    
    public static Integer byte2decimalARGB(Byte theByte){
//        Integer temp = theByte.intValue();
//        return 256+temp; // 負數的表示法、java會自動補 XFFFF
        return Byte.toUnsignedInt(theByte); //之前就有想到 問題在非unsignde int 發現java 只有signed
    }
    
    
    //
    public static ImagePixelARGB getPixelARGB(BufferedImage image, Integer x, Integer y){
        Integer pixel = image.getRGB(x, y);
        return ImageInfoUtils.intPixcel2ARGB(pixel);
    }
    //
}
