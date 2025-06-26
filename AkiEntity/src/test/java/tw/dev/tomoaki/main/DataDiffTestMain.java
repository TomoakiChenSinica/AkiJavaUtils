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
package tw.dev.tomoaki.main;

import java.util.Arrays;
import tw.dev.tomoaki.util.entity.extend.HistoricalDataDiff;
import tw.dev.tomoaki.util.entity.extend.PairDataDiff;

/**
 *
 * @author arche
 */
public class DataDiffTestMain {

    public static void main(String[] args) {
        test1();
    }
    
    private static void test1() {
        HistoricalDataDiff<String> historicalDiff = HistoricalDataDiff.create();
        PairDataDiff<String> diff = historicalDiff.compare();
        String msgFmt = "diff.more= %s, diff.less= %s, diff.overlap= %s";
        System.out.println(String.format(msgFmt, diff.getMoreDataList(), diff.getLessDataList(), diff.getOverlappingDataList()));

        diff = historicalDiff.recordNewDatas(Arrays.asList("1", "3")).compare();
        System.out.println(String.format(msgFmt, diff.getMoreDataList(), diff.getLessDataList(), diff.getOverlappingDataList()));    

        diff = historicalDiff.recordNewDatas(Arrays.asList("1", "3")).compare();
        System.out.println(String.format(msgFmt, diff.getMoreDataList(), diff.getLessDataList(), diff.getOverlappingDataList()));    

        diff = historicalDiff.recordNewDatas(Arrays.asList("2", "3")).compare();
        System.out.println(String.format(msgFmt, diff.getMoreDataList(), diff.getLessDataList(), diff.getOverlappingDataList()));    
    }
}
