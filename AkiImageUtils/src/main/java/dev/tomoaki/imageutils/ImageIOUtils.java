/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.tomoaki.imageutils;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author arche_000
 */
public class ImageIOUtils {

    public static BufferedImage getBufferedImage(String filePath) throws ImageUtilsException {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return ImageIO.read(file);
            } else {
                throw new ImageUtilsException("File not exist : " + filePath);
            }
        } catch (Exception ex) {
            throw new ImageUtilsException(ex);
        }
    }
}
