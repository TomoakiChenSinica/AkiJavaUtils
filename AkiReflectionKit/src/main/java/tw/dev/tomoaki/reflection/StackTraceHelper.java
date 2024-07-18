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

import java.util.stream.Stream;

/**
 *
 * @author tomoaki
 */
public class StackTraceHelper {
    
    /*public static void test1() {
        Stream.of(Thread.currentThread().getStackTrace()).forEach(System.out::println);
    }*/
    
    public static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[3];
    }
    
    
    /**
     * 
     * [2] 已經是排除掉此 Method 自身 
     */
    public static StackTraceElement getCurrentStackTraceElement() {
        // https://chatgpt.com/share/2eb037d8-04d0-4d1d-8216-0eb1b198570a ，之前好像也學過，第三筆會是目前執行的 Method
        return Thread.currentThread().getStackTrace()[2];
    }
}
