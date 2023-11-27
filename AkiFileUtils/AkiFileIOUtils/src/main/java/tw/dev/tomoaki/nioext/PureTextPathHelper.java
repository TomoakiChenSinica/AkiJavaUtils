/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.nioext;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author tomoaki
 */
public class PureTextPathHelper {

    public final static String SEPARATOR_WINDOWS = "\\";
    public final static String SEPARATOR_WINDOWS_JPN = "¥";
    public final static String SEPARATOR_WINDOWS_KOR = "₩";
    public final static String SEPARATOR_UNIX_LIKE = "/";
    
    
    
    public static String obtainPureTextPath(String desigSeparator, String base, String... others) {
        List<String> pathNodes = new ArrayList();
        pathNodes.add(base);
        pathNodes.addAll(processOtherPaths(others, desigSeparator));
        return pathNodes.stream().collect(Collectors.joining(desigSeparator));
    }

//    protected static List<String> obtainPathsNodes(String base, String... others) {
//        List<String> pathNodes = new ArrayList();
//        pathNodes.add(base);
//        pathNodes.addAll(pathNodes);
//        return pathNodes;
//    }
    
    protected static List<String> processOtherPaths(String[] otherPaths, String separator) {
        return Stream.of(otherPaths).map(String::trim).map(path -> trimSeparator(path, separator)).collect(Collectors.toList());
    }
    
//    protected static String trimSeparator(String text, String separator) {
//        String resultText = text;
//        if("\\".equals(separator)) separator = "\\" + "\\"; //要加上跳脫符號(煩死啦)
//        resultText = resultText.replaceAll(String.format("^[%s]", separator), "");
//        resultText = resultText.replaceAll(String.format("[%s]$", separator), "");
//        return resultText;
//    }
    protected static String trimSeparator(String text, String separator) {
        String resultText = text;
        if("\\".equals(separator)) separator = "\\" + "\\"; //要加上跳脫符號(煩死啦)        
        resultText = resultText.replaceAll(String.format("^[%s]", separator), "");
        resultText = resultText.replaceAll(String.format("[%s]$", separator), "");
        return resultText;
    }    

    
    public static String obtainPureTextPath(String desigSeparator, Path path) {
        String localSystemSeparator = java.io.File.separator;
        String strPath = path.toString();
        return PureTextPathHelper.replaceAllSeparator(strPath, localSystemSeparator, desigSeparator);
    }
    
    private static String replaceAllSeparator(String pureTextPath, String oriSeparator, String replaceWith) {
        if("\\".equals(oriSeparator)) oriSeparator = "\\" + "\\"; //要加上跳脫符號(煩死啦)
        return pureTextPath.replaceAll(oriSeparator, replaceWith);
    }
     
}
