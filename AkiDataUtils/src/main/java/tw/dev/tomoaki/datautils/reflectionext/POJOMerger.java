/*
 * Copyright 2024 tomoaki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tw.dev.tomoaki.datautils.reflectionext;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static tw.dev.tomoaki.datautils.reflectionext.POJOReflector.tryGetFieldValue;

/**
 *
 * @author tomoaki
 */
public class POJOMerger {

    public static <T extends Object> T doRightJoinInto(T mergedObject, T object2, T... moreObjects) {
        return doMergeInto(mergedObject, JoinMethod.RIGHT_JOIN, object2, moreObjects);
    }

    public static <T extends Object> T doRightJoin(T object1, T object2, T... moreObjects) {
        return doMerge(JoinMethod.RIGHT_JOIN, object1, object2, moreObjects);
    }

    /**
     * 將同樣 Entity 的資料進行合併，以越前面的資料為主，假設資料為 Null 會被另外的 Object 資料蓋過去。
     *
     * @param <T> 資料格式(Generic Type)
     * @param mergedObj
     * @param majorObj
     * @param minorObj
     * @param otherObjs
     * @return 合併後的資料
     *
     */
    /*public static <T extends Object> T doLeftJoin(T mergedObj, T majorObj, T minorObj, T... otherObjs) {
        if (majorObj == null) {
            throw new IllegalArgumentException("majorObj Is Null");
        }
        mergedObj = doMerge(JoinMethod.LEFT_JOIN, majorObj, minorObj, otherObjs);
        return mergedObj;
    }*/
    /**
     * 將同樣 Entity 的資料進行合併，以越前面的資料為主，假設資料為 Null 會被另外的 Object 資料蓋過去。
     *
     * @param <T> 資料格式(Generic Type)
     * @param mergedAndMajorObject
     * @param minorObj
     * @param otherObjs
     * @return 合併後的資料
     *
     */
    public static <T extends Object> T doLeftJoinInto(T mergedAndMajorObject, T minorObj, T... otherObjs) {
        if (mergedAndMajorObject == null) {
            throw new IllegalArgumentException("mergedAndMajorObject Is Null");
        }
        return doMergeInto(mergedAndMajorObject, JoinMethod.LEFT_JOIN, minorObj, otherObjs);
    }

    /**
     * 將同樣 Entity 的資料進行合併，以越前面的資料為主，假設資料為 Null 會被另外的 Object 資料蓋過去。
     *
     * @param <T> 資料格式(Generic Type)
     * @param majorObj
     * @param minorObj
     * @param otherObjs
     * @return 合併後的資料
     *
     */
    public static <T extends Object> T doLeftJoin(T majorObj, T minorObj, T... otherObjs) {
        if (majorObj == null) {
            throw new IllegalArgumentException("majorObj Is Null");
        }
        return doMerge(JoinMethod.LEFT_JOIN, majorObj, minorObj, otherObjs);
    }

    public static <T extends Object> T doMerge(JoinMethod joinMethod, T object1, T object2, T... otherObjects) {
        try {
            if (JoinMethod.LEFT_JOIN.equals(joinMethod) && object1 == null) {
                throw new IllegalArgumentException("object1 is Null");
            }

            Class objClass = object1.getClass();
            T mergedObject = (T) objClass.getDeclaredConstructor().newInstance();
            List<T> otherObjectList = Stream.concat(Stream.of(object2), Stream.of(otherObjects)).collect(Collectors.toList());
            T[] otherObjectArr = otherObjectList.toArray((T[]) Array.newInstance(objClass, otherObjectList.size()));
            mergedObject = doMergeInto(mergedObject, joinMethod, object1, otherObjectArr);
            return mergedObject;
        } catch (IllegalArgumentException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new ReflectionExtException(ex);
        }
    }

    public static <T extends Object> T doMergeInto(T mergedObject, JoinMethod joinMethod, T mergeWithObject, T... otherObjects) {
        if (mergedObject == null) {
            throw new IllegalArgumentException("mergedObject Is Null");
        }

        Class objClass = mergedObject.getClass();
        List<T> objList = Stream.concat(Stream.of(mergedObject, mergeWithObject), Stream.of(otherObjects)).filter(Objects::nonNull).collect(Collectors.toList());

        Field[] fields = objClass.getDeclaredFields();
        Stream.of(fields).forEach(field -> {
            try {
                Method getter = POJOReflector.obtainFieldGetter(objClass, field, Boolean.FALSE);
                Method setter = POJOReflector.obtainFieldSetter(objClass, field, Boolean.FALSE);
                Stream<T> objStream = objList.stream();
                switch (joinMethod) {
                    case LEFT_JOIN: {
                        doLeftJoinFieldInfo(mergedObject, objStream, getter, setter, field); //嚴格來說因為 Call By Reference 所以不用特別回傳(接回傳)，加上 Stream 不能這樣使用(接回傳值) (X)
                        break;
                    }
                    case RIGHT_JOIN: {
                        doRightJoinFieldInfo(mergedObject, objStream, getter, setter, field);
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return mergedObject;

    }

    protected static <T extends Object> T doLeftJoinFieldInfo(T mergedObject, Stream<T> objStream, Method getter, Method setter, Field field) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object objectValue = objStream.map(obj -> tryGetFieldValue(obj, field, getter)).filter(Objects::nonNull).findFirst().orElse(null);
        if (objectValue != null) {
            setter.invoke(mergedObject, objectValue);
        }
        return mergedObject;
    }

    protected static <T extends Object> T doRightJoinFieldInfo(T mergedObject, Stream<T> objStream, Method getter, Method setter, Field field) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<Object> objectValueList = objStream.map(obj -> tryGetFieldValue(obj, field, getter)).filter(Objects::nonNull).collect(Collectors.toList());
        if (objectValueList != null && !objectValueList.isEmpty()) {
            Object objectValue = objectValueList.get(objectValueList.size() - 1);
            setter.invoke(mergedObject, objectValue);
        }
        return mergedObject;
    }
}
