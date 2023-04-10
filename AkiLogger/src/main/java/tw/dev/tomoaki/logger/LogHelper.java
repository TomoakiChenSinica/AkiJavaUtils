/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.logger;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arche
 */
public class LogHelper {

//    private static final String selfClassName = LogHelper.class.getName();
//    private static final String threadClassName = Thread.class.getName();
//
//    private static Map<String, Boolean> excludeClassNameExistMap;
    private static final String SELF_CLASS_NAME = LogHelper.class.getName();
    private static final String THREAD_CLASS_NAME = Thread.class.getName();

    private static Map<String, Boolean> EXCLUDE_CLASS_NAME_EXIST_MAP;

    static {
        EXCLUDE_CLASS_NAME_EXIST_MAP = new HashMap();
        EXCLUDE_CLASS_NAME_EXIST_MAP.put(SELF_CLASS_NAME, Boolean.TRUE);
        EXCLUDE_CLASS_NAME_EXIST_MAP.put(THREAD_CLASS_NAME, Boolean.TRUE);
    }

    public static StackTraceElement getCallerStackTrace() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement stElement : stElements) {
            if (!isExcluded(stElement)) {
                return stElement;
            }
        }
        return null;
    }

//    public static Caller getCaller() {
//        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
//        for (StackTraceElement stElement : stElements) {
//            System.out.println("stElement= " + stElement);
//            if (!isExcluded(stElement)) {
//                return obtainCaller(stElement);
//            }
//        }
//        return null;
//    }
    public static Caller getCaller() {
        StackTraceElement stackTraceElement = getCallerStackTrace();
        if(stackTraceElement == null) {
            return null;
        }
        return Caller.Factory.create(stackTraceElement);
    }

    protected static Boolean isExcluded(StackTraceElement stElement) {
        String stClassName = stElement.getClassName();
        Boolean isExcluded = EXCLUDE_CLASS_NAME_EXIST_MAP.get(stClassName);
        return isExcluded == null ? Boolean.FALSE : isExcluded;
    }

    protected static Caller obtainCaller(StackTraceElement stElement) {
        Caller caller = Caller.Factory.create(stElement);
        return caller;
    }

}
