/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static sun.jvm.hotspot.HelloWorld.e;
import tw.dev.tomoaki.article.entity.AritcleTokenMap;
import tw.dev.tomoaki.article.entity.ArticleTokenOption;
import tw.dev.tomoaki.article.exception.ArticleCreatorException;
import tw.dev.tomoaki.article.helper.ArticleHelper;
import tw.dev.tomoaki.article.module.BasicTokenModule;
import tw.dev.tomoaki.article.module.intf.ArticleEntityDataTokenRuleModule;
import tw.dev.tomoaki.article.module.intf.ArticleTokenModule;
import tw.dev.tomoaki.util.entity.extend.PairDataDiff;
//import tw.dev.tomoaki.util.DateTimeProvider;

/**
 *
 * @author tomoaki
 */
public abstract class ArticleCreator {

    private AritcleTokenMap tokensReplaceMapper;
    protected List<ArticleTokenModule> moduleList;
    protected Boolean printLog = false;

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

    // ------------------------------------------------------------------------------
    public void addModule(ArticleEntityDataTokenRuleModule entityTokenModule) {
        this.moduleList.add(entityTokenModule);
    }
    // ------------------------------------------------------------------------------

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
        this.tokensReplaceMapper.put(desigLevel, token, strNum);
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
        this.doValidateTokenImpl();
        String newText = this.replaceTokens(oriText/*, tokensReplaceMapper*/);
        return newText;
    }

    public List<ArticleTokenModule> getModuleList() {
        return this.moduleList;
    }

    /**
     * 要注意，這個是尚未處理過如何對應，單純設定 token 樣式、規格，但未實作
     *
     * @return ArticleTokenOption 清單
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

    public List<String> getImplTokenList() {
        List<String> tokensList = new ArrayList();
        tokensList.addAll(this.tokensReplaceMapper.getFlatTokenList());
        return tokensList;
    }

    public Map<String, String> getTokenMap() {
        return this.tokensReplaceMapper.getFlatTokenReplaceMap();
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="內部初始化、設定變數">
    private void doVariableInit() {
        this.tokensReplaceMapper = new AritcleTokenMap();
        this.moduleList = new ArrayList();
    }

    private void doBaseReplaceRulesSetup() {
        BasicTokenModule basicTokenModule = new BasicTokenModule();
        basicTokenModule.addRule(this);
        this.moduleList.add(basicTokenModule);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="內部輔助">
    private String replaceTokens(String oriText /*, Map<String, String> tokensMapper*/) {
        String newText = oriText;
        List<Map<String, String>> tokensMapperList = this.tokensReplaceMapper.getTokenReplaceMapList();
        for (Map<String, String> tokensMapper : tokensMapperList) {
            Set<Map.Entry<String, String>> tokensMapperEntrySet = tokensMapper.entrySet();
            for (Map.Entry<String, String> tokensMapperEntry : tokensMapperEntrySet) {
                String token = tokensMapperEntry.getKey();
                String word = tokensMapperEntry.getValue();
                if (token != null && word != null) {
                    newText = newText.replace(token, word);
                }
            }
        }
        return newText;
    }

    private void doValidateTokenImpl() {
        if (this.printLog) {
            List<String> optionalTokenList = this.getOptionalTokenList();
            List<String> implTokenList = this.getImplTokenList();
            PairDataDiff diff = PairDataDiff.Factory.create(optionalTokenList, implTokenList);
            if (diff.getLessDataList() != null && diff.getLessDataList().isEmpty() == false) {
                System.err.println(diff.getLessDataList() + " Not Implement Yet");
            }
        }
    }

    private String expandInterator(String oriText) {
        // 藉此將類迴圈展開 <article:forEach>
        return oriText;
    }
//</editor-fold>

}
