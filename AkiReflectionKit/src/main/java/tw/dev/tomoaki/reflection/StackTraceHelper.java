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
package tw.dev.tomoaki.reflection;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author tomoaki
 */
public class StackTraceHelper {

    private final static int BASIC_OFFSET_SELF_CALLER_METHOD = 2;

    public static StackTraceElement getCallerStackTraceElement() {
        return getCallerStackTraceElement(0 + 1);
    }

    public static StackTraceElement getCallerStackTraceElement(int addonOffset) {
        return Thread.currentThread().getStackTrace()[BASIC_OFFSET_SELF_CALLER_METHOD + 1 + addonOffset]; //每多call一層 method ，要加 1        
    }
    
    // --------------------------------------------------------------------------------------------------

    public static StackTraceElement getCurrentStackTraceElement() {
        //因為右多一層 Method 轉 call ，所以又要多加 1
        return getCurrentStackTraceElement(0 + 1);
    }

    /**
     * https://chatgpt.com/share/2eb037d8-04d0-4d1d-8216-0eb1b198570a
     * ，之前好像也學過，第三筆會是目前執行的 Method [2] 已經是排除掉此 Method 自身
     *
     * @param addonOffset Caller 可能是經過好幾層才呼叫到此 Method
     * @return 格式為 StackTraceElement
     */
    public static StackTraceElement getCurrentStackTraceElement(int addonOffset) {
        return Thread.currentThread().getStackTrace()[BASIC_OFFSET_SELF_CALLER_METHOD + addonOffset]; //每多call一層 method ，要加 1
    }
    
    public static void printStackTrace() {
      Stream.of(Thread.currentThread().getStackTrace()).forEach(System.out::println);
    }
}
