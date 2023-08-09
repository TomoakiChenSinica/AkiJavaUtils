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
package tw.dev.tomoaki.util.regularexpression.impl.result;

/**
 *
 * @author tomoaki
 */
public class FileNameRegExpResult {
    
    private String prefix;
    private String suffix;

    protected FileNameRegExpResult() {
    }
    
    public static class Factory {
        
        public static FileNameRegExpResult create(String prefix, String suffix) {
            FileNameRegExpResult result = new FileNameRegExpResult();
            result.prefix = prefix;
            result.suffix = suffix;
            return result;
        }
    }
    
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    
    
}
