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
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.util.datetime.enumoption.LunarMonth;

/**
 *
 * @author tomoaki
 */
public class LunarMonthTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LunarMonth april = LunarMonth.APRIL;
        System.out.println(april);        
        System.out.println(april.getDisplayFullNameTW());
        System.out.println(april.getDisplayFullNameEN());
    }
    
}
