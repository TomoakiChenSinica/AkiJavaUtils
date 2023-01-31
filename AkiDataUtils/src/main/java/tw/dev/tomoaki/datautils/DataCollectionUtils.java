/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datautils;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static <T> T lastOf(List<T> dataList) {
        return lastOf(dataList.stream());
    }

    public static <T> T lastOf(Stream<T> dataStream) {
        return dataStream.reduce((first, second) -> second).orElse(null);
    }

    public static <T> List<T> skipLast(List<T> dataList, Long desigSkipLastSize) {
        /**
         * https://www.baeldung.com/java-stream-operated-upon-or-closed-exception
         * https://stackoverflow.com/questions/23860533/copy-a-stream-to-avoid-stream-has-already-been-operated-upon-or-closed 
         * https://mkyong.com/java8/java-stream-has-already-been-operated-upon-or-closed/
         * 注意如果是 1. 將 Stream 傳到另一個 Function，Supplier將無法正常 2. 同一個Function下，存到另一個變數也是
         */
        
        Supplier<Stream<T>> dataStreamSupplier = () -> dataList.stream();
        Long dataStreamSize = dataStreamSupplier.get().count();
        Long limitSize = dataStreamSize - desigSkipLastSize;
        if (limitSize < 0) {
            throw new IllegalArgumentException(String.format("desigSkipLastSize= %s Is Bigger Than Datas Size= %s", dataStreamSize, desigSkipLastSize));
        }
        return dataStreamSupplier.get().limit(limitSize).collect(Collectors.toList());
    }

//    public static <T> List<T> skipLast(Stream<T> dataStream, Long desigSkipLastSize) {
//        Supplier<Stream<T>> dataStreamSupplier = () -> dataStream;
//        Long dataStreamSize = dataStreamSupplier.get().count();
//        Long limitSize = dataStreamSize - desigSkipLastSize;
//        if(limitSize < 0) {
//            throw new IllegalArgumentException(String.format("desigSkipLastSize= %s Is Bigger Than Datas Size= %s", dataStreamSize, desigSkipLastSize));
//        }
//        return dataStreamSupplier.get().limit(limitSize).collect(Collectors.toList());
//    }
    
    public static <T> List<T> reverse(List<T> dataList) {
        return dataList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), data -> {
            Collections.reverse(data); 
            return data;
        }));
    }
}
