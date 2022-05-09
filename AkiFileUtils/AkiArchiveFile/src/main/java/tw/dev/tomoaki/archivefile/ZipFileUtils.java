/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.archivefile;

import java.util.zip.ZipEntry;
import tw.dev.tomoaki.archivefile.entity.ArchiveFileOutputStream;

/**
 *
 * @author arche https://www.journaldev.com/960/java-unzip-file-example
 */
public class ZipFileUtils {

    /**
     *
     *
     */
    private ArchiveFileOutputStream processZipEntry2ArchiveFileOutputStream(ZipEntry zipEntry) {
        String fileName = zipEntry.getName();
        File newFile = new File(destDir + File.separator + fileName);

    }
}
