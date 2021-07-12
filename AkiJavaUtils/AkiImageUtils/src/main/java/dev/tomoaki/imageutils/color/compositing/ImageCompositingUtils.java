/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.tomoaki.imageutils.color.compositing;

import dev.tomoaki.imageutils.ImageInfoUtils;
import dev.tomoaki.imageutils.ImageUtilsException;
import dev.tomoaki.imageutils.entity.ImagePixelARGB;
import java.awt.image.BufferedImage;

/**
 *
 * @author arche_000
 */
public class ImageCompositingUtils {
    
    public BufferedImage compositing(BufferedImage backgroundImage, BufferedImage sourceImage, Integer startX, Integer startY, ImageCompositingOption option) throws ImageUtilsException{
        return this.compositing(backgroundImage, sourceImage, startX, startY, option, null);
    }
    
    public BufferedImage compositing(BufferedImage backgroundImage, BufferedImage sourceImage, Integer startX, Integer startY, ImageCompositingOption option, Integer changeAlpha) throws ImageUtilsException{
        Integer sourceImageHeight = sourceImage.getHeight();
        Integer sourceImageWidth = sourceImage.getWidth();
        Integer backgroundImageHeight = backgroundImage.getHeight();
        Integer backgroundImageWidth = backgroundImage.getWidth();
        
        if(startX > backgroundImageWidth - 1){
            throw new ImageUtilsException("startX[" + startX + "]out of background width[" + backgroundImageWidth + "]");
        }
        
        if(startY > backgroundImageHeight - 1){
            throw new ImageUtilsException("startY[" + startY + "]out of background height[" + backgroundImageHeight + "]");
        }        
        
        Integer maxX = this.countMaxBound(backgroundImageWidth, startX, sourceImageWidth, option);
        Integer maxY = this.countMaxBound(backgroundImageHeight, startY, sourceImageHeight, option);
        for(Integer x = startX ; x < maxX ;  x++){
            for(Integer y = startY ; y < maxY ; y++){
                ImagePixelARGB sourceImagePixel = ImageInfoUtils.getPixelARGB(sourceImage, x - startX, y - startY);
                ImagePixelARGB backgroundImagePixel = ImageInfoUtils.getPixelARGB(backgroundImage, x, y);
                if(changeAlpha != null){
//                    Double changeAlphaRatio = changeAlpha / 256.0;
//                    Integer oriAlpha = sourceImagePixel.getAlpha();
//                    Integer resultAlpha = ((Double)(oriAlpha * changeAlphaRatio)).intValue();
//                    sourceImagePixel.setAlpha(resultAlpha);
                    sourceImagePixel.setAlpha(changeAlpha);

                }
                ImagePixelARGB resultARGB = this.compositingPixel(backgroundImagePixel, sourceImagePixel);
                Integer resultPixel = ImageInfoUtils.imageARGB2IntPixcel(resultARGB);
                backgroundImage.setRGB(x, y, resultPixel);
            }
        }
        return backgroundImage;
    }
    
    private Integer countMaxBound(Integer backgroundBound, Integer startPosition, Integer sourceBound,  ImageCompositingOption option){
        Integer sourceEndPosition = startPosition + sourceBound;
        if(ImageCompositingOption.LIMIT_INSIDE_BACKGROND.equals(option)){
            Integer maxBound = (backgroundBound > sourceEndPosition) ? sourceEndPosition : backgroundBound;
            return maxBound;
        } else {
            Integer maxBound = (backgroundBound > sourceEndPosition) ? sourceEndPosition : backgroundBound;
            return maxBound;        
        }
    }
    
    
//    protected Double countCompositingAlphaRatio(Integer backgroundAlpha, Integer sourceAlpha){
//        Double backgroundAlphaRatio = backgroundAlpha / 256.0;
//        Double sourceAlphaRatioRatio = sourceAlpha / 256.0;
//        Double outAlphaRtio = backgroundAlphaRatio + sourceAlphaRatioRatio * (1.0 - backgroundAlphaRatio);
//        return outAlphaRtio;
//    }    
    protected Double countCompositingAlphaRatio(Integer backgroundAlpha, Integer sourceAlpha){
        Double backgroundAlphaRatio = backgroundAlpha / 256.0;
        Double sourceAlphaRatioRatio = sourceAlpha / 256.0;
        Double outAlphaRtio = sourceAlphaRatioRatio + backgroundAlphaRatio * (1.0 - sourceAlphaRatioRatio);
        return outAlphaRtio;
    }        
    
    protected Double countCompositingAlphaRatio(ImagePixelARGB backgroundImagePixel, ImagePixelARGB sourceImagePixel){
        Integer backgroundAlpha = backgroundImagePixel.getAlpha();
        Integer sourceAlpha = sourceImagePixel.getAlpha();
        return this.countCompositingAlphaRatio(backgroundAlpha, sourceAlpha);
    }
    
    protected ImagePixelARGB compositingPixel(ImagePixelARGB backgroundPixel, ImagePixelARGB sourcePixel){
        Double outAlphaRatio = this.countCompositingAlphaRatio(backgroundPixel, sourcePixel);
        Double backgroundAlphaRatio = backgroundPixel.getAlpha() / 256.0;
        Double sourcePixelAlphaRatio = sourcePixel.getAlpha() / 256.0;
        
        Integer red = this.countCompositingColor(backgroundPixel.getRed(), backgroundAlphaRatio, sourcePixel.getRed(), sourcePixelAlphaRatio, outAlphaRatio);
        Integer green = this.countCompositingColor(backgroundPixel.getGreen(), backgroundAlphaRatio, sourcePixel.getGreen(), sourcePixelAlphaRatio, outAlphaRatio);
        Integer blue = this.countCompositingColor(backgroundPixel.getBlue(), backgroundAlphaRatio, sourcePixel.getBlue(), sourcePixelAlphaRatio, outAlphaRatio);
        Integer alpha = ((Double)(outAlphaRatio * 256.0)).intValue();
        ImagePixelARGB newImagePixelARGB = new ImagePixelARGB(alpha, red, green, blue);
        
        return newImagePixelARGB;
    }
    
    protected Integer countCompositingColor(Integer backgroundColor, Double backgroundAlphaRatio, Integer sourceColor, Double sourceAlphaRatio, Double outAlphaRatio){
        if(outAlphaRatio == 0.0){
            return 0;
        } 
//        Double doubleResult = (backgroundColor * backgroundAlphaRatio + sourceColor * sourceAlphaRatio * (1 - backgroundAlphaRatio)) / outAlphaRatio;
        Double doubleResult = (sourceColor * sourceAlphaRatio + backgroundColor * backgroundAlphaRatio * (1.0 - sourceAlphaRatio)) / outAlphaRatio;

        return doubleResult.intValue();
    }
}
