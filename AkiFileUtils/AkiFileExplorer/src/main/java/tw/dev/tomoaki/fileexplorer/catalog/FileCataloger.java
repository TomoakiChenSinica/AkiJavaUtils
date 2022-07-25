/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileexplorer.catalog;

import java.io.File;
import java.util.List;
import tw.dev.tomoaki.fileexplorer.core.AbsFileAnalyzer;

/**
 * 
 * @author Tomoaki Chen
 */
public class FileCataloger extends AbsFileAnalyzer<VoidResult> {

    public FileCataloger() {
        super(VoidResult.class);
    }
    
    
    
    @Override
    public VoidResult doAnalyze4Dir(String parentDirPath, File dir, VoidResult result) {
        File[] fileList = dir.listFiles();
        for(File file : fileList) {
            this.doAnalyze(file);
        }
        return null;
    }

    @Override
    public VoidResult doAnalyze4File(String parentDirPath, File file, VoidResult result) {
        System.out.format("File: %s\\%s \r\n", file.getParentFile().getAbsolutePath(), file.getName());
        return null;
    }
    
}
