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
 * https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/319741/
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
