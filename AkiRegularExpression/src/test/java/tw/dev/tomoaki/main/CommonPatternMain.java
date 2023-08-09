/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;
import tw.dev.tomoaki.util.regularexpression.helper.RegExpCommonPattern;

/**
 *
 * @author arche
 */
public class CommonPatternMain {

    public static void main(String[] args) {
        test3();
    }

    protected void test1() {
        String str = "This is a book, it's very funny. 這是一本書，非常的好笑!";
        RegExpProcessor regExpProcessor = RegExpProcessor.Factory.create(RegExpCommonPattern.PUNCTUATION);
        RegExpResult regExpResult = regExpProcessor.processMatch(str);
        regExpResult.getMatchResults().forEach(result -> {
            System.out.println(result);
        });
    }

    protected static void test2() {
        System.out.println("RegExpCommonPatter.HTTP_URL= " + RegExpCommonPattern.HTTP_URL);

    }
    
    protected static void test3() {
        RegExpProcessor regExpProcessor = RegExpProcessor.Factory.create(RegExpCommonPattern.FILE_SUFFIX);
        System.out.println(regExpProcessor.processMatch("FullText.pdf").getMatchResults().get(0));
        System.out.println(regExpProcessor.processMatch("FullText.txt").getMatchResults().get(0));
    }
}
