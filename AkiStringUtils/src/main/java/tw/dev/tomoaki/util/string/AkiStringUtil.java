/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.string;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author tomoaki
 */
public class AkiStringUtil {

    public static String shorten(String oriStr, Integer keepLength) {
        Integer oriStrLength = oriStr.length();
        if (keepLength >= oriStrLength) {
            return oriStr;
        }

        Integer prefixKeepLength = keepLength / 2;
        Integer suffixKeepLength = keepLength - prefixKeepLength;

        String prefix = oriStr.substring(0, prefixKeepLength);
        String suffix = oriStr.substring(oriStrLength - suffixKeepLength, oriStrLength);
        return prefix + "..." + suffix;

    }

    public static String shorten(String oriStr, Integer prefixKeepLength, Integer suffixKeepLength) {
        return shorten(oriStr, prefixKeepLength, suffixKeepLength, "...");
    }

    public static String shorten(String oriStr, Integer prefixKeepLength, Integer suffixKeepLength, String omitSymbol) {
        Integer oriStrLength = oriStr.length();
        if (prefixKeepLength + suffixKeepLength >= oriStrLength) {
            return oriStr;
        } else {
            String prefix = oriStr.substring(0, prefixKeepLength);
            String suffix = oriStr.substring(oriStrLength - suffixKeepLength, oriStrLength);
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

    public static Boolean notEmptyStrictly(String text) {
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

    public static String capitalizeHeader(String oriText, Integer charCount) {
        String headerLetter = oriText.substring(0, 0 + charCount);
        String otherLetters = oriText.substring(charCount);
        return headerLetter.toUpperCase().concat(otherLetters);

    }

    public static String concatSafely(String replaceNullText, String... texts) {
        return Stream.of(texts).map(text -> text == null ? replaceNullText : text).collect(Collectors.joining());
    }

    public static String concatWithSafely(String replaceNullText, String concatWithText, String... texts) {
        return Stream.of(texts).map(text -> text == null ? replaceNullText : text).collect(Collectors.joining(concatWithText));
    }
}
