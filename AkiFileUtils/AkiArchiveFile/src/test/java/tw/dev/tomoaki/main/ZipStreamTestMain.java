/*
 * Copyright 2025 tomoaki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tw.dev.tomoaki.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author tomoaki
 */
public class ZipStreamTestMain {
    
    public static void main(String[] args) throws IOException {
        testZip2();
    }
    
    public static void testZip1() throws IOException {
        String filePath = Path.of("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\output\\ZipFileJava2.zip").toString();
        System.out.println(filePath);
        
        FileOutputStream fos = new FileOutputStream(filePath);
        ZipOutputStream zos = new ZipOutputStream(fos);

        zos.putNextEntry(new ZipEntry("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\input\\1.txt"));
        zos.putNextEntry(new ZipEntry("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\input\\2.txt"));
        zos.putNextEntry(new ZipEntry("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\input\\3.txt"));
        
        Integer outputBufferSize = 1024 * 1024;        
        byte[] buffer = new byte[outputBufferSize];                
        zos.write(buffer); // Files.copy(Path.of("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles", "ZipFileJava.zip"), zos);       
        zos.close();
        
        /*int len;
        while ((len = zos.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
        }*/       
    }
    
    public static void testZip2() throws IOException {
        String targetZipPath = Path.of("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\output\\ZipFileJava3.zip").toString();
        System.out.println(targetZipPath);
        
        FileOutputStream fos = new FileOutputStream(targetZipPath);
        ZipOutputStream zos = new ZipOutputStream(fos);

        Path rootDirPath = Path.of("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\input");
        Path file1Path = Path.of("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\input\\1.txt");
        Path file2Path = Path.of("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\input\\2.txt");
        Path file3Path = Path.of("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\input\\3.txt");
        
        // System.out.println(rootDirPath.relativize(file1Path));
        // System.out.println(rootDirPath.relativize(file2Path));
        // System.out.println(rootDirPath.relativize(file3Path));
        zos.putNextEntry(new ZipEntry(rootDirPath.relativize(file1Path).toString()));
        zos.putNextEntry(new ZipEntry(rootDirPath.relativize(file2Path).toString()));
        zos.putNextEntry(new ZipEntry(rootDirPath.relativize(file3Path).toString()));
        
        Integer outputBufferSize = 1024 * 1024;        
        byte[] buffer = new byte[outputBufferSize];                
        zos.write(buffer); // Files.copy(Path.of("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles", "ZipFileJava.zip"), zos);       
        zos.close();
    }    
}
