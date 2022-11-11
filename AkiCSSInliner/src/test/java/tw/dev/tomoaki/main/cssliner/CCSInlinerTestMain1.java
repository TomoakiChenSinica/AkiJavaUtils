/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki.main.cssliner;

import java.io.IOException;
import tw.dev.tomoaki.cssinliner.AkiCSSInliner;
import tw.dev.tomoaki.util.cast.JavaToJson;

/**
 *
 * @author Tomoaki Chen
 */
public class CCSInlinerTestMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
//        String html = "<style>"
//                + ".hightlight { \n"
//                + " color: red; \n"
//                + " font-size: 16px; \n"
//                + "} \n"
//                + "</style> \n"
//                + "<style> \n"
//                + ".bold { \n"
//                + " font-weight: bold; \n"
//                + "} \n"
//                + "</style> \n"
//                + "<div class=\"hightlight bold\"> \n"
//                + "test \n"
//                + "</div> \n";
        String html = "<style>"
                + ".highlight { \n"
                + " color: red; \n"
                + "} \n"
                + ".bigger {\n"
                + "  font-size:12px;"
                + "} \n"
                + "</style> \n"
                + "<style> \n"
                + ".bold { \n"
                + " font-weight: bold; \n"
                + "} \n"
                + "</style> \n"
                + "<div class=\"highlight bold\" style=\"font-size: 24px;\"> \n"
                + "test \n"
                + "</div> \n"
                + "<div class=\"highlight bigger\"> \n"
                + "test2 \n"
                + "</div> \n";   
        System.out.println("html= \n" + html);
        AkiCSSInliner inliner = new AkiCSSInliner();
        ///System.out.println( JavaToJson.getJsonString(inliner.obtainRuleList(html)) );
        String newHtml = inliner.inlineStyle(html);
        System.out.println("newHtml= \n" + newHtml);
    }
    
}
