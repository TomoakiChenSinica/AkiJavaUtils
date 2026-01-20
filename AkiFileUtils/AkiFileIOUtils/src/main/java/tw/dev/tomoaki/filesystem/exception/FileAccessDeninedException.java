package tw.dev.tomoaki.filesystem.exception;

import java.nio.file.Path;

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
/**
 *
 * @author tomoaki
 */
public class FileAccessDeninedException extends RuntimeException {

    public static FileAccessDeninedException create(Path targetPath) {
        String msgFmt = "%s Is Not Readable/Writeable";
        return new FileAccessDeninedException(String.format(msgFmt, targetPath));
    }

    public static FileAccessDeninedException create(String msgFmt, Object... args) {
        return new FileAccessDeninedException(String.format(msgFmt, args));
    }     
    
    public FileAccessDeninedException(String msg) {
        super(msg);
    }

    public FileAccessDeninedException(Exception ex) {
        super(ex);
    }
}
