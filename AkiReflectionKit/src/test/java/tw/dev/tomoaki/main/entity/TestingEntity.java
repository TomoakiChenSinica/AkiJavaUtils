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
package tw.dev.tomoaki.main.entity;

import tw.dev.tomoaki.reflection.StackTraceHelper;

/**
 *
 * @author tomoaki
 */
public class TestingEntity {
    
    public void test1() {
        System.out.println("stack trace for test1(): ");
        StackTraceHelper.printStackTrace();
    }
    
    public void test2() {
        System.out.println("stack trace for test2(): ");
        StackTraceHelper.printStackTrace();
        test2_1();
    }
    
    protected void test2_1() {
    // protected void test2_1() {
        System.out.println("stack trace for test2_1(): ");
        StackTraceHelper.printStackTrace();
    }    
}
