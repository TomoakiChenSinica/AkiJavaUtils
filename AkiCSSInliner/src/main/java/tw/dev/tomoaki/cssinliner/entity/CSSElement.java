/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.cssinliner.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomoaki Chen
 */
public class CSSElement {

    private String selector;
    private List<CSSStyle> styleList;

    protected CSSElement() {
        this.styleList = new ArrayList();
    }

    public static class Factory {

        public static CSSElement create(String selector) {
            CSSElement element = new CSSElement();
            element.selector = selector;
            return element;
        }
    }
    
    public void addStyle(String name, String value) {
        CSSStyle style = new CSSStyle();
        style.setName(name);
        style.setValue(value);
        this.styleList.add(style);
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public List<CSSStyle> getStyleList() {
        return styleList;
    }

    public void setStyleList(List<CSSStyle> styleList) {
        this.styleList = styleList;
    }
    
    public String toInlineStyle() {
        String inlineStyle = "";
//        this.styleList.forEach(cssStyle -> inlineStyle += cssStyle + ";");
        for(CSSStyle cssStyle : this.styleList) {
            inlineStyle += cssStyle;
        }
        return inlineStyle;
    }
    
}
