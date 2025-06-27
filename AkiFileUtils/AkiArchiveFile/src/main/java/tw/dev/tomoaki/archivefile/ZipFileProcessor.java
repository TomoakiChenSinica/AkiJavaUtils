/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.archivefile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import tw.dev.tomoaki.archivefile.entity.ArchiveFileOutputStream;

/**
 *
 * @author arche
 */
public class ZipFileProcessor {

    // https://www.tutorialspoint.com/javazip/javazip_deflateroutputstream.htm
    // https://www.tutorialspoint.com/javazip/javazip_zipoutputstream.htm
    // https://www.tutorialspoint.com/javazip/javazip_zipoutputstream_putnextentry.htm
    
    private Integer outputBufferSize = 1024 * 1024; // private Integer unarchiveBufferSize = 1024 * 1024;
    private Boolean printLog = Boolean.TRUE;

    public static class Factory {

        public static ZipFileProcessor create() {
            ZipFileProcessor fileProcessor = new ZipFileProcessor();
            return fileProcessor;
        }

        public static ZipFileProcessor create(Integer outputBufferSize) {
            ZipFileProcessor fileProcessor = new ZipFileProcessor();
            fileProcessor.outputBufferSize = outputBufferSize;
            return fileProcessor;
        }
    }

    public void doUnzip(String destDir, String filePath) throws IOException {
        List<ArchiveFileOutputStream> afosList = ZipFileUtils.unzipArchive(destDir, filePath);
        for (ArchiveFileOutputStream afos : afosList) {
            FileOutputStream fos = afos.getFileOutputStream();
            byte[] buffer = new byte[1024 * 1024];
            fos.write(buffer);
            fos.close();
        }
    }
}
