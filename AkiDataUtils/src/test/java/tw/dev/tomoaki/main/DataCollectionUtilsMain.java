/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki.main;

import java.util.Arrays;
import java.util.List;
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
    
}
