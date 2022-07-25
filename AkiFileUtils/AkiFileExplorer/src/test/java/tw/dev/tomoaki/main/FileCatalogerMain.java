/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.fileexplorer.catalog.FileCataloger;

/**
 *
 * @author Tomoaki Chen
 */
public class FileCatalogerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FileCataloger fileCataloger = new FileCataloger();
        fileCataloger.doAnalyze("C:\\Users\\tomoaki\\Documents\\庶務處理\\2022-07-18\\file\\");
    }
    
}
