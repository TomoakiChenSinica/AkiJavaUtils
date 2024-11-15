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
package tw.dev.tomoaki.reflection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author tomoaki
 *
 * 主要源自於: https://www.baeldung.com/java-find-all-classes-in-package
 */
public class ClassFinder {

    public List<Class> findInPackageWithClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(ClassFileHelper.obtainFilePathName(packageName));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(fileName -> fileName.endsWith(".class"))
                .map(fileName -> obtainClass(packageName, fileName))
                .collect(Collectors.toList());
    }

    /* 搬到 ClassFileHelper
    protected String obtainFilePathName(String packageName) {
        return packageName.replaceAll("[.]", "/");
    }*/
    protected Class obtainClass(String packageName, String fileName) {
        try {
            return Class.forName(ClassFileHelper.obtainClasssName(packageName, fileName));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /* 搬到 ClassFileHelper
    protected String obtainClasssName(String packageName, String fileName) {
        String classSimpleName = this.obtainClassSimpleName(fileName);
        return String.format("%s.%s", packageName, classSimpleName);
    }
    
    protected String obtainClassSimpleName(String fileName) {
        return fileName.replaceAll(REG_EXP_CLASS_FILE_SUFFIX, "");
    }*/
}
