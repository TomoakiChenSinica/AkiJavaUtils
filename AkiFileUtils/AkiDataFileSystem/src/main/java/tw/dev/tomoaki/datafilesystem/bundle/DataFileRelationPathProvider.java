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

import java.nio.file.Path;
import tw.dev.tomoaki.datafilesystem.core.NewDataFilePathProvider;
import tw.dev.tomoaki.datafilesystem.core.RecentDataFilePathProvider;
import tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation;

/**
 *
 * @author tomoaki
 */
public class DataFileRelationPathProvider<DATA_FILE extends DataFileRelation> implements RecentDataFilePathProvider<DATA_FILE>, NewDataFilePathProvider<DATA_FILE> {
   
    
    @Override
    public Path obtainRecentFilePath(DATA_FILE data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean hasRecentFile(DATA_FILE data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean isRecentFileExists(DATA_FILE data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Path obtainNewFilePath(DATA_FILE data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }    
}
