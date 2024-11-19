/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileioutils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Tomoaki Chen
 */
public class AkiFileWriteUtils {

    //https://stackoverflow.com/questions/4685563/how-to-pass-a-function-as-a-parameter-in-java
    private static final FileNeedCopy DEFAULT = (sourcePath, targetPath) -> {
        try {
            if (Files.notExists(targetPath)) {
                return true;
            }

            FileTime lastModifiedTime4Source = Files.getLastModifiedTime(sourcePath);
            FileTime lastModifiedTime4Target = Files.getLastModifiedTime(targetPath);
            return lastModifiedTime4Source.toMillis() > lastModifiedTime4Target.toMillis();
        } catch (Exception ex) {
            throw new AkiFileException(ex);
        }
    };
    
    public static void checkFileDirectory(Path filePath) throws IOException {
        Path directoryPath = filePath.getParent();
        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
    }
            
//<editor-fold defaultstate="collapsed" desc="之前就寫到的，當時已知道nio為New IO">
    public static File write(InputStream sourceFileStream, String targetFilePath) throws IOException {
        if (targetFilePath == null) {
            throw new IllegalArgumentException("targetFilePath Is Null");
        }
        Path targetPath = Paths.get(targetFilePath);
        return AkiFileWriteUtils.write(sourceFileStream, targetPath);
    }

    public static File write(InputStream sourceFileStream, File targetFile) throws IOException {
        if (targetFile == null) {
            throw new IllegalArgumentException("targetFile Is Null");
        }
        return AkiFileWriteUtils.write(sourceFileStream, targetFile.toPath());
    }

    public static File write(InputStream sourceFileStream, Path targetPath) throws IOException {
        Files.copy(sourceFileStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        IOUtils.closeQuietly(sourceFileStream);
        return targetPath.toFile();
    }

    public static File write(String sourceFilePath, String targetDirPath, String targetFileName) throws IOException {
        if (targetDirPath == null) {
            throw new IllegalArgumentException("targetDirPath Is Null");
        }

        if (targetDirPath == null) {
            throw new IllegalArgumentException("targetFileName Is Null");
        }

        Path sourcePath = Paths.get(sourceFilePath);
        Path targetPath = Paths.get(targetDirPath, targetFileName);
        return write(sourcePath, targetPath);
    }

    public static File write(String sourceFilePath, String targetFilePath) throws IOException {
        Path sourcePath = Paths.get(sourceFilePath);
        Path targetPath = Paths.get(targetFilePath);
        return write(sourcePath, targetPath);
    }

    public static File write(String sourceFilePath, File targetFile) throws IOException {
        Path targetPath = targetFile.toPath();
        return write(sourceFilePath, targetPath);
    }

    public static File write(String sourceFilePath, Path targetPath) throws IOException {
        Path sourcePath = Paths.get(sourceFilePath);
        return write(sourcePath, targetPath);
    }

    public static File write(Path sourcePath, Path targetPath) throws IOException {
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        return targetPath.toFile();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="知道上面問題後調整">
    /*
    public static File write(InputStream sourceFileStream, String targetFilePath) throws IOException {
        if(targetFilePath == null) {
            return null;
        }
        Path targetPath = Paths.get(targetFilePath);
        return AkiFileWriteUtils.write(sourceFileStream, targetPath);
    }

    public static File write(InputStream sourceFileStream, File targetFile) throws IOException {
        if (targetFile == null) {
            return null;
        }
        return AkiFileWriteUtils.write(sourceFileStream, targetFile.toPath());        
    }

    public static File write(InputStream sourceFileStream, Path targetPath) throws IOException {
        byte[] byteBuffer = new byte[1024];
        while( (sourceFileStream.read(byteBuffer)) != -1 ) {    
            System.out.println("byteBuffer= " + byteBuffer);
            Files.write(targetPath, byteBuffer, StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE);
        }
        IOUtils.closeQuietly(sourceFileStream);
        return targetPath.toFile();
    }

    public static File write(String sourceFilePath, String targetFilePath) throws IOException {
        Path sourcePath = Paths.get(sourceFilePath);
        Path targetPath = Paths.get(targetFilePath);
        return write(sourcePath, targetPath);
    }       
    
    public static File write(String sourceFilePath, File targetFile) throws IOException {
        Path targetPath = targetFile.toPath();
        return write(sourceFilePath, targetPath);
    }        
    
    public static File write(String sourceFilePath, Path targetPath) throws IOException {
        Path sourcePath = Paths.get(sourceFilePath);
        return write(sourcePath, targetPath);
    }    
    
    
    public static File write(Path sourcePath, Path targetPath) throws IOException {        
        //https://medium.com/@clu1022/java-nio-buffer-c98b52fd93ca
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        SeekableByteChannel channel = Files.newByteChannel(sourcePath);
     
        while( (channel.read(byteBuffer) != -1) ) {
            byte[] datas = byteBuffer.array();
//            System.out.println("datas= " + datas);            
            Files.write(targetPath, datas, StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE);
        }
        return targetPath.toFile();
    }     
     */
//</editor-fold>
    
    public static File copyNecessarily(String sourceFilePath, String targetDirPath, String targetFileName, FileNeedCopy fileNeedCopyFunc) throws IOException {
        Path sourcePath = Paths.get(sourceFilePath);
        Path targetPath = Paths.get(targetDirPath, targetFileName);
        return copyNecessarily(sourcePath, targetPath);
    }    
    
    public static File copyNecessarily(String sourceFilePath, String targetDirPath, String targetFileName) throws IOException {
        Path sourcePath = Paths.get(sourceFilePath);
        Path targetPath = Paths.get(targetDirPath, targetFileName);
        return copyNecessarily(sourcePath, targetPath);
    }

    public static File copyNecessarily(String sourceFilePath, String targetFilePath, FileNeedCopy fileNeedCopyFunc) throws IOException {
        Path sourcePath = Paths.get(sourceFilePath);
        Path targetPath = Paths.get(targetFilePath);
        return copyNecessarily(sourcePath, targetPath, fileNeedCopyFunc);
    }

    public static File copyNecessarily(String sourceFilePath, String targetFilePath) throws IOException {
        Path sourcePath = Paths.get(sourceFilePath);
        Path targetPath = Paths.get(targetFilePath);
        return copyNecessarily(sourcePath, targetPath);
    }

    public static File copyNecessarily(Path sourcePath, Path targetPath) throws IOException {
        return copyNecessarily(sourcePath, targetPath, null);
    }

    public static File copyNecessarily(Path sourcePath, Path targetPath, FileNeedCopy fileNeedCopyFunc) throws IOException {
        fileNeedCopyFunc = (fileNeedCopyFunc == null) ? DEFAULT : fileNeedCopyFunc;
        if (fileNeedCopyFunc.isNecessary(sourcePath, targetPath)) {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
        return targetPath.toFile();
    }
}
