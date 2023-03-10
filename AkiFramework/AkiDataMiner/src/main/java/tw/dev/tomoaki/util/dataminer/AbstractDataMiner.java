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
    
    private List<T>oriDataList;
    protected Q preQuery;
    private Boolean forceSearch = false;
    
    public List<T> doMining(Q query) {
        List<T> dataList = null;
        if (!forceSearch && justNeedFilter(query)) {                       
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
        if(this.isDataListNull()) {
            throw new DataMinerException("oriDataList After doSearch Is Null");
        }
        List<T> resultList = new ArrayList(oriDataList);
        resultList = this.tryFilter(oriDataList, query);
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
