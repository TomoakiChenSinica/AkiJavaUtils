/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.exception;

/**
 *
 * @author arche
 */
public class ArticleCreatorException extends RuntimeException {
    
    public ArticleCreatorException(Exception ex) {
        super(ex);
    }
    
    public ArticleCreatorException(String msg) {
        super(msg);
    } 
    
}
