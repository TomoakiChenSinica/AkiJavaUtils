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

import java.lang.reflect.Method;
import tw.dev.tomoaki.main.annotation.MethodAnnotation;
import tw.dev.tomoaki.reflection.AnnotationHelper;
import tw.dev.tomoaki.reflection.MethodHelper;

/**
 *
 * @author tomoaki
 */
public class MethodModule {

    @MethodAnnotation(code = "This is a book")
    public void test1() throws NoSuchMethodException, ClassNotFoundException {
        // Method currMethod = this.getClass().getEnclosingMethod();
        // System.out.println(currMethod);

        Method selfMethod = MethodHelper.obtainCurrentMethod();
        MethodAnnotation[] annotations = selfMethod.getAnnotationsByType(MethodAnnotation.class);
        if (annotations != null && annotations.length > 0) {
            System.out.println(annotations[0].code());
        }
    }

    @MethodAnnotation(code = "This is a pen")
    public void test2() throws NoSuchMethodException, ClassNotFoundException {
        MethodAnnotation anno = AnnotationHelper.getAnnotationOnCurrentMethod(MethodAnnotation.class);
        if (anno != null) {
            System.out.println(anno.code());
        }
    }

    @MethodAnnotation(code = "This is a penceil")
    public void test3() throws NoSuchMethodException, ClassNotFoundException {
        test3_1();
    }

    // public void test3_1() throws NoSuchMethodException, ClassNotFoundException {
    protected void test3_1() throws NoSuchMethodException, ClassNotFoundException {
        MethodAnnotation annOnCurrent = AnnotationHelper.getAnnotationOnCurrentMethod(MethodAnnotation.class);
        if (annOnCurrent != null) {
            System.out.println("annOnCurrent= " + annOnCurrent.code());
        }

        Method callerMethod = MethodHelper.obtainCallertMethod();
        System.out.println("callerMethod = " + callerMethod.getName());
    }
}
