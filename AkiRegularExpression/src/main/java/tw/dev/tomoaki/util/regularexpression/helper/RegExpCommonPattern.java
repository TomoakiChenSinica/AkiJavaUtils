/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.regularexpression.helper;

/**
 *
 * @author Tomoaki Chen
 */
public class RegExpCommonPattern {
    
    /**
     * 尋找網址的 RegExp
     * 來源： https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url
     */
    public static final String HTTP_URL = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
    
    
    public static final String PURE_HTML_URL = "(https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*))";
    
    
    public static final String FILE_SUFFIX = "[^\\.]*$";
    
}
