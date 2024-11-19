/*
 * Copyright 2024 tomoaki.
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
package tw.dev.tomoaki.datafilesystem.bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import tw.dev.tomoaki.datafilesystem.core.DataFileManager;
import tw.dev.tomoaki.fileioutils.AkiFileWriteUtils;
import tw.dev.tomoaki.nioext.PathExt;

/**
 *
 * @author tomoaki
 * @param <DATA>
 * @param <PATH_PROVIDER>
 */
//public abstract class DataRelatedFileManager<DATA, FILE extends DataFileRelation<DATA>, PATH_PROVIDER extends DataRelatedFilePathProvider<DATA, FILE>> implements DataFileManager<DATA> {
public abstract class DataRelatedFileManager<DATA, PATH_PROVIDER extends DataRelatedFilePathProvider> implements DataFileManager<DATA> {

    private PATH_PROVIDER pathProvider;

    protected PATH_PROVIDER getPathProvider() {
        if (this.pathProvider == null) {
            this.pathProvider = obtainPathProvider();
        }
        return this.pathProvider;
    }

    protected abstract PATH_PROVIDER obtainPathProvider();

    protected abstract String obtainFileNotFoundMsg(DATA data);

    @Override
    public Path obtainSaveFilePath(DATA data, Boolean overwriteRecentFile) {
        Path recentPath = getPathProvider().obtainRecentFilePath(data);
        Path path = (recentPath != null && overwriteRecentFile) ? recentPath : getPathProvider().obtainNewFilePath(data);
        return path;
    }

    @Override
    public File save(DATA data, InputStream inputStream, Boolean overwriteRecentFile) throws IOException {
        Path path = this.obtainSaveFilePath(data, overwriteRecentFile);
        AkiFileWriteUtils.checkFileDirectory(path);
        File file = AkiFileWriteUtils.write(inputStream, path);
        return file;
    }

    @Override
    public File delete(DATA data) throws IOException {
        Path path = getPathProvider().obtainRecentFilePath(data);
        File file = path.toFile();
        if (file == null || !file.exists()) {
            throw new FileNotFoundException(obtainFileNotFoundMsg(data));
        }
        file.delete();
        return file;
    }

}
