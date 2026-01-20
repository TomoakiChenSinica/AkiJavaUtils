/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datafilesystem.bundle;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import tw.dev.tomoaki.datafilesystem.core.naming.UUIDNamingStrategy;
import tw.dev.tomoaki.datafilesystem.core.NewDataFilePathProvider;
import tw.dev.tomoaki.datafilesystem.core.RecentDataFilePathProvider;
import tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation;
import tw.dev.tomoaki.filesystem.exception.FileAccessDeninedException;
import tw.dev.tomoaki.datafilesystem.core.helper.DataFileRelationHelper;
import tw.dev.tomoaki.nioext.PathExt;
import tw.dev.tomoaki.datafilesystem.core.DataFileNamingStrategy;

/**
 *
 * @author tomoaki
 * @param <DATA> 主要資料，DATA 和 DATA_FILE 是「一對多」
 * @param <DATA_FILE> 繼承於 DataFileRelation(又繼承於 DataFile) 的類別
 * @param <FILE_TYPE> DATA 和 DATA_FILE 為一對多，FILE_TYPE 協助找出是其中哪一個 DATA_FILE
 *
 * 實作 RecentDataFilePathProvider 和 NewDataFilePathProvider，<br>
 * 此類別適合用在當資料庫或資料架構，是 DATA --> DATA_FILE，DATA 和 DATA_FILE 是「一對多」，<br>
 * 並且 DATA_FILE 有欄位、屬性標註「類型」。<br>
 * 檔案實際存放路徑邏輯是: <br>
 * getFileRoot() 和 DATA_FILE.getFileRealName() (定義於 DataFileRelation -->
 * DataFile) 串起來，<br>
 * 詳情可以看 DataFileRelationHelper <br>。
 *
 */
public abstract class DataRelatedTypedFilePathProvider<DATA, DATA_FILE extends DataFileRelation<DATA>, FILE_TYPE> implements RecentDataFilePathProvider<DATA_FILE>, NewDataFilePathProvider<DATA_FILE> {
    
    protected DataFileNamingStrategy<DATA_FILE> fileCreator;
    
    public DataRelatedTypedFilePathProvider() {
        this.fileCreator = new UUIDNamingStrategy<>();        
    }
    
    public DataRelatedTypedFilePathProvider(DataFileNamingStrategy<DATA_FILE> fileCreator) {
        this.fileCreator = fileCreator;
    }    
    
    /**
     *
     * 需實作，指定檔案所在的根目錄
     *
     * @param fileType 指定「類型」的檔案
     * @return 根目錄
     */
    protected abstract String getFileRoot(FILE_TYPE fileType);

    /**
     * 需實作，從 DATA 轉換為 DATA_FILE。<br>
     * DATA 和 DATA_FILE 在資料庫為 一對多。
     *
     * @param dataEntity 主資料
     * @param fileType 與主資料關聯的 DATA_FILE，是哪一種類型
     * @return 有紀錄實際檔案路徑/名稱的資料
     */
    protected abstract DATA_FILE obtainDataFile(DATA dataEntity, FILE_TYPE fileType);

    /**
     * 需實作，從 DATA 轉換為 DATA_FILE。<br>
     * DATA 和 DATA_FILE 在資料庫為 一對多。
     *
     * @param dataFile 有紀錄實際檔案路徑/名稱的資料
     * @return 檔案類型
     */
    protected abstract FILE_TYPE obtainFileType(DATA_FILE dataFile);

    
    /**
     * 提供 Logger，可以覆寫
     * @return 此類別中用的 Logger
     */
    protected Logger logger() {
        return Logger.getLogger(getClass().getName());
    }
    
    /**
     * 需實作，從 DATA 轉換為 DATA_FILE。<br>
     * DATA 和 DATA_FILE 在資料庫為 一對多。
     *
     * @param dataEntity 主資料
     * @param fileType 與主資料關聯的 DATA_FILE，是哪一種類型
     * @return 相關的檔案的路徑，格式為 Path
     */
    public Path obtainRecentFilePath(DATA dataEntity, FILE_TYPE fileType) {
        DATA_FILE dataFile = this.obtainDataFile(dataEntity, fileType);
        return this.obtainRecentFilePath(dataFile);
    }

    /**
     * 產生「既有的」檔案路徑。<br>
     *
     * @param dataFile 有紀錄實際檔案路徑/名稱的資料
     * @return 「既有檔案」的實際路徑
     * @throws FileAccessDeninedException 當欲尋找的檔案路徑在根目錄之外會丟出此 Exception
     */
    @Override
    public Path obtainRecentFilePath(DATA_FILE dataFile) {
        FILE_TYPE fileType = obtainFileType(dataFile);
        String strFileRoot = getFileRoot(fileType); 
        Path dbPath = DataFileRelationHelper.obtainFilePath(strFileRoot, dataFile); // Path dbPath = DataFileRelationHelper.obtainFilePath(getFileRoot(fileType), dataFile);
        if (!PathExt.isUnderRoot(dbPath, strFileRoot)) { // if (!PathExt.isUnderRoot(dbPath, getFileRoot(fileType))) {
            String msgFmt = "'%s'(dbPath) is not under %s(fileType)'s root %s";
            logger().warning(String.format(msgFmt, dbPath, fileType, strFileRoot));         
            throw FileAccessDeninedException.create(dbPath);
        }
        return dbPath;
    }

    public Path obtainNewFilePath(DATA dataEntity, FILE_TYPE fileType) {
        DATA_FILE dataFile = this.obtainDataFile(dataEntity, fileType);
        return this.obtainNewFilePath(dataFile);
    }

    @Override
    public Path obtainNewFilePath(DATA_FILE dataFile) {
        FILE_TYPE fileType = obtainFileType(dataFile);
        String strFileRoot = getFileRoot(fileType);
        Path newPath = Paths.get(strFileRoot, createFileName(dataFile)); // Path newPath = Paths.get(getFileRoot(fileType), createFileName(dataFile));
        if (!PathExt.isUnderRoot(newPath, strFileRoot)) { // if (!PathExt.isUnderRoot(newPath, getFileRoot(fileType))) {
            String msgFmt = "'%s'(newPath) is not under %s(fileType)'s root %s";
            logger().warning(String.format(msgFmt, newPath, fileType, strFileRoot));
            throw FileAccessDeninedException.create(newPath);
        }
        return newPath;
    }

    public String createFileName(DATA dataEntity, FILE_TYPE fileType) {
        return this.createFileName(this.obtainDataFile(dataEntity, fileType));
    }
    
    public String createFileName(DATA_FILE dataFile) {
        return this.fileCreator.createFileName(dataFile);
    }
    
    public String createFileName(DATA_FILE dataFile, String extension) {
        return this.fileCreator.createFileName(dataFile, extension);
    }

    public Boolean hasRecentFile(DATA dataEntity, FILE_TYPE fileType) {
        DATA_FILE dataFile = this.obtainDataFile(dataEntity, fileType);
        return DataFileRelationHelper.hasDataFile(dataFile);
    }

    @Override
    public Boolean hasRecentFile(DATA_FILE dataFile) {
        return DataFileRelationHelper.hasDataFile(dataFile);
    }

    public Boolean isRecentFileExists(DATA dataEntity, FILE_TYPE fileType) {
        DATA_FILE dataFile = this.obtainDataFile(dataEntity, fileType);
        return this.isRecentFileExists(dataFile);
    }

    @Override
    public Boolean isRecentFileExists(DATA_FILE dataFile) {
        FILE_TYPE fileType = obtainFileType(dataFile);
        Path path = DataFileRelationHelper.obtainFilePath(getFileRoot(fileType), dataFile);
        return Files.exists(path);
    }  
}
