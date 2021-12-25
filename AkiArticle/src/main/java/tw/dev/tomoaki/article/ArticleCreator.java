/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import tw.dev.tomoaki.util.DateTimeProvider;

/**
 *
 * @author tomoaki
 */
public abstract class ArticleCreator {
    
//    Map<String, String> tokensReplaceMapper;
//    
//    
//    
//    public final static String TOKEN_OLD_NOW_YEAAR = "${NowYear}";
//    public final static String TOKEN_OLD_ROC_YEAR = "${rocYear}"; //protected -> public 
//    
//    public final static String TOKEN_NOW_YEAR = "${Common.NowYear}";
//    public final static String TOKEN_NOW_ROC_YEAR = "${Common.NowRocYear}"; 
//    
//     
//    public ArticleCreator(){
//        this.doVariableInit();
//        this.doBaseReplaceRulesSetup();
//    }
//    
//    public ArticleCreator(Boolean useBaseMapper){
//        this.doVariableInit();
//        if(useBaseMapper == true){
//            this.doBaseReplaceRulesSetup();
//        }
//    }
//    
//    protected void doVariableInit(){
//        this.tokensReplaceMapper = new LinkedHashMap();
//    }
//    
//    protected void doBaseReplaceRulesSetup(){
//        this.addTokenReplaceRule(TOKEN_OLD_NOW_YEAAR, DateTimeProvider.GetYear());     
//        this.addTokenReplaceRule(TOKEN_OLD_ROC_YEAR, DateTimeProvider.GetYear() - 1911);        
//        this.addTokenReplaceRule(TOKEN_NOW_YEAR, DateTimeProvider.GetYear());
//        this.addTokenReplaceRule(TOKEN_NOW_ROC_YEAR, DateTimeProvider.GetYear() - 1911);
//    }
//    
//    protected abstract void doCustomRulesSetup();    
//   
//    private String replaceTokens(String oriText, Map<String, String> tokensMapper){
//        String newText = oriText;
//        Set<Map.Entry<String, String>> tokensMapperEntrySet = tokensMapper.entrySet();
//        for(Map.Entry<String, String> tokensMapperEntry : tokensMapperEntrySet){
//            String token = tokensMapperEntry.getKey();
//            String word = tokensMapperEntry.getValue();
//            if(token != null && word != null){
//                newText = newText.replace(token, word);
//            }
//        }                
//        return newText;
//    }
//    
//    
//   
//    
//    public void addTokenReplaceRule(String token, String word){
//        this.tokensReplaceMapper.put(token, word);
//    }
//    
//    public void addTokenReplaceRule(String token, Integer num){
//        String strNum = Integer.toString(num);
//        this.tokensReplaceMapper.put(token, strNum);
//    }
//    
//    public void addTokenReplaceRule(String token, Long num){
//        String strNum = Long.toString(num);
//        this.tokensReplaceMapper.put(token, strNum);
//    }
//    
//    public void addTokenReplaceRule(String token, java.util.Date date){
//        String strDate = DateTimeProvider.DateToString(date);
//        this.tokensReplaceMapper.put(token, strDate);
//    }
//    
//    public void addTokenReplaceRule(String token, java.sql.Date date){
//        String strDate = DateTimeProvider.DateToString(date);
//        this.tokensReplaceMapper.put(token, strDate);
//    }
//    
//    public void addTokenReplaceRules(Map<String, String> tokenReplaceMapper){
//        Set<Map.Entry<String, String>> tokensMapperEntrySet = tokenReplaceMapper.entrySet();
//        for(Map.Entry<String, String> tokensMapperEntry : tokensMapperEntrySet){
//            String token = tokensMapperEntry.getKey();
//            String word = tokensMapperEntry.getValue();
//            this.tokensReplaceMapper.put(token, word);
//        }            
//    }
//    
//    public String getArticle(String oriText){        
//        this.doCustomRulesSetup();        
//        String newText = this.replaceTokens(oriText, tokensReplaceMapper);
//        return newText;
//    }
//    
//    public List<String> getTokensList(){
//        List<String> tokensList = new ArrayList();
//        tokensList.addAll(this.tokensReplaceMapper.keySet());
//        return tokensList;
//    }
    
    

    
            
}
