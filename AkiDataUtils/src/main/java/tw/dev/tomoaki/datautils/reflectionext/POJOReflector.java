/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datautils.reflectionext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tw.dev.tomoaki.util.string.AkiStringUtil;

/**
 *
 * @author tomoaki
 */
public class POJOReflector {

    private final static String GETTER_PREFIX = "get";
    private final static String SETTER_PREFIX = "set";

    /**
     * 以 Major 為主
     */
    public static <T extends Object> T merge(T majorObj, T minorObj, T... otherObjs) {
        try {
            Class objClass = majorObj.getClass();
            T mergedObj = (T) objClass.getDeclaredConstructor().newInstance();
//        Stream<T> objStream = Stream.concat(Stream.of(majorObj, minorObj), Stream.of(otherObjs));
            List<T> objList = Stream.concat(Stream.of(majorObj, minorObj), Stream.of(otherObjs)).collect(Collectors.toList());

            Field[] fields = objClass.getDeclaredFields();
            Stream.of(fields).forEach(field -> {
                try {
                    Method getter = POJOReflector.obtainFieldGetter(objClass, field, Boolean.FALSE);
                    Method setter = POJOReflector.obtainFieldSetter(objClass, field, Boolean.FALSE);
                    Stream<T> objStream = objList.stream();
                    mergeField(mergedObj, objStream, getter, setter, field);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            return mergedObj;
        } catch (IllegalArgumentException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new ReflectionExtException(ex);
        }
    }

    protected static <T extends Object> void mergeField(T mergedObj, Stream<T> objStream, Method getter, Method setter, Field field) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> fieldType = field.getType();
        Object objectValue = objStream.map(obj -> {
            try {
                return getter.invoke(obj);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }).filter(Objects::nonNull).findFirst().orElse(null);

        if (objectValue != null) {
            setter.invoke(mergedObj, /*fieldType.cast(objectValue)*/ objectValue);
        }
    }    
    
    public static <T extends Object> Method obtainFieldGetter(Class<T> objectClass, Field objectField, Boolean includeExtension) throws NoSuchMethodException {
        String guessedMethodName = obtainFieldGuessedGetterName(objectField.getName());
        return includeExtension ? objectClass.getMethod(guessedMethodName) : objectClass.getDeclaredMethod(guessedMethodName); //後者不會包含繼承來的，資料來源: https://blog.csdn.net/GuoCong666/article/details/79131280
    }

    public static <T extends Object> Method obtainFieldSetter(Class<T> objectClass, Field objectField, Boolean includeExtension) throws NoSuchMethodException {
        Class<?> fieldType = objectField.getType();
        String guessedMethodName = obtainFieldGuessedSetterName(objectField.getName());
        return includeExtension ? objectClass.getMethod(guessedMethodName, fieldType) : objectClass.getDeclaredMethod(guessedMethodName, fieldType); //後者不會包含繼承來的，資料來源: https://blog.csdn.net/GuoCong666/article/details/79131280
    }

    private static String capitalize(String fieldName) {
        if (Objects.isNull(fieldName) && fieldName.isBlank()) {
            String msgFmt = "fieldName= '%s' Is Null Or Empty";
            throw new IllegalArgumentException(String.format(msgFmt, fieldName));
        }
        return AkiStringUtil.capitalizeHeader(fieldName, 1);
    }

    private static String obtainFieldGuessedGetterName(String fieldName) {
        return GETTER_PREFIX.concat(capitalize(fieldName));
    }

    private static String obtainFieldGuessedSetterName(String fieldName) {
        return SETTER_PREFIX.concat(capitalize(fieldName));
    }

}
