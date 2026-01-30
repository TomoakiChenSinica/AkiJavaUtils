/*
 * Copyright 2026 tomoaki.
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
package tw.dev.tomoaki.archivefile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import tw.dev.tomoaki.archivefile.core.ArchiveEntryNaming;

/**
 *
 * @author tomoaki
 */
public class Zipper {
 
    private final static ArchiveEntryNaming DEFAULT_DIR_NAMING = path -> path.getFileName().toString() + "/";
    private final static ArchiveEntryNaming DEFAULT_FILE_NAMING = path -> path.getFileName().toString();
    
    private ArchiveEntryNaming dirNaming, fileNaming;
    
    public Zipper() {
        dirNaming = DEFAULT_DIR_NAMING;
        fileNaming = DEFAULT_FILE_NAMING;
    }
    
    public Zipper(ArchiveEntryNaming fileNaming) {
        this.dirNaming = DEFAULT_DIR_NAMING;
        this.fileNaming = fileNaming;
    }        
    
    public Zipper(ArchiveEntryNaming dirNaming, ArchiveEntryNaming fileNaming) {
        this.dirNaming = dirNaming;
        this.fileNaming = fileNaming;
    }    
    
    // public OutputStream zip(Path targetPath, ArchiveEntryNaming dirNaming, ArchiveEntryNaming fileNaming, Path sourcePath, Path... otherSourcePaths) throws IOException {
    public OutputStream zipFile(Path targetPath, Path sourcePath, Path... otherSourcePaths) throws IOException {    
        /*
        Stream<Path> sourcePaths = Stream.concat(Stream.of(sourcePath), Stream.of(otherSourcePaths));       
        try (OutputStream os = Files.newOutputStream(targetPath); ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os)) {
            for(Path srcPath : sourcePaths.collect(Collectors.toList())) {
                if(Files.isHidden(srcPath)) {
                    continue;
                }
                
                if (Files.isDirectory(srcPath)) {
                    String dirName = dirNaming.obtainName(srcPath);
                    ZipArchiveEntry dirEntry = new ZipArchiveEntry(dirName);
                    zos.putArchiveEntry(dirEntry);
                    // System.out.println("dirName= " + dirName);
                    
                    List<Path> childPaths = Files.list(srcPath).collect(Collectors.toList());
                    for (Path childPath : childPaths) {
                        // System.out.println("under dir= " + dirName + " find: " + childPath);
                        archiveByApacheZip(zos, childPath, p -> dirName + fileNaming.obtainName(p));
                        zos.closeArchiveEntry(); // 沒有寫會被警告 java.io.IOException: This archive contains unclosed entries.                  
                    }
                    continue;
                }
            }
            
            archiveByApacheZip(zos, sourcePath, fileNaming);
            zos.closeArchiveEntry();            
            return zos;
        } */
        Path[] sourcePaths = Stream.concat(Stream.of(sourcePath), Stream.of(otherSourcePaths)).toArray(Path[]::new);
        return this.zipFile(targetPath, sourcePaths);        
    }
    
    public OutputStream zipFile(Path targetPath, Path[] sourcePaths) throws IOException {    
        try (OutputStream os = Files.newOutputStream(targetPath); ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os)) {
            for(Path srcPath : sourcePaths) {
                if(Files.isHidden(srcPath)) {
                    continue;
                }
                
                if (Files.isDirectory(srcPath)) {
                    String dirName = dirNaming.obtainName(srcPath);
                    ZipArchiveEntry dirEntry = new ZipArchiveEntry(dirName);
                    zos.putArchiveEntry(dirEntry);
                    // System.out.println("dirName= " + dirName);
                    
                    List<Path> childPaths = Files.list(srcPath).collect(Collectors.toList());
                    for (Path childPath : childPaths) {
                        // System.out.println("under dir= " + dirName + " find: " + childPath);
                        archiveByApacheZip(zos, childPath, p -> dirName + fileNaming.obtainName(p));
                        zos.closeArchiveEntry(); // 沒有寫會被警告 java.io.IOException: This archive contains unclosed entries.                  
                    }
                    continue;
                }
                archiveByApacheZip(zos, srcPath, fileNaming);
                zos.closeArchiveEntry();                
            }                     
            return zos;
        }
    }

    public OutputStream zipStream(Path targetPath, Path sourcePath, Path... otherSourcePaths) throws IOException {    
        Path[] sourcePaths = Stream.concat(Stream.of(sourcePath), Stream.of(otherSourcePaths)).toArray(Path[]::new);
        return this.zipStream(targetPath, sourcePaths);        
    }    

    public OutputStream zipStream(Path targetPath, Path[] sourcePaths) throws IOException {
        OutputStream os = Files.newOutputStream(targetPath);
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);

        for (Path srcPath : sourcePaths) {
            if (Files.isHidden(srcPath)) {
                continue;
            }

            if (Files.isDirectory(srcPath)) {
                // FIXME: 之後要不要更切出去?
                String dirName = dirNaming.obtainName(srcPath);
                ZipArchiveEntry dirEntry = new ZipArchiveEntry(dirName);
                zos.putArchiveEntry(dirEntry);
                // System.out.println("dirName= " + dirName);

                List<Path> childPaths = Files.list(srcPath).collect(Collectors.toList());
                for (Path childPath : childPaths) {
                    // System.out.println("under dir= " + dirName + " find: " + childPath);
                    
                    archiveByApacheZip(zos, childPath, p -> dirName + fileNaming.obtainName(p));
                    zos.closeArchiveEntry(); // 沒有寫會被警告 java.io.IOException: This archive contains unclosed entries.                  
                }
                continue;
            }
            archiveByApacheZip(zos, srcPath, fileNaming);
            zos.closeArchiveEntry();
        }
        return zos;
    }


    
    
    private static ZipOutputStream archiveByNativeZip(ZipOutputStream zos, Path srcPath, ArchiveEntryNaming naming) throws IOException {
        ZipEntry zipEntry = new ZipEntry(naming.obtainName(srcPath)); // new ZipEntry("hello-" + srcPath.getFileName());
        zos.putNextEntry(zipEntry);
        Files.copy(srcPath, zos); // zos 缺乏好的 write
        return zos;
    }

    // ZipArchiveEntry 其實繼承 ZipEntry
    private static ZipArchiveOutputStream archiveByApacheZip(ZipArchiveOutputStream zos, Path srcPath, ArchiveEntryNaming naming) throws IOException {
        String archivedFileName = naming.obtainName(srcPath); // "HELLO-" + srcPath.getFileName().toString();
        ZipArchiveEntry entry = new ZipArchiveEntry(archivedFileName);
        System.out.println("archivedFileName= " + archivedFileName);
        
        zos.putArchiveEntry(entry);
        zos.write(srcPath);
        return zos;
    }    
}
