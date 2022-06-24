/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tomoaki Chen
 */
public class UrlAppender {

    protected List<String> urlPathList;
    protected Map<String, Object> queryParamMap;

    protected Boolean trimHeadSlash = true;
    protected Boolean trimTailSlash = true;
    
    
    protected UrlAppender() {
        
    }

    public static class Factory {

        public static UrlAppender create() {
            UrlAppender appender = new UrlAppender();
            appender.initUrlPathList();
            appender.initQueryParamMap();
            return appender;
        }
    }
    
//<editor-fold defaultstate="collapsed" desc="初始化/設定 變數的 methods">
    protected void initUrlPathList() {
        this.urlPathList = new ArrayList();
    }    
    
    protected void initQueryParamMap() {
        this.queryParamMap = new LinkedHashMap();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="調整設定">
    public void turnOnTrimHeadSlash() {
        this.trimHeadSlash = true;
        
    }
    
    public void turnOffTrimHeadSlash() {
        this.trimHeadSlash = true;
    }
    
    public void turnOnTrimTailSlash() {
        this.trimTailSlash = true;
        
    }
    
    public void turnOffTrimTailSlash() {
        this.trimTailSlash = true;
    }        
//</editor-fold>
            
    public UrlAppender append(String path) {
        if(path == null) {
            return this;
        }
        
        String resultPath = path;
        resultPath = this.trim(path);
        resultPath = this.trimSlash(resultPath);       

        if(resultPath.isEmpty() == false) {
            this.urlPathList.add(resultPath);
        }
        return this;
    }
    
    public UrlAppender appendQueryParam(String paramName, Object paramValue) {
        queryParamMap.put(paramName, paramValue);
        return this;
    }
    
    public String buildUrl() {
        String url = "";
        Integer pathCounter = 0;
        for(String path : this.urlPathList) {
            pathCounter++;
            if(pathCounter >= 2) {
                url += "/";
            }
            url += path;
        }
        if(!this.queryParamMap.isEmpty()) {
            url += "?";
            Integer paramCounter = 0;            
            for(String paramName : queryParamMap.keySet()) {
                paramCounter++;
                if(paramCounter >= 2) {
                    url += "&";
                }
                Object paramValue = this.queryParamMap.get(paramName);
                if(paramValue != null) {
                    url += paramName + "=" + paramValue;
                } else {
                     url += paramName + "=";               
                }
            }
        }
        
        this.initUrlPathList();
        this.initQueryParamMap();
        return url;
    }
    
    protected String trim(String path) {
        return path.trim();
    }
    
    protected String trimSlash(String path) {
        if(this.trimHeadSlash) path = path.replaceAll("^/", "");
        if(this.trimTailSlash) path = path.replaceAll("/$", "");
        return path;
    } 
}
