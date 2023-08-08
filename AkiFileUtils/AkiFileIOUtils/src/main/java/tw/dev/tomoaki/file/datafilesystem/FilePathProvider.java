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
public interface FilePathProvider<T> {

    public Path obtainFilePath(T data);

    public Boolean hasFile(T data);

    public Boolean isFileExists(T data);
}
