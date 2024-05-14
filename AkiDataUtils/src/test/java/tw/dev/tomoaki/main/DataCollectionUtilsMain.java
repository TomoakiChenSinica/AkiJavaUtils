/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki.main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tw.dev.tomoaki.datautils.DataCollectionUtils;

/**
 *
 * @author Tomoaki Chen
 */
public class DataCollectionUtilsMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        test3(1, 2);
        System.out.println(test4(Stream.of(1, 3, 7, 31, 0, -1, 23).collect(Collectors.toList()), 1, 2));
    }

    protected static void test1() {
        // TODO code application logic here
        List dataList = Arrays.asList(1, 3, 7, 31, 0, -1, 23);
        System.out.println(DataCollectionUtils.lastOf(dataList));
        System.out.println(DataCollectionUtils.skipLast(dataList, 3l));
//        Stream dataStream = dataList.stream();
//        Long desigSkipLastSize = 3l;
//        Supplier<Stream<Integer>> dataStreamSupplier = () -> dataList.stream();//可以//Stream.of(1, 2, 3, 4, 5, 6, 7);
//        Long dataStreamSize = dataStreamSupplier.get().count();
//        Long limitSize = dataStreamSize - desigSkipLastSize;
//        if(limitSize < 0) {
//            throw new IllegalArgumentException(String.format("desigSkipLastSize= %s Is Bigger Than Datas Size= %s", dataStreamSize, desigSkipLastSize));
//        }
//        System.out.println(dataStreamSupplier.get().limit(limitSize).collect(Collectors.toList()));
    }

    protected static void test2() {
        List<Integer> dataList = Arrays.asList(1, 3, 7, 31, 0, -1, 23);
        Integer data = null;
        System.out.println(DataCollectionUtils.append(dataList, data));
    }

    protected static void test3(int fromIndex, int toIndex) {
        List<Integer> dataList = Stream.of(1, 3, 7, 31, 0, -1, 23).collect(Collectors.toList()); // Arrays.asList(1, 3, 7, 31, 0, -1, 23);
        System.out.println(String.format("At First, dataList= %s", dataList));
        Integer desigData = dataList.get(fromIndex);
        System.out.println(String.format("desigData= %s", desigData));
        dataList.remove(fromIndex);
        System.out.println(String.format("After Remove, dataList= %s", dataList));
        dataList.add(toIndex, desigData);
        System.out.println(String.format("After Add, dataList= %s", dataList));
    }

    protected static List test4(List dataList, int fromIndex, int toIndex) {
        return DataCollectionUtils.swap(dataList, fromIndex, toIndex);
    }
}
