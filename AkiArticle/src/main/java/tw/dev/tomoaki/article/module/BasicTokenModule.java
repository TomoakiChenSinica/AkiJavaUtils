/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.module;

import java.time.LocalDate;
import tw.dev.tomoaki.article.ArticleCreator;
import tw.dev.tomoaki.article.annotaion.ArticleToken;
import tw.dev.tomoaki.article.module.intf.ArticleIndependentTokenRuleModule;

/**
 *
 * @author Tomoaki Chen
 */
public class BasicTokenModule extends ArticleIndependentTokenRuleModule {

    @ArticleToken(summary = "當下的西元年份", description = "當下的西元年份，此為舊token，不建議使用")
    public final static String TOKEN_OLD_NOW_YEAAR = "${NowYear}";
    @ArticleToken(summary = "當下的台灣民國年份", description = "當下的台灣民國年份，此為舊token，不建議使用")
    public final static String TOKEN_OLD_ROC_YEAR = "${rocYear}";
    @ArticleToken(summary = "當下的西元年份", description = "當下的西元年份")
    public final static String TOKEN_NOW_YEAR = "${Common.NowYear}";
    @ArticleToken(summary = "當下的台灣民國年份", description = "當下的台灣民國年份")
    public final static String TOKEN_NOW_ROC_YEAR = "${Common.NowRocYear}";

    @Override
    public void addRule(ArticleCreator creator) {
        LocalDate today = LocalDate.now();
        Integer thisYear = today.getYear();
        Integer taiwanThisYear = thisYear - 1911;
        creator.addTokenReplaceRule(TOKEN_OLD_NOW_YEAAR, thisYear);
        creator.addTokenReplaceRule(TOKEN_OLD_ROC_YEAR, taiwanThisYear);
        creator.addTokenReplaceRule(TOKEN_NOW_YEAR, thisYear);
        creator.addTokenReplaceRule(TOKEN_NOW_ROC_YEAR, taiwanThisYear);
    }
    
}
