/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.fileioutils;

import java.nio.file.Path;

/**
 *
 * @author tomoaki
 */
public interface FileNeedCopy {

    public Boolean isNecessary(Path source, Path target);
}
