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

    protected DataExistMap<T> standardGroup;
    protected DataExistMap<T> compareGroup;

    /**
     * compare 組相對於 standard 組多了哪些資料
     */
    protected List<T> moreDataList;

    /**
     * compare 組相對於 standard 組少了哪些資料
     */
    protected List<T> lessDataList;

    /**
     * compare組 和 standard組的相同之處
     */
    protected List<T> overlappingDataList;

    protected PairDataDiff() {
        this.moreDataList = new ArrayList();
        this.lessDataList = new ArrayList();
        this.overlappingDataList = new ArrayList();
    }

    /**
     * 比較 standard組 和 compare組 的不同
     *
     * @param standardList standard組的資料清單
     * @param compareList compare組的資料清單
     * @return 差異資料
     */
    public static <T> PairDataDiff create(List<T> standardList, List<T> compareList) { //function 的 要放前面?
        PairDataDiff diff = new PairDataDiff();
        diff.standardGroup = new DataExistMap(standardList);
        diff.compareGroup = new DataExistMap(compareList);
        diff.doSetupMoreDataList();
        diff.doSetupLessDataList();
        diff.doSetupOverlappingDataList();
        return diff;
    }

    /**
     * 計算compare 組相對於 standard 組多了哪些資料   <br>
     * --> compare 組的在 standard中會找不到 <br>
     */
    protected void doSetupMoreDataList() {
        List<T> dataList = compareGroup.existList();
        for (T data : dataList) {
            if (this.standardGroup.contains(data) == false) { //compare 的資料在 standard中找不到
                this.moreDataList.add(data);
            }
        }
    }

    // 這是一種方法，原本有想到一種是需要 實作一個 equal method，讓 T 和 T 比較是否為同
    /**
     * 計算compare 組相對於 standard 組少了哪些資料 <br>
     * --> standard 組的在 compare 中會找不到 <br>
     */
    protected void doSetupLessDataList() {
        List<T> dataList = this.standardGroup.existList();
        for (T data : dataList) {
            if (this.compareGroup.contains(data) == false) { //compare 的資料在 standard中找不到
                this.lessDataList.add(data);
            }
        }
    }

    /**
     * standard 在 compare 組中找的到的。 <br>
     * 紀錄在 overlappingDataList
     *
     */
    protected void doSetupOverlappingDataList() {
        List<T> dataList = this.standardGroup.existList();
        for (T data : dataList) {
            if (this.compareGroup.contains(data) == true) {
                this.overlappingDataList.add(data);
            }
        }
    }

    /**
     * compare組(後面那組資料清單) 比 standard組(前面那組資料清單) 多了哪些資料
     *
     * @return compare組 比 standard組 多了哪些資料
     */
    public List<T> getMoreDataList() {
        return moreDataList;
    }

    /**
     * compare組(後面那組資料清單) 比 standard組(前面那組資料清單) 少了哪些資料
     *
     * @return compare組 比 standard組 少了哪些資料
     */
    public List<T> getLessDataList() {
        return lessDataList;
    }

    /**
     * compare組(後面那組資料清單) 比 standard組(前面那組資料清單) 都有的資料
     *
     * @return compare組 和 standard組 都有的資料
     */
    public List<T> getOverlappingDataList() {
        return overlappingDataList;
    }
    
    public Boolean isDiffExist() {
        return !this.lessDataList.isEmpty() || !this.moreDataList.isEmpty();
    }

}
