/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.module.intf;

import java.util.List;
import tw.dev.tomoaki.article.ArticleCreator;

/**
 *
 * @author tomoaki
 * @param <T> 指定的資料型態
 * 
 */
public abstract class ArticleEntityDataTokenRuleModule<T> implements ArticleTokenModule {
    
    public abstract void addRule(ArticleCreator creator, T data);
    
//    public abstract void addRule(ArticleCreator creator, List<T> dataList);        
    
    @Override
    public String toString() {
        return this.getClass().getName();
    }    
}
