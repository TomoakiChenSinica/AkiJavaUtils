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
package tw.dev.tomoaki.util.regularexpression.impl;

import java.util.List;
import tw.dev.tomoaki.util.regularexpression.RegExpMatchInfo;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;
import tw.dev.tomoaki.util.regularexpression.helper.RegExpCommonPattern;
import tw.dev.tomoaki.util.regularexpression.impl.result.FileNameRegExpResult;

/**
 *
 * @author tomoaki
 */
public class FileNameRegExpUtil {
    
    public static RegExpProcessor fileNameProcessor;
    
    static {
        fileNameProcessor = RegExpProcessor.Factory.create(RegExpCommonPattern.FILE_NAME_CAPTURE);
    }
    
    public static FileNameRegExpResult process(String fileName) {
        RegExpResult regExpResult = fileNameProcessor.processMatch(fileName);
        if(!regExpResult.isFind()) {
            throw new IllegalArgumentException("fileName Is Wrong");
        }
        
        RegExpMatchInfo infos = regExpResult.getMatchInfos().get(0);
        List<String> captureList = infos.getCaptureList();
        String filePrefix = captureList.get(0);
        String fileSuffix = captureList.get(1);
//        System.out.println(filePrefix + ", " + fileSuffix);
        return FileNameRegExpResult.Factory.create(filePrefix, fileSuffix);
    }
}
