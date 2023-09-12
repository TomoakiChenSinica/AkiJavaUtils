/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.commondatavalidator;

/**
 *
 * @author Tomoaki Chen
 */
public class StringValidator {
    
    public static Boolean isValueExist(String text) {
        return text != null && !text.isEmpty();
    }
    
    public static Boolean isValueNotExist(String text) {
        return text == null || text.isEmpty();
    }
    
    public static Boolean isValueTrimExist(String text) {
        return text != null && !text.trim().isEmpty(); /*!text.isBlank();*/
    }
    
    public static Boolean isValueTrimNotExist(String text) {
        return text == null || text.trim().isEmpty();
    }
    
    
    public static Boolean isHtmlEmpty(String htmlText) {
        throw new UnsupportedOperationException("Method Not Supported Yet");
//        if(htmlText==null)
//            return true;
//        
//        String tmp = htmlText;
//        
//        tmp = StringEscapeUtils.unescapeHtml(tmp);
//        
//        tmp = tmp.replace("\r", "");
//        tmp = tmp.replace("\n", "");
//        
//        tmp = tmp.replaceAll("<(\\\\){0,1}.*?>", "");
//        tmp = tmp.replaceAll("([\\s\\t\\f\\n\\u00A0]+)", "");
//        
//        return tmp.isEmpty();
//        return null;
    }
}
