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
//應該可以使用 Functional Interface 的 Annoataion 「@FunctionalInterface」，之前就有意識到這種用法「只能一個 method」，
//而這好像就是 Functional Interface 的定義 (https://vocus.cc/article/60d79c47fd89780001db1095)
public interface FileNeedCopy {

    public Boolean isNecessary(Path source, Path target);
}
