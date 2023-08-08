/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.file.datafilesystem;

import java.io.File;
import java.io.InputStream;

/**
 *
 * @author tomoaki
 * 
 */
public interface FileManager_Backup2<SOURCE_DATA, TARGET_DATA> {

    public File save(TARGET_DATA data, InputStream is);
    
    public File copy(SOURCE_DATA sourceData, TARGET_DATA targetData);
    
    public File delete(TARGET_DATA targetData);
}
