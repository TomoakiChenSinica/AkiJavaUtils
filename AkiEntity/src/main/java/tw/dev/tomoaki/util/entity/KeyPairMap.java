/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tomoaki
 */
public class KeyPairMap<Tkey1, Tkey2, Tvalue> {

    private Map<Tkey1, Map<Tkey2, Tvalue>> theMap;
    private Boolean useLinkedHashMap;

    public KeyPairMap() {
        theMap = new HashMap();
    }

    public List<Tvalue> getAllValueList() {
        List<Tvalue> allValueList = new ArrayList();
        Collection<Map<Tkey2, Tvalue>> valueMapList = theMap.values();
        for (Map<Tkey2, Tvalue> valueMap : valueMapList) {
            Collection<Tvalue> valueList = valueMap.values();
            if (valueList != null) {
                allValueList.addAll(valueList);
            }
        }
        return allValueList;
    }

    public Tvalue get(Tkey1 key1, Tkey2 key2) {
        Map<Tkey2, Tvalue> innerMap = this.getInnerMap(key1);
        Tvalue value = innerMap.get(key2);
        return value;
    }

    public void put(Tkey1 key1, Tkey2 key2, Tvalue value) {
        Map<Tkey2, Tvalue> innerMap = this.getInnerMap(key1);
        innerMap.put(key2, value);
        this.theMap.put(key1, innerMap);
    }

    //以上20171113 新增
    public void remove(Tkey1 key1, Tkey2 key2) {
        Map<Tkey2, Tvalue> innerMap = this.getInnerMap(key1);
        innerMap.remove(key2);
    }
    //以上20171113新增

    public Set<Tkey1> getFirstPairKeys() {
        return theMap.keySet();
    }

    public Map<Tkey1, Map<Tkey2, Tvalue>> getAsMap() {
        return theMap;
    }

    /**
     * 2017-10-16 ，暫時由protected 改為 public ，因為 StageWebServiceResult
     * 應用此，且有狀況需要用到....
     *
     * @param key1 第一層的 key
     * @return 內層的 Map<Tkey2, Tvalue> Map
     */
    public Map<Tkey2, Tvalue> getInnerMap(Tkey1 key1) {
        Map<Tkey2, Tvalue> innerMap = theMap.get(key1);
        if (innerMap == null) {
            innerMap = new HashMap();
        }
        return innerMap;
    }

    public Collection<Map<Tkey2, Tvalue>> getInnerMaps() {
        return this.theMap.values();
    }

    public List<Tkey2> getSecondKeys() {
        List<Tkey2> tempSecondKeys = new ArrayList();
        Collection<Map<Tkey2, Tvalue>> innerMaps = this.getInnerMaps();

        for (Map<Tkey2, Tvalue> innerMap : innerMaps) {
            tempSecondKeys.addAll(innerMap.keySet());
        }
        DataExistMap<Tkey2> key2ExistMap = new DataExistMap(tempSecondKeys);
        return key2ExistMap.existList();
    }

    public void setInnerMap(Tkey1 key1, Map<Tkey2, Tvalue> innerMap) {
        theMap.put(key1, innerMap);
    }
    
    public Boolean isEmpty() {
        return this.theMap.isEmpty();
    }

}
