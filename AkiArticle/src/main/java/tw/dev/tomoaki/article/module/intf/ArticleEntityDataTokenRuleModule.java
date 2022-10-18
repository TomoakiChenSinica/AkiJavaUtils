/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.module.intf;

import java.util.List;
import java.util.Map;
import tw.dev.tomoaki.article.ArticleCreator;

/**
 *
 * @author tomoaki
 */
public abstract class ArticleEntityDataTokenRuleModule<T> extends ArticleTokenModule {
    
    
    public abstract void doSetupRule(T data);
    
    public abstract void doSetupRule(List<T> dataList);        
}
