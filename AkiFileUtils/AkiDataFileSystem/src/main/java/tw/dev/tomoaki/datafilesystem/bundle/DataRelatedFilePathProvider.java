/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datafilesystem.bundle;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import tw.dev.tomoaki.datafilesystem.core.NewDataFilePathProvider;
import tw.dev.tomoaki.datafilesystem.core.RecentDataFilePathProvider;
import tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation;
import tw.dev.tomoaki.datafilesystem.core.exception.PermissionDeninedException;
import tw.dev.tomoaki.datafilesystem.core.helper.DataFileRelationHelper;
import tw.dev.tomoaki.nioext.PathExt;

/**
 *
 * @author tomoaki
 * @param <DATA>
 * @param <DATA_FILE>
 */
public abstract class DataRelatedFilePathProvider<DATA, DATA_FILE extends DataFileRelation<DATA>> implements RecentDataFilePathProvider<DATA>, NewDataFilePathProvider<DATA> {

    protected abstract String getFileRoot();

    protected abstract DATA_FILE obtainDataFile(DATA data);

    @Override
    public Path obtainRecentFilePath(DATA dataEntity) {
        DATA_FILE dataFile = this.obtainDataFile(dataEntity);
        Path dbPath = DataFileRelationHelper.obtainFilePath(getFileRoot(), dataFile);
        if(PathExt.isUnderRoot(dbPath, getFileRoot())) {
            throw PermissionDeninedException.Factory.create(dbPath);
        }
        return dbPath;
    }

    @Override
    public Path obtainNewFilePath(DATA dataEntity) {
        // return Paths.get(getFileRoot(), createFileName(dataEntity));
        Path newPath = Paths.get(getFileRoot(), createFileName(dataEntity));
        if(PathExt.isUnderRoot(newPath, getFileRoot())) {
            throw PermissionDeninedException.Factory.create(newPath);
        }        
        return newPath;
    }

    @Override
    public String createFileName(DATA dataEntity) {
        UUID uuid = UUID.randomUUID();
        return new StringBuilder()
                .append(uuid.toString())
                .toString();
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
