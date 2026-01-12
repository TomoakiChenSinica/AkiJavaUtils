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
package tw.dev.tomoaki.datetimeexpression.entity.core;

/**
 *
 * @author tomoaki
 */
public abstract class ExpressionPart {

    private Boolean isFillable = false;
    private String matchText;
    private Integer addendNums = 0;

    public abstract String getClozePartName();

    public Boolean getIsFillable() {
        return isFillable;
    }

    public void setIsFillable(Boolean isFillable) {
        this.isFillable = isFillable;
    }

    public String getMatchText() {
        return matchText;
    }

    public void setMatchText(String matchText) {
        this.matchText = matchText;
    }

    public Integer getAddendNums() {
        return addendNums;
    }

    public void setAddendNums(Integer addendNums) {
        this.addendNums = addendNums;
    }

}
