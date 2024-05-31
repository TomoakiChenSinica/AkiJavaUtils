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

import tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation;
import tw.dev.tomoaki.file.entity.FileNameDetail;

/**
 *
 * @author tomoaki
 * @param <DATA_FILE>
 * 
 */
public abstract class DataRelatedFileURLName<DATA_FILE extends DataFileRelation> extends FileNameDetail { 
    
    public abstract String obtainDataRelatedFilePrefix(DATA_FILE dataFile);

    public abstract String obtainDataRelatedFileSuffix(DATA_FILE dataFile);

}
