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
import tw.dev.tomoaki.javamethod.JavaMethodHelper;
import tw.dev.tomoaki.javamethod.entity.JavaMethodInfo;
import tw.dev.tomoaki.article.entity.ArticleTokenOption;

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

//    public static List<String> obtainModuleTokenList(Object module) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
//        List<String> tokenList = new ArrayList();
    public static List<ArticleTokenOption> obtainModuleTokenList(Object module) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        List<ArticleTokenOption> tokenList = new ArrayList();
        Field[] fields = ArticleHelper.obtainFields(module);
        for (Field field : fields) {
            String tokenName = field.getName();
//            System.out.println("tokenName= " + tokenName);
            String token = field.get(module).toString();

            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
//                System.out.println(annotation);

                Method[] methods = annotation.annotationType().getDeclaredMethods();
                /*
                for(Method method : methods) {
                    Class attrValueType = method.getReturnType();
                    if(method.getParameterTypes().length == 0 && attrValueType!= void.class) {
                        String attrName = method.getName();
                        Object attrValue = method.invoke(annotation);
                        System.out.println("attrName= " + attrName + ", attrValue= " + attrValue);
                    }
                }
                 */
                ArticleTokenOption tokenOption = new ArticleTokenOption();
                tokenOption.setName(tokenName);
                tokenOption.setToken(token);
                List<JavaMethodInfo> infoList = JavaMethodHelper.obtainPureMethodOnly(annotation, methods);
                if (infoList != null) {
                    for (JavaMethodInfo info : infoList) {
                        String annotationAttrName = info.getMethodName();
                        String annotationAttrValue = info.getMethodReturnValue().toString();
                        switch (annotationAttrName) {
                            case "summary": {
                                tokenOption.setSummary(annotationAttrValue);
                                break;
                            }
                            case "description": {
                                tokenOption.setDescription(annotationAttrValue);
                            }
                        }
                    }
                    tokenList.add(tokenOption);
                }
            }
        }
        return tokenList;
    }

}
