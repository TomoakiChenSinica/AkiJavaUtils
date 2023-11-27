/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tw.dev.tomoaki.file.datafilesystem;

import java.nio.file.Path;

/**
 *
 * @author tomoaki
 */
public interface RecentFilePathProvider<T> {

    /**
     * 取得與 data 資料有關的「現有」檔案
     * 
     * @param data 任意格式資料
     * @return 檔案路徑，型態是 java.nio.file.Path
     * 
     */
    public Path obtainRecentFilePath(T data);

    public Boolean hasRecentFile(T data);

    public Boolean isRecentFileExists(T data);
}
