/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.regularexpression.helper;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Tomoaki Chen
 */
public class RegExpCommonPattern {

    /**
     * 尋找網址的 RegExp 來源：
     * https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url
     */
    public static final String HTTP_URL = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";

    public static final String PURE_HTML_URL = "(https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*))";

    public static final String FILE_SUFFIX = "[^\\.]*$";

    /**
     * https://www.shareintelli.com/1317/
     *
     * 原文: \pP 其中的小写 p 是 property 的意思，表示 Unicode 属性，用于 Unicode 正表达式的前缀。 大写 P 表示
     * Unicode 字符集七个字符属性之一：标点字符。 L：字母； M：标记符号（一般不会单独出现）； Z：分隔符（比如空格、换行等）；
     * S：符号（比如数学符号、货币符号等）； N：数字（比如阿拉伯数字、罗马数字等）； C：其他字符
     */
//    public static final String PUNCTUATION = "[\\p{P}|\\p{Z}}|\\p{N}}|\\p{S}]";
    public static final String SYMBOL = "[\\p{P}|\\p{Z}}|\\p{N}}|\\p{S}]";

    public static final String PUNCTUATION = "[\\p{P}]";

    public static final String ABBREV_END_WITH_DOT = "[\\w]+\\.";
    public static final List<String> ABBREV_PATTERNS = Arrays.asList(ABBREV_END_WITH_DOT);

}
