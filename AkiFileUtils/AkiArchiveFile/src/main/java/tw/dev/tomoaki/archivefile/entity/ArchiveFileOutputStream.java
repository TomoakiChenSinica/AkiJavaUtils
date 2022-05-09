/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.archivefile.entity;

import java.io.FileOutputStream;

/**
 *
 * @author arche
 */
public class ArchiveFileOutputStream {
    
    private String fileName;
    private FileOutputStream fileOutputStream;

    protected ArchiveFileOutputStream() {
    }
    
    public static class Factory {
        public static ArchiveFileOutputStream create(String fileName, FileOutputStream fileOutputStream) {
            ArchiveFileOutputStream afos = new ArchiveFileOutputStream();
            afos.fileName = fileName;
            afos.fileOutputStream = fileOutputStream;
            return afos;
        }
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }

    public void setFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }
}
