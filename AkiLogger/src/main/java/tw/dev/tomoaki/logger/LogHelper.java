/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.logger;

import java.util.HashMap;
import java.util.Map;
import tw.dev.tomoaki.util.entity.DataExistMap;

/**
 *
 * @author arche
 */
public class LogHelper {

    private static final String SELF_CLASS_NAME = LogHelper.class.getName();
    private static final String THREAD_CLASS_NAME = Thread.class.getName();
    
    private static final DataExistMap<String> EXCLUDE_CLASS_NAME_EXIST_MAP; // private static Map<String, Boolean> EXCLUDE_CLASS_NAME_EXIST_MAP;

    static {
        EXCLUDE_CLASS_NAME_EXIST_MAP = new DataExistMap();
        EXCLUDE_CLASS_NAME_EXIST_MAP.add(SELF_CLASS_NAME);
        EXCLUDE_CLASS_NAME_EXIST_MAP.add(THREAD_CLASS_NAME);
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

    protected static Boolean isExcluded(StackTraceElement stElement) {
        String stClassName = stElement.getClassName();
        Boolean isExcluded = EXCLUDE_CLASS_NAME_EXIST_MAP.contains(stClassName);
        return isExcluded;
    }

    protected static Caller obtainCaller(StackTraceElement stElement) {
        Caller caller = Caller.Factory.create(stElement);
        return caller;
    }

    public static Caller getCaller() {
        StackTraceElement stackTraceElement = getCallerStackTrace();
        if (stackTraceElement == null) {
            return null;
        }
        return Caller.Factory.create(stackTraceElement);
    }

    protected static void addExcluded(Object object) {
        EXCLUDE_CLASS_NAME_EXIST_MAP.add(object.getClass().getName());
    }

    protected static void addExcluded(Class clazz) {
        EXCLUDE_CLASS_NAME_EXIST_MAP.add(clazz.getName());
    }

}
