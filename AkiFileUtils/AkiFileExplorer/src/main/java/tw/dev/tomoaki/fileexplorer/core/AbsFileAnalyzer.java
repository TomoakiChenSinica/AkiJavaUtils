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
public abstract class AbsFileAnalyzer<T extends AbsFileAnalyzeResult> {
    
    private Class resultClazz;
    
    protected AbsFileAnalyzer(Class resultClazz) {
        this.resultClazz = resultClazz;
    }
    
    public List<T> doAnalyze(File desigFile) {
        try {
            List<T> resultList = new ArrayList();
//            T result = (T) resultClazz.getConstructor().newInstance();
            if (desigFile != null && desigFile.exists()) {
                if (desigFile.isDirectory()) {
                    resultList = this.doAnalyze4Dir(desigFile, resultList);
                }

                if (desigFile.isFile()) {
                    resultList = this.doAnalyze4File(desigFile, resultList);
                }
            }
            return resultList;
        } catch(Exception ex) {
            throw new FileAnalyzeException(ex);
        }
    }
    
    public abstract List<T> doAnalyze4Dir(File dir, List<T> resultList);
    
    public abstract List<T> doAnalyze4File(File file, List<T> resultList);
    
}
