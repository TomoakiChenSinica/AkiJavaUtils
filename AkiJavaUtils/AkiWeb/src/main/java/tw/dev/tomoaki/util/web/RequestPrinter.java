/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Tomoaki Chen
 */
public class RequestPrinter {
    
    public static void print(HttpServletRequest request) {
        /* https://stackoverflow.com/questions/2184286/difference-between-getlocalport-and-getserverport-in-servlets */
        System.out.println("request.getContextPath() : " +  request.getContextPath());
        System.out.println("request.getLocalAddr() : " +  request.getLocalAddr());
        System.out.println("request.getLocalName() : " +  request.getLocalName());
        System.out.println("request.getPathInfo() : " + request.getPathInfo());
        System.out.println("request.getPathTranslated() : " + request.getPathTranslated());
        System.out.println("request.getRemoteAddr() : " + request.getRemoteAddr());
        System.out.println("request.getRemoteHost() : " + request.getRemoteHost());
        System.out.println("request.getRemoteUser() : " + request.getRemoteUser());
        System.out.println("request.getServerName() : " + request.getServerName());                
        System.out.println("request.getServletPath() : " + request.getServletPath());                
        System.out.println("request.getRequestURI() : " + request.getRequestURI());                
        
        System.out.println("request.getLocalPort() : " + request.getLocalPort());
        System.out.println("request.getRemotePort() : " + request.getRemotePort());        
        System.out.println("request.getLocalPort() : " + request.getServerPort());
        
    }
}
