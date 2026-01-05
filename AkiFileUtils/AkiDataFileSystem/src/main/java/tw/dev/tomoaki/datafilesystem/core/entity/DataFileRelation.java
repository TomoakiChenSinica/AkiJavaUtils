/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datafilesystem.core.entity;

/**
 *
 * @author tomoaki
 * @param <DATA> 此檔案「屬於」的資料/Table
 * 
 * 檔案所關聯的「資料/Table」需需作此 interfaces，存有該檔案實際的檔名、即使用者看到的名字等資訊。<br>
 * <DATA> 則是該 Table 所屬於「該資料」。<br>
 * DataFileRelation 等於用於建立 DATA 與檔案的一對多關係
 * 
 */
public interface DataFileRelation<DATA> extends DataFile {

}
