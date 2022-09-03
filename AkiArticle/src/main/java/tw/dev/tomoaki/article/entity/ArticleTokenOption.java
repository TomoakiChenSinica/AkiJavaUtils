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

    private String name;
    private String token;
    private String summary;
    private String description;
    private Integer level;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

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
        String format = "name= %s, token= %s, summary= %s, description= %s, level= %s";
        return String.format(format, this.name, this.token, this.summary, this.description, this.level);
    }
    
   

}
