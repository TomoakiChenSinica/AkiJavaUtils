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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
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

    private final static Path TARGET_PATH = Paths.get("C:\\AkiRoot\\ProgramPlayGround\\Java\\ZipFiles\\output", "Interns-202602031034.zip");

    public static void main(String[] args) {
        try {
             test1(); // 再次依次(?)
            // test2();
//             test2_2();
            // test3();
            // test3_2();
            // test4();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 實驗項目
     * 1. 給檔案路徑，使用檔案的 OutputStream
     * 2. 手動關閉 stream，
     *
     *
     */
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

    /**
     * 實驗項目
     * 1. 給檔案路徑，使用檔案的 OutputStream
     * 2. 手動關閉 stream
     * 3. 額外檔名命名方式
     */
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

    /**
     * 實驗項目
     * 1. 使用記憶體緩存的 ByteArrayOutputStream
     * 2. 取得 OutputStream 後轉成 InputStram 寫檔
     *
     */
    private static void test2_2() throws IOException {
        List<String> internNos = Arrays.asList("150001", "150002", "150003", "150004", "150005", "150006");
        Path internFileRootPath = Paths.get("C:\\DATA\\Files\\SummerIntern");

        Zipper zipper = new Zipper(path -> "INTERN-" + path.getFileName().toString());
        Path[] paths = internNos.stream()
                .map(no -> Paths.get(internFileRootPath.toString(), no))
                .toArray(Path[]::new);

        OutputStream os = zipper.zipStream(paths);
        System.out.println("os instanceof ByteArrayOutputStream= " + (os instanceof ByteArrayOutputStream));

        if (os instanceof ByteArrayOutputStream) {
            InputStream is = new ByteArrayInputStream((((ByteArrayOutputStream) os).toByteArray()));
            Files.copy(is, TARGET_PATH);
        }
    }


    private static void test3() throws IOException {
        List<String> internNos = Arrays.asList("150001", "150002", "150003", "150004", "150005", "150006");
        Path internFileRootPath = Paths.get("C:\\DATA\\Files\\SummerIntern");

        Zipper zipper = new Zipper(path -> "INTERN" + path.getFileName().toString(), path -> "INTERN-" + path.getFileName().toString());
        Path[] paths = internNos.stream()
                .map(no -> Paths.get(internFileRootPath.toString(), no))
                .toArray(Path[]::new);

        File file = zipper.zipFile(TARGET_PATH, paths);
        System.out.println("file= " + file);
    }

    /**
     * 對比 test3_2()，差別在於實驗 Path 使用 slash 和 back-slash 是否有影響
     *
     */
    private static void test3_2() throws IOException {
        List<String> internNos = Arrays.asList("150001", "150002", "150003", "150004", "150005", "150006");
        Path internFileRootPath = Paths.get("/DATA/Files/SummerIntern/");

        Zipper zipper = new Zipper(path -> "INTERN" + path.getFileName().toString(), path -> "INTERN-" + path.getFileName().toString());
        Path[] paths = internNos.stream()
                .map(no -> Paths.get(internFileRootPath.toString(), no))
                .toArray(Path[]::new);

        File file = zipper.zipFile(TARGET_PATH, paths);
        System.out.println("file= " + file);
    }

    private static void test4() throws IOException {
        List<String> internNos = Arrays.asList("150001", "150002", "150003", "150004", "150005", "150006");
        Path internFileRootPath = Paths.get("/DATA/Files/SummerIntern/");

        Zipper zipper = new Zipper(path -> "INTERN" + path.getFileName().toString(), path -> "INTERN-" + path.getFileName().toString());
        Path[] paths = internNos.stream()
                .map(no -> Paths.get(internFileRootPath.toString(), no))
                .toArray(Path[]::new);
        OutputStream os = Files.newOutputStream(TARGET_PATH);
        os = zipper.zipStream(os, paths);
        os.flush();
        os.close();
    }
}
