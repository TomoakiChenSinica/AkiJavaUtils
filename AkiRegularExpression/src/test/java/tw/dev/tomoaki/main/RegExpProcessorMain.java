/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.util.regularexpression.helper.RegExpCommonPattern;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;

/**
 *
 * @author arche
 */
public class RegExpProcessorMain {

    public static void main(String[] args) {
        String article = "This is a book, https://stackoverflow.com";
        String pattern = RegExpCommonPattern.PURE_HTML_URL;
        RegExpProcessor processor = RegExpProcessor.Factory.create(pattern);
        RegExpResult result = processor.processMatch(article);
        System.out.println(result.isFind());
        System.out.println(result.getGroupResults());
        System.out.println(processor.processFormatReplace(article, "<a href=\"" + RegExpProcessor.formatReplaceToken + "\" target=\"_blank\">" + RegExpProcessor.formatReplaceToken + " </a>"));
    }

    public static void testNums() {
        String pattern = "[0-9]{2}";
        RegExpProcessor processor = RegExpProcessor.Factory.create(pattern);
        RegExpResult result = processor.processMatch("12345");
        System.out.println(result.isFind());
        System.out.println(result.getGroupResults());
    }
}
