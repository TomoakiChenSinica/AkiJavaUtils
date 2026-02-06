/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.lab;

import java.util.stream.Stream;
import tw.dev.tomoaki.util.web.UrlAppender;

/**
 *
 * @author Tomoaki Chen
 */
public class UrlAppenderTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        test1();
        test2();
        test3();*/
        
        test4();
    }

    private static void test1() {
        UrlAppender appender = UrlAppender.create();
        String url = appender.append("https://eform.iis.sincia.edu.tw/")
                .append("")
                .append("/test")
                .append("\\123")
                .buildUrl();
        System.out.println("url= " + url);

        String url2 = appender.buildUrl();
        System.out.println("url2= " + url2);
    }

    private static void test2() {
        UrlAppender appender = UrlAppender.create("https://eform.iis.sincia.edu.tw/");
        // UrlAppender appender = UrlAppender.Factory.create("https://eform.iis.sincia.edu.tw");
        String url = appender.append("")
                .append("/test")
                .append("\\123")
                .buildUrl();
        System.out.println("url= " + url);

        String url2 = appender.buildUrl();
        System.out.println("url2= " + url2);
    }
    
    private static void test3() {
        UrlAppender appender = UrlAppender.create("https://eform.iis.sincia.edu.tw/123");
        String url = appender.append("")
                .append("/test/456")
                .append("\\789")
                .buildUrl();
        System.out.println("url= " + url);

        String url2 = appender.buildUrl();
        System.out.println("url2= " + url2);
    }
    
    private static void test4() {
       String[] paths = {"https://DATA/Files", "////DATA/Files"};
       Stream.of(paths)
             .map(path -> path.replaceAll("[^:]/{2,}", "/"))
             .forEach(path -> System.out.println("path= " + path));      
    }
    
}
// 晚九