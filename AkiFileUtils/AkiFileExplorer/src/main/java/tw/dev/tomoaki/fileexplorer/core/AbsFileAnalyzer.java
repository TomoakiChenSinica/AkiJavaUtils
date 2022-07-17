/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.fileexplorer.core;

import java.io.File;

/**
 *
 * @author arche
 */
public abstract class AbsFileAnalyzer<T extends AbsFileAnalyzeResult> {
    
    private Class resultClazz;
    
    protected AbsFileAnalyzer(Class resultClazz) {
        this.resultClazz = resultClazz;
    }
    
    public T doAnalyze(File desigFile) {
        try {
            T result = (T) resultClazz.getConstructor().newInstance();
            if (desigFile != null && desigFile.exists()) {
                if (desigFile.isDirectory()) {
                    result = this.doAnalyze4Dir(desigFile, result);
                }

                if (desigFile.isFile()) {
                    result = this.doAnalyze4File(desigFile, result);
                }
            }
            return result;
        } catch(Exception ex) {
            throw new FileAnalyzeException(ex);
        }
    }
    
    public abstract T doAnalyze4Dir(File dir, T result);
    
    public abstract T doAnalyze4File(File file, T result);
    
}
