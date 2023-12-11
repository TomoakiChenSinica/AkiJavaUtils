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
package tw.dev.tomoaki;

import tw.dev.tomoaki.util.string.StringTrimmer;

/**
 *
 * @author tomoaki
 */
public class StringTrimmerTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String article = " 資創中心支付112年​​12月正編人員薪俸扣項    "; 
        String fixedArticle1 = StringTrimmer.propose(article).trimSpace().produce();
        String fixedArticle2 = StringTrimmer.propose(article).trimUnicodeIllegal().produce();
        String fixedArticle3 = StringTrimmer.propose(article).trimSpace().trimUnicodeIllegal().produce();
        System.out.println(String.format("article= %s, article.length= %s", article, article.length()));
        System.out.println(String.format("fixedArticle1= %s, fixedArticle1.length= %s", fixedArticle1, fixedArticle1.length()));
        System.out.println(String.format("fixedArticle2= %s, fixedArticle2.length= %s", fixedArticle2, fixedArticle2.length()));
        System.out.println(String.format("fixedArticle3= %s, fixedArticle3.length= %s", fixedArticle3, fixedArticle3.length()));
        
    }
    
}
