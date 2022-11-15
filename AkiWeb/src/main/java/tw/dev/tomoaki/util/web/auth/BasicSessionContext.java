/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import tw.dev.tomoaki.util.web.UrlAppender;

/**
 *
 * @author Tomoaki Chen
 */
public abstract class BasicSessionContext {
    
    private String lastRequestUrl;    
    private String remoateAddress;
    private Boolean isAuthorized = false;
    
    public static String sessionAttrKey = "seSSionCoNtExt";     
    
    public void init(HttpServletRequest request) {
        this.lastRequestUrl = this.obtainRequestUrlWithQueryParam(request);
        this.remoateAddress = request.getRemoteAddr();
    }

    public String getLastRequestUrl() {
        return lastRequestUrl;
    }

    public void setLastRequestUrl(String lastRequestUrl) {
        this.lastRequestUrl = lastRequestUrl;
    }

    public String getRemoateAddress() {
        return remoateAddress;
    }

    public void setRemoateAddress(String remoateAddress) {
        this.remoateAddress = remoateAddress;
    }

    public Boolean getIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(Boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }       
    
    protected String obtainRequestUrlWithQueryParam(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        String requestUrlWithQueryParam = (queryString == null || queryString.isEmpty()) ? requestUrl : requestUrl + "?" + queryString;
        return requestUrlWithQueryParam;
    }


    public static <T extends BasicSessionContext> T obtainSessionContext(HttpSession session, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        Object objSessionContext = session.getAttribute(sessionAttrKey);
//        return objSessionContext == null ? new T() : (T)objSessionContext;
        return objSessionContext == null ? clazz.newInstance() : (T)objSessionContext;
        
    }
}
