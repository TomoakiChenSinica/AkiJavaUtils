/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tw.dev.tomoaki.datautils.reflectionext;

/**
 *
 * @author tomoaki
 */
public interface Mergeable<T> {

    default T merge(T other) {
        return (T) POJOReflector.merge(this, other);
//        T result = (T) POJOReflector.merge(this, other);
//        this = result;
//        return result;
    }
}
