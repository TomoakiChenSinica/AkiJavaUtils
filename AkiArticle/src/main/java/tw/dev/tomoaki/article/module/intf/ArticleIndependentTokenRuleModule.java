/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.module.intf;

import tw.dev.tomoaki.article.entity.ArticleTokenMap;
import tw.dev.tomoaki.article.helper.ArticleHelper;

/**
 *
 * @author tomoaki
 */
public abstract class ArticleIndependentTokenRuleModule extends ArticleTokenModule {
    
    public abstract void doSetupRule();

}
