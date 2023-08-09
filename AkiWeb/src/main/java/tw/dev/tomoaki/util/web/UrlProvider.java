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
public class UrlProvider {

    public static String[] urlHeaderList = {"https://", "http://"};
    
    private HttpServletRequest request;
    private String url = "";
    private String protocol = "";
    private String hostName = "";  //domain name，即該server的名稱
    private Integer port = 80;
    private String contextPath = "";
    private String pathInfo = "";

    protected UrlProvider(HttpServletRequest request) {
        this.request = request;
    }

    public static class Factory {

        public static UrlProvider create(HttpServletRequest request) {
            UrlProvider urlProvider = new UrlProvider(request);
            urlProvider.doParseRequestInfo();
            return urlProvider;
        }
    }

    protected void doParseRequestInfo() {
        this.protocol = request.getScheme();
        this.hostName = request.getServerName();
        this.port = request.getServerPort();
        if (request.getContextPath() != null) {
            this.contextPath = request.getContextPath().replaceAll("/", "");
        }
    }


    public String getURL() {
        return null;
    }

    public String obtainSystemRootPath() {
        String rootPath = "";
        rootPath += this.protocol + "://";
        rootPath += this.hostName;
        if (this.port != 80 && this.port != 443) {
            rootPath += ":" + this.port + "/";
        } else {
            rootPath += "/";
        }

        if (this.contextPath != "") {
            rootPath += this.contextPath + "/";
        }
        return rootPath;
    }

    public String appendUrl(String tempPathInfo) {
        String theURL = this.obtainSystemRootPath();
//        theURL += this.protocol + "://";
//        theURL += this.hostName;
//        if (this.port != 80 && this.port != 443) {
//            theURL += ":" + this.port + "/";
//        } else {
//            theURL += "/";
//        }
//
//        if (!"".equals(this.contextPath)) {
//            theURL += this.contextPath + "/";
//        }

        if (tempPathInfo.charAt(0) == '/') {
            this.pathInfo = tempPathInfo.substring(1);
        } else {
            this.pathInfo = tempPathInfo;
        }
        theURL += this.pathInfo;
        //theURL += request.getRequestURI();
        return theURL;
    }

    public UrlAppender newUrlAppender() {
        UrlAppender appender = UrlAppender.Factory.create();
        appender.append(this.obtainSystemRootPath());
        return appender;
    }
    

//<editor-fold defaultstate="collapsed" desc="其他輔助method">
    public static String createConnectableUrl(String oriUrl) {
        String connectAbleUrl = oriUrl;
        if (checkContainsUrlHeader(oriUrl) == false) {
            connectAbleUrl = "https://" + oriUrl;
        }
        return connectAbleUrl;
    }

    protected static boolean checkContainsUrlHeader(String oriUrl) {
        for (String urlHeader : urlHeaderList) {
            if (oriUrl.contains(urlHeader)) {
                return true; //只要符合其中一個即可
            }
        }
        return false;
    }    
//</editor-fold>

}
