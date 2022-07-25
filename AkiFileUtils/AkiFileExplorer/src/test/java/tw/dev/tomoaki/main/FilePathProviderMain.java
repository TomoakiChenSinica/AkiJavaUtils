/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.fileexplorer.filepath.DirFilePath;
import tw.dev.tomoaki.fileexplorer.filepath.FilePathProvider;

/**
 *
 * @author Tomoaki Chen
 */
public class FilePathProviderMain {
    
    public static void main(String[] args) {
        FilePathProvider filePathProvider = new FilePathProvider();
        DirFilePath dirFilePath = filePathProvider.doAnalyze("C:\\Users\\tomoaki\\Documents\\庶務處理\\2022-07-18\\file\\brief");
        dirFilePath.getFilePathList().forEach(filePath ->{
            System.out.println(filePath);
        });
    }
}
