/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.filesystem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author tomoaki
 *
 * 
 */
public class SimpleFileManager {

    private static final Integer DEFAULT_BUFFER_SIZE = 1024;
    private Integer bufferSize = DEFAULT_BUFFER_SIZE;
    
    private String rootFilePathText;

    public File save(Path targetPath, InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputParam Is Null");
        }

//        byte[] byteBuffer = new byte[bufferSize];
//        while ((byteBuffer = inputStream.readNBytes(bufferSize)) != null) {
//        }
        byte[] byteBuffer = new byte[bufferSize];
        while ((inputStream.read(byteBuffer)) != -1) {
            Files.write(targetPath, byteBuffer);
        } 
        return targetPath.toFile();
    }

    public File copy(Path sourcePath, Path targetPath) {
        throw new UnsupportedOperationException("Method Not Supported Yet");
    }

    public File delete(Path path) {
        throw new UnsupportedOperationException("Method Not Supported Yet");
    }
}
