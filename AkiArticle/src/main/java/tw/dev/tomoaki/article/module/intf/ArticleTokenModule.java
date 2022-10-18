/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.module.intf;

import tw.dev.tomoaki.article.entity.ArticleTokenMap;
import tw.dev.tomoaki.article.entity.IterableArticleTokenMap;
import tw.dev.tomoaki.article.helper.ArticleHelper;

/**
 *
 * @author Tomoaki Chen
 */
public abstract class ArticleTokenModule {
 
    protected IterableArticleTokenMap moduleArticleTokenMap;    
    
    public ArticleTokenModule() {
        this.moduleArticleTokenMap = new IterableArticleTokenMap();
    }
    
    public String replaceToken(String oriArticle) {
        return ArticleHelper.replaceTokens(oriArticle, moduleArticleTokenMap);
    }    

    public IterableArticleTokenMap getModuleArticleTokenMap() {
        return moduleArticleTokenMap;
    }
    
    
    
    protected void doAddRule(String strToken, Long longWord) {
        this.moduleArticleTokenMap.put(strToken, longWord);
    }
    
    protected void doAddRule(String strToken, Integer intWord) {
        this.moduleArticleTokenMap.put(strToken, intWord);
    }
    
    
    protected void doAddRule(String strToken, String word) {
        this.moduleArticleTokenMap.put(strToken, word);  
    }
    
    protected String obtainTokenWithIndex(String strToken, Long index) {
        return String.format("%s[%s]", strToken, index);
    }
}
