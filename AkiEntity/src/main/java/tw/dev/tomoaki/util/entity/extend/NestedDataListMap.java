/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity.extend;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import tw.dev.tomoaki.util.entity.DataRecordMap;
import tw.dev.tomoaki.util.entity.core.KeyPairDataListMap;
import tw.dev.tomoaki.util.entity.core.KeyPairMap;

/**
 *
 * @author Tomoaki Chen
 */
public class NestedDataListMap<TKey1, TKey2, TValue> implements KeyPairDataListMap<TKey1, TKey2, TValue> {

    private Map<TKey1, DataRecordMap<TKey2, TValue>> containerMap;
    protected Boolean keepOrdered4FirstKey = false;
    protected Boolean keepOrdered4SecondKey = false;

    protected NestedDataListMap() {
    }

    public static <Tkey1, Tkey2, Tvalue> NestedDataListMap<Tkey1, Tkey2, Tvalue> create() {
        NestedDataListMap theMap = new NestedDataListMap();
        theMap.doSetupContainerMap();
        return theMap;
    }

    public static <Tkey1, Tkey2, Tvalue> NestedDataListMap<Tkey1, Tkey2, Tvalue> create(Boolean keepOrdered) {
        return create(keepOrdered, keepOrdered);
    }

    public static <Tkey1, Tkey2, Tvalue> NestedDataListMap<Tkey1, Tkey2, Tvalue> create(Boolean keepOrdered4FirstKey, Boolean keepOrdered4SecondKey) {
        NestedDataListMap theMap = new NestedDataListMap();
        theMap.keepOrdered4FirstKey = keepOrdered4FirstKey;
        theMap.keepOrdered4SecondKey = keepOrdered4SecondKey;
        theMap.doSetupContainerMap();
        return theMap;
    }

    protected Map<TKey1, DataRecordMap<TKey2, TValue>> obtainContainerMap() {
        return keepOrdered4FirstKey ? new LinkedHashMap() : new HashMap();
    }

    protected void doSetupContainerMap() {
        this.containerMap = obtainContainerMap();
    }

    protected DataRecordMap<TKey2, TValue> obtainInnerMap() {
        return new DataRecordMap(keepOrdered4SecondKey);

    }

    @Override
    public void put(TKey1 key1, TKey2 key2, List<TValue> value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<List<TValue>> getAllValueList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<List<TValue>> get(TKey1 key1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TValue> get(TKey1 key1, TKey2 key2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove(TKey1 key1, TKey2 key2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean contains(TKey1 key1, TKey2 key2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
