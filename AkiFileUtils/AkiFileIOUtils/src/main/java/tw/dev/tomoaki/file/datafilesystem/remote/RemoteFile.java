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
package tw.dev.tomoaki.file.datafilesystem.remote;

import java.nio.file.Path;

/**
 *
 * @author tomoaki
 */
public class RemoteFile {

    private Path filePath;

    protected RemoteFile() {
    }

    public static class Factory {

        public static RemoteFile create(Path filePath) {
            RemoteFile remoteFile = new RemoteFile();
            remoteFile.filePath = filePath;
            return remoteFile;
        }
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return filePath.getFileName().toString();
    }

//    private String fileName;
//    private String filePath;
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public String getFilePath() {
//        return filePath;
//    }
//
//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }    
}
