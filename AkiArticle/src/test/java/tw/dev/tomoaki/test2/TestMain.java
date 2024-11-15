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
package tw.dev.tomoaki.test2;

/**
 *
 * @author tomoaki
 */
public class TestMain {

    public static void main(String[] args) {
        print("1234", "4321");

        Object obj = new Object();
        Class clazz = obj.getClass();
        System.out.println(obj instanceof Object);
        System.out.println(obj instanceof Class);
        System.out.println(clazz instanceof Object);        
        System.out.println(clazz instanceof Class);
    }

    private static void print(String text) {
        System.out.println(text);
    }

    private static void print(String text, String... otherTexts) {
        System.out.println(text);
        System.out.println(otherTexts);
    }
}
