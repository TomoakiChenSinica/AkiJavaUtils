/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.article.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomoaki Chen
 */
public class ArticleHelper {
    
    protected static Field[] obtainFields(Object obj) {
//        Field[] fields = obj.getClass().getFields();
        Field[] fields = obj.getClass().getFields();
        return fields;
    }
    
    public static List<String> obtainModuleTokenList(Object module) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        List<String> tokenList = new ArrayList();
        Field[] fields = ArticleHelper.obtainFields(module);
        for(Field field : fields) {            
            String tokenName = field.getName();
            System.out.println("tokenName= " + tokenName);
//            if(attrName.startsWith("TOKEN_")) {
//                String attrValue = (String)field.get(module);
//                tokenList.add(attrValue);                                
//            }
            Annotation[] annotations =field.getDeclaredAnnotations();
            for(Annotation annotation : annotations) {
                System.out.println(annotation);                      
                Method[] methods = annotation.annotationType().getDeclaredMethods();
                for(Method method : methods) {
                    if(method.getParameterTypes().length == 0 && method.getReturnType() != void.class) {
                        String attrName = method.getName();
                        Object attrValue = method.invoke(annotation);
                        System.out.println("attrName= " + attrName + ", attrValue= " + attrValue);
                    }
                }
            }
//            Annotation tokenAnnotation = field.getAnnotation(ArticleToken.class);            
//            System.out.println("attrName= " + attrName + ", tokenAnnotation= " + tokenAnnotation);
        }
        return tokenList;
    }
    
}
