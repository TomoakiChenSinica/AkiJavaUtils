/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.file.datafilesystem;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

/**
 *
 * @author tomoaki
 * 
 * 這支總覺得好像沒甚麼等待實作的必要...................?
 */
public interface FileManager_Backup {

    public File save(Path path, InputStream is);
    
    public File copy(Path sourcePath, Path targetPath);
    
    public File delete(Path path);
}
