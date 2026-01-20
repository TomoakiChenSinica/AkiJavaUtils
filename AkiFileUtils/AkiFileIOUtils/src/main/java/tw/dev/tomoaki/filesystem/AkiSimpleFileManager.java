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
import tw.dev.tomoaki.fileioutils.AkiFileWriteUtils;
import tw.dev.tomoaki.filesystem.exception.FileAccessDeninedException;
import tw.dev.tomoaki.nioext.PathExt;

/**
 *
 * @author tomoaki
 *
 *
 */
public class AkiSimpleFileManager {

    private static final Integer DEFAULT_BUFFER_SIZE = 1024;
    private Integer bufferSize = DEFAULT_BUFFER_SIZE;

    private String rootFilePathText;

    protected AkiSimpleFileManager(String rootFilePathText) {
        this.rootFilePathText = rootFilePathText;
    }

    public static AkiSimpleFileManager create(String rootFilePathText) {
        AkiSimpleFileManager manager = new AkiSimpleFileManager(rootFilePathText);
        return manager;
    }

    public File read(Path targetPath) {
        if(!PathExt.isUnderRoot(targetPath, rootFilePathText)) {
            throw FileAccessDeninedException.create(targetPath);
        }
        return targetPath.toFile();
    }
    
    public File save(Path targetPath, InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputParam Is Null");
        }
        
        if(!PathExt.isUnderRoot(targetPath, rootFilePathText)) {

        }

        byte[] byteBuffer = new byte[bufferSize];
        while ((inputStream.read(byteBuffer)) != -1) {
            Files.write(targetPath, byteBuffer);
        }
        return targetPath.toFile();
    }

    public File copy(Path sourcePath, Path targetPath) throws IOException {
        if(!PathExt.isUnderRoot(sourcePath, rootFilePathText)) {
            throw FileAccessDeninedException.create(sourcePath);
        }
        
        if(!PathExt.isUnderRoot(targetPath, rootFilePathText)) {
            throw FileAccessDeninedException.create(targetPath);
        }
        
        return AkiFileWriteUtils.copyNecessarily(sourcePath, targetPath);
    }

    public File delete(Path targetPath) {
        if(!PathExt.isUnderRoot(targetPath, rootFilePathText)) {
            throw FileAccessDeninedException.create(targetPath);
        }
        File targetFile = targetPath.toFile();
        targetFile.deleteOnExit();
        return targetFile;
    }
}
