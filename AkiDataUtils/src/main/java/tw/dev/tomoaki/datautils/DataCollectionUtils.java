/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datautils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tw.dev.tomoaki.util.entity.DataExistMap;

/**
 *
 * @author Tomoaki Chen
 *
 * https://www.baeldung.com/java-stream-skip-vs-limit
 * https://stackify.com/streams-guide-java-8/
 * https://www.geeksforgeeks.org/stream-skip-method-java-examples
 * https://www.baeldung.com/java-8-streams
 * https://www.runoob.com/java/java8-streams.html
 */
public class DataCollectionUtils {

    /**
     * 協助取得 List 的最後一筆資料 
     * 
     * @param <T> 資料類型
     * @param dataList 資料清單(集合)，格式為 List
     * @return 清單中的最後一筆資料
     * 
     */
    public static <T> T lastOf(List<T> dataList) {
        return lastOf(dataList.stream());
    }

    /**
     * 協助取得 Stream 的最後一筆資料 
     * 
     * @param <T> 資料類型
     * @param dataStream 資料 Stream
     * @return 清單中的最後一筆資料
     * 
     */    
    public static <T> T lastOf(Stream<T> dataStream) {
        return dataStream.reduce((first, second) -> second).orElse(null);
    }

    // --------------------------------------------------------------------------------------------------------
    
    public static <T> List<T> skipLast(List<T> dataList, Long desigSkipLastSize) {
        /**
         * https://www.baeldung.com/java-stream-operated-upon-or-closed-exception
         * https://stackoverflow.com/questions/23860533/copy-a-stream-to-avoid-stream-has-already-been-operated-upon-or-closed
         * https://mkyong.com/java8/java-stream-has-already-been-operated-upon-or-closed/
         * 注意如果是 1. 將 Stream 傳到另一個 Function，Supplier將無法正常 2.
         * 同一個Function下，存到另一個變數也是
         */

        Supplier<Stream<T>> dataStreamSupplier = () -> dataList.stream();
        Long dataStreamSize = dataStreamSupplier.get().count();
        Long limitSize = dataStreamSize - desigSkipLastSize;
        if (limitSize < 0) {
            throw new IllegalArgumentException(String.format("desigSkipLastSize= %s Is Bigger Than Datas Size= %s", dataStreamSize, desigSkipLastSize));
        }
        return dataStreamSupplier.get().limit(limitSize).collect(Collectors.toList());
    }

    public static <T> List<T> reverse(List<T> dataList) {
        //https://stackabuse.com/java-8-streams-collect-and-reverse-stream-into-list/
        return dataList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), data -> {
            Collections.reverse(data);
            return data;
        }));
    }
    
    // --------------------------------------------------------------------------------------------------------
    
    public static <T> List<T> append(List<T> dataList, T data) {
        if (dataList == null && data == null) {
            return null;
        }

        if (dataList == null) {
            return Stream.of(data).collect(Collectors.toList());
        }

        if (data == null) {
            return dataList;
        }

        dataList.add(data);
        return dataList;
    }

    public static <T> List<T> appendAll(List<T> dataList1, List<T> dataList2) {
        if (dataList1 == null && dataList2 == null) {
            return null;
        }

        if (dataList1 == null) {
            return dataList2;
        }

        if (dataList2 == null) {
            return dataList1;
        }

        dataList1.addAll(dataList2);
        return dataList1;
    }
    
    public static <T> Stream<T> append(Stream<T> dataStream, T data) {
        if (dataStream == null && data == null) {
            return null;
        }

        if (dataStream == null) {
            return Stream.of(data);
        }

        if (data == null) {
            return dataStream;
        }

        return Stream.concat(dataStream, Stream.of(data));

    }

    public static <T> Stream<T> appendAll(Stream<T> dataStream1, Stream<T> dataStream2) {
        if (dataStream1 == null && dataStream2 == null) {
            return null;
        }

        if (dataStream1 == null) {
            return dataStream2;
        }

        if (dataStream2 == null) {
            return dataStream1;
        }
        return Stream.concat(dataStream1, dataStream2);
    }    
    
    public static <T> List<T> appendAll(T data, List<T> dataList) {
        if(data == null && dataList == null) {
            return null;
        }
        
        if(dataList == null) {
            return Arrays.asList(data);
        }
        
        if(data == null) {
            // 印出資訊?
            return dataList;
        }
        
        return appendAll(Stream.of(data), dataList.stream()).collect(Collectors.toList());
    }    
    
    // --------------------------------------------------------------------------------------------------------
    
//    public static <T> List<T> leftJoin(List<T> dataList1, List<T> dataList2) {
//        
//    }
    
    
    // --------------------------------------------------------------------------------------------------------
    
    public static <T> List<T> swap(List<T> dataList, int fromIndex, int toIndex) {        
        T desigData = dataList.get(fromIndex);
        dataList.remove(fromIndex); // https://stackoverflow.com/questions/21795376/java-how-to-remove-an-integer-item-in-an-arraylist
        dataList.add(toIndex, desigData);
        return dataList;
    }
    
    // --------------------------------------------------------------------------------------------------------
    
    public static <T> List<T> removeFrom(List<T> fromDataList, List<T> removingDataList) {
        if(removingDataList == null || removingDataList.isEmpty()) {
            return fromDataList;
        }
        
        DataExistMap<T> dataExist = DataExistMap.createOrdered(fromDataList);
        removingDataList.forEach(dataExist::remove);
        return dataExist.existList();
    }
    
    // --------------------------------------------------------------------------------------------------------
    
    public static <T> List<T> preordering(List<T> dataList, T... needPreorderingDatas) {
        return preordering(dataList, Arrays.asList(needPreorderingDatas));
    }
    
    public static <T> List<T> preordering(List<T> dataList, List<T> needPreorderingDataList) {
        DataExistMap<T> needPreorderingMap = DataExistMap.create(needPreorderingDataList);
        Predicate<T> needPreordering = data -> needPreorderingMap.contains(data);
        return preordering(dataList, needPreordering);
    }
    
    public static <T> List<T> preordering(List<T> dataList, Predicate<T> needPreordering) {
        Stream<T> preorderingDataStream = dataList.stream().filter(needPreordering);        
        Stream<T> otherDataStream = dataList.stream().filter(needPreordering.negate()); //.filter(Predicate.not(needPreordering));
        return Stream.concat(preorderingDataStream, otherDataStream).collect(Collectors.toList());
    }
}
