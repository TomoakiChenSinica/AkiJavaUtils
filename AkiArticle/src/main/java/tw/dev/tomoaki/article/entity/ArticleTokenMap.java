/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @author arche
 */
public class ArticleTokenMap {
    
    private Integer highestLevel;
    private Map<Integer, Map<String, String>> tokenReplaceMapKeyByLevelMap;
    
    public ArticleTokenMap() {
        this.highestLevel = 0;
        this.tokenReplaceMapKeyByLevelMap = new TreeMap(Collections.reverseOrder());
    }
    
    
//<editor-fold defaultstate="collapsed" desc="寫入的相關metdhos">
    public void put(String token, Long longWord) {
        this.put(1, token, longWord.toString());
    }
    
    public void put(Integer level, String token, Long longWord) {
        this.put(level, token, longWord.toString());
    }    
    
    public void put(String token, Integer intWord) {
        this.put(1, token, intWord.toString());
    }
    
    public void put(Integer level, String token, Integer intWord) {
        this.put(level, token, intWord.toString());
    }
    
    
    public void put(String token, String word) {
        this.put(1, token, word);
    }
    
    public void put(Integer level, String token, String word) {
        Map<String, String> desigLevelTokenReplaceMap = this.getDesigLevelTokenReplaceMap(level);
        desigLevelTokenReplaceMap.put(token, word);
        
        tokenReplaceMapKeyByLevelMap.put(level, desigLevelTokenReplaceMap);
        if(level > this.highestLevel) {
            highestLevel = level;
        }
    }    
//</editor-fold>        
    protected Map<String, String> getDesigLevelTokenReplaceMap(Integer desigLevel) {
        Map<String, String> desigLevelTokenReplaceMap = this.tokenReplaceMapKeyByLevelMap.get(desigLevel);
        return (desigLevelTokenReplaceMap == null) ? new HashMap()/*LinkedHashMap()*/ : desigLevelTokenReplaceMap;
    }
    
    public List<Integer> getLevelList() {
        //https://www.baeldung.com/java-stream-ordering tre set 會排序
        return tokenReplaceMapKeyByLevelMap.keySet().stream().collect(Collectors.toList());
    }
    
    public List<Map<String, String>> getTokenReplaceMapList() {
        return tokenReplaceMapKeyByLevelMap.values().stream().collect(Collectors.toList());
    }
    
    public Map<String, String> getFlatTokenReplaceMap() {
        Map<String, String> resultMap = new LinkedHashMap();
        List<Map<String, String>> mapList = this.getTokenReplaceMapList(); //將各層級的 replaceMap 串成list
        if (mapList != null) {
            for (Map<String, String> map : mapList) {
                Set<Map.Entry<String, String>> mapEntrySet = map.entrySet();
                if (mapEntrySet != null) {
                    for (Map.Entry<String, String> mapEntry : mapEntrySet) {
                        resultMap.merge(mapEntry.getKey(), mapEntry.getValue(), String::concat);
                    }
                }
            }
        }
        return resultMap;
    }
    
    public List<String>getFlatTokenList() {
        return this.getFlatTokenReplaceMap().keySet().stream().collect(Collectors.toList());
    }
    
}
