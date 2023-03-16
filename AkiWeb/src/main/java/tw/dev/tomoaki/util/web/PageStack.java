/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web;

import java.util.Stack;

/**
 *
 * @author Tomoaki Chen
 */
public class PageStack extends Stack<PageInfo> {
    
    public void push(String url) {
        PageInfo info = new PageInfo();
        info.setUrl(url);
        this.push(info);
    }
}
