/*
 * Copyright 2024 tomoaki.
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
package tw.dev.tomoaki.util.datetime.util;

import tw.dev.tomoaki.util.datetime.entity.LocalYearMonth;

/**
 *
 * @author tomoaki
 */
public class LocalYearMonthHelper {

    public static LocalYearMonth parseStandardFormat(String strYearMonth) {
        String[] arr = strYearMonth.split("-");
        String year = arr[0];
        String month = arr[1];
        return LocalYearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
    }
}
