/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.util.Objects;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;
import tw.dev.tomoaki.util.regularexpression.helper.RegExpCommonPattern;
import tw.dev.tomoaki.util.regularexpression.impl.UnicodeValidateHelper;

/**
 *
 * @author arche
 */
public class CommonPatternMain {

    public static void main(String[] args) {
        test4();
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

    protected static void test4() {
        final String errorArticle = "資創中心支付112年​​12月正編人員薪俸扣項";
        RegExpProcessor regExpProcessor = RegExpProcessor.Factory.create(RegExpCommonPattern.INVISIBLE_OR_UNUSED);
        RegExpResult matchResult = regExpProcessor.processMatch(errorArticle);
        System.out.println(matchResult.isFind());
//        matchResult.getMatchResults().forEach(System.out::println);
        String fixedArticle = errorArticle.replaceAll(RegExpCommonPattern.INVISIBLE_OR_UNUSED, "");
        String msg = "errorArticle= %s, fixedArticle= %s, equal? %s. Length of errorArticle= %s, Length of fixedArticle= %s";
        System.out.println(String.format(msg, errorArticle, fixedArticle, Objects.equals(errorArticle, fixedArticle), errorArticle.length(), fixedArticle.length()));

//        System.out.println(UnicodeValidateHelper.hasIllegalCharacter(errorArticle));
    }
}
