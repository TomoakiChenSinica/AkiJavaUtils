/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import tw.dev.tomoaki.util.entity.core.KeyPairMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tomoaki
 * @param <Tkey1> 雙索引鍵(1)
 * @param <Tkey2> 雙索引鍵(2)
 * @param <Tvalue>
 *
 */
public class NestedMap<Tkey1, Tkey2, Tvalue> implements KeyPairMap<Tkey1, Tkey2, Tvalue> {

    protected Map<Tkey1, Map<Tkey2, Tvalue>> containerMap;
    protected Boolean keepOrdered4FirstKey = false;
    protected Boolean keepOrdered4SecondKey = false;

    protected Boolean isValueCollection;

    protected NestedMap() {
        /*
        TypeVariable<?>[] params = this.getClass().getTypeParameters();
        // System.out.println( params[2].getGenericDeclaration() );
        System.out.println( params[2].getClass());
        
        
//        Type superClass = getClass().getGenericSuperclass();
//        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[2];
//        System.out.println("type= " + type);
         */
    }

    public static <Tkey1, Tkey2, Tvalue> NestedMap<Tkey1, Tkey2, Tvalue> create() {
        NestedMap theMap = new NestedMap();
        theMap.doSetupContainerMap();
        return theMap;
    }

    public static <Tkey1, Tkey2, Tvalue> NestedMap<Tkey1, Tkey2, Tvalue> create(Boolean keepOrdered) {
        return create(keepOrdered, keepOrdered);
    }

    public static <Tkey1, Tkey2, Tvalue> NestedMap<Tkey1, Tkey2, Tvalue> create(Boolean keepOrdered4FirstKey, Boolean keepOrdered4SecondKey) {
        NestedMap theMap = new NestedMap();
        theMap.keepOrdered4FirstKey = keepOrdered4FirstKey;
        theMap.keepOrdered4SecondKey = keepOrdered4SecondKey;
        theMap.doSetupContainerMap();
        return theMap;
    }
//<editor-fold defaultstate="collapsed" desc="設定、初始話此 Entity 的 Methods">

    /**
     * 產生用來裝資料的容器(Container)，在這裡是用 Map<Tkey1, Map<Tkey2, Tvalue>>
     *
     * @return 資料容器(Map<Tkey1, Map<Tkey2, Tvalue>>
     */
    protected Map<Tkey1, Map<Tkey2, Tvalue>> obtainContainerMap() {
        return keepOrdered4FirstKey ? new LinkedHashMap() : new HashMap();
    }

    protected void doSetupContainerMap() {
        this.containerMap = obtainContainerMap();
    }

    protected Map<Tkey2, Tvalue> obtainInnerMap() {
        return keepOrdered4SecondKey ? new LinkedHashMap() : new HashMap();

    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="實作 KeyPairMap 中的 Methods">
    @Override
    public void put(Tkey1 key1, Tkey2 key2, Tvalue value) {
        Map<Tkey2, Tvalue> innerMap = this.getInnerMap(key1, true);
        innerMap.put(key2, value);
        this.containerMap.put(key1, innerMap);
    }

    @Override
    public List<Tvalue> getAllValueList() {
        List<Tvalue> allValueList = new ArrayList();
        Collection<Map<Tkey2, Tvalue>> valueMapList = containerMap.values();
        for (Map<Tkey2, Tvalue> valueMap : valueMapList) {
            Collection<Tvalue> valueList = valueMap.values();
            if (valueList != null) {
                allValueList.addAll(valueList);
            }
        }
        return allValueList;
    }

    @Override
    public List<Tvalue> get(Tkey1 key1) {
        Map<Tkey2, Tvalue> innerMap = getInnerMap(key1);
        return (innerMap == null) ? null : new ArrayList(innerMap.values());
    }

    @Override
    public Tvalue get(Tkey1 key1, Tkey2 key2) {
        Map<Tkey2, Tvalue> innerMap = this.getInnerMap(key1);
        Tvalue value = (innerMap == null) ? null : innerMap.get(key2);
        return value;
    }

    @Override
    public void remove(Tkey1 key1, Tkey2 key2) {
        Map<Tkey2, Tvalue> innerMap = this.getInnerMap(key1);
        if (innerMap != null) {
            innerMap.remove(key2);
        }
    }

    @Override
    public Boolean isEmpty() {
        return this.containerMap.isEmpty();
    }

    @Override
    public Integer size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean contains(Tkey1 key1, Tkey2 key2) {
        return (get(key1, key2) != null);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="NestedMap 獨立於 KeyPairDataExistedMap 之外的 Methods">
    public Map<Tkey1, Map<Tkey2, Tvalue>> getAsMap() {
        return containerMap;
    }

    /**
     * 2017-10-16 ，暫時由protected 改為 public ，因為 StageWebServiceResult
     * 應用此，且有狀況需要用到....
     *
     * @param key1 第一層的 key
     * @return 內層的 Map<Tkey2, Tvalue> Map
     */
    public Map<Tkey2, Tvalue> getInnerMap(Tkey1 key1) {
        return this.getInnerMap(key1, false);
    }

    public void setInnerMap(Tkey1 key1, Map<Tkey2, Tvalue> innerMap) {
        containerMap.put(key1, innerMap);
    }

    protected Map<Tkey2, Tvalue> getInnerMap(Tkey1 key1, Boolean nullCreate) {
        Map<Tkey2, Tvalue> innerMap = containerMap.get(key1);
        if (innerMap == null && nullCreate) {
            innerMap = this.obtainInnerMap();
        }
        return innerMap;
    }

    public Collection<Map<Tkey2, Tvalue>> getInnerMaps() {
        return this.containerMap.values();
    }

    public Set<Tkey1> getFirstPairKeys() {
        return containerMap.keySet();
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
    /*
    
    protected void doSetupIsValueCollection() {
        
    }*/
//</editor-fold>

}
