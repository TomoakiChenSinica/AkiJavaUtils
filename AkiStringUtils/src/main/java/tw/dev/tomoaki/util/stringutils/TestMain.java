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
package tw.dev.tomoaki.util.stringutils;

/**
 *
 * @author tomoaki
 */
public class TestMain {
 
    public static void main(String[] args) {
        String msg = "br>需檢查資訊設備，理由為: 出差人有薪資計畫為核心研究計畫: 「(3006) 資訊科學研究-行政與技術業務」\\r\\n 前往或過境特定國家(地區)需完成資安相關程序。系統會寄送相關規定，請務必依照信件辦理。";
        System.out.println(msg);
        System.out.println(AkiStringUtil.parseHtmlFormat(msg));
    }
}
