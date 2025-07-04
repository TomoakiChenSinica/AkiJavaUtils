/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import tw.dev.tomoaki.util.web.request.ProxyRequestHelper;

/**
 *
 * @author Tomoaki Chen
 */
public class AppUrlProvider {

    public static String[] urlHeaderList = {"https://", "http://"};

    private HttpServletRequest initRequest;
    private String url = "";
    private String protocol = "";
    private String hostName = "";  //domain name，即該server的名稱
    private Integer port = null; // 80;
    private String contextPath = "";
    private String pathInfo = "";
    
    private List<String> hostHeaderNames;
    private List<String> portHeaderNames;

    protected AppUrlProvider(HttpServletRequest request) {
        this.initRequest = request;
    }

    public static AppUrlProvider create(HttpServletRequest initRequest) {
        return AppUrlProvider.create(initRequest, false);
    }

    public static AppUrlProvider create(HttpServletRequest initRequest, Boolean isUnderProxy) {
        AppUrlProvider urlProvider = new AppUrlProvider(initRequest);
        urlProvider.doParseRequestInfo(isUnderProxy);
        return urlProvider;
    }

//<editor-fold defaultstate="collapsed" desc="內部 Methods - 設定、初始化變數 Methods">
    protected void doParseRequestInfo(Boolean isUnderProxy) {
        /*
        this.protocol = initRequest.getScheme();
        this.hostName = initRequest.getServerName();
        this.port = isUnderProxy ? ProxyRequestHelper.obtainServerPort(initRequest) : initRequest.getServerPort();
        if (initRequest.getContextPath() != null) {
            this.contextPath = initRequest.getContextPath().replaceAll("/", "");
        } */
        this.hostName = initRequest.getServerName();
        this.port = this.obtainPort(isUnderProxy);
        this.protocol = this.obtainProtocol(isUnderProxy);
        if (initRequest.getContextPath() != null) {
            this.contextPath = initRequest.getContextPath().replaceAll("/", "");
        }
        //fffffffff        
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="外部可以呼叫的 Methods">
    public String appendUrl(String tempPathInfo) {
        String theURL = this.obtainSystemRootPath();
        if (tempPathInfo.charAt(0) == '/') {
            this.pathInfo = tempPathInfo.substring(1);
        } else {
            this.pathInfo = tempPathInfo;
        }
        theURL += this.pathInfo;
        //theURL += request.getRequestURI();
        return theURL;
    }
    
    public String obtainRootUrl() {
        return this.obtainSystemRootPath();
    }

    public UrlAppender newUrlAppender() {
        UrlAppender appender = UrlAppender.create();
        appender.append(this.obtainSystemRootPath());
        return appender;
    }

    @Override
    public String toString() {
        String msgFmt = "%s[protocol= %s, hostName= %s, port= %s, contextPath= %s]";
        return String.format(msgFmt, getClass().getName(), protocol, hostName, port, contextPath);
    }
//</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="其他輔助 Methods">
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

//<editor-fold defaultstate="collapsed" desc="內部 Methods - 產生資料用">
    protected /*public*/ String obtainSystemRootPath() {
        String rootPath = "";
        rootPath += this.protocol + "://";
        rootPath += this.hostName;
        if (this.port != 80 && this.port != 443) {
            rootPath += ":" + this.port + "/";
        } else {
            rootPath += "/";
        }

        if (!"".equals(this.contextPath)) {
            rootPath += this.contextPath + "/";
        }
        return rootPath;
    }

    protected Integer obtainPort(Boolean isUnderProxy) {
        return isUnderProxy ? ProxyRequestHelper.obtainServerPort(initRequest) : initRequest.getServerPort();
    }

    protected String obtainProtocol(Boolean isUnderProxy) {
        if (isUnderProxy) {
            String tempProtocol = this.obtainProtocolWithPort();
            if (tempProtocol != null) {
                return tempProtocol;
            }
        }        
        return initRequest.getScheme();
    }

    protected String obtainProtocolWithPort() {
        if (port == null) {
            return null;
        }

        switch (port) {
            case 80: {
                return "http";
            }
            case 443: {
                return "https";
            }
            default: {
                return null;
            }
        }
    }
//</editor-fold>

    
}
