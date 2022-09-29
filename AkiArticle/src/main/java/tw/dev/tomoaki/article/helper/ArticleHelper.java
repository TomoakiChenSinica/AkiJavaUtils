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
import java.util.Map;
import java.util.Set;
import tw.dev.tomoaki.article.entity.ArticleTokenMap;
import tw.dev.tomoaki.javamethod.JavaMethodHelper;
import tw.dev.tomoaki.javamethod.entity.JavaMethodInfo;
import tw.dev.tomoaki.article.entity.ArticleTokenOption;

/**
 *
 * @author Tomoaki Chen
 */
public class ArticleHelper {

    private static final String RESERVED_WORD_ITERATOR = "#{%s}[]";
    public static final String REGEXP_WORD_MATCH_ITERATOR = "#\\{.*\\}\\[\\]";
    public static final String REGEXP_WORD_CAPTURE_ITERATOR = "#\\{(.*)\\}\\[\\]";
    
    
    protected static Field[] obtainFields(Object obj) {
        Field[] fields = obj.getClass().getFields();
        return fields;
    }

    public static List<ArticleTokenOption> obtainModuleTokenList(Object module) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        List<ArticleTokenOption> tokenList = new ArrayList();
        Field[] fields = ArticleHelper.obtainFields(module);
        for (Field field : fields) {
            String tokenName = field.getName();
            String token = field.get(module).toString();

            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                Method[] methods = annotation.annotationType().getDeclaredMethods();

                ArticleTokenOption tokenOption = new ArticleTokenOption();
                tokenOption.setName(tokenName);
                tokenOption.setToken(token);
                List<JavaMethodInfo> infoList = JavaMethodHelper.obtainPureMethodOnly(annotation, methods);
                if (infoList != null) {
                    for (JavaMethodInfo info : infoList) {
                        String annotationAttrName = info.getMethodName();
                        Object objAnnotationAttrValue = info.getMethodReturnValue();
//                        System.out.println("objAnnotationAttrValue= " + objAnnotationAttrValue + ", type= " + objAnnotationAttrValue.getClass().getSimpleName());
                        switch (annotationAttrName) {
                            case "summary": {
                                tokenOption.setSummary(objAnnotationAttrValue.toString());
                                break;
                            }
                            case "description": {
                                tokenOption.setDescription(objAnnotationAttrValue.toString());
                                break;
                            }
                            case "level": {
                                tokenOption.setLevel((Integer) objAnnotationAttrValue);
                                break;
                            }
                        }
                    }
                    tokenList.add(tokenOption);
                }
            }
        }
        return tokenList;
    }

    public static String replaceTokens(String oriText, ArticleTokenMap articleTokenNap) {
        String newText = oriText;
        List<Map<String, String>> tokensMapperList = articleTokenNap.getTokenReplaceMapList();
        for (Map<String, String> tokensMapper : tokensMapperList) {
            Set<Map.Entry<String, String>> tokensMapperEntrySet = tokensMapper.entrySet();
            for (Map.Entry<String, String> tokensMapperEntry : tokensMapperEntrySet) {
                String token = tokensMapperEntry.getKey();
                String word = tokensMapperEntry.getValue();
                if (token != null && word != null) {
                    newText = newText.replace(token, word);
                }
            }
        }
        return newText;
    }
    
    public static String convert2IteratorPart(String oriArticlePart) {
        return String.format(RESERVED_WORD_ITERATOR, oriArticlePart);
    }
}
