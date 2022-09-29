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
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import static javax.management.Query.value;
import tw.dev.tomoaki.article.entity.ArticleTokenMap;
import tw.dev.tomoaki.article.entity.ArticleTokenOption;
import tw.dev.tomoaki.article.exception.ArticleCreatorException;
import tw.dev.tomoaki.article.helper.ArticleHelper;
import tw.dev.tomoaki.article.module.BasicTokenModule;
import tw.dev.tomoaki.article.module.intf.ArticleTokenModule;
import tw.dev.tomoaki.util.entity.extend.PairDataDiff;
//import tw.dev.tomoaki.util.DateTimeProvider;

/**
 *
 * @author tomoaki
 */
public abstract class ArticleCreator {

//    private ArticleTokenMap tokensReplaceMapper;
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


    public String getArticle(String oriText) {
//        this.doCustomRulesSetup();
        this.doValidateTokenImpl(); //有問題
        String newText = this.replaceTokens(oriText/*, tokensReplaceMapper*/);
        return newText;
    }

    public List<ArticleTokenModule> getModuleList() {
        return this.moduleList;
    }

    /**
     * 要注意，這個是尚未處理過如何對應，單純設定 token 樣式、規格，但未實作
     */
    public List<ArticleTokenOption> getTokenOptionList() {
        try {
            if (this.moduleList != null && this.moduleList.isEmpty() == false) {
                List<ArticleTokenOption> optionList = new ArrayList();
                for (ArticleTokenModule module : moduleList) {
                    List<ArticleTokenOption> partOptionList = ArticleHelper.obtainModuleTokenList(module);
                    optionList.addAll(partOptionList);
                }
                return optionList;
            }
        } catch (Exception ex) {
            throw new ArticleCreatorException(ex);
        }
        return null;
    }

    public List<String> getOptionalTokenList() {
        List<String> optionalTokenList = this.getTokenOptionList().stream().map(articleTokenOption -> articleTokenOption.getToken()).collect(Collectors.toList());
        return optionalTokenList;
    }

    /**
     * 這個 methods 以 丟List進去來說就不合理，因為同一個 ${Player.Name}，(在資料為清單狀況下)不只一種可能
     */
    public List<String> getImplTokenList() {
        List<String> tokensList = new ArrayList();
        for (ArticleTokenModule tokenModule : this.moduleList) {
            List<String> partList = tokenModule.getModuleArticleTokenMap().getFlatTokenList();
            tokensList.addAll(partList);
        }
        return tokensList;
    }

    public Map<String, String> getTokenMap() {
        Map<String, String> resultMap = new LinkedHashMap();
        for (ArticleTokenModule tokenModule : this.moduleList) {
            ArticleTokenMap moduleTokenMap = tokenModule.getModuleArticleTokenMap();
            Map<String, String> tokenMap = moduleTokenMap.getFlatTokenReplaceMap();
            if (tokenMap != null) {
                for (Entry<String, String> entry : tokenMap.entrySet()) {
                    resultMap.merge(entry.getKey(), entry.getValue(), String::concat);
                }
            }
        }
        return resultMap;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="內部初始化、設定變數">
    private void doVariableInit() {
//        this.tokensReplaceMapper = new ArticleTokenMap();
        this.moduleList = new ArrayList();
    }

    private void doBaseReplaceRulesSetup() {
        BasicTokenModule basicTokenModule = new BasicTokenModule();
        basicTokenModule.doSetupRule();
        this.moduleList.add(basicTokenModule);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="內部輔助">
    private String replaceTokens(String oriText) {
        String newText = oriText;
        for (ArticleTokenModule tokenModule : this.moduleList) {
            newText = tokenModule.replaceToken(newText);
        }
        return newText;
    }

    private void doValidateTokenImpl() {
        List<String> optionalTokenList = this.getOptionalTokenList();
        List<String> implTokenList = this.getImplTokenList();
        PairDataDiff diff = PairDataDiff.Factory.create(optionalTokenList, implTokenList);
        if (diff.getLessDataList() != null && diff.getLessDataList().isEmpty() == false) {
            System.err.println("[ArticleCreator]doValidateTokenImpl(): " + diff.getLessDataList() + " Not Implement Yet");
        }
    }
//</editor-fold>
}
