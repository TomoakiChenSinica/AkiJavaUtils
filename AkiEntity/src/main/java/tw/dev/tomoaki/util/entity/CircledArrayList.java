/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import tw.dev.tomoaki.util.entity.core.CircledList;

/**
 *
 * @author tomoaki
 * @param <T> 資料型態
 */
public class CircledArrayList<T> extends ArrayList<T> implements CircledList<T> {

    public CircledArrayList(Collection<T> dataCollection) {
        super(dataCollection);
    }

    public CircledArrayList(T... datas) {
        super(Arrays.asList(datas));
    }

    // private Integer index = 0;
    @Override
    public T previous(Integer index) {
        return this.get(this.obtainPreviousIndex(index));
    }

    @Override
    public T next(Integer index) {
        return this.get(this.obtainNextIndex(index));
    }

    @Override
    public T searchPrevious(T indexedOjb) {
        Integer index = this.indexOf(indexedOjb);
        return this.previous(index);
    }

    @Override
    public T searchNext(T indexedOjb) {
        Integer index = this.indexOf(indexedOjb);
        return this.next(index);
    }

//<editor-fold defaultstate="collapsed" desc="以下輔助 Methods">
    protected Integer obtainPreviousIndex(Integer index) {
        Integer currIndex = index;
        Integer lastIndex = this.size() - 1;
        if (--currIndex < 0) {
            currIndex = lastIndex;
        }
        return currIndex;
    }

    protected Integer obtainNextIndex(Integer index) {
        Integer currIndex = index;
        Integer lastIndex = this.size() - 1;
        if (++currIndex > lastIndex) {
            currIndex = 0;
        }
        return currIndex;
    }
//</editor-fold>
}
