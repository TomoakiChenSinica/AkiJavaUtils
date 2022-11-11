/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main.ccsparser;

import com.gargoylesoftware.css.dom.CSSRuleListImpl;
import com.gargoylesoftware.css.dom.CSSStyleRuleImpl;
import com.gargoylesoftware.css.dom.CSSStyleSheetImpl;
import com.gargoylesoftware.css.parser.CSSOMParser;
import com.gargoylesoftware.css.parser.InputSource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import tw.dev.tomoaki.cssinliner.helper.InputSourceHelper;

/**
 *
 * @author Tomoaki Chen
 */
public class CCSParserTestMain1 {

    public static void main(String[] args) throws IOException {
        String css = ".title {"
                + " font-weight: bold;"
                + " color: red;"
                + "}";
        CSSOMParser parser = new CSSOMParser();
        InputSource source = InputSourceHelper.createSource(css);
        CSSStyleSheetImpl styleSheet = parser.parseStyleSheet(source, null);

        CSSRuleListImpl rules = styleSheet.getCssRules();
        List<CSSStyleRuleImpl> ruleList = rules.getRules().stream().map(data -> (CSSStyleRuleImpl) data).collect(Collectors.toList());
        ruleList.forEach(data -> {
            System.out.println("selectorText= " + data.getSelectorText());
            System.out.println("cssText= " + data.getCssText());
            data.getStyle().getProperties().forEach(property -> {
                System.out.println("name= " + property.getName());
                System.out.println("value(CssText)= " + property.getValue().getCssText());
                System.out.println("value(StringValue)= " + property.getValue().getStringValue());
                
            });
        });

    }
}
