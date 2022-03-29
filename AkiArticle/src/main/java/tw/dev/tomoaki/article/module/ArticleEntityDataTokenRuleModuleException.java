/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.module;

/**
 *
 * @author Tomoaki Chen
 */
public class ArticleEntityDataTokenRuleModuleException extends RuntimeException{
    
    public ArticleEntityDataTokenRuleModuleException(Exception ex){
        super(ex);
    }

    public ArticleEntityDataTokenRuleModuleException(String msg){
        super(msg);
    }    
    
}
