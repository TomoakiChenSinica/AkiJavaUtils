/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.stringutils;

/**
 *
 * @author tomoaki
 */
public class AkiStringUtil {
    
    public static String shortStr(String oriStr, Integer keepLength){        
        Integer oriStrLength = oriStr.length();
        if(keepLength >= oriStrLength){
            return oriStr;
        }
        
        
        Integer prefixLength = keepLength / 2;
        Integer suffixLength = keepLength - prefixLength;
        
        String prefix = oriStr.substring(0, prefixLength) ;
        String suffix = oriStr.substring(oriStrLength - suffixLength,  oriStrLength);
        return prefix + "..." + suffix;
        
    }
    
    public static String shortStr(String oriStr, Integer prefixLength, Integer suffixLength){  
        return shortStr(oriStr, prefixLength, suffixLength, "‧‧‧");
    }       
    
    public static String shortStr(String oriStr, Integer prefixLength, Integer suffixLength, String omitSymbol){  
        Integer oriStrLength = oriStr.length();
        if(prefixLength + suffixLength>= oriStrLength){
            return oriStr;
        } else {
            String prefix = oriStr.substring(0, prefixLength) ;
            String suffix = oriStr.substring(oriStrLength - suffixLength,  oriStrLength);        
//            return prefix + "‧‧‧" + suffix;            
            return prefix + omitSymbol + suffix;
        }
    }    
    
    public static String parseHtmlFormat(String oriStr){
        String newStr = oriStr;
        newStr = newStr.replace("\r\n", "<br/>");
        newStr = newStr.replace("\r", "<br/>");
        return newStr;
    }
    
    public static String replaceCharAt(String oriText, Integer position, String replaceText){
        String preStr = oriText.substring(0, position); //包頭去尾
        String sufStr = oriText.substring(position + 1, oriText.length() );
        return preStr + replaceText + sufStr;
    }
    
    public static Boolean strictNotEmpty(String text){
        return text != null && !text.trim().isEmpty();                   
    }
}
