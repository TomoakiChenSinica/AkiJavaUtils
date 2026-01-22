/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datafilesystem.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 *
 * @author tomoaki
 * @param <T>
 *
 */
public interface DataFileManager<T> {

    /**
     * 
     * 
     * @param data
     * @param overwriteRecentFile
     *  
     */
    public Path obtainSaveFilePath(T data, Boolean overwriteRecentFile);

    /**
     * save Inputstream {@code is} to file with {@code data}
     * 
     * @param data
     * @param is
     * @param overwriteRecentFile
     * 
     */
    public File save(T data, InputStream is, Boolean overwriteRecentFile) throws IOException;

    public File delete(T data) throws IOException;
}
