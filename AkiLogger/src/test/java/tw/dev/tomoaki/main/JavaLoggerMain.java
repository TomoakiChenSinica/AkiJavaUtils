package tw.dev.tomoaki.main;



import tw.dev.tomoaki.logger.AkiLogger;
import tw.dev.tomoaki.logger.LogHelper;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */



/**
 *
 * @author arche
 */
public class JavaLoggerMain {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
        test3();
    }
    
    private static void test1() {
        for(StackTraceElement stElement : Thread.currentThread().getStackTrace()) {
            String className = stElement.getClassName();
            System.out.println(String.format("className= %s", className));
        }
    }
    
    private static void test2() {
        System.out.println(LogHelper.getCaller());
    }
    
    private static void test3() {
        AkiLogger.Factory.create();
    }
}
