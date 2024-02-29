/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.string;

/**
 *
 * @author tomoaki
 */
public class AkiStringUtil {

    public static String shortStr(String oriStr, Integer keepLength) {
        Integer oriStrLength = oriStr.length();
        if (keepLength >= oriStrLength) {
            return oriStr;
        }

        Integer prefixLength = keepLength / 2;
        Integer suffixLength = keepLength - prefixLength;

        String prefix = oriStr.substring(0, prefixLength);
        String suffix = oriStr.substring(oriStrLength - suffixLength, oriStrLength);
        return prefix + "..." + suffix;

    }

    public static String shortStr(String oriStr, Integer prefixLength, Integer suffixLength) {
        return shortStr(oriStr, prefixLength, suffixLength, "‧‧‧");
    }

    public static String shortStr(String oriStr, Integer prefixLength, Integer suffixLength, String omitSymbol) {
        Integer oriStrLength = oriStr.length();
        if (prefixLength + suffixLength >= oriStrLength) {
            return oriStr;
        } else {
            String prefix = oriStr.substring(0, prefixLength);
            String suffix = oriStr.substring(oriStrLength - suffixLength, oriStrLength);
//            return prefix + "‧‧‧" + suffix;            
            return prefix + omitSymbol + suffix;
        }
    }

    public static String parseHtmlFormat(String oriStr) {
        if (oriStr == null) {
            return null;
        }

        String newStr = oriStr;
        newStr = newStr.replace("\\r\\n", "<br/>");
        newStr = newStr.replace("\\r", "<br/>");
        newStr = newStr.replace("\\n", "<br/>");
        //以上 這組叫 Escape sequences https://en.wikipedia.org/wiki/Escape_sequences_in_C、https://www.geeksforgeeks.org/escape-sequences-in-java/
        return newStr;
    }

    public static String replaceCharAt(String oriText, Integer position, String replaceText) {
        if (oriText == null) {
            return null;
        }

        String preStr = oriText.substring(0, position); //包頭去尾
        String sufStr = oriText.substring(position + 1, oriText.length());
        return preStr + replaceText + sufStr;
    }

    public static Boolean strictNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    public static String trimSafely(String str) {
        return (str == null) ? null : str.trim();
    }

    public static String trimUnicodeIllegal(String str) {
        return str.replaceAll("\\p{C}", ""); //詳情請看 AkiRegularExpression 的 RegExpCommonPattern 中關於 Unicode，下半段
    }

    public static String trimUnicodeIllegalSafely(String str) {
        return (str == null) ? null : str.replaceAll("\\p{C}", ""); //詳情請看 AkiRegularExpression 的 RegExpCommonPattern 中關於 Unicode，下半段
    }
    
    /*
    public static String capitalize(String oriText, Integer start, Integer end) {
        
        String headLetter = fieldName.substring(0, 0);
        String otherLetters = fieldName.substring(1);
        return GETTER_PREFIX.concat(headLetter.toUpperCase()).concat(otherLetters);
        
    }
    */
    public static String capitalizeHeader(String oriText, Integer charCount) {
        String headerLetter = oriText.substring(0, 0 + charCount);
        String otherLetters = oriText.substring(charCount);       
        return headerLetter.toUpperCase().concat(otherLetters);
        
    }    
}
