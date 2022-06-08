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

    private HttpServletRequest request;
    private String url = "";
    private String protocol = "";    //http or https or something else
    private String hostName = "";    //domain name，即該server的名稱
    private Integer port = 80;           //project 所用的 port

    private String contextPath = ""; //project 目錄 (例如 bpm-web)
    //private String pathName;  //頁面名稱，一般是URL
    private String systemPath = "";
    private String localPath = "";

    protected UrlProvider() {
    }

    protected UrlProvider(String protocol, String hostName, Integer port, String contextPath) {
        this.protocol = protocol;
        this.hostName = hostName;
        this.port = port;
        this.contextPath = contextPath;
    }

    public static class Factory {

        public static UrlProvider create(HttpServletRequest request) {
            UrlProvider provider = new UrlProvider();
            provider.request = request;
            provider.doParseRequestInfo();
            return provider;
        }

        public static UrlProvider create(String protocol, String hostName, Integer port, String contextPath) {
            UrlProvider provider = new UrlProvider(protocol, hostName, port, contextPath);
            return provider;
        }
    }

//<editor-fold defaultstate="collapsed" desc="設定變數">
    protected void doParseRequestInfo() {
        this.protocol = request.getScheme();
        this.hostName = request.getServerName();
        this.port = request.getServerPort();
        this.contextPath = request.getContextPath().replaceAll("/", "");
    }

//</editor-fold>
    protected void setupWebRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getLocalPath() {
        if (localPath == null || "".equals(localPath)) {
            localPath = UrlProvider.createLocalPath(protocol, hostName, port, contextPath);
        }
        return localPath;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getHostName() {
        return this.hostName;
    }

    public Integer getPort() {
        return this.port;
    }

    public String getContextPath() {
        return this.contextPath;
    }

    public String appendUrl(String tempPathInfo) {
        String theURL = "";

        theURL += this.localPath + "/";

        if (tempPathInfo.charAt(0) == '/') {
            this.systemPath = tempPathInfo.substring(1);
        } else {
            this.systemPath = tempPathInfo;
        }

        theURL += this.systemPath;
//        System.out.println(theURL);
        return theURL;
    }

    public static String createLocalPath(String protocol, String hostName, Integer port, String contextPath) {
        String localPath = "";
        localPath += protocol + "://";
        localPath += hostName;
        if (port == null) {//其實不太可能發生
            localPath += "/";
        } else if (port != 80) {
            localPath += ":" + port + "/";
        } else {
            localPath += "/";
        }

//        if(port != 80)
//            localPath += ":" + port + "/";
        if (contextPath != "") {
            localPath += contextPath;
        }
        return localPath;
    }
}
