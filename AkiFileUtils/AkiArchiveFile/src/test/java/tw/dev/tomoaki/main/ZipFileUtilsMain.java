/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import tw.dev.tomoaki.archivefile.ZipFileProcessor;
import tw.dev.tomoaki.archivefile.ZipFileUtils;
import tw.dev.tomoaki.archivefile.entity.ArchiveFileOutputStream;

/**
 *
 * @author arche
 */
public class ZipFileUtilsMain {

    public static void main(String[] args) throws IOException {
        test12();
    }
    
    protected static void test1() throws IOException {
        String filePath = "D:\\Temp\\AkiJavaUtils-main-mailimprove.zip";
        String destDir = "D:\\Temp\\ZipFileTest";
//        List<ArchiveFileOutputStream> afosList = ZipFileUtils.unzipArchive(destDir, filePath);
//        for (ArchiveFileOutputStream afos : afosList) {
//            FileOutputStream fos = afos.getFileOutputStream();
//            byte[] buffer = new byte[1024 * 1024];
//            fos.write(buffer);
//            fos.close();
//        }
        ZipFileProcessor fileProcessor = ZipFileProcessor.create();
        fileProcessor.doUnzip(destDir, filePath);    
    }
    
    protected static void test12() throws IOException {
        String filePath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\input\\素材2.zip";
        // System.out.println(filePath);
        String destDir =  "C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\output\\素材2";

        ZipFileProcessor fileProcessor = ZipFileProcessor.create();
        fileProcessor.doUnzip(destDir, filePath);    
    }    
    
    protected static void test2() throws IOException {
        // System.out.println("test2()");        
        String filePath = "C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\input\\ZipFile.zip";
        String destDir = "C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\output\\ZipFile";
        ZipFileProcessor fileProcessor = ZipFileProcessor.create();
        fileProcessor.doUnzip(destDir, filePath);    
    }    
}
