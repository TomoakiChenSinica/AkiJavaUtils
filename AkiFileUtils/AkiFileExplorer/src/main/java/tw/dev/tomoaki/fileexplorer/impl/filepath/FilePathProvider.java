/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileexplorer.impl.filepath;

import java.io.File;
import tw.dev.tomoaki.fileexplorer.core.AbsFileAnalyzer;

/**
 *
 * @author Tomoaki Chen
 */
public class FilePathProvider extends AbsFileAnalyzer<DirFilePath>{

    public FilePathProvider(){
        super(DirFilePath.class);
    }
    
    @Override
    public DirFilePath doAnalyze4Dir(String parentDirPath, File desigDir, DirFilePath result) {
        for(File file : desigDir.listFiles()) {
            result = this.doAnalyze(file, result);
        }
        return result;
    }

    @Override
    public DirFilePath doAnalyze4File(String parentDirPath, File desigFile, DirFilePath result) {
//        File parentDir = file.getParentFile();
//        /*String*/ parentDirPath = parentDir.getAbsolutePath();
//        String splitSymbol = this.obtainSplitSymbol(parentDirPath);
//        String 
        result.add(desigFile.getAbsolutePath());
        return result;
    }
    
    protected String obtainSplitSymbol(String parentDirPath) {
        if(parentDirPath.contains("/")) {
            return "/";
        } else if(parentDirPath.contains("\\")) {
            return "\\";
        } else if(parentDirPath.contains("$")) {
            return "$";
        } else {
            return null;
        }
    }
    
}
