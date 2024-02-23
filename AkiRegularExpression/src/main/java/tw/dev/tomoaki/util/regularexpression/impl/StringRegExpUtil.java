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
package tw.dev.tomoaki.util.regularexpression.impl;

import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;

/**
 *
 * @author tomoaki
 */
public class StringRegExpUtil {
    
    private final static RegExpProcessor CAPITALIZER = RegExpProcessor.Factory.create("^\\w"); 
    
    public static String capitalize(String input) {        
        RegExpResult result = CAPITALIZER.processMatch(input);
        if(result.isFind()) {
            String resultWord = result.getMatchResults().get(0);
            String upperCasedResultWord = resultWord.toUpperCase();
            return CAPITALIZER.processReplace(input, upperCasedResultWord);
        }
        return input;
    }
}
