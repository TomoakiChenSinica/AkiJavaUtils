/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.commondatavalidator;

import java.util.Objects;
import java.util.stream.Stream;

/**
 *
 * @author Tomoaki Chen
 */
public class StringValidator {
    
    private StringValidator() {
    }

    public static Boolean isValueExist(String text) {
        return text != null && !text.isEmpty();
    }

    public static Boolean isValueNotExist(String text) {
        return text == null || text.isEmpty();
    }

    public static Boolean isValueTrimExist(String text) {
        return text != null && !text.trim().isEmpty();
        /*!text.isBlank();*/
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

    // ---------------------------------------------------------------------------------------    
    public static Boolean equalsSafely(String text1, String text2) {
        return (StringValidator.isValueTrimNotExist(text1) && StringValidator.isValueTrimNotExist(text2))
                || Objects.equals(text1, text2);
    }

    public static Boolean equalsTrimly(String text1, String text2) {
//        if (text1 == null && text2 == null) {
//            return true;
//        }
//
//        if (text1 != null && text2 == null) {
//            return false;
//        }
//
//        if (text1 != null && text2 == null) {
//            return false;
//        }
        long numsOfNull = Stream.of(text1, text2).filter(Objects::isNull).count();
        if (numsOfNull == 2) {
            return true;
        }

        if (numsOfNull == 1) {
            return false;
        }
        return Objects.equals(text1.trim(), text2.trim());
    }

    public static Boolean equalsTrimlyAndSafely(String text1, String text2) {
        return (StringValidator.isValueTrimNotExist(text1) && StringValidator.isValueTrimNotExist(text2))
                || equalsTrimly(text1, text2);
    }
}
