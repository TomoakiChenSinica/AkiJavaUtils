/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileexplorer.impl.filepath;

import java.util.ArrayList;
import java.util.List;
import tw.dev.tomoaki.fileexplorer.core.AbsFileAnalyzeResult;

/**
 *
 * @author Tomoaki Chen
 */
public class DirFilePath implements AbsFileAnalyzeResult {
    
    private List<String> filePathList;
    
    
    public DirFilePath() {
        this.filePathList = new ArrayList();
    }
    
    public void add(String filePath) {
        this.filePathList.add(filePath);
    }
    
    public List<String> getFilePathList() {
        return this.filePathList;
    }
}
