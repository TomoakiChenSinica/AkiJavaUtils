package tw.dev.tomoaki.util.entity;

import java.util.List;

/**
 *
 * @author tomoaki
 */
public interface KeyPairMap<TKey1, TKey2, TValue> {

    public void put(TKey1 key1, TKey2 TKey2, TValue value);

    public List<TValue> getAllValueList();
    
    public TValue get(TKey1 key1, TKey2 key2);

    public void remove(TKey1 key1, TKey2 key2);
    
    public Boolean isEmpty();
    
    public Integer size();
}
