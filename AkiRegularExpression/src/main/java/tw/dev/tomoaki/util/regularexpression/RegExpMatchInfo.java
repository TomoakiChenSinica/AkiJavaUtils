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
package tw.dev.tomoaki.util.regularexpression;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomoaki
 */
public class RegExpMatchInfo {
 
    private String match;
    private List<String> captureList;

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public List<String> getCaptureList() {
        return captureList;
    }

    public void setCaptureList(List<String> captureList) {
        this.captureList = captureList;
    }
    
    public void addCapture(String capture) {
        if(this.captureList == null) {
            this.captureList = new ArrayList();
        }
        this.captureList.add(capture);
    }
}
