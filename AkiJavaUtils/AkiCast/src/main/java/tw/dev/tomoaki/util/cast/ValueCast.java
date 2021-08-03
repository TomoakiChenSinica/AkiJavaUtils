/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.cast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
/**
 *
 * @author tomoaki
 */
 enum JavaCommonDataType {
        STRING ,BOOLEAN, INTEGER, LONG
}
public class ValueCast<T> {
    
    /**
     *整合提供一些Java 常見的類別的轉換，<br/>
     * 將String 資料轉換成指定的類型。 <br/>
     * 類型透過 strType 指定，基本上 是首字大寫。有：<br/>
     * String  : 字串 <br/>
     * Boolean ： 布林值 <br/>
     * Integer：整數 <br/>
     * Long ：長整數 <br/>
     */
    public static <T>T castJavaCommonDataType(String strValue, String strType) throws ValueCastException{
        switch(strType){
            case "String":
                return castJavaCommonDataType(strValue, JavaCommonDataType.STRING);
            case "Boolean":
                return castJavaCommonDataType(strValue, JavaCommonDataType.BOOLEAN);
            case "Integer":
                return castJavaCommonDataType(strValue, JavaCommonDataType.INTEGER);
            case "Long":
                return castJavaCommonDataType(strValue, JavaCommonDataType.LONG);                     
            default:
                throw new ValueCastException("javaCommonDataType is illegal");                      
        
        }
    }
    
    
    public static <T>T castJavaCommonDataType(String strValue, JavaCommonDataType javaCommonDataType) throws ValueCastException{
        if(strValue == null)
            return null;
        
        switch(javaCommonDataType){
            case STRING:
                return (T)strValue;            
            case BOOLEAN:
                //return castObjectToGenericClass(objStrValue, (Class<T>) Boolean.TYPE);                
                return (T) ValueCast.castStringToBoolean(strValue);
            case INTEGER:
                return (T) ValueCast.castStringToInteger(strValue);
                //return castObjectToGenericClass(objStrValue, (Class<T>) Integer.TYPE);                                
            case LONG:
                return (T) ValueCast.castStringToLong(strValue);
                
            default:
                throw new ValueCastException("javaCommonDataType is illegal");                
        }
    }
    
    
    /**
     * 將數字型別從 Integer 轉成 Long
     * Java原生行別好像不能直接指定
     * 
     * @param intValue 型別為 Integer 的數字
     * @return 型別轉為 Long 後的數字
     */
    public static Long castIntegerToLong(Integer intValue){
        String strIntValue = intValue.toString();
        Long longValue = Long.parseLong(strIntValue);
        return longValue;        
    }
    
    public static Long castStringToLong(String strValue){
        Long longValue = null;
        if(strValue != null && !"".equals(strValue)){
            longValue = Long.valueOf(strValue);
        }
        return longValue;
    }
    
    public static Integer castStringToInteger(String strValue){
        return Integer.valueOf(strValue);
    }
    
    
    
    
    /*public static String ObjectCastString(Serializable serialObj){
    
    }*/
    public static String castObjectToString(Object theObject) throws ValueCastException{
        String strObj = null;
        try{
            ByteArrayOutputStream  baos = new ByteArrayOutputStream ();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(theObject);
            oos.close();
            
            byte[] byteObj = baos.toByteArray();
            strObj = Base64.getEncoder().encodeToString(byteObj);
            //strObj = new String(baos.toByteArray(), StandardCharsets.UTF_8);            
            baos.close();
        }catch(IOException ex){
            throw new ValueCastException(ex);
        }
        return strObj;
    }
    
    
    public static Object castStringToObject(String strObj) throws ValueCastException{
        Object theObject = null;
        try{
            //byte[] bytesObj = strObj.getBytes(StandardCharsets.UTF_8);
            byte[] bytesObj = Base64.getDecoder().decode(strObj);
            ByteArrayInputStream  bais = new ByteArrayInputStream (bytesObj);        
            ObjectInputStream ois = new ObjectInputStream(bais);
            theObject = ois.readObject();
            bais.close();
            ois.close();
        }catch(IOException | ClassNotFoundException ex){
            throw new ValueCastException(ex);
        }
        return theObject;        
    }    
    /*
    public static <T>T castStringToObject(String strObj, Class<T> objectType) throws ValueCastException{
        T theObject = null;
        try{
            //byte[] bytesObj = strObj.getBytes(StandardCharsets.UTF_8);
            byte[] bytesObj = Base64.getDecoder().decode(strObj);
            ByteArrayInputStream  bais = new ByteArrayInputStream (bytesObj);        
            ObjectInputStream ois = new ObjectInputStream(bais);
            //theObject = ois.reado
        }catch(IOException ex){
            throw new ValueCastException(ex);
        }
        return (T)theObject;        
    }
    */
    
    public static String castBooleanToString(Boolean booleanValue){
        String strValue;
        strValue = Boolean.toString(booleanValue);
        return strValue;
    }
    
    public static Boolean castStringToBoolean(String strValue){
        Boolean booleanValue = Boolean.valueOf(strValue);
        return booleanValue;        
    }
    
    
    public static <T>T castObjectToGenericClass(Object theObject, Class<T> classType){
        T genericClass = null;
        genericClass = classType.cast(theObject);
        return genericClass;
    }
    
    public static <T>T castObjectToGenericClass(Object theObject, String strClassType) throws ClassNotFoundException{
        Class<T> classType = (Class<T>)Class.forName(strClassType);
        return castObjectToGenericClass(theObject, classType);
    }
    
}
