/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tw.dev.tomoaki.datafilesystem.core;

import java.nio.file.Path;

/**
 *
 * @author tomoaki
 * @param <T>
 */
public interface RecentDataFilePathProvider<T> {

    /**
     * Gets recent file related to the given {@code data}
     * 
     * @param data 任意格式({@literal <T>})資料
     * @return 檔案路徑，型態是 java.nio.file.Path
     * 
     */
    public Path obtainRecentFilePath(T data);

    /**
     * Check {@code data} has related file or not
     * 
     * @param data the data with type {@literal <T>}
     * @return the file is 
     */
    public Boolean hasRecentFile(T data);

    public Boolean isRecentFileExists(T data);
}
