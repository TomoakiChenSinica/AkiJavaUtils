/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomoaki Chen
 */
public class UrlAppender {

    protected List<String> urlPathList;

    protected Boolean trimHeadSlash = true;
    protected Boolean trimTailSlash = true;
    
    
    protected UrlAppender() {
        
    }

    public static class Factory {

        public static UrlAppender create() {
            UrlAppender appender = new UrlAppender();
            appender.initUrlPathList();
            return appender;
        }
    }
    
//<editor-fold defaultstate="collapsed" desc="初始化/設定 變數的 methods">
    protected void initUrlPathList() {
        this.urlPathList = new ArrayList();
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
    
    public String buildUrl() {
        String url = "";
        Integer counter = 0;
        for(String path : this.urlPathList) {
            counter++;
            if(counter >= 2) {
                url += "/";
            }
            url += path;
        }
        this.initUrlPathList();
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
