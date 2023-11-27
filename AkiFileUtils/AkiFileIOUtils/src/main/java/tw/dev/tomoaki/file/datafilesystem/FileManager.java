/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.file.datafilesystem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 *
 * @author tomoaki
 *
 */
public interface FileManager<T> {

    public Path obtainSaveFilePath(T data, Boolean overwriteRecentFile);

    public File save(T data, InputStream is, Boolean overwriteRecentFile) throws IOException;

    public File delete(T data) throws IOException;
}
