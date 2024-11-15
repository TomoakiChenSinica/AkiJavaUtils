/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.entity;

/**
 *
 * @author Tomoaki Chen
 */
public class ArticleTokenOption {

    /**
     * 定義 Token 的 Module(XxxModule，實作 ArticleTokenModule)的名稱
     */
    private String moduleName;

    /**
     * 定義 Token 的 Module(XxxModule)的 Class
     */
    private Class moduleClass;
    
//    /**
//     * 定義 Token 的 Module(XxxModule)的實體 Object
//     */
//    private Object moduleInstance;

    /**
     * Token 名稱
     */
    private String name;

    /**
     * Token 實際樣子
     */
    private String token;

    /**
     * Token 的簡介
     */
    private String summary;

    /**
     * Token 的說明
     */
    private String description;
    private Integer level;

    /**
     * 取得定義此 Token 的 Module(XxxModule，實作 ArticleTokenModule)的名稱
     *
     * @return Module 名稱
     */    
    public String getModuleName() {
        return moduleName;
    }

    /**
     * 設定定義此 Token 的 Module(XxxModule，實作 ArticleTokenModule)的名稱
     *
     * @param moduleName Module  名稱
     */    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * 取得定義此 Token 的 Module(XxxModule)的 Class
     *
     * @return Module Class
     */     
    public Class getModuleClass() {
        return moduleClass;
    }

    /**
     * 設定定義此 Token 的 Module(XxxModule)的 Class
     *
     * @param moduleClass Module Class
     */     
    public void setModuleClass(Class moduleClass) {
        this.moduleClass = moduleClass;
    }

//    public Object getModuleInstance() {
//        return moduleInstance;
//    }
//
//    public void setModuleInstance(Object moduleInstance) {
//        this.moduleInstance = moduleInstance;
//    }
    
    /**
     * 取得 Token 名稱
     *
     * @return Token 名稱
     */
    public String getName() {
        return name;
    }

    /**
     * 設定 Token 名稱
     *
     * @param name Token 名稱
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得 Token 實際樣子，比如 ${xxx.ooo}
     *
     * @return Token 實際樣子
     */
    public String getToken() {
        return token;
    }

    /**
     * 設定 Token 實際樣子，比如 ${xxx.ooo}
     *
     * @param token Token 實際樣子
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 取得 Token 簡介
     *
     * @return Token 簡介
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 設定 Token 簡介
     *
     * @param summary Token 簡介
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 取得 Token 說明
     *
     * @return Token 說明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 設定 Token 說明
     *
     * @param description Token 說明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        String format = "%s[moduleName=%s , moduleClass= %s, name= %s, token= %s, summary= %s, description= %s, level= %s]";
        return String.format(format, getClass().getName(), this.moduleName, this.moduleClass, this.name, this.token, this.summary, this.description, this.level);
    }

}
