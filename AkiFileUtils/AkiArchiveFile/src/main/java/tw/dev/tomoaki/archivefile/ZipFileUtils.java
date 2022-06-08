/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.archivefile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import tw.dev.tomoaki.archivefile.entity.ArchiveFileOutputStream;

/**
 *
 * @author arche Resources¡G
 * https://www.journaldev.com/960/java-unzip-file-example
 * https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/319741/
 */
public class ZipFileUtils {
    
    public static Boolean printLog = Boolean.FALSE;
    public static Integer bufferSize = 1024 * 1024;

    public static List<ArchiveFileOutputStream> unzipArchive(String destDir, String filePath) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(filePath);
        return unzipArchive(destDir, fis);
    }

    public static List<ArchiveFileOutputStream> unzipArchive(String destDir, File file) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(file);
        return unzipArchive(destDir, fis);
    }

    public static List<ArchiveFileOutputStream> unzipArchive(String destDir, FileInputStream fis) throws FileNotFoundException, IOException {
        List<ArchiveFileOutputStream> afosList = new ArrayList();
        ZipInputStream zis = new ZipInputStream(fis);
//        ZipEntry ze = zis.getNextEntry();
//        while (ze != null) {
//            ArchiveFileOutputStream afos = processZipEntry2ArchiveFileOutputStream(destDir, ze);
//            afosList.add(afos);
//        }
        ZipEntry ze;
        do {
            ze = zis.getNextEntry();
            if(ze == null) break;
            ArchiveFileOutputStream afos = processZipEntry2ArchiveFileOutputStream(destDir, zis, ze);
            afosList.add(afos);
        } while (true);

        return afosList;
    }

    /**
     *
     *
     */
    private static ArchiveFileOutputStream processZipEntry2ArchiveFileOutputStream(String destDir, ZipInputStream zis, ZipEntry zipEntry) throws FileNotFoundException, IOException {        
        String fileName = zipEntry.getName();
        File newFile = new File(destDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(newFile);
        
        Integer len;
        byte[] buffer = new byte[bufferSize];
        while((len = zis.read(buffer)) > 0) {
            fos.write(buffer);
        }
        ArchiveFileOutputStream afos = ArchiveFileOutputStream.Factory.create(fileName, fos);
        zis.closeEntry();
//        fos.close();
        return afos;
    }
}
