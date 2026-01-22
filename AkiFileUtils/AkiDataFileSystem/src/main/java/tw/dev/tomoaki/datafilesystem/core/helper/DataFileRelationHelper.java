/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datafilesystem.core.helper;

import java.nio.file.Path;
import java.nio.file.Paths;
import tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation;
import tw.dev.tomoaki.datafilesystem.core.exception.DataFileException;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;

/**
 *
 * @author tomoaki
 * @param <T>
 */
public class DataFileRelationHelper<T extends DataFileRelation> {

    /**
     * 此框架(?)的檔案路徑規則為: <br>
     * 指定的根目錄路徑(dataFileRootPath) 串 指定資料中路徑(dataFile.fileRealName) 資料 <br>
     * 如果指定資料(dataFile)不存在或 dataFile.fileRealName 不存在，<br>
     * 會直接回傳 null
     * 
     * @param <T>
     * @param dataFileRootPath 指定的檔案根目錄
     * @param dataFile 與檔案相關的資料
     * @return (資料相關的)檔案的實際路徑
     */
    public static <T extends DataFileRelation> Path obtainFilePath(String dataFileRootPath, T dataFile) {
        if (hasDataFile(dataFile) && StringValidator.isValueExist(dataFile.getFileRealName())) {
            return Paths.get(dataFileRootPath, dataFile.getFileRealName());
        }
        return null;
    }

    /**
     * 檢查與檔案相關之資料是否存在，此 Method 不會實際檢查檔案系統上檔案是否存在
     * 
     * @param <T>
     * @param dataFile 與檔案相關的資料
     * @return
     */    
    public static <T extends DataFileRelation> Boolean hasDataFile(T dataFile) {
        return (dataFile != null);
    }
            
    public static <T extends DataFileRelation> void doValidateDataFileRealName(T dataFile) {
        if(!DataFileRelationHelper.hasDataFile(dataFile)) {
            throw new IllegalArgumentException("dataFile is null");
        }        
        
        String realName = dataFile.getFileRealName();
        if(realName == null || realName.isEmpty()) {
            throw new DataFileException("dataFile.getFileRealName() return empty");
        }
    }
}
