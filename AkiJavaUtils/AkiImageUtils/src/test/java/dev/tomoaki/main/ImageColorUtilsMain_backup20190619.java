/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.tomoaki.main;

import dev.tomoaki.imageutils.ImageIOUtils;
import dev.tomoaki.imageutils.ImageUtilsException;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 *
 * @author arche_000
 */
public class ImageColorUtilsMain_backup20190619 {

    public static void main(String[] args) throws ImageUtilsException {
        Scanner scanner = new Scanner(System.in);
//        while (true) {
        try {
            String filePath = "D:\\ProTest\\test-small.bmp";//scanner.next();
//        File file = new File(filePath);
            BufferedImage image = ImageIOUtils.getBufferedImage(filePath);

            System.out.println("image type : " + image.getType());
            ColorModel colorModel = image.getColorModel();
            Integer width = image.getWidth();
            Integer height = image.getHeight();
            for (Integer x = 0; x < width; x++) {
                for (Integer y = 0; y < height; y++) {
//                        Integer rgb = image.getRGB(x, y);
//                        System.out.print(rgb + " ");
                    //image.getColorModel().getRed(0)
                    Integer rgb = image.getRGB(x, y);
//                        System.out.println("temp.byteValue() " + ByteBuffer.allocate(8).putInt(temp).array()); byte 直接印出
                    byte[] rgbByte = ByteBuffer.allocate(3).putInt(rgb).array();   //用多少byte 表示 要決定 

                    System.out.print("(");
                    System.out.print(colorModel.getRed(rgbByte));
                    System.out.print(", ");
                    System.out.print(colorModel.getGreen(rgbByte));
                    System.out.print(", ");
                    System.out.print(colorModel.getBlue(rgbByte));
                    System.out.print(") ");
//                        

                }
                System.out.println("\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // }
    }

    public static void test(ColorModel colorModel, Integer rgb) {
        byte[] rgbByte = ByteBuffer.allocate(3).putInt(rgb).array();   //用多少byte 表示 要決定 

        System.out.print("(");
        System.out.print(colorModel.getRed(rgbByte));
        System.out.print(", ");
        System.out.print(colorModel.getGreen(rgbByte));
        System.out.print(", ");
        System.out.print(colorModel.getBlue(rgbByte));
        System.out.print(") ");
    }
}
