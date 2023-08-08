/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileioutils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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

//<editor-fold defaultstate="collapsed" desc="(2023/08/08)以下兩個是以前就寫，當時指大概知道nio(知道代表New IO)">
    public static void writeFile(InputStream sourceFileStream, String targetFilePath) throws IOException {
        File targetFile = new File(targetFilePath);
        Files.copy(sourceFileStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        IOUtils.closeQuietly(sourceFileStream);
    }

    public static void writeFile(InputStream sourceFileStream, File targetFile) throws IOException {
        Files.copy(sourceFileStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        IOUtils.closeQuietly(sourceFileStream);
    }
//</editor-fold>

    public static Path copyNecessarily(Path source, Path target) throws IOException {
        return copyNecessarily(source, target, null);
    }

    public static Path copyNecessarily(Path source, Path target, FileNeedCopy fileNeedCopyFunc) throws IOException {
        fileNeedCopyFunc = (fileNeedCopyFunc == null) ? DEFAULT : fileNeedCopyFunc;
        if (fileNeedCopyFunc.isNecessary(source, target)) {
            return Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
        return null;
    }
}
