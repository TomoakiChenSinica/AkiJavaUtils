/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa;

/**
 *
 * @author tomoaki
 */
public class KeyValuePair<T> {

    private String colName;
//    private T colValue; //還是要用T ?
//    private Class<T> valueType;
    private Object colValue;
    private Class valueType;

    public KeyValuePair(String colName, Object colValue) {
        this.colName = colName;
        this.colValue = colValue;
        this.valueType = colValue.getClass();
    }

    public String getColName() {
        return colName;
    }

    public Object getColValue() {
        return colValue;
    }

    public Class getValueType() {
        return valueType;
    }
    
    

}
