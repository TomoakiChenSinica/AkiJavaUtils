/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki;

import tw.dev.tomoaki.util.web.UrlAppender;

/**
 *
 * @author Tomoaki Chen
 */
public class UrlAppenderMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        test1();
        test2();
    }

    private static void test1() {
        UrlAppender appender = UrlAppender.Factory.create();
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
        UrlAppender appender = UrlAppender.Factory.create("https://eform.iis.sincia.edu.tw/");
        String url = appender.append("")
                .append("/test")
                .append("\\123")
                .buildUrl();
        System.out.println("url= " + url);

        String url2 = appender.buildUrl();
        System.out.println("url2= " + url2);
    }
}
