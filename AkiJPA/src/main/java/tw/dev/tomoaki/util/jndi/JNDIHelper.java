/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.jndi;

import java.util.Objects;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import static jdk.internal.org.jline.utils.AttributedStringBuilder.append;

/**
 *
 * @author tomoaki
 */
public class JNDIHelper {

    private final static String JNDI_NAME_APPLICATION_NAME = "java:app/AppName";

    private final static String JDNI_NAME_PATTERN_COMPONENT = "java:global/${APP_NAME}/${MODULE_NAME}/${FACADE_CLASS_NAME}!${FACADE_PATH}";

    private static InitialContext globalContext;

    protected static InitialContext obtainContext() throws NamingException {
        return globalContext = (globalContext != null) ? globalContext : new InitialContext();
    }

    public static String getAppName() throws NamingException {
        // TODO code application logic here
        InitialContext context = obtainContext();
        String applicationName = (String) context.lookup(JNDI_NAME_APPLICATION_NAME);
        return applicationName;
    }

    public static <T> T getComponent(String appName, String componentLocatedModuleName, Class<T> componentClass) throws NamingException {
        String componentJNDIName = obtainComponentJNDIName(appName, componentLocatedModuleName, componentClass);
        InitialContext context = obtainContext();
        return (T) context.lookup(componentJNDIName);
    }

    public static String obtainComponentJNDIName(String componentLocatedModuleName, Class<?> componentClass) throws NamingException {
//        componentClass.getP
        String facaadeClassName = componentClass.getSimpleName();
        String componentPath = componentClass.getName();
        return obtainComponentJNDIName(componentLocatedModuleName, facaadeClassName, componentPath);

    }

    public static String obtainComponentJNDIName(String appName, String componentLocatedModuleName, Class<?> componentClass) {
        String facaadeClassName = componentClass.getSimpleName();
        String componentPath = componentClass.getName();
        return obtainComponentJNDIName(appName, componentLocatedModuleName, facaadeClassName, componentPath);
    }

    public static String obtainComponentJNDIName(String componentLocatedModuleName, String componentClassName, String componentPath) throws NamingException {
        String appName = getAppName();
        return obtainComponentJNDIName(appName, componentLocatedModuleName, componentClassName, componentPath);
    }

    public static String obtainComponentJNDIName(String appName, String componentLocatedModuleName, String componentClassName, String componentPath) {
//        String jndiName = JDNI_NAME_PATTERN_FACADE;
//        jndiName.replace("${APP_NAME}", getAppName());
//        jndiName.replace("${MODULE_NAME}")

        /*
        String appName = getAppName();
        String moduleName = "Xxxxxx"; //可以自動嗎?        
        String componentPath = "xxx.xxx.xxx";
         */
//        String jndiName = new StringBuilder()
//                .append("java:global/")
//                .append(appName)
//                .append("/")
//                .append(componentLocatedModuleName)
//                .append("/")
//                .append(componentClassName)
//                .append("!")
//                .append(componentPath)
//                .toString();
        StringBuilder jndiNameBuilder = new StringBuilder()
                .append("java:global/")
                .append(appName)
                .append("/");
        
        if (!Objects.equals(appName, componentLocatedModuleName)) {
            jndiNameBuilder = jndiNameBuilder
                    .append(componentLocatedModuleName)
                    .append("/");
        }
        String jndiName = jndiNameBuilder
                .append(componentClassName)
                .append("!")
                .append(componentPath)
                .toString();

        return jndiName;
    }
}
