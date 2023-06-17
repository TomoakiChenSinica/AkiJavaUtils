/*
 * Copyright 2023 tomoaki.
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
package tw.dev.tomoaki.util.stringutils;

import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author tomoaki
 */
public class StringSorter {

//    public static List<String> doStandardSort(List<String> strList) {
//        return doStandardSort(strList, Boolean.TRUE);
//    }
//    
//    public static List<String> doStandardSort(List<String> strList, Boolean asc) {
//        List<String> sortedStrList = strList.stream()
//                .sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
//                .collect(toList());
//        return sortedStrList;
//    }
    
    public static List<String> doStandardSortASC(List<String> strList) {
        List<String> sortedStrList = strList.stream()
                .sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
                .collect(toList());
        return sortedStrList;
    }    
    
    public static List<String> doStandardSortDESC(List<String> strList) {
        List<String> sortedStrList = strList.stream()
                .sorted(Comparator.nullsFirst(Comparator.reverseOrder()))
                .collect(toList());
        return sortedStrList;
    }       
}
