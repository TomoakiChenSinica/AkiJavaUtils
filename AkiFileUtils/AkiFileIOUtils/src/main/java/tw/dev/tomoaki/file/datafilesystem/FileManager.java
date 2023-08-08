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
public interface FileManager<T> {

    public File save(T data, InputStream is);

    public File delete(T data);
}
