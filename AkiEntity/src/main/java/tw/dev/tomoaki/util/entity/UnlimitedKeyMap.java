package tw.dev.tomoaki.util.entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author tomoaki
 */
public class UnlimitedKeyMap<K extends Object, V> {

    private Map<String, V> dataMap;

    protected UnlimitedKeyMap() {
    }

    public static class Factory {

        public static UnlimitedKeyMap create() {
            UnlimitedKeyMap theMap = new UnlimitedKeyMap();
            theMap.dataMap = new HashMap();
            return theMap;
        }

        public static UnlimitedKeyMap createInOrdered() {
            UnlimitedKeyMap theMap = new UnlimitedKeyMap();
            theMap.dataMap = new LinkedHashMap();
            return theMap;            
        }
    }

    public void put(V value, K... keys) {
        String vKey = this.obtainVirtualKey(keys);
        this.dataMap.put(vKey, value);
    }
    
    public V get(K... keys) {
        String vKey = this.obtainVirtualKey(keys);
        return this.dataMap.get(vKey);
    }
    
    
    

    protected String obtainVirtualKey(K... keys) {
        String vKey = Stream.of(keys).map(Object::toString).collect(Collectors.joining("@")); //https://stackoverflow.com/questions/37987051/how-to-concatenate-a-string-with-the-new-1-8-stream-api
        return vKey;
    }
}
