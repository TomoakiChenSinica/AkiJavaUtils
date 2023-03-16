/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author tomoaki
 */
public class WebHelper {
    public static String getNowPage(HttpServletRequest request){
        return request.getRequestURI();
    }
    
    public static String getQueryString(HttpServletRequest request){
        return request.getQueryString();
    }
    
    public static String getNowPageAndQuery(HttpServletRequest request){
        String nowPage = WebHelper.getNowPage(request);
        String query =  WebHelper.getQueryString(request);
        String result;
        if(query != null){
            result = nowPage + "?" + query;
        } else {
            result = nowPage;
        }
        return result;
    }
}
