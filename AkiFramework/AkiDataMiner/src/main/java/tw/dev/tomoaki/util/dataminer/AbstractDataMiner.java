/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.dataminer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomoaki Chen
 */
public abstract class AbstractDataMiner<T, Q extends DataMinerOptionQuery> {

    private List<T> oriDataList;
    protected Q preQuery;
    private Boolean forceSearch = false;

    public final List<T> doMining(Q query) {
        List<T> dataList;
        if (!forceSearch && justNeedFilter(query)) { //沒有設定成強制查詢(forceSearch) 且 判斷根據 query 判斷只需要 Filter oriDataList
            dataList = this.doFilter(query);
        } else {
            this.oriDataList = this.doSearch(query); //其實doSearch裡面就有存
            dataList = this.doFilter(query);
            this.forceSearch = false;
        }
        preQuery = this.copyQuery(query);
        return dataList;
    }

    public void turnOnForceSearch() {
        this.forceSearch = true;
    }

    /**
     * public -> protected
     */
    private List<T> doFilter(Q query) {
        if (this.isDataListNull()) {
            throw new DataMinerException("oriDataList After doSearch Is Null");
        }
        List<T> resultList = new ArrayList(oriDataList); //為的是複製一份 List
        // resultList = this.tryFilter(oriDataList, query); //之前可能是因為「tryFilter」內都是 Stream 實作所以沒問題，不然理論上會改到 oriDataList ?
        resultList = this.tryFilter(resultList, query);
        return resultList;
    }

    private List<T> doSearch(Q query) {
        return this.trySearch(query);
    }

    protected Boolean isDataListNull() {
        return this.oriDataList == null;
    }

    protected Boolean isDataListEmpty() {
        return this.oriDataList != null && this.oriDataList.isEmpty();
    }

    protected abstract Q copyQuery(Q query);

    protected abstract List<T> trySearch(Q query);

    protected abstract List<T> tryFilter(List<T> dataList, Q query);

    protected abstract Boolean justNeedFilter(Q query);

}
