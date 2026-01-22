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
package tw.dev.tomoaki.datafilesystem.core.entity;

/**
 * Base interface representing a file associated with data.
 * <p>
 * This is the lowest-level abstraction and is typically not used directly.<br>
 * Instead, use {@link DataFileRelation} which extends this interface.
 * </p>
 * 
 * @author tomoaki
 *
 */
public interface DataFile {

    /**
     * Returns the display name of the file shown to users.
     *
     * @return the user-friendly display name of the file
     */    
    public String getFileDisplayName();

    /**
     * Returns the actual file name stored in the file system.
     *
     * @return the real file name used for storage
     */    
    public String getFileRealName();
}
