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
package tw.dev.tomoaki.util.string.name;

/**
 *
 * @author tomoaki
 */
public class LatinNameHelper {

//    private static final String COMMON_PATTERN = "";
    public static String createCommonFullName(String firstName, String lastName, String... middleNames) {
        StringBuilder builder = new StringBuilder();
        builder.append(firstName);
        builder.append(" ");        
        if (middleNames != null) {
            for (String middleName : middleNames) {
                if(middleName != null) {
                    builder.append(middleName);
                    builder.append(" ");
                }
            }
        }
        builder.append(lastName);
        return builder.toString();
    }

    public static String createLastNameFirstFullName(String lastName, String firstName) {
        StringBuilder builder = new StringBuilder();
        return builder.append(lastName)
                .append(", ")
                .append(firstName)
                .toString();
    }
}
