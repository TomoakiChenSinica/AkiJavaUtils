/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.tomoaki.main;

import dev.tomoaki.imageutils.ImageInfoUtils;
import dev.tomoaki.imageutils.ImageIOUtils;
import dev.tomoaki.imageutils.ImageUtilsException;
import dev.tomoaki.imageutils.color.ImageColorUtils;
import dev.tomoaki.imageutils.color.compositing.ImageCompositingOption;
import dev.tomoaki.imageutils.color.compositing.ImageCompositingUtils;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author arche_000
 */
public class ImageColorUtilsMain {

    public static void main(String[] args) throws ImageUtilsException, IOException {
        testCompositing();
    }
    
    public static void testCompositing() throws ImageUtilsException, IOException{
        ImageColorUtils colorUtils = new ImageColorUtils();
        String backgroundImageFilePath = "D:\\ProTest\\anime-very-cute-girl-image-3840x2160.jpg";//scanner.next();
        BufferedImage backgroundImage = ImageIOUtils.getBufferedImage(backgroundImageFilePath);        
        
        String sourceImageFilePath = "D:\\ProTest\\Beauty.png";//scanner.next();
        BufferedImage sourceImage = ImageIOUtils.getBufferedImage(sourceImageFilePath);          
        
        ImageCompositingUtils utils = new ImageCompositingUtils();
        BufferedImage processedImage = utils.compositing(backgroundImage, sourceImage, 0, 0, ImageCompositingOption.LIMIT_INSIDE_BACKGROND, 64);
        ImageIO.write(processedImage, "bmp", new File("D:\\ProTest\\com.bmp"));
    }

    public static void testGray() throws IOException, ImageUtilsException {
        ImageColorUtils colorUtils = new ImageColorUtils();
        String filePath = "D:\\ProTest\\Beauty.png";//scanner.next();
        BufferedImage image = ImageIOUtils.getBufferedImage(filePath);

        BufferedImage processedImage = colorUtils.doGrayscale(image);
        ImageIO.write(processedImage, "png", new File("D:\\ProTest\\Beauty-gray.png"));
    }

    public static void test1() {
        Scanner scanner = new Scanner(System.in);
//        while (true) {
        try {
            String filePath = "D:\\ProTest\\test-smallest.png";//scanner.next();
//        File file = new File(filePath);
            BufferedImage image = ImageIOUtils.getBufferedImage(filePath);

            System.out.println("image type : " + image.getType());
            ColorModel colorModel = image.getColorModel();
            for (Integer index = 4; index <= 4; index++) {
                try {
                    test(image, colorModel, index);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // }        
    }

    public static void test(BufferedImage image, ColorModel colorModel, Integer bufferAllocate) {
        System.out.println("========================= bufferAllocate = " + bufferAllocate + " start ========================= ");
        Integer width = image.getWidth();
        Integer height = image.getHeight();
//        for (Integer x = 0; x < width; x++) {
//            for (Integer y = 0; y < height; y++) {
//
//                Integer rgb = image.getRGB(x, y);
//                byte[] rgbByte = ByteBuffer.allocate(bufferAllocate).putInt(rgb).array();   //用多少byte 表示 要決定 
//                printByteArr(rgbByte);
//
//            }
//            System.out.println("\n");
//        }

//        for (Integer x = 0; x < width; x++) {
//            for (Integer y = 0; y < height; y++) {
//                Integer rgb = image.getRGB(x, y);
//                byte[] rgbByte = ByteBuffer.allocate(bufferAllocate).putInt(rgb).array();   //用多少byte 表示 要決定 
//                printByteArr2(rgbByte);
//            }
//            System.out.println("\n");
//        }
        for (Integer x = 0; x < width; x++) {
            for (Integer y = 0; y < height; y++) {

                Integer rgb = image.getRGB(x, y);
                System.out.print(ImageInfoUtils.intPixcel2ARGB(rgb));

            }
            System.out.println("\n");
        }

//        for (Integer x = 0; x < width; x++) {
//            for (Integer y = 0; y < height; y++) {
//                Integer rgb = image.getRGB(x, y);
//                byte[] rgbByte = ByteBuffer.allocate(bufferAllocate).putInt(rgb).array();   //用多少byte 表示 要決定 
//                printByteArr3(rgbByte);
//            }
//            System.out.println("\n");
//        }
        System.out.println("========================= bufferAllocate = " + bufferAllocate + " end =========================\r\n\r\n ");

    }

    public static String getByteArr(byte[] bytes) {
        String strByte = "";
        for (Byte theByte : bytes) {
            strByte += theByte.toString();
        }
        return strByte;
    }

    public static void printByteArr(byte[] bytes) {
        System.out.print("(");
        for (Byte theByte : bytes) {
            System.out.format("0x%x ,", theByte);
        }
        System.out.print(")");

    }

    public static void printByteArr2(byte[] bytes) {
        System.out.print("(");
        for (byte theByte : bytes) {
            System.out.print(theByte + " ");
        }
        System.out.print(")");

    }

    public static void printByteArr3(byte[] bytes) {

        System.out.print("(");
        Integer count = 0;
        for (byte theByte : bytes) {
            count++;
            if (count > 1) {
                System.out.print(",");
            }
            String binaryStr = Integer.toBinaryString(Byte.toUnsignedInt(theByte));
//            System.out.print(Integer.toBinaryString(theByte) + " ");
            System.out.format("0x%x(%s)", theByte, binaryStr);
        }
        System.out.print(")  ");

    }

}
