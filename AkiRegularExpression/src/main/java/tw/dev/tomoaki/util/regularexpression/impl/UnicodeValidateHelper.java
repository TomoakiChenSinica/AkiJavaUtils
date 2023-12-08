package tw.dev.tomoaki.util.regularexpression.impl;

import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;
import tw.dev.tomoaki.util.regularexpression.helper.RegExpCommonPattern;

/**
 *
 * @author tomoaki
 */
public class UnicodeValidateHelper {

    private static final RegExpProcessor illegalCharProcessor = RegExpProcessor.Factory.create(RegExpCommonPattern.INVISIBLE_OR_UNUSED);

    public static Boolean hasIllegalCharacter(String article) {
//        return Pattern.matches(RegExpCommonPattern.INVISIBLE_OR_UNUSED, article);
//        return article.contains(RegExpCommonPattern.INVISIBLE_OR_UNUSED);
        RegExpResult matchResult = illegalCharProcessor.processMatch(article);
        return matchResult.isFind();
    }
}
