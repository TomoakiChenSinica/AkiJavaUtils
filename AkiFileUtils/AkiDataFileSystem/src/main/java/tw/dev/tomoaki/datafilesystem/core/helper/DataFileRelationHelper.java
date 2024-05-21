/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datafilesystem.core.helper;

import java.nio.file.Path;
import java.nio.file.Paths;
import tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;

/**
 *
 * @author tomoaki
 * @param <T>
 */
public class DataFileRelationHelper<T extends DataFileRelation> {

    public static <T extends DataFileRelation> Path obtainFilePath(String dataFileRootPath, T dataFile) {
        if (hasDataFile(dataFile) && StringValidator.isValueExist(dataFile.getFileRealName())) {
            return Paths.get(dataFileRootPath, dataFile.getFileRealName());
        }
        return null;
    }

    public static <T extends DataFileRelation> Boolean hasDataFile(T dataFile) {
        return (dataFile != null);
    }
}
