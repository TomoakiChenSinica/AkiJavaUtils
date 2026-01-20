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
package tw.dev.tomoaki.main;

import java.nio.file.Path;
import java.nio.file.Paths;
import tw.dev.tomoaki.nioext.PathExt;

/**
 *
 * @author tomoaki
 */
public class NioFileTestMain {
    
    public static void main(String[] args) {
        Path rootPath = Paths.get("/DATA/Files/Abc");
        Path path1 = Paths.get("/DATA/Files/Abc", "123", "321");
        Path path2 = Paths.get("\\DATA\\Files\\Abc", "123", "321");
        
        String msgFmt = "rootPath= %s, path1= %s, path2= %s";
        System.out.println(String.format(msgFmt, rootPath, path1, path2));

        System.out.println(PathExt.toLaxRealPath(path1));
        System.out.println(PathExt.toLaxRealPath(path2));

        
        System.out.println(path1.startsWith(rootPath));
        System.out.println(path2.startsWith(rootPath));
        System.out.println(PathExt.toLaxRealPath(path1).startsWith(rootPath));
        System.out.println(PathExt.toLaxRealPath(path2).startsWith(rootPath));          
        System.out.println(PathExt.toLaxRealPath(path1).startsWith(PathExt.toLaxRealPath(rootPath)));
        System.out.println(PathExt.toLaxRealPath(path2).startsWith(PathExt.toLaxRealPath(rootPath)));          
    }
}
