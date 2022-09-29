/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.entity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 *
 * @author arche
 */
public class IterableArticleTokenMap {

    /**
     * 指向(紀錄)最後一次「讀取」的位置 -->從-1開始，即第一次會指到頭(0)
     */
    private Integer dataPopIndex; 
    
    /**
     * 指向(紀錄)最後一個「可以寫入」的位置  --> 從0開始
     */
    private Integer dataPutIndex;
    private Map<Integer, Map<String, String>> tokenReplaceMapKeyByIndexMap;

    public IterableArticleTokenMap() {
        this.initDataPopIndex();
        this.dataPutIndex = 0;
        this.tokenReplaceMapKeyByIndexMap = new LinkedHashMap();
    }

//<editor-fold defaultstate="collapsed" desc="寫入的相關metdhos">
    public void put(String token, Long longWord) {
        this.put(token, longWord.toString());
    }

    public void put(String token, Integer intWord) {
        this.put(token, intWord.toString());
    }

    public void put(String token, String word) {
        Map<String, String> desigIndexTokenReplaceMap = this.obtainDesigIndexTokenReplaceMap(dataPutIndex);
        desigIndexTokenReplaceMap.put(token, word);
        tokenReplaceMapKeyByIndexMap.put(dataPutIndex, desigIndexTokenReplaceMap);
    }
//</editor-fold>        

//<editor-fold defaultstate="collapsed" desc="讀取的相關 metdhos">
    public Map<String, String> pop() {
        return this.tokenReplaceMapKeyByIndexMap.get(++dataPopIndex);
    }   
//</editor-fold>
    

//<editor-fold defaultstate="collapsed" desc="輔助用methods">
    protected Map<String, String> obtainDesigIndexTokenReplaceMap(Integer desigIndex) {
        Map<String, String> desigIndexTokenReplaceMap = this.tokenReplaceMapKeyByIndexMap.get(desigIndex);
        return (desigIndexTokenReplaceMap == null) ? new HashMap() : desigIndexTokenReplaceMap;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="操作 dataXxxIndex 的 methods">
    public void nextDataPutIndex() {
        this.dataPutIndex++;
    }
       
    public final void initDataPopIndex() {
        this.dataPopIndex = -1;
    }
//</editor-fold>
    
}
