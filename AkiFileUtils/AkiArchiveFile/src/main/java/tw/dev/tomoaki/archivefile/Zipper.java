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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import tw.dev.tomoaki.archivefile.core.ArchiveEntryNaming;
import tw.dev.tomoaki.archivefile.core.ArchiveEntryPathNaming;

/**
 *
 * @author tomoaki
 */
public class Zipper {

    private final static Logger LOGGER = Logger.getLogger(Zipper.class.getCanonicalName());

    private final static ArchiveEntryPathNaming DEFAULT_DIR_NAMING = path -> path.getFileName().toString(); // + "/"; // private final static ArchiveEntryNaming DEFAULT_DIR_NAMING = path -> path.getFileName().toString() + "/";
    private final static ArchiveEntryPathNaming DEFAULT_FILE_NAMING = path -> path.getFileName().toString();

    private final ArchiveEntryNaming dirNaming, fileNaming;

    public Zipper() {
        dirNaming = DEFAULT_DIR_NAMING;
        fileNaming = DEFAULT_FILE_NAMING;
    }

    public Zipper(ArchiveEntryPathNaming fileNaming) {
        this.dirNaming = DEFAULT_DIR_NAMING;
        this.fileNaming = fileNaming;
    }

    public Zipper(ArchiveEntryPathNaming dirNaming, ArchiveEntryPathNaming fileNaming) {
        this.dirNaming = dirNaming;
        this.fileNaming = fileNaming;
    }

    public File zipFile(Path targetPath, Path sourcePath, Path... otherSourcePaths) throws IOException {
        Path[] sourcePaths = Stream.concat(Stream.of(sourcePath), Stream.of(otherSourcePaths)).toArray(Path[]::new);
        return this.zipFile(targetPath, sourcePaths);
    }

    public File zipFile(Path targetPath, Path[] sourcePaths) throws IOException {
        try (OutputStream os = this.zipStream(targetPath, sourcePaths)) {
        }
        return targetPath.toFile();
    }

    /**
     * 將檔案清單({@code sourcePaths})壓縮成壓縮檔案資料流({@link ZipArchiveOutputStream})，<br>
     *
     * @param targetPath 目標檔案位置
     * @param sourcePath 要壓縮的檔案清單
     * @return 寫入(壓縮資料)的資料流
     * @throws IOException
     */
    public OutputStream zipStream(Path targetPath, Path sourcePath, Path... otherSourcePaths) throws IOException {
        Path[] sourcePaths = Stream.concat(Stream.of(sourcePath), Stream.of(otherSourcePaths)).toArray(Path[]::new);
        return this.zipStream(targetPath, sourcePaths);
    }

    /**
     * 將檔案清單({@code sourcePaths})壓縮成壓縮檔案資料流({@link ZipArchiveOutputStream})，<br>
     *
     * @param sourcePaths 要壓縮的檔案清單
     * @return 寫入(壓縮資料)的資料流
     * @throws IOException
     */
    public OutputStream zipStream(Collection<Path> sourcePaths) throws IOException {
        Path[] sourcePathArr = sourcePaths.stream().toArray(Path[]::new);
        return zipStream(sourcePathArr);
    }

    /**
     * 將檔案清單({@code sourcePaths})壓縮成壓縮檔案資料流({@link ZipArchiveOutputStream})，<br>
     * 寫入到指定的檔案路徑{@code targetPath}，此方法是透過檔案資料流
     *
     * @param targetPath 要寫入壓縮資料的檔案路徑
     * @param sourcePaths 要壓縮的檔案清單
     * @return 寫入(壓縮資料)的資料流
     * @throws IOException
     */
    public OutputStream zipStream(Path targetPath, Collection<Path> sourcePaths) throws IOException {
        Path[] sourcePathArr = sourcePaths.stream().toArray(Path[]::new);
        return zipStream(targetPath, sourcePathArr);
    }

    /**
     * 將檔案清單({@code sourcePaths})壓縮成壓縮檔案資料流({@link ZipArchiveOutputStream})，<br>
     * 此方法是透過緩存的資料流 {@link ByteArrayOutputStream}實作 <br>
     *
     * @param sourcePaths 要壓縮的檔案清單
     * @return 寫入(壓縮資料)的資料流
     * @throws IOException
     */
    public OutputStream zipStream(Path[] sourcePaths) throws IOException {
        OutputStream os = new ByteArrayOutputStream(); // https://chatgpt.com/share/698056e2-e4c8-800d-90c3-1d1df360ffa1 ，常態下，開銷交給 JVM 記憶體而非 OS 檔案系統
        return this.zipStream(os, sourcePaths);
    }

    /**
     * 將檔案清單({@code sourcePaths})壓縮成壓縮檔案資料流({@link ZipArchiveOutputStream})，<br>
     * 寫入到指定的檔案路徑{@code targetPath}，寫入到指定的檔案路徑{@code targetPath}，此方法是透過檔案資料流
     *
     * @param targetPath 要寫入壓縮資料的檔案路徑
     * @param sourcePaths 要壓縮的檔案清單
     * @return 寫入(壓縮資料)的資料流
     * @throws IOException
     */
    public OutputStream zipStream(Path targetPath, Path[] sourcePaths) throws IOException {
        OutputStream os = Files.newOutputStream(targetPath);
        return this.zipStream(os, sourcePaths);
    }

    /**
     * 將檔案清單({@code sourcePaths})壓縮成壓縮檔案資料流({@link ZipArchiveOutputStream})，<br>
     * 寫入到指定的檔案路徑{@code targetPath}
     *
     * @param os 要寫成(壓縮資料)的資料流
     * @param sourcePaths 要壓縮的檔案清單
     * @return 寫入(壓縮資料)的資料流
     * @throws IOException
     */
    public OutputStream zipStream(OutputStream os, Collection<Path> sourcePaths) throws IOException {
        Path[] sourcePathArr = sourcePaths.stream().toArray(Path[]::new);
        return zipStream(os, sourcePathArr);
    }

    /**
     * 將檔案清單({@code sourcePaths})壓縮成壓縮檔案資料流({@link ZipArchiveOutputStream})，<br>
     * 寫入傳入的資料流 {@code os}
     *
     * @param os 要寫成(壓縮資料)的資料流
     * @param sourcePaths 要壓縮的檔案清單
     * @return 寫入(壓縮資料)的資料流
     * @throws IOException
     */
    public OutputStream zipStream(OutputStream os, Path[] sourcePaths) throws IOException { // public OutputStream zipStream(Path targetPath, Path[] sourcePaths) throws IOException {
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);

        for (Path srcPath : sourcePaths) {
            if (Files.isHidden(srcPath)) {
                continue;
            }

            if (Files.isDirectory(srcPath)) {
                // FIXME: 之後要不要更切出去?
                String dirName = dirNaming.obtainName(srcPath);
                ZipArchiveEntry dirEntry = new ZipArchiveEntry(dirName  + "/");
                zos.putArchiveEntry(dirEntry);
                LOGGER.fine(() -> "dirName= " + dirName);

                List<Path> childPaths = Files.list(srcPath).collect(Collectors.toList());
                for (Path childPath : childPaths) {
                    LOGGER.fine(() -> "under dir= " + dirName + " find: " + childPath);
                    archiveByApacheZip(zos, childPath, p -> dirName + "/" + fileNaming.obtainName(p));
                    zos.closeArchiveEntry(); // 沒有寫會被警告 java.io.IOException: This archive contains unclosed entries.
                }
                continue;
            }
            archiveByApacheZip(zos, srcPath, fileNaming);
            zos.closeArchiveEntry();
        }
        // return zos;
        /* 實驗1 test1()、test4(): 回傳 OutputStream 進行 close flush 會有問題
        return os;
        */
        zos.close(); // 不 close() ZipArchiveOutputStream，會傳的(原始) OutputStream 會無法正常完成使用或close()
        return os;
    }

//<editor-fold defaultstate="collapsed" desc="內部輔助 Methods">

    private static ZipOutputStream archiveByNativeZip(ZipOutputStream zos, Path srcPath, ArchiveEntryNaming naming) throws IOException {
        ZipEntry zipEntry = new ZipEntry(naming.obtainName(srcPath));
        zos.putNextEntry(zipEntry);
        Files.copy(srcPath, zos); // zos 缺乏好的 write
        return zos;
    }

    // ZipArchiveEntry 其實繼承 ZipEntry
    private static ZipArchiveOutputStream archiveByApacheZip(ZipArchiveOutputStream zos, Path srcPath, ArchiveEntryNaming naming) throws IOException {
        String archivedFileName = naming.obtainName(srcPath);
        ZipArchiveEntry entry = new ZipArchiveEntry(archivedFileName);
        LOGGER.fine( String.format("archivedFileName= %s" , archivedFileName) );

        zos.putArchiveEntry(entry);
        zos.write(srcPath);
        return zos;
    }
//</editor-fold>
}
