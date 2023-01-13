/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;
import org.jsoup.select.Elements;
import tw.dev.tomoaki.cssinliner.helper.HtmlDocumentHelper;

/**
 *
 * @author Tomoaki Chen
 */
public class JSoupTestMain1 {

    public static void main(String[] args) {
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

        test3(html);
    }

    public static void test3(String html) {
        Document htmlDoc = Jsoup.parse(html);
        Elements theElements = htmlDoc.select(".highlight");
        HtmlDocumentHelper.appendInlineStyle(theElements, "background: blue;");
        System.out.println(htmlDoc.toString());

    }

    public static void test2(String html) {
        Document htmlDoc = Jsoup.parse(html);
        Elements theElements = htmlDoc.select(".highlight");
//        System.out.println(theElements);
        System.out.println(theElements.attr("class"));
        theElements.attr("id", "123");
        System.out.println(htmlDoc.toString());
    }

    public static void test1(String html) {
        System.out.println(HtmlDocumentHelper.obtainCssCode(html));
    }

    public static void test0(String html) {
        Document htmlDoc = Jsoup.parse(html);
        Elements elements = htmlDoc.select("style"); //select 抓 html 誤建有可能會抓到多個，所以用 Elements
        elements.forEach(element -> {
//            element.getAllElements().forEach(e -> System.out.println(e.data());
//            for(Element e : element.getAllElements()) {
//                System.out.println(e.data());
//            }
            System.out.println(element.data());
        });
    }
}
