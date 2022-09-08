/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author arche
 */
public class AritcleTokenMap {
    
    private Integer highestLevel;
    private Map<Integer, Map<String, String>> tokenReplaceMapKeyByLevelMap;
    
    public AritcleTokenMap() {
        this.highestLevel = 0;
        this.tokenReplaceMapKeyByLevelMap = new HashMap();
    }
    
    public void put(Integer level, String token, String word) {
        Map<String, String> desigLevelTokenReplaceMap = this.getDesigLevelTokenReplaceMap(level);
        desigLevelTokenReplaceMap.put(token, word);
        
        tokenReplaceMapKeyByLevelMap.put(level, desigLevelTokenReplaceMap);
        if(level > this.highestLevel) {
            highestLevel = level;
        }
    }
    
    protected Map<String, String> getDesigLevelTokenReplaceMap(Integer desigLevel) {
        Map<String, String> desigLevelTokenReplaceMap = this.tokenReplaceMapKeyByLevelMap.get(desigLevel);
        return (desigLevelTokenReplaceMap == null) ? new HashMap() : desigLevelTokenReplaceMap;
    }
    
    public List<Integer> getLevelList() {
        //https://www.baeldung.com/java-stream-ordering tre set 會排序
        tokenReplaceMapKeyByLevelMap.keySet().stream();
    }
    
}
