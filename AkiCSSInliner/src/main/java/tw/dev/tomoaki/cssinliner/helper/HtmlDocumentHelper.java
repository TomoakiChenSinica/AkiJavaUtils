/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.cssinliner.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Tomoaki Chen
 */
public class HtmlDocumentHelper {

    public static String obtainCssCode(String html) {
        Document htmlDoc = Jsoup.parse(html);
        return obtainCssCode(htmlDoc);
    }

    public static String obtainCssCode(Document htmlDoc) {
        StringBuilder bulder = new StringBuilder();
        Elements elements = htmlDoc.select("style"); //會抓到<style></style> select 抓 html 誤建有可能會抓到多個，所以用 Elements
        elements.forEach(element -> {
            String partOfCssCode = element.data();
            partOfCssCode = (partOfCssCode == null) ? "" : partOfCssCode;
            bulder.append(partOfCssCode);
        });
        return bulder.toString();
    }
//    public static String obtainCssCode(Document htmlDoc) {
//        StringBuffer buffer = new StringBuffer ();        
//        Elements elements = htmlDoc.select("style"); //select 抓 html 誤建有可能會抓到多個，所以用 Elements
//        elements.forEach(element -> {
//            String partOfCssCode = element.data();
//            partOfCssCode = (partOfCssCode == null) ? "" :  partOfCssCode;
//            buffer.append(partOfCssCode);
//        });
//        return buffer.toString();
//    }
    
    public static Elements appendInlineStyle(Elements elements, String inlineStyle) {        
        elements.forEach(element -> {
            element = appendInlineStyle(element, inlineStyle);
        });
        return elements;
    }    
   
    
    public static Element appendInlineStyle(Element element, String inlineStyle) {
        String oriInlineStyle = element.attr("style");
        oriInlineStyle += inlineStyle;
        element.attr("style", oriInlineStyle);
        return element;
    }
}
