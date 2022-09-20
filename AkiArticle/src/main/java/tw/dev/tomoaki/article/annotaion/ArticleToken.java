/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.annotaion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author Tomoaki Chen
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ArticleToken {

    String summary() default "";
    String description() default "";
    int level() default 1; //java 除了一般變數型態，其他只吃 String, Class
}
