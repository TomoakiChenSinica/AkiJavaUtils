/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.reflection.entity;

/**
 *
 * @author Tomoaki Chen
 */
public class JavaMethodInfo {
    
    private String methodName;
    private Class methodReturnType;
    private Object methodReturnValue;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class getMethodReturnType() {
        return methodReturnType;
    }

    public void setMethodReturnType(Class methodReturnType) {
        this.methodReturnType = methodReturnType;
    }

    public Object getMethodReturnValue() {
        return methodReturnValue;
    }

    public void setMethodReturnValue(Object methodReturnValue) {
        this.methodReturnValue = methodReturnValue;
    }
    
    
}
