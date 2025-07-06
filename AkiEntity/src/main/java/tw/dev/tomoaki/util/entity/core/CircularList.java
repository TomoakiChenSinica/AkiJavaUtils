/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tw.dev.tomoaki.util.entity.core;

import java.util.List;

/**
 *
 * @author tomoaki
 * @param <T>
 */
public interface CircularList<T> extends List<T> {

//    public T previous();
//    
//    public T next();
    public T previous(Integer index);

    public T searchPrevious(T indexedOjb);

    public T next(Integer index);

    public T searchNext(T indexedOjb);

}
