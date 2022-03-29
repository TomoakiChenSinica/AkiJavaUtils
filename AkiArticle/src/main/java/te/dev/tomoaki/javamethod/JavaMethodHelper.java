/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package te.dev.tomoaki.javamethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import te.dev.tomoaki.javamethod.entity.JavaMethodInfo;

/**
 *
 * @author Tomoaki Chen
 */
public class JavaMethodHelper {

    public static List<JavaMethodInfo> obtainPureMethodOnly(Object ownMethodObject, Method[] methods) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<JavaMethodInfo> methodInfoList;
        if (methods != null && methods.length == 0) {
            methodInfoList = null;
        } else {
            methodInfoList = new ArrayList();
            for (Method method : methods) {
                Class attrValueType = method.getReturnType();
//                System.out.println("[JavaMethodHelper] attrValueType= " + attrValueType);
                if (method.getParameterTypes().length == 0 && attrValueType != void.class) {
                    String attrName = method.getName();
                    Object attrValue = method.invoke(ownMethodObject);
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
}
