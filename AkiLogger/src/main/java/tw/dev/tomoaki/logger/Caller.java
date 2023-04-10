/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.logger;

/**
 *
 * @author arche
 */
public class Caller {

    private String className;
    private String simpleClassName;
    private String methodName;
//    
//    public Caller(String className, String methodName) {
//        this.className = className;
//        this.methodName = methodName;
//        
//    }

    protected Caller() {
    }

    public static class Factory {

        public static Caller create(StackTraceElement stElement) {
            Caller caller = new Caller();
            caller.className = stElement.getClassName();
            caller.simpleClassName = stElement.getFileName();
            caller.methodName = stElement.getMethodName();
            return caller;
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "Caller{" + "className= " + className + ", simpleClassName= " + simpleClassName + ", methodName= " + methodName + "}";
    }



}
