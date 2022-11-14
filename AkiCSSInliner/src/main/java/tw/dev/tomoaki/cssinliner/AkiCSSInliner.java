/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package tw.dev.tomoaki.cssinliner;

import com.gargoylesoftware.css.dom.CSSRuleListImpl;
import com.gargoylesoftware.css.dom.CSSStyleRuleImpl;
import com.gargoylesoftware.css.dom.CSSStyleSheetImpl;
import com.gargoylesoftware.css.parser.CSSOMParser;
import com.gargoylesoftware.css.parser.InputSource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import tw.dev.tomoaki.cssinliner.entity.CSSElement;
import tw.dev.tomoaki.cssinliner.helper.HtmlDocumentHelper;
import tw.dev.tomoaki.cssinliner.helper.InputSourceHelper;

/**
 *
 * @author Tomoaki Chen
 *
 * https://github.com/lodenrogue/CSS-Inliner/blob/master/src/com/lodenrogue/cssinliner/CssInliner.java
 * https://stackoverflow.com/questions/4521557/automatically-convert-style-sheets-to-inline-style
 */
public class AkiCSSInliner {

    private Boolean removeClass = true;
    private Boolean removeStyleElement = true;

    protected AkiCSSInliner() {
    }

    public static class Factory {

        public static AkiCSSInliner create() {
            AkiCSSInliner inliner = new AkiCSSInliner();
            return inliner;
        }

        public static AkiCSSInliner create(Boolean removeClass, Boolean removeStyleElement) {
            AkiCSSInliner inliner = new AkiCSSInliner();
            inliner.removeClass = removeClass;
            inliner.removeStyleElement = removeStyleElement;
            return inliner;
        }
    }

    public String inlineStyle(String html) throws IOException {
        //https://stackoverflow.com/questions/26173769/how-to-avoid-surrounding-html-head-tags-in-jsoup-parse
        Document doc = Jsoup.parse(html);
//        Document doc = Jsoup.parse(html, Parser.xmlParser());
//        Document doc = Jsoup.parseBodyFragment(html);
        List<CSSElement> cssElementList = this.obtainRuleList(doc);
//        cssElementList.forEach(cssElement -> System.out.println(cssElement.toInlineStyle()) );
        for (CSSElement cssElement : cssElementList) {
            String cssSelector = cssElement.getSelector();
            String cssInlineStyle = cssElement.toInlineStyle();
            Elements elements = doc.select(cssSelector);
            elements = HtmlDocumentHelper.appendInlineStyle(elements, cssInlineStyle);
//            elements.forEach(element -> System.out.println(element));
            this.tryRemoveClass(elements, cssSelector);
            this.tryRemoveStyleElement(doc);
        }
        return doc.toString();
    }

    protected Elements tryRemoveClass(Elements elements, String cssClassSelector) {
        if (removeClass) {
            String cssClassName = cssClassSelector.replace("*.", "");
            elements = elements.removeClass(cssClassName);
        }
        return elements;
    }

    protected void tryRemoveStyleElement(Document doc) {
        if (removeStyleElement) {
            Elements elements = doc.select("style");
            elements.remove();
        }
    }

    public List<CSSElement> obtainRuleList(String html) throws IOException {
        Document doc = Jsoup.parse(html);
        return this.obtainRuleList(doc);
    }

    public List<CSSElement> obtainRuleList(Document doc) throws IOException {
        String cssCode = HtmlDocumentHelper.obtainCssCode(doc);

        CSSOMParser parser = new CSSOMParser();
        InputSource source = InputSourceHelper.createSource(cssCode);
        CSSStyleSheetImpl styleSheet = parser.parseStyleSheet(source, null);

        CSSRuleListImpl rules = styleSheet.getCssRules();
        List<CSSStyleRuleImpl> ruleList = rules.getRules().stream().map(data -> (CSSStyleRuleImpl) data).collect(Collectors.toList());
        List<CSSElement> cssList = ruleList.stream().map(rule -> {
            String selector = rule.getSelectorText();
            CSSElement cssElement = CSSElement.Factory.create(selector);
            rule.getStyle().getProperties().forEach(property -> {
                String name = property.getName();
//                String value = property.getValue().getStringValue(); //遇到比如 16px會錯
                String value = property.getValue().getCssText();
                cssElement.addStyle(name, value);
            });
            return cssElement;
        }).collect(Collectors.toList());
        return cssList;
    }

//    public CSSElement obtainRule(CSSStyleRuleImpl ruleImpl) {
//    }

    /*
    public List<CSSElement> obtainRuleList(String html) throws IOException {
        List<CSSElement> cssList = new ArrayList();
        Document doc = Jsoup.parse(html);
        String cssCode = HtmlDocumentHelper.obtainCssCode(doc);
        
        CSSOMParser parser = new CSSOMParser();
        InputSource source = InputSourceHelper.createSource(cssCode);
        CSSStyleSheetImpl styleSheet = parser.parseStyleSheet(source, null);
        
        CSSRuleListImpl rules = styleSheet.getCssRules();        
        List<CSSStyleRuleImpl> ruleList = rules.getRules().stream().map(data -> (CSSStyleRuleImpl) data).collect(Collectors.toList());
        ruleList.forEach(data -> {
//            System.out.println("selectorText= " + data.getSelectorText());
            String selector = data.getSelectorText();
            CSSElement cssElement = CSSElement.Factory.create(selector);
//            Elements elements = doc.select(data.getSelectorText());
//            elements.forEach(element -> System.out.println("docElement= " + element));
            
//            System.out.println("cssText= " + data.getCssText());
//            data.getSelectors().forEach(selector -> System::out::println);
            data.getStyle().getProperties().forEach(property -> {
//                System.out.println("name= " + property.getName());
//                System.out.println("value(CssText)= " + property.getValue().getCssText());
//                System.out.println("value(StringValue)= " + property.getValue().getStringValue());
                cssElement.addStyle(property.getName(), property.getValue().getStringValue());
            });
        });     
        return null;
    }    
     */
}
