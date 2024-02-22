package tw.dev.tomoaki.util.entity.core;

import java.util.List;

/**
 *
 * @author tomoaki
 * 
 * @param <TKey1> 第一組 Key 的型態
 * @param <TKey2> 第二組 Key 的型態
 * @param <TValue> 兩組 Key 對應的值清單 的的型態
 */
public interface KeyPairDataListMap<TKey1, TKey2, TValue> extends KeyPairMap<TKey1, TKey2, List<TValue>> {
    
//    public void put(TKey1 key1, TKey2 key2, TValue value);
}
