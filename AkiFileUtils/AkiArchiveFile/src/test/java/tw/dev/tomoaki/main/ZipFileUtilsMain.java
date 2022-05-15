/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import tw.dev.tomoaki.archivefile.ZipFileUtils;
import tw.dev.tomoaki.archivefile.entity.ArchiveFileOutputStream;

/**
 *
 * @author arche
 */
public class ZipFileUtilsMain {

    public static void main(String[] args) throws IOException {
        System.out.println("ZipFileUtilsMain():");
        String filePath = "D:\\Temp\\Temp.zip";
        String destDir = "D:\\Temp\\ZipFileTest";
        List<ArchiveFileOutputStream> afosList = ZipFileUtils.unzipArchive(destDir, filePath);
        for (ArchiveFileOutputStream afos : afosList) {
            FileOutputStream fos = afos.getFileOutputStream();
            byte[] buffer = new byte[1024 * 1024];
            fos.write(buffer);
        }
    }
}
