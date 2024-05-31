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
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.file.entity.FileNameDetail;

/**
 *
 * @author tomoaki
 */
public class FileNameDetailTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       test1(); 
    }
    
    private static void test1() {
        System.out.println(FileNameDetail.Factory.create("", "htaccess"));
        System.out.println(FileNameDetail.Factory.create("", "htaccess"));
        System.out.println(FileNameDetail.Factory.create(null, "htaccess"));
    }
    
}
