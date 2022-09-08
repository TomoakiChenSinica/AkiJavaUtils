/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import tw.dev.tomoaki.article.entity.AritcleTokenMap;
import tw.dev.tomoaki.article.entity.ArticleTokenOption;
import tw.dev.tomoaki.article.helper.ArticleHelper;
import tw.dev.tomoaki.article.module.BasicTokenModule;
import tw.dev.tomoaki.article.module.intf.ArticleTokenModule;
//import tw.dev.tomoaki.util.DateTimeProvider;

/**
 *
 * @author tomoaki
 */
public abstract class ArticleCreator {

    private AritcleTokenMap tokensReplaceMapper;
//    private Map<String, String> tokensReplaceMapper;
    protected List<ArticleTokenModule> moduleList;

    public ArticleCreator() {
        this.doVariableInit();
        this.doBaseReplaceRulesSetup();
    }

    public ArticleCreator(Boolean useBaseMapper) {
        this.doVariableInit();
        if (useBaseMapper == true) {
            this.doBaseReplaceRulesSetup();
        }
    }

    protected abstract void doCustomRulesSetup();

//    public void addTokenReplaceRule(String token, String word) {
//        this.tokensReplaceMapper.put(token, word);
//    }
//
//    public void addTokenReplaceRule(String token, Integer num) {
//        String strNum = Integer.toString(num);
//        this.tokensReplaceMapper.put(token, strNum);
//    }
//
//    public void addTokenReplaceRule(String token, Long num) {
//        String strNum = Long.toString(num);
//        this.tokensReplaceMapper.put(token, strNum);
//    }
    public void addTokenReplaceRule(String token, String word) {
        this.tokensReplaceMapper.put(1, token, word);
    }
    
    public void addTokenReplaceRule(Integer desigLevel, String token, String word) {
        this.tokensReplaceMapper.put(desigLevel, token, word);
    }    

    public void addTokenReplaceRule(String token, Integer num) {
        String strNum = Integer.toString(num);
        this.tokensReplaceMapper.put(1, token, strNum);
    }
    
    public void addTokenReplaceRule(Integer desigLevel, String token, Integer num) {
        String strNum = Integer.toString(num);
        this.tokensReplaceMapper.putdesigLevel1, token, strNum);
    }    

    public void addTokenReplaceRule(String token, Long num) {
        String strNum = Long.toString(num);
        this.tokensReplaceMapper.put(1, token, strNum);
    }    
    
    public void addTokenReplaceRule(Integer desigLevel, String token, Long num) {
        String strNum = Long.toString(num);
        this.tokensReplaceMapper.put(desigLevel, token, strNum);
    }        

    public void addTokenReplaceRules(Map<String, String> tokenReplaceMapper) {
        Set<Map.Entry<String, String>> tokensMapperEntrySet = tokenReplaceMapper.entrySet();
        for (Map.Entry<String, String> tokensMapperEntry : tokensMapperEntrySet) {
            String token = tokensMapperEntry.getKey();
            String word = tokensMapperEntry.getValue();
            this.tokensReplaceMapper.put(1, token, word);
        }
    }

    public String getArticle(String oriText) {
        this.doCustomRulesSetup();
        String newText = this.replaceTokens(oriText, tokensReplaceMapper);
        return newText;
    }
    
//<editor-fold defaultstate="collapsed" desc="外部使用的">        

    public List<String> getTokensList() {
        List<String> tokensList = new ArrayList();
        tokensList.addAll(this.tokensReplaceMapper.keySet());
        return tokensList;
    }

    public Map<String, String> getTokenMap() {
        return this.tokensReplaceMapper;
    }

    public List<ArticleTokenOption> getTokenOptionList() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (this.moduleList != null && this.moduleList.isEmpty() == false) {
            List<ArticleTokenOption> optionList = new ArrayList();
            for (ArticleTokenModule module : moduleList) {
                List<ArticleTokenOption> partOptionList = ArticleHelper.obtainModuleTokenList(module);
                optionList.addAll(partOptionList);
            }
            return optionList;
        }
        return null;
    }

    public List<ArticleTokenModule> getModuleList() {
        return this.moduleList;
    }
//</editor-fold>

    private void doVariableInit() {
        this.tokensReplaceMapper = new AritcleTokenMap();
        this.moduleList = new ArrayList();
    }

    private void doBaseReplaceRulesSetup() {
        BasicTokenModule basicTokenModule = new BasicTokenModule();
        basicTokenModule.addRule(this);
        this.moduleList.add(basicTokenModule);
    }

    private String replaceTokens(String oriText, Map<String, String> tokensMapper) {
        String newText = oriText;
        Set<Map.Entry<String, String>> tokensMapperEntrySet = tokensMapper.entrySet();
        for (Map.Entry<String, String> tokensMapperEntry : tokensMapperEntrySet) {
            String token = tokensMapperEntry.getKey();
            String word = tokensMapperEntry.getValue();
            if (token != null && word != null) {
                newText = newText.replace(token, word);
            }
        }
        return newText;
    }
}
