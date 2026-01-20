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
 * @param <DATA_FILE> 繼承於 DataFileRelation(又繼承於 DataFile) 的類別。會包含檔案的真實名稱、顯示(用)名稱等
 * 
 * 實作 RecentDataFilePathProvider 和 NewDataFilePathProvider，<br>
 * 此類別適合用在當資料庫或資料架構，是 DATA --> DATA_FILE，DATA 和 DATA_FILE 是「一對多」。<br>
 * 檔案實際存放路徑邏輯是: <br>
 * getFileRoot() 和 DATA_FILE.getFileRealName() (定義於 DataFileRelation --> DataFile) 串起來，<br>
 * 詳情可以看 DataFileRelationHelper <br>。
 * 
 */
public abstract class DataRelatedFilePathProvider<DATA, DATA_FILE extends DataFileRelation<DATA>> implements RecentDataFilePathProvider<DATA>, NewDataFilePathProvider<DATA> {

    protected DataFileNamingStrategy<DATA_FILE> fileCreator;
    
    public DataRelatedFilePathProvider() {
        this.fileCreator = new UUIDNamingStrategy<>();
    }
    
    public DataRelatedFilePathProvider(DataFileNamingStrategy<DATA_FILE> fileCreator) {
        this.fileCreator = fileCreator;
    }
    
    /**
     * 
     * 需實作，指定檔案所在的根目錄
     * 
     * @return 根目錄
     */
    protected abstract String getFileRoot();

    /**
     * 需實作，從 DATA 轉換為 DATA_FILE。<br>
     * DATA 和 DATA_FILE 在資料庫為 一對多。
     * 
     * @param dataEntity 主資料 
     * @return 相關的檔案
     */
    protected abstract DATA_FILE obtainDataFile(DATA dataEntity);
    

    /**
     * 提供 Logger，可以覆寫
     * @return 此類別中用的 Logger
     */
    protected Logger logger() {
        return Logger.getLogger(getClass().getName());
    }

    /**
     * 產生「既有的」檔案路徑。<br>
     * 
     * @param dataEntity 主資料 
     * @return 「既有檔案」的實際路徑
     * @throws FileAccessDeninedException 當欲尋找的檔案路徑在根目錄之外會丟出此 Exception
     */
    @Override
    public Path obtainRecentFilePath(DATA dataEntity) {
        DATA_FILE dataFile = this.obtainDataFile(dataEntity);
        String strFileRoot = getFileRoot();        
        Path dbPath = DataFileRelationHelper.obtainFilePath(strFileRoot, dataFile); // Path dbPath = DataFileRelationHelper.obtainFilePath(getFileRoot(), dataFile);                
        if(dbPath != null && !PathExt.isUnderRoot(dbPath, strFileRoot)) { // if(dbPath != null && !PathExt.isUnderRoot(dbPath, getFileRoot())) {
            String msgFmt = "'%s'(dbPath) is not under root %s";
            logger().warning(String.format(msgFmt, dbPath, strFileRoot));              
            throw FileAccessDeninedException.create(dbPath);
        }
        return dbPath;
    }

    @Override
    public Path obtainNewFilePath(DATA dataEntity) {
        String strFileRoot = getFileRoot();        
        Path newPath = Paths.get(strFileRoot, createFileName(dataEntity)); // Path newPath = Paths.get(getFileRoot(), createFileName(dataEntity));
        if(newPath != null && !PathExt.isUnderRoot(newPath, strFileRoot)) { // if(newPath != null && !PathExt.isUnderRoot(newPath, getFileRoot())) {
            String msgFmt = "'%s'(newPath) is not under root %s";
            logger().warning(String.format(msgFmt, newPath, strFileRoot));               
            throw FileAccessDeninedException.create(newPath);
        }        
        return newPath;
    }

    // @Override
    public String createFileName(DATA dataEntity) {
        return fileCreator.createFileName(this.obtainDataFile(dataEntity));        
    }
    
    public String createFileName(DATA dataEntity, String extension) {
        return fileCreator.createFileName(this.obtainDataFile(dataEntity), extension);
    }

    @Override
    public Boolean hasRecentFile(DATA dataEntity) {
        DATA_FILE dataFile = this.obtainDataFile(dataEntity);
        return DataFileRelationHelper.hasDataFile(dataFile);
    }

    @Override
    public Boolean isRecentFileExists(DATA dataEntity) {
        DATA_FILE dataFile = this.obtainDataFile(dataEntity);
        Path path = DataFileRelationHelper.obtainFilePath(getFileRoot(), dataFile);
        return Files.exists(path);
    }
    
    
}
