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
package tw.dev.tomoaki;

import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.util.stringutils.StringSorter;

/**
 *
 * @author tomoaki
 */
public class StringOrderTestMain {

    public static void main(String[] args) {
//        List<String> strList = Stream.of("j-1", "j-2", null, null)
//                .sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
//                .collect(toList());
//        System.out.println(strList);
        List<String> strList = Arrays.asList("j-1", "j-2", null, null);
        System.out.println(StringSorter.doStandardSortDESC(strList));
        System.out.println(StringSorter.doStandardSortASC(strList));
        
    }
}
