/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.nioext;

import java.io.IOException;
import java.nio.file.Path;

/**
 *
 * @author tomoaki
 */
public class PathExt {

//    public static String obtainPureTextPath(Path thePath, String separator) {
//    }
    /**
     * java.nio.Path.toRealPath() 會檢查實際檔案是否存在。<br>
     * 此 Method 僅將路徑轉成無冗餘的絕對路徑。
     * 
     * @param targetPath 目標路徑
     */
    public static Path toLaxRealPath(Path targetPath) {
        return targetPath.toAbsolutePath().normalize();
    }

    /**
     * 檢查指定的目標路徑(targetPath) 是否在指定的目錄(rootPath)底下。
     *
     * @param targetPath 目標(要檢查的)路徑
     * @param rootPath 根目錄，合理的路徑要在這底下
     * @return 目標路徑(targetPath) 是否在指定的目錄(rootPath)底下
     */
    public static Boolean isUnderRoot(Path targetPath, Path rootPath) {
        Path realPath = PathExt.toLaxRealPath(targetPath); // targetPath.toRealPath(); toRealPath 會檢查檔案是否實際存在
        return realPath.startsWith(rootPath);
    }

    /**
     * 檢查指定的目標路徑(targetPath) 是否在指定的目錄(rootPath)底下。
     *
     * @param targetPath 目標(要檢查的)路徑
     * @param rootPathText 根目錄，合理的路徑要在這底下
     * @return 目標路徑(targetPath) 是否在指定的目錄(rootPath)底下
     */
    public static Boolean isUnderRoot(Path targetPath, String rootPathText) {
        Path realPath = PathExt.toLaxRealPath(targetPath); // targetPath.toRealPath();
        return realPath.startsWith(rootPathText);
    }
}
