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
package tw.dev.tomoaki.file.datafilesystem;

/**
 *
 * @author tomoaki
 * 
 * 單純產生「檔案」相關資訊，不理會「實際路徑」
 * 
 */
public interface FileCreator<T> {

    /**
     * 會需要存檔案時，如何依據傳進來的資料(data) 產生檔名
     * 
     * @param data 資料，跟此資料的關聯檔案，檔案名稱如何(根據資料)產生檔名
     * @return 
     * 
     */
    public String createFileName(T data);

}
