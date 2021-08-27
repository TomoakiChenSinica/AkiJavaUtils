/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.entity.extend;

import java.util.ArrayList;
import java.util.List;
import tw.dev.tomoaki.util.entity.DataExistMap;

/**
 *
 * @author Tomoaki Chen
 */
public class PairDataDiff<T> {

    private DataExistMap<T> standardGroup;
    private DataExistMap<T> compareGroup;
    
    
    /**
     * compare 組相對於 standard 組多了哪些資料 
     */
    private List<T> moreDataList;
    
    /**
     * compare 組相對於 standard 組少了哪些資料 
     */
    private List<T> lessDataList;

    protected PairDataDiff() {
        this.moreDataList = new ArrayList();
        this.lessDataList = new ArrayList();
    }

    public static class Factory {

        public static <T> PairDataDiff create(List<T> standardList, List<T> compareList) { //function 的 要放前面?
            PairDataDiff diff = new PairDataDiff();
            diff.standardGroup = new DataExistMap(standardList);
            diff.compareGroup = new DataExistMap(compareList);
            diff.doSetupMoreDataList();
            diff.doSetupLessDataList();
            return diff;
        }
    }
    
    /**
     * 計算compare 組相對於 standard 組多了哪些資料  
     * --> compare 組的在 standard中會找不到
     */
    protected void doSetupMoreDataList() {
        List<T> dataList = compareGroup.existList();
        for(T data : dataList) {
            if(this.standardGroup.contains(data) == false) { //compare 的資料在 standard中找不到
                this.moreDataList.add(data);
            }
        }
    }
    
    // 這是一種方法，原本有想到一種是需要 實作一個 equal method，讓 T 和 T 比較是否為同
    /**
     * 計算compare 組相對於 standard 組少了哪些資料  
     * --> standard 組的在 compare 中會找不到
     */
    protected void doSetupLessDataList() {
        List<T> dataList = this.standardGroup.existList();
        for(T data : dataList) {
            if(this.compareGroup.contains(data) == false) { //compare 的資料在 standard中找不到
                this.lessDataList.add(data);
            }
        }
    }    

    public List<T> getMoreDataList() {
        return moreDataList;
    }

    public List<T> getLessDataList() {
        return lessDataList;
    }
    
    
}
