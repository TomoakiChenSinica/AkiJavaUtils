/*
 * Copyright 2025 tomoaki.
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

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author tomoaki
 */
public class JavaYearMonthTestMain {
    
    public static void main(String[] args) {
        YearMonth yearMonth1 = YearMonth.parse("2025/03", DateTimeFormatter.ofPattern("yyyy/MM"));
        System.out.println(String.format("yearMonth1.year= %s, yearMonth.month= %s", yearMonth1.getYear(), yearMonth1.getMonthValue()));
    }
}
