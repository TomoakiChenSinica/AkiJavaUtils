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
public class MethodHelper {

    
    public static Method obtainCallertMethod(Class<?>... args) throws NoSuchMethodException, ClassNotFoundException {
        //因為右多一層 Method 轉 call ，所以又要多加 1
        return obtainCallertMethod(0 + 1, args);
    }

    public static Method obtainCallertMethod(int addonOffset, Class<?>... args) throws NoSuchMethodException, ClassNotFoundException {
        StackTraceElement element = StackTraceHelper.getCallerStackTraceElement(1 + addonOffset);
        String currentClassName = element.getClassName();
        Class<?> currentClass = Class.forName(currentClassName);
        String methodName = element.getMethodName();
        return currentClass.getDeclaredMethod(methodName, args); // 這樣會被當成 Method 不帶任何參數，可能會有問題
    }
    
    
    
    public static Method obtainCurrentMethod(Class<?>... args) throws NoSuchMethodException, ClassNotFoundException {
        //因為右多一層 Method 轉 call ，所以又要多加 1
        return obtainCurrentMethod(0 + 1, args); 
    }
    
    public static Method obtainCurrentMethod(int addonOffset, Class<?>... args) throws NoSuchMethodException, ClassNotFoundException {
        StackTraceElement element = StackTraceHelper.getCurrentStackTraceElement(1 + addonOffset); // 要撇開調自身這個 Method (obtainCurrentMethod)
        String currentClassName = element.getClassName();
        Class<?> currentClass = Class.forName(currentClassName);
        String methodName = element.getMethodName();
        // return currentClass.getMethod(methodName); // 這只能拿到 public Method
        return currentClass.getDeclaredMethod(methodName, args);
    }
    

//<editor-fold defaultstate="collapsed" desc="尋找 Methods 系列。有被 Annotation 標註的">
    
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
