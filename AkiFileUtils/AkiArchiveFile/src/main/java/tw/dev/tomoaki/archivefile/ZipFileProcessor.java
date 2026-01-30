/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.archivefile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import tw.dev.tomoaki.archivefile.entity.ArchiveFileOutputStream;

/**
 *
 * @author arche
 */
public class ZipFileProcessor {

    // private Integer unarchiveBufferSize = 1024 * 1024;
    private Integer outputBufferSize = 1024 * 1024;
    private Boolean printLog = Boolean.TRUE;

    public static ZipFileProcessor create() {
        ZipFileProcessor fileProcessor = new ZipFileProcessor();
        return fileProcessor;
    }

    public static ZipFileProcessor create(Integer outputBufferSize) {
        ZipFileProcessor fileProcessor = new ZipFileProcessor();
        fileProcessor.outputBufferSize = outputBufferSize;
        return fileProcessor;
    }

    public void doUnzip(String destDir, String filePath) throws IOException {
        List<ArchiveFileOutputStream> afosList = ZipFileUtils.unzipArchive(destDir, filePath);       
        for (ArchiveFileOutputStream afos : afosList) {
//            System.out.println("afos= " + afos);
            try (FileOutputStream fos = afos.getFileOutputStream()) {
                // byte[] buffer = new byte[outputBufferSize]; // new byte[1024 * 1024];
                // fos.write(buffer);
                // fos.close();
                Files.copy(Path.of(filePath), (OutputStream) fos);
                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
