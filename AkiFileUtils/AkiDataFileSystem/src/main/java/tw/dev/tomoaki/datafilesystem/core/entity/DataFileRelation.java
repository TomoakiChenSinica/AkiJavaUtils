/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datafilesystem.core.entity;

/**
 *
 * @author tomoaki
 * @param <DATA>
 */
public interface DataFileRelation<DATA> {

    public String getFileDisplayName();

    public String getFileRealName();
}
