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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import tw.dev.tomoaki.article.annotaion.ArticleToken;
import tw.dev.tomoaki.reflection.MethodHelper;
import tw.dev.tomoaki.reflection.entity.JavaMethodInfo;
import tw.dev.tomoaki.article.entity.ArticleTokenOption;
import tw.dev.tomoaki.reflection.ClassFinder;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;

/**
 *
 * @author Tomoaki Chen
 */
public class ArticleHelper {

    private static final String RESERVED_WORD_ITERATOR = "#{%s}[]";
    public static final String REGEXP_WORD_ITERATOR = "#\\{.*\\}\\[\\]";
    public static final String REGEXP_STANDARD_TOKEN = "\\$\\{[\\w.]+\\}";

    public static List<ArticleTokenOption> obtainModuleTokenListWithPackageName(String packageName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        ClassFinder finder = new ClassFinder();
        List<Class> moduleClassList = finder.findInPackageWithClassLoader(packageName);
        // List<Object> moduleObjList = classList.stream().map(clazz -> clazz.getConstructor().newInstance()).collect(Collectors.toList());
        /*
        List<ArticleTokenOption> allModuleTokenList = new ArrayList();
        for (Class moduleClass : moduleClassList) {
            List<ArticleTokenOption> tokenList = obtainModuleTokenList(moduleClass);
            allModuleTokenList.addAll(tokenList);
        }
        return allModuleTokenList;*/
        return obtainModuleTokenListWithModuleClass(moduleClassList);
    }

    public static List<ArticleTokenOption> obtainModuleTokenListWithClassName(List<String> moduleClassNameList) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
        List<Class> moduleClassList = new ArrayList();
        for(String moduleClassName : moduleClassNameList) {
            moduleClassList.add(Class.forName(moduleClassName));
        } 
        return obtainModuleTokenListWithModuleClass(moduleClassList);
    }
    
    public static List<ArticleTokenOption> obtainModuleTokenListWithClassName(String moduleClassName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
        Class moduleClass = Class.forName(moduleClassName);
        return obtainModuleTokenListWithModuleClass(moduleClass);
    }

    public static List<ArticleTokenOption> obtainModuleTokenListWithModuleInstance(List<Object> moduleInstanceList) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (moduleInstanceList == null || moduleInstanceList.isEmpty()) {
            return null;
        }

        List<ArticleTokenOption> allModuleTokenList = new ArrayList();
        for (Object moduleInstance : moduleInstanceList) {
            List<ArticleTokenOption> tokenList = obtainModuleTokenListWithModuleClass(moduleInstance.getClass());
            allModuleTokenList.addAll(tokenList);
        }
        return allModuleTokenList;
    }     
    
    public static List<ArticleTokenOption> obtainModuleTokenListWithModuleInstance(Object moduleInstance) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        return obtainModuleTokenListWithModuleClass(moduleInstance.getClass());
    }
    
    public static List<ArticleTokenOption> obtainModuleTokenListWithModuleClass(List<Class> moduleClassList) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (moduleClassList == null || moduleClassList.isEmpty()) {
            return null;
        }

        List<ArticleTokenOption> allModuleTokenList = new ArrayList();
        for (Class moduleClass : moduleClassList) {
            List<ArticleTokenOption> tokenList = obtainModuleTokenListWithModuleClass(moduleClass);
            allModuleTokenList.addAll(tokenList);
        }
        return allModuleTokenList;
    }    
    
    public static List<ArticleTokenOption> obtainModuleTokenListWithModuleClass(Class moduleClass) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        List<ArticleTokenOption> tokenList = new ArrayList();

        Field[] fields = obtainTokenAnnotatedFields(moduleClass);
        if(fields == null) {
            return tokenList;
        }
        
        for (Field field : fields) {
            String tokenName = field.getName();
            String token = field.get(null).toString();

            Annotation[] annotations = obtainTokenAnnotationOnField(field); // field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                Method[] methods = annotation.annotationType().getDeclaredMethods();

                ArticleTokenOption tokenOption = new ArticleTokenOption();
                tokenOption.setModuleName(moduleClass.getSimpleName());
                tokenOption.setModuleClass(moduleClass);
                tokenOption.setName(tokenName);
                tokenOption.setToken(token);
                List<JavaMethodInfo> infoList = MethodHelper.obtainPureMethodOnly(annotation, methods);
                if (infoList != null) {
                    for (JavaMethodInfo info : infoList) {
                        String annotationAttrName = info.getMethodName();
                        Object objAnnotationAttrValue = info.getMethodReturnValue();
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
    
    public static String convert2IteratorPart(String oriArticlePart) {
        return String.format(RESERVED_WORD_ITERATOR, oriArticlePart);
    }

    public static List<String> obtainTokensInArticle(String article) {
        return obtainTokensInArticle(article, REGEXP_STANDARD_TOKEN);
    }

    public static List<String> obtainTokensInArticle(String article, String regExpToken) {
        RegExpProcessor regExpProcessor = RegExpProcessor.Factory.create(regExpToken);
        RegExpResult result = regExpProcessor.processMatch(article);
        return result.getMatchResults();
    }

//<editor-fold defaultstate="collapsed" desc="內部輔助 Methods">
    protected static Field[] obtainTokenAnnotatedFields(Object moduleObj) {
        return obtainTokenAnnotatedFields(moduleObj.getClass());
    }

    protected static Field[] obtainTokenAnnotatedFields(Object moduleObj, Object... otherModuleObjs) {
        Class[] otherObjClasses = Stream.of(otherModuleObjs).map(Object::getClass).toArray(Class[]::new);
        return obtainTokenAnnotatedFields(moduleObj.getClass(), otherObjClasses);
    }

    protected static Field[] obtainTokenAnnotatedFields(Class objClass) {
        return Stream.of(objClass.getFields())
                .filter(field -> obtainTokenAnnotationOnField(field) != null)
                .toArray(Field[]::new);
    }

    protected static Field[] obtainTokenAnnotatedFields(Class objClass, Class... otherObjClasses) {
        return Stream.concat(Stream.of(objClass), Stream.of(otherObjClasses))
                .map(Class::getFields)
                .flatMap(Stream::of)
                .filter(field -> obtainTokenAnnotationOnField(field) != null)
                .toArray(Field[]::new);
    }

    protected static Annotation[] obtainTokenAnnotationOnField(Field field) {
        return field.getDeclaredAnnotationsByType(ArticleToken.class);
    }
//</editor-fold>   
}
