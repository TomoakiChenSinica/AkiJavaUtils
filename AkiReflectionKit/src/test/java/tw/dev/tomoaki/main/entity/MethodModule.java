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
import java.util.Objects;
import java.util.stream.Stream;
import tw.dev.tomoaki.main.annotation.MethodAnnotation;
import tw.dev.tomoaki.reflection.JavaMethodHelper;

/**
 *
 * @author tomoaki
 */
public class MethodModule {

    @MethodAnnotation(code = "This is a book")
    public void test1() throws NoSuchMethodException, ClassNotFoundException {
        // Method currMethod = this.getClass().getEnclosingMethod();
        // System.out.println(currMethod);
        
        Method selfMethod = JavaMethodHelper.obtainCurrentMethod();
//        System.out.println("selfMethod= " + selfMethod);
        MethodAnnotation[] annotations = selfMethod.getAnnotationsByType(MethodAnnotation.class);
        // Stream.of(annotations).filter(Objects::nonNull).forEach(System.out::println);
        if(annotations != null && annotations.length > 0) {
            System.out.println(annotations[0].code());
        }
    }
}
