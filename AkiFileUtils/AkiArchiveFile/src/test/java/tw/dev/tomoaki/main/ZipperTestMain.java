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

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.archivefile.Zipper;

/**
 *
 * @author tomoaki
 */
public class ZipperTestMain {

    private final static Path TARGET_PATH = Paths.get("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\output", "Interns-202601301332.zip");

    public static void main(String[] args) {
        try {
            // test1();
            test2();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void test1() throws IOException {
        List<String> internNos = Arrays.asList("150001", "150002", "150003", "150004", "150005", "150006");
        Path internFileRootPath = Paths.get("C:\\DATA\\Files\\SummerIntern");

        Zipper zipper = new Zipper();
        Path[] paths = internNos.stream()
                .map(no -> Paths.get(internFileRootPath.toString(), no))
                .toArray(Path[]::new);

        OutputStream os = zipper.zipStream(TARGET_PATH, paths);
        os.close();
    }
    
    private static void test2() throws IOException {
        List<String> internNos = Arrays.asList("150001", "150002", "150003", "150004", "150005", "150006");
        Path internFileRootPath = Paths.get("C:\\DATA\\Files\\SummerIntern");

        Zipper zipper = new Zipper(path -> "INTERN-" + path.getFileName().toString());
        Path[] paths = internNos.stream()
                .map(no -> Paths.get(internFileRootPath.toString(), no))
                .toArray(Path[]::new);

        OutputStream os = zipper.zipStream(TARGET_PATH, paths);
        os.close();
    }    
}
