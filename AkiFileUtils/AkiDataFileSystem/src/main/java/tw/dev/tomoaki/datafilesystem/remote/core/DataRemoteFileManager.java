/*
 * Copyright 2023 tomoaki.
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
package tw.dev.tomoaki.datafilesystem.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import tw.dev.tomoaki.datafilesystem.remote.DataRemoteFile;

/**
 *
 * @author tomoaki
 */
public interface DataRemoteFileManager<T> {
    
    public Path obtainRemoteDestFilePath(T data);

    public DataRemoteFile copyToRemote(T data) throws IOException;
    
    public DataRemoteFile copyFromRemote(T data) throws IOException;

    public DataRemoteFile deleteFromRemote(T data) throws IOException;    
}
