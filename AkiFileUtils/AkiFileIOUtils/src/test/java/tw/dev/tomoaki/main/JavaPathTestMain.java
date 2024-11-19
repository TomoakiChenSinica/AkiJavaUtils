package tw.dev.tomoaki.main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author tomoaki
 */
public class JavaPathTestMain {

    // private final static String FILE_PATH = "C:\\AkiRoot\\ProgramPlayGround\\Java\\Others";
    private final static String FILE_PATH_NAME = "C:\\AkiRoot\\ProgramPlayGround\\Java\\Others";
    private final static Path FILE_PATH = Paths.get(FILE_PATH_NAME);

    public static void main(String[] args) {
        final Path targetPath = Paths.get(FILE_PATH_NAME, "..");
        test5(targetPath);
    }

    protected static void test1(Path targetPath) {
        // https://stackoverflow.com/questions/1844688/how-can-i-read-all-files-in-a-folder-from-java?page=1&tab=scoredesc#tab-top
        // targetPath.getFileSystem().newWatchService().
        try (Stream<Path> paths = Files.walk(targetPath, 1)) { // 舊方法: 從 File.listFiles() 取得 ，targetPath.toFile().listFiles()
            paths.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected static void test2(Path targetPath) {
        try (Stream<Path> paths = Files.walk(targetPath, 1)) { // 舊方法: 從 File.listFiles() 取得 ，targetPath.toFile().listFiles()
            paths.map(path -> {
                try {
                    return path.toRealPath();
                } catch (Exception ex) {
                    return null;
                }
            }).forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected static void test3(Path targetPath) {
        try (Stream<Path> paths = Files.walk(targetPath, 2)) { // 舊方法: 從 File.listFiles() 取得 ，targetPath.toFile().listFiles()
            paths.forEach(path -> {
                try {
                    System.out.println(path.toRealPath());
                    System.out.println(path.toAbsolutePath());
                    System.out.println(path.startsWith(FILE_PATH));
                    System.out.println(path.startsWith(FILE_PATH_NAME));
                    System.out.println(path.startsWith(targetPath));
                    System.out.println("------------------------------------------");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected static void test4(Path targetPath) {
        try (Stream<Path> paths = Files.walk(targetPath, 2)) { // 舊方法: 從 File.listFiles() 取得 ，targetPath.toFile().listFiles()
            paths.forEach(path -> {
                try {
                    System.out.println(FILE_PATH);
                    System.out.println(targetPath);
                    System.out.println("******************************************");
                    Path realPath = path.toRealPath();
                    System.out.println(realPath);
                    System.out.println(realPath.toAbsolutePath());
                    System.out.println(realPath.startsWith(FILE_PATH));
                    System.out.println(realPath.startsWith(FILE_PATH_NAME));
                    System.out.println(realPath.startsWith(targetPath));
                    System.out.println("------------------------------------------");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected static void test5(Path targetPath) {
        try (Stream<Path> paths = Files.walk(targetPath, 2)) { // 舊方法: 從 File.listFiles() 取得 ，targetPath.toFile().listFiles()
            paths.forEach(path -> {
                try {
//                    System.out.println(FILE_PATH);
//                    System.out.println(targetPath);
//                    System.out.println("******************************************");
                    System.out.println(path);                    
                    System.out.println(path.toRealPath());
                    System.out.println(path.normalize());
                    System.out.println("------------------------------------------");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
