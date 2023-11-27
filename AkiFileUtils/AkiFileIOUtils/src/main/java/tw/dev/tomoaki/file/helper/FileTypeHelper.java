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
package tw.dev.tomoaki.file.helper;

import java.util.Arrays;

/**
 *
 * @author tomoaki
 */
public class FileTypeHelper {
    
//    public static String guessFileTypeName(String fileName) {
//        Stream.
//        
//    }        
    public static String analyzeFileNameExt(String fileName) {
        String[] nameArr = fileName.split("\\.");
        Integer arrLen = nameArr.length;
        if(arrLen == 1) {
            String msgFmt = "No Filename Extension Find In fileName= %s";
            throw new IllegalArgumentException(String.format(msgFmt, fileName));
        }
        return nameArr[arrLen - 1];
    }
}
