/*
 * Copyright 2025 arche.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tw.dev.tomoaki.util.entity.extend;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author arche
 */
public class HistoricalDataDiff<T> {
    
    private List<T> nowDataList;
    private List<T> newDataList;
    
    public HistoricalDataDiff() {
    }
    
    public HistoricalDataDiff(List<T> initDataList) {
        this.nowDataList = initDataList;
    }
    
    public static <T> HistoricalDataDiff<T> create() {
        return new HistoricalDataDiff<>();
    }
    
    public static <T> HistoricalDataDiff<T> create(List<T> initDataList) {
        if(initDataList == null) {
            return new HistoricalDataDiff();
        }
        return new HistoricalDataDiff<>(initDataList);
    }
    
    public static <T> HistoricalDataDiff<T> create(T... initDatas) {
        return new HistoricalDataDiff<>(Arrays.asList(initDatas));
    } 
   
    public HistoricalDataDiff recordNewDatas(List<T> newDataList) {
        this.newDataList = newDataList;
        return this;
    }
    
    public PairDataDiff<T> compare() {
        PairDataDiff<T> diff = PairDataDiff.create(nowDataList, newDataList);
        this.nowDataList = this.newDataList;
        this.newDataList = null;
        return diff;
    }
}
