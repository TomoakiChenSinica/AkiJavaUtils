package tw.dev.tomoaki.util.entity.core;

import java.util.List;

/**
 *
 * @author tomoaki
 * @param <TKey1> 第一組 Key 的型態
 * @param <TKey2> 第二組 Key 的型態
 * @param <TValue> 兩組 Key 對應的值 的的型態
 */
public interface KeyPairMap<TKey1, TKey2, TValue> {

    public void put(TKey1 key1, TKey2 key2, TValue value);

    public List<TValue> getAllValueList();
    
    public List<TValue> get(TKey1 key1);
    
    public TValue get(TKey1 key1, TKey2 key2);

    public void remove(TKey1 key1, TKey2 key2);
    
    public Boolean isEmpty();
    
    public Integer size();
    
    public Boolean contains(TKey1 key1, TKey2 key2);
}
