/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity;

import java.util.List;

/**
 *
 * @author tomoaki
 * @param <Tkey1> 雙索引鍵(1)
 * @param <Tkey2> 雙索引鍵(2)
 */
public class KeyPairDataExistMap<Tkey1, Tkey2> {

    private NestedMap<Tkey1, Tkey2, Boolean> dataExistMap = new NestedMap<Tkey1, Tkey2, Boolean>();

    public KeyPairDataExistMap() {
    }

    public static class Factory {

//        public static KeyPairMap create() {
//        }
    }

    public void add(Tkey1 data1, Tkey2 data2) {
        dataExistMap.put(data1, data2, Boolean.TRUE);
    }

    public void remove(Tkey1 data1, Tkey2 data2) {
        dataExistMap.remove(data1, data2);
    }

    public Boolean contains(Tkey1 data1, Tkey2 data2) {
        if (this.dataExistMap.get(data1, data2) != null && this.dataExistMap.get(data1, data2)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isEmpty() {
        List<Boolean> valueList = this.dataExistMap.getAllValueList();
        if (valueList == null || valueList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
 
    public NestedMap<Tkey1, Tkey2, Boolean> getAsKeyPariMap() {
        return this.dataExistMap;
    }
}
