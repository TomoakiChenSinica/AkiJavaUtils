/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.fileexplorer.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arche
 */
public abstract class AbsFileAnalyzer<T extends FileAnalyzeResult> {
    
    private Class resultClazz;
    
    protected AbsFileAnalyzer(Class resultClazz) {
        this.resultClazz = resultClazz;
    }
    
    public T doAnalyze(String desigFilePath) {
        File desigFile = new File(desigFilePath);
        if(desigFile.exists()) {
            return this.doAnalyze(desigFile);
        } else {
            String msg = String.format("File[%s] Not Exists", desigFilePath);
            throw new FileAnalyzeException(msg);  
        }
    }
    
    public T doAnalyze(File desigFile) {
        try {
            T result = (T) resultClazz.getConstructor().newInstance();
            return this.doAnalyze(desigFile, result);
        } catch(FileAnalyzeException ex) {
            throw ex;
        } catch(Exception ex) {
            throw new FileAnalyzeException(ex);
        }
    }
    
    protected T doAnalyze(File desigFile, T result) {
        try {
            String parentDirPath = "";
            if (desigFile != null && desigFile.exists()) {
                if (desigFile.isDirectory()) {
                    result = this.doAnalyze4Dir(parentDirPath, desigFile, result);
                }

                if (desigFile.isFile()) {
                    result = this.doAnalyze4File(parentDirPath, desigFile, result);
                }
            }
            return result;
        } catch(Exception ex) {
            throw new FileAnalyzeException(ex);
        }    
    }
    
    public abstract T doAnalyze4Dir(String parentDirPath, File desigDir, T result);
    
    public abstract T doAnalyze4File(String parentDirPath, File desigFile, T result);
    
}
