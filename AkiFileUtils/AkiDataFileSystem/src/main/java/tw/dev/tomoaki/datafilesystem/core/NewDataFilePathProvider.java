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

import java.nio.file.Path;

/**
 *
 * @author tomoaki
 * @param <T>
 */
public interface NewDataFilePathProvider<T> extends DataFileCreator<T> {

    /**
     * 配合 FileCreator 繼承來的 createFileName， <br>
     * 組合出「檔案的實際路徑」。
     * 
     * @param data 資料
     * @return 資料關聯檔案的路徑
     *  
     */
    public Path obtainNewFilePath(T data);

}
