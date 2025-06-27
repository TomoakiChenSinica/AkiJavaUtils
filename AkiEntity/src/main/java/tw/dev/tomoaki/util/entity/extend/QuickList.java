/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity.extend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import tw.dev.tomoaki.util.entity.DataExistMap;

/**
 *
 * @author Tomoaki Chen
 * @param <T>
 */
public class QuickList<T extends Object> implements List<T> {

    private DataExistMap<T> dataExistMap;

    public QuickList() {
//        this.dataExistMap = new DataExistMap();
//        this.dataExistMap.useLinkedMap();
        this.dataExistMap = DataExistMap.createOrdered();
    }

    @Override
    public int size() {
        return dataExistMap.existList().size();
    }

    @Override
    public boolean isEmpty() {
        return this.dataExistMap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.dataExistMap.contains((T) o);
    }

    @Override
    public Iterator<T> iterator() {
        return this.dataExistMap.existList().iterator();
    }

    @Override
    public Object[] toArray() {
        return this.dataExistMap.existList().toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(T e) {
        try {
            this.dataExistMap.add(e);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        try {
            this.dataExistMap.remove((T) o);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        try {
            this.dataExistMap.addAll(new ArrayList(clctn));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        this.dataExistMap = DataExistMap.createOrdered();
    }

    @Override
    public T get(int i) {
        return this.dataExistMap.existList().get(i);
    }

    @Override
    public T set(int i, T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(int i, T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remove(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.dataExistMap.existList().listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return this.dataExistMap.existList().listIterator(i);
    }

    @Override
    public List<T> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        String result = "[";
        Integer counter = 0;
        List<T> entityList = this.dataExistMap.existList();
        for(T entity : entityList) {
            counter++;
            if(counter >= 2) {
                result += ", ";
            }
            result += entity;
        }
        result += "]";
        return result;
    }
}
