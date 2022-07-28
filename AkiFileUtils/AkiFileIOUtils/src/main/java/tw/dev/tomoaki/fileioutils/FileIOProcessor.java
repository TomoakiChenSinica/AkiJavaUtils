/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileioutils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Tomoaki Chen
 */
public class FileIOProcessor {

    public void writeFile(InputStream sourceFileStream, String targetFilePath) throws IOException {
        File targetFile = new File(targetFilePath);
        java.nio.file.Files.copy(sourceFileStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);        
        IOUtils.closeQuietly(sourceFileStream);
    }
    
    public void writeFile(InputStream sourceFileStream, File targetFile) throws IOException {
    java.nio.file.Files.copy(sourceFileStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);        
        IOUtils.closeQuietly(sourceFileStream);    
    }
}
