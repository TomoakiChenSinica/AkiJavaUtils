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
package tw.dev.tomoaki.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 *
 * @author tomoaki
 */
public class AnnotationHelper {

    public static <T extends Annotation> T getAnnotationOnCurrentMethod(Class<T> classOfAnnotation) throws NoSuchMethodException, ClassNotFoundException {
        return getAnnotationOnCurrentMethod(classOfAnnotation, 0 + 1);
    }
    
    public static <T extends Annotation> T[] findAnnotationOnCurrentMethod(Class<T> classOfAnnotation) throws NoSuchMethodException, ClassNotFoundException {
        return findAnnotationOnCurrentMethod(classOfAnnotation, 0 + 1);
    }    
    
    public static <T extends Annotation> T getAnnotationOnCurrentMethod(Class<T> classOfAnnotation, int addonOffset) throws NoSuchMethodException, ClassNotFoundException {
        Method currMethod = MethodHelper.obtainCurrentMethod(1 + addonOffset);
        return getAnnotationOnMethod(classOfAnnotation, currMethod);
    }
    
    public static <T extends Annotation> T[] findAnnotationOnCurrentMethod(Class<T> classOfAnnotation, int addonOffset) throws NoSuchMethodException, ClassNotFoundException {
        Method currMethod = MethodHelper.obtainCurrentMethod(1 + addonOffset);
        return findAnnotationOnMethod(classOfAnnotation, currMethod);
    }
    
    // ---------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static <T extends Annotation> T getAnnotationOnMethod(Class<T> classOfAnnotation, Method methodInClass) throws NoSuchMethodException, ClassNotFoundException {
        T[] annotations = findAnnotationOnMethod(classOfAnnotation, methodInClass);
        if (annotations != null && annotations.length > 0) {
            return annotations[0];
        }
        return null;
    }
    
    public static <T extends Annotation> T[] findAnnotationOnMethod(Class<T> classOfAnnotation, Method methodInClass) throws NoSuchMethodException, ClassNotFoundException {
        // Method selfMethod = MethodHelper.obtainCurrentMethod(); //這樣會把 Method 抓成「這個 Method 自己」
        T[] annotations = methodInClass.getAnnotationsByType(classOfAnnotation);
        return annotations;
    }
    
    // ---------------------------------------------------------------------------------------------------------------------------------------------------   
}
