/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;

/**
 *
 * @author Tomoaki Chen
 * 
 * 並沒有以很正規(?)的角度看「哪一段才叫做 PATH」
 * https://zh.wikipedia.org/zh-tw/%E7%BB%9F%E4%B8%80%E8%B5%84%E6%BA%90%E6%A0%87%E8%AF%86%E7%AC%A6
 */
public class UrlAppender {

    /**
     * 會固定當成 
     */
    protected List<String> staticUrlHeaderList;
    
    protected List<String> urlPathList;
    protected Map<String, Object> queryParamMap;

    protected Boolean trimHeadSlash = true;
    protected Boolean trimTailSlash = true;
    
    
    protected UrlAppender() {
        
    }

    public static UrlAppender create() {
        UrlAppender appender = new UrlAppender();
        appender.staticUrlHeaderList = Arrays.asList();
        appender.initUrlPathList();
        appender.initQueryParamMap();
        return appender;
    }

    public static UrlAppender create(String staticPath, String... otherStaticPaths) {
        UrlAppender appender = new UrlAppender();
        appender.staticUrlHeaderList = Stream.concat(Stream.of(staticPath), Stream.of(otherStaticPaths)).collect(Collectors.toList());
        appender.initUrlPathList();
        appender.initQueryParamMap();
        return appender;
    }

    
//<editor-fold defaultstate="collapsed" desc="初始化/設定 變數的 methods">
    protected void initUrlPathList() {
        this.urlPathList = new ArrayList();
    }    
    
    protected void initQueryParamMap() {
        this.queryParamMap = new LinkedHashMap();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="外部呼叫以調整設定的 Methods">
    public UrlAppender turnOnTrimHeadSlash() {
        this.trimHeadSlash = true;
        return this;
    }
    
    public UrlAppender turnOffTrimHeadSlash() {
        this.trimHeadSlash = true;
        return this;
    }
    
    public UrlAppender turnOnTrimTailSlash() {
        this.trimTailSlash = true;
        return this;
    }
    
    public UrlAppender turnOffTrimTailSlash() {
        this.trimTailSlash = true;
        return this;
    }        
//</editor-fold>
            
//<editor-fold defaultstate="collapsed" desc="外部呼叫的主要 Methods">
    public UrlAppender append(String path) {
        if(StringValidator.isValueTrimNotExist(path)) { // if(path == null) {
            return this;
        }
        
        /* 2025-01-02 tomoaki: 這裡改成存最原始類型
        // String resultPath = path;
        String resultPath = this.trim(path);
        resultPath = this.trimSlash(resultPath);

        if(resultPath.isEmpty() == false) {
            this.urlPathList.add(resultPath);
        }*/
        this.urlPathList.add(path);
        return this;
    }
    
    public UrlAppender appendQueryParam(String paramName, Object paramValue) {
        queryParamMap.put(paramName, paramValue);
        return this;
    }
        
    public String buildUrl() {
        String url = "";
        Stream<String> allPathsStream = Stream.concat(this.staticUrlHeaderList.stream(), this.urlPathList.stream());
        Stream<String> processedPathsStream = allPathsStream.filter(StringValidator::isValueTrimExist).map(this::trim).map(this::trimSlash);
        // processedPathsStream.forEach(System.out::println); // 執行完就會結束
        url += processedPathsStream.collect(Collectors.joining("/"));
               
        
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
    
    
    @Override
    public String toString() {
        String msgFmt = "%s[staticUrlHeaderList= %s]";
        return String.format(msgFmt, this.staticUrlHeaderList);
    }
//</editor-fold>
        
//<editor-fold defaultstate="collapsed" desc="內部輔助 Methods，對 path 做一些處理、修飾">
    protected String trim(String path) {
        return path.trim();
    }
    
    protected String trimSlash(String path) {
        if(this.trimHeadSlash) path = path.replaceAll("^[\\\\|/]", "");
        if(this.trimTailSlash) path = path.replaceAll("[\\\\|/]$", "");
        return path;
    }
    
    protected String compactSlash(String path) {
       path = path.replaceAll("[^:]/{2,}", "/");
       return path;
    }    
//</editor-fold>    
}
