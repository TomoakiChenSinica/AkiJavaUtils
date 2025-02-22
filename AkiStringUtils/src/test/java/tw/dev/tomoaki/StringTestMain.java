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
package tw.dev.tomoaki;

import tw.dev.tomoaki.util.string.AkiStringUtil;

/**
 *
 * @author tomoaki
 */
public class StringTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        testImputation();
        testConcat();
    }

    private static void testShorten() {
        String sentence1 = "Hello World";
        System.out.println(AkiStringUtil.shorten(sentence1, 2, 2));
        System.out.println(AkiStringUtil.shorten(sentence1, 2, 2, "XXX"));

    }

    private static void testConcat() {
        System.out.println(AkiStringUtil.concatSafelyWith(" ", "this", "is", "a", "book"));
        System.out.println(AkiStringUtil.concatBetweenSafelyWith("", " ", "this", "is", "a", "book"));
        System.out.println(AkiStringUtil.concatBetweenSafelyWith("!", " ", "this", "is", null, "book", null));
        System.out.println(AkiStringUtil.concatBetweenSafely("!", "this", "is", null, "book", null));
        System.out.println(AkiStringUtil.concatBetweenSafely(" ", "this", "is", null, "book", null));
        System.out.println(AkiStringUtil.concatBetweenActually("!", "this", "is", null, "book", null));
    }

    private static void testImputation() {
        System.out.println(AkiStringUtil.imputation(1, 2, '0'));
        System.out.println(AkiStringUtil.imputation(12, 2, '0'));
    }
}
