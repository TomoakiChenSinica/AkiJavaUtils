/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.reflection;

import java.lang.annotation.Annotation;
import tw.dev.tomoaki.reflection.entity.JavaMethodInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Tomoaki Chen
 */
public class JavaMethodHelper {
    
    
    public static Method obtainCurrentMethod() throws NoSuchMethodException, ClassNotFoundException {
        StackTraceElement element = StackTraceHelper.getCallerStackTraceElement(); // StackTraceHelper.getCurrentStackTraceElement();
        // Class<?> currentClass = element.getClass();
        String currentClassName = element.getClassName();
        Class<?> currentClass = Class.forName(currentClassName);
        String methodName = element.getMethodName();
        System.out.println("currentClassName= " + currentClassName +  ", currentClass= " + currentClassName + ", methodName= " + methodName);
        return currentClass.getMethod(methodName);
    }
    

//<editor-fold defaultstate="collapsed" desc="尋找 Methods 系列，與 Annotation 相關">
    
    /**
     * 找到有被任意 Annotation 註譯的 Methods
     * 
     * @param clazzType 指定的類別，會檢查此類別中的 methods
     * @return Method 清單，格式為 Method 的 List
     */
    public static List<Method> obtainMethodListIsAnnotated(Class<?> clazzType) {
        return Stream.of(clazzType.getMethods()).filter(method -> {
            Annotation[] annotations = method.getAnnotations();
            return annotations.length > 0;
        }).collect(Collectors.toList());
    }

    /**
     * 找到有被指定 Annotation 註譯的 Methods
     * 
     * @param clazzType 指定的類別，會檢查此類別中的 methods
     * @param desigAnnotationType 指定 Annotation 類型
     * @return Method 清單，格式為 Method 的 List
     */    
    public static List<Method> obtainMethodListIsAnnotatedWith(Class<?> clazzType, Class<?> desigAnnotationType) {
        return Stream.of(clazzType.getMethods()).filter(method -> {
            Annotation[] annotations = method.getAnnotations();
            Long matchedAnnotationCount = Stream.of(annotations).map(Annotation::annotationType)
                    .filter(annotationType -> Objects.equals(annotationType, desigAnnotationType))
                    .count();
            return matchedAnnotationCount > 0;
        }).collect(Collectors.toList());
    }
    
//    public static <T extends Annotation> List<Method> obtainMethodListByAnnotation(Class<?> clazzType, Class<T> desigAnnotationType) {
//        return Stream.of(clazzType.getMethods()).filter(method -> {
//            Annotation[] annotations = method.getAnnotations();
//            Long matchedAnnotationCount = Stream.of(annotations).map(Annotation::annotationType)
//                    .filter(annotationType -> Objects.equals(annotationType, desigAnnotationType))
//                    .count();
//            return matchedAnnotationCount > 0;
//        }).collect(Collectors.toList());
//    }

//</editor-fold>
    
    public static List<JavaMethodInfo> obtainPureMethodOnly(Object ownMethodObject, Method[] methods) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<JavaMethodInfo> methodInfoList;
        if (methods == null || methods.length == 0) {
            methodInfoList = null;
        } else {
            methodInfoList = new ArrayList();
            for (Method method : methods) {
                Class attrValueType = method.getReturnType();
                if (method.getParameterTypes().length == 0 && attrValueType != void.class) {
                    String attrName = method.getName();
                    Object attrValue = method.invoke(ownMethodObject); //要執行 Method ，所以必須要 實體(物件，在這裡即 ownMethodObject)
                    JavaMethodInfo methodInfo = new JavaMethodInfo();
                    methodInfo.setMethodName(attrName);
                    methodInfo.setMethodReturnType(attrValueType);
                    methodInfo.setMethodReturnValue(attrValue);

                    methodInfoList.add(methodInfo);
                }
            }
        }
        return methodInfoList;
    }
    
    
    
    // --------------------------------------------------------------------------------------------------    
}
