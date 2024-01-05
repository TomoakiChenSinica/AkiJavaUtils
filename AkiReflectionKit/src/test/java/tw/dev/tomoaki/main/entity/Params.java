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

import tw.dev.tomoaki.main.annotation.TestAnnotation1;
import tw.dev.tomoaki.main.annotation.TestAnnotation2;

/**
 *
 * @author tomoaki
 */
public class Params {

    @TestAnnotation1
    @TestAnnotation2
    private String param1;

    @TestAnnotation1
    @TestAnnotation2
    private String param2;

    @TestAnnotation1
    @TestAnnotation2
    protected String param3;

    @TestAnnotation1(code= "ForParam4")
    @TestAnnotation2(code = "ForParam4")
    public String param4;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

}
