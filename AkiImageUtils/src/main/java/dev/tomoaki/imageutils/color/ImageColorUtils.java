/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.tomoaki.imageutils.color;

import dev.tomoaki.imageutils.ImageInfoUtils;
import dev.tomoaki.imageutils.ImageIOUtils;
import dev.tomoaki.imageutils.ImageUtilsException;
import dev.tomoaki.imageutils.entity.ImagePixelARGB;
import static java.awt.Color.blue;
import static java.awt.Color.green;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

/**
 *
 * @author arche_000
 */
public class ImageColorUtils {

//    public static BufferedImage doGrayscale(BufferedImage image){
//        
//        return image;
//    } 
    public BufferedImage doGrayscale(String filePath) throws ImageUtilsException {
        BufferedImage image = ImageIOUtils.getBufferedImage(filePath);
        BufferedImage processedImage = doGrayscale(image);
        return processedImage;
    }

//    public BufferedImage doGrayscale(BufferedImage image) {
//        ColorModel colorModel = image.getColorModel();
//        Graphics graphic = image.getGraphics();
//        Integer width = image.getWidth();
//        Integer height = image.getHeight();
//        for (Integer x = 0; x < width; x++) {
//            for (Integer y = 0; y < height; y++) {
//                Integer rgb = image.getRGB(x, y);//pixel 直接印出來的是啥 ?
//                //不同格式 pixel 值 RGB順序似乎不同? 似乎沒有-->搞不懂modal 的意義
//                byte[] rgbByte = ByteBuffer.allocate(8).putInt(rgb).array();   //用多少byte 表示 要決定 
//                Integer red = colorModel.getRed(rgbByte);
//                Integer green = colorModel.getGreen(rgbByte); //可是印出來又是可讀的數字
//                Integer blue = colorModel.getBlue(rgbByte);
//                
//            
//                Integer gray = (red + green + blue) / 3; //rgb 都這個，怎麼 轉乘 byte
//               
//                
//                
//            }
//        }
//        return image;
//    }
  public BufferedImage doGrayscale(BufferedImage image) {
//        BufferedImage newImage = new BufferedImage();
        Integer width = image.getWidth();
        Integer height = image.getHeight();
        for (Integer x = 0; x < width; x++) {
            for (Integer y = 0; y < height; y++) {
                Integer rgb = image.getRGB(x, y);//pixel 直接印出來的是啥 ?
                ImagePixelARGB argb = ImageInfoUtils.intPixcel2ARGB(rgb);
                Integer red = argb.getRed();
                Integer green = argb.getGreen();
                Integer blue = argb.getBlue();
                Integer gray = (red + green + blue) / 3; //rgb 都這個，怎麼 轉乘 byte
                
                ImagePixelARGB  newARGB = new ImagePixelARGB(argb.getAlpha(), gray, gray, gray);
                Integer pixel = ImageInfoUtils.imageARGB2IntPixcel(newARGB);
                image.setRGB(x, y, pixel);                
            }
        }
        return image;
    }    
}
