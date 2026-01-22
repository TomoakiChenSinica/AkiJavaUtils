/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datafilesystem.core.entity;

/**
 * Represents a file that is associated with a specific data entity.
 * <p>
 * This interface should be implemented by entities (e.g., database table records)
 * that need to maintain a relationship with files. It stores the file name in the
 * file system ({@link #getFileRealName}) and the user-facing display name ({@link #getFileDisplayName}).
 * </p>
 * <p>
 * The generic type {@code <DATA>} represents the parent data entity that owns this file.
 * This interface establishes a one-to-many relationship between {@code DATA} and its related files.
 * </p>
 * @author tomoaki
 * @param <DATA> the type of the parent data entity that this file belongs to
 * 
 */
public interface DataFileRelation<DATA> extends DataFile {

}
